package com.example.materialdesign;

import android.os.Bundle;

import androidx.core.app.SharedElementCallback;
import androidx.fragment.app.Fragment;
import androidx.transition.Transition;
import androidx.transition.TransitionInflater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.transition.MaterialContainerTransform;

import java.util.List;
import java.util.Map;

public class ArubaFragment extends Fragment {


    public ArubaFragment() {
        // Required empty public constructor
    }

    public static ArubaFragment newInstance() {
        ArubaFragment fragment = new ArubaFragment();
        Bundle args = new Bundle();

        fragment.setSharedElementEnterTransition(new MaterialContainerTransform());

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View itemView = inflater.inflate(R.layout.aruba_layout, container, false);
        ImageView img = itemView.findViewById(R.id.imgimg);
        return itemView;



    }


}
