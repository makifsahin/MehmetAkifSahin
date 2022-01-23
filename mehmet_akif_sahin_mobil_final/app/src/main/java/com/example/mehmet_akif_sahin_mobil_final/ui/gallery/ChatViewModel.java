package com.example.mehmet_akif_sahin_mobil_final.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ChatViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ChatViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Herhangi bir konuşmanız yok");
    }

    public LiveData<String> getText() {
        return mText;
    }
}