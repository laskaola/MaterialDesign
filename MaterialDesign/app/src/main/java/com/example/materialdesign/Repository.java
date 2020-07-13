package com.example.materialdesign;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Repository {



    private Map<String, String> arubaPackage;
    private Map<String, String> jamaicaPackage;
    private Map<String, String> bermudaPackage;
    private Map<String, String> bahamaPackage;
    private List<Map<String, String>> listOfPackages;

    public Repository() {

        arubaPackage = new HashMap<>();
        jamaicaPackage = new HashMap<>();
        bermudaPackage = new HashMap<>();
        bahamaPackage = new HashMap<>();
        listOfPackages = new LinkedList<>();

        arubaPackage.put("TITLE", "Aruba");
        arubaPackage.put("IMAGE", Integer.toString(R.drawable.aruba));

        jamaicaPackage.put("TITLE", "Jamaica");
        jamaicaPackage.put("IMAGE", Integer.toString(R.drawable.jamaica));

        bermudaPackage.put("TITLE", "Bermuda");
        bermudaPackage.put("IMAGE", Integer.toString(R.drawable.bermuda));

        bahamaPackage.put("TITLE", "Bahamas");
        bahamaPackage.put("IMAGE", Integer.toString(R.drawable.bahama));

        listOfPackages.add(arubaPackage);
        listOfPackages.add(jamaicaPackage);
        listOfPackages.add(bermudaPackage);
        listOfPackages.add(bahamaPackage);

    }

    public MutableLiveData<List<Map<String, String>>> getRepositoryData() {

        MutableLiveData<List<Map<String, String>>> data = new MutableLiveData<>();
        data.setValue(listOfPackages);
        return data;
    }


}
