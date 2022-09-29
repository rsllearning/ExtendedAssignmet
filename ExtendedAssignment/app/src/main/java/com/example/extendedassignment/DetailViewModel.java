package com.example.extendedassignment;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class DetailViewModel extends AndroidViewModel {
    private ContactRepo contactRepo;
    public LiveData<List<Contact>> contactList;
    public DetailViewModel(@NonNull Application application) {
        super(application);
        contactRepo=new ContactRepo(application);
        contactList=contactRepo.getAllData();
    }
    public void update(Contact contact){
        contactRepo.updateData(contact);
    }
    public void delete(Contact contact){
        contactRepo.deleteData(contact);;
    }
    public  void deleteAll(){contactRepo.deleteAllData();}
    public LiveData<List<Contact>> getAllData() {
        return contactList;
    }

}
