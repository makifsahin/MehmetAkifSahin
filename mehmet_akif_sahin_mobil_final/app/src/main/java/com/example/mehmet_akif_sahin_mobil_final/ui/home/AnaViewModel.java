package com.example.mehmet_akif_sahin_mobil_final.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AnaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AnaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Profil");
    }

    public LiveData<String> getText() {
        return mText;
    }
}