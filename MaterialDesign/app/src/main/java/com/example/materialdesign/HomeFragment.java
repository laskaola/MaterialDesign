package com.example.materialdesign;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private static View itemView;
    public static List<Map<String, String>> dataPackage;
    private static RecyclerView recyclerView;
    private MaterialCardView card;
    private MaterialTextView title;
    private static HomeFragment fragment;

    public HomeFragment() {

    }


    public static HomeFragment newInstance(List<Map<String, String>> data) {
        fragment = new HomeFragment();
        dataPackage = data;


        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        itemView = inflater.inflate(R.layout.home_fragment, container, false);


        recyclerView = itemView.findViewById(R.id.recyclerView);
        card = itemView.findViewById(R.id.card);
        title = itemView.findViewById(R.id.title);
        
        final RecyclerViewAdapter adapter = new RecyclerViewAdapter(this.getActivity(), this.getContext(), dataPackage);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        recyclerView.setAdapter(adapter);

        fragment.getExitTransition();

        return itemView;
    }

    public static void beginTransaction() {
        fragment.getFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .addSharedElement(itemView.findViewById(R.id.home), "shared_element")
                .replace(R.id.home, ArubaFragment.newInstance(), fragment.getTag())
                .addToBackStack(fragment.getTag())
                .commit();
    }

}
