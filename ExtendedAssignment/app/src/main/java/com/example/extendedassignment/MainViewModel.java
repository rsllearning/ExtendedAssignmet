package com.example.extendedassignment;

import android.app.Application;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private ContactRepo contactRepo;
    public LiveData<List<Contact>> contactList;

    public MainViewModel(@NonNull Application  application) {
        super(application);
        contactRepo=new ContactRepo(application);
        contactList=contactRepo.getAllData();
    }
    public void insert(Contact contact){
        contactRepo.insertData(contact);
    }
    public void delete(Contact contact){
        contactRepo.deleteData(contact);
    }
    public void update(Contact contact){
        contactRepo.updateData(contact);
    }
    public  void deteleAll(){contactRepo.deleteAllData();}
    public LiveData<List<Contact>> getAllData(){
        return contactList;

    }
}
