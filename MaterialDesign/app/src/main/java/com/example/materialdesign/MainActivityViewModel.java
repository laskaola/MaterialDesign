package com.example.materialdesign;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import java.util.List;
import java.util.Map;

public class MainActivityViewModel extends ViewModel implements ViewModelStoreOwner {

    private MutableLiveData<List<Map<String, String>>> repositoryViewModel;
    private Repository repository;

    public void init() {
        if(repositoryViewModel != null) {
            return;
        }
           repository = new Repository();
           repositoryViewModel = repository.getRepositoryData();
    }

    public void reloadData() {
    //    repositoryViewModel = repository.resetData();
    }

    public LiveData<List<Map<String, String>>> getRepositoryViewModel() {
        return repositoryViewModel;
    }


    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return null;
    }
}
