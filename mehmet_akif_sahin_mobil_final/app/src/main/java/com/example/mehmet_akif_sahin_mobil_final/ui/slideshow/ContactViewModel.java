package com.example.mehmet_akif_sahin_mobil_final.ui.slideshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ContactViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ContactViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Herhangi bir kişi kayıtlı değil");
    }

    public LiveData<String> getText() {
        return mText;
    }
}