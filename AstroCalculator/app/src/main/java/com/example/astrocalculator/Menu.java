package com.example.astrocalculator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Menu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Menu extends Fragment {

    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";

    TextView latitude;
    TextView longitude;

    EditText latitudeEdit;
    EditText longitudeEdit;

    MaterialButton set;


    private static Menu fragment;
    private static Bundle args;

    public Menu() {
        // Required empty public constructor
    }



    public static Menu newInstance() {

        if(fragment == null)
            fragment = new Menu();
        args = new Bundle();

        args.putString(LATITUDE, "Set latitude:");
        args.putString(LONGITUDE, "Set longitude:");

        fragment.setArguments(args);
        return fragment;
    }

    public static Menu getInstance() {
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView = inflater.inflate(R.layout.menu, container, false);

        latitude = itemView.findViewById(R.id.latitude);
        longitude = itemView.findViewById(R.id.longitude);

        latitudeEdit = itemView.findViewById(R.id.latitude_edit);
        longitudeEdit = itemView.findViewById(R.id.longitude_edit);

        set = itemView.findViewById(R.id.set);

        Spinner spinner = itemView.findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ((MainActivity)getActivity()).resetCounter();

                switch(position) {
                    case 0:
                        ((MainActivity)getActivity()).setUpdatePeriod(1);
                        break;
                    case 1:
                        ((MainActivity)getActivity()).setUpdatePeriod(5);
                        break;
                    case 2:
                        ((MainActivity)getActivity()).setUpdatePeriod(15);
                        break;
                    case 3:
                        ((MainActivity)getActivity()).setUpdatePeriod(30);
                        break;
                    case 4:
                        ((MainActivity)getActivity()).setUpdatePeriod(60);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        latitude.setText(fragment.getArguments().getString(LATITUDE));
        longitude.setText(fragment.getArguments().getString(LONGITUDE));



        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalculatorData.getInstance().changeLocation(LONGITUDE, Double.parseDouble(longitudeEdit.getText().toString()));
                CalculatorData.getInstance().changeLocation(LATITUDE, Double.parseDouble(latitudeEdit.getText().toString()));
                ((MainActivity)getActivity()).reloadViewModel();
                ((MainActivity)getActivity()).updateLocation();
            }
        });

        return itemView;
    }

    @Override
    public void onResume() {
        super.onResume();

        latitude.setText(fragment.getArguments().getString(LATITUDE));
        longitude.setText(fragment.getArguments().getString(LONGITUDE));

    }

}
