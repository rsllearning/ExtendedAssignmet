package com.example.extendedassignment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class InsertViewModel extends AndroidViewModel {

    private ContactRepo contactRepo;
    public LiveData<List<Contact>> contactList;
    public InsertViewModel(@NonNull Application application) {
        super(application);
        contactRepo=new ContactRepo(application);
        contactList=contactRepo.getAllData();
    }
    public void insert(Contact contact){
        contactRepo.insertData(contact);
    }
    public void update(Contact contact){
        contactRepo.updateData(contact);
    }
    public LiveData<List<Contact>> getAllData(){
        return contactList;

    }
}
