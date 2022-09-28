package com.example.extendedassignment;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;


/*
* Repo class, this is not a part of Architectural Components, it is for interaction b/w viewModel and RoomDataBase
* we are creating and LiveData of type @List<Contact>
* there we instantiate objects for Dao class, dataBase class and  your LiveData of List<Contact>
*/
public class ContactRepo {

    ContactDataBase contactDataBase;
    private ContactDao contactDao;
    private ContactRepo contactRepo;
    private LiveData<List<Contact>> contactList;
    public ContactRepo(Application application){
        contactDataBase=ContactDataBase.getInstance(application);
        contactDao=contactDataBase.contactDao();
        contactList=contactDao.getAllData();
    }
    public void insertData(Contact contact){new insertTask(contactDao).execute(contact);};
    public void updateData(Contact contact){new updateTask(contactDao).execute(contact);};
    public void deleteData(Contact contact){new deleteTask(contactDao).execute(contact);};
    public void deleteAllData(){new deleteAllTask(contactDao).execute();};


    public LiveData<List<Contact>> getAllData(){
        return contactList;
    };

    public static class insertTask extends AsyncTask<Contact,Void,Void> {
        private  ContactDao contactDao;

        public insertTask(ContactDao contactDao) {
            this.contactDao = contactDao;
        }

        @Override
        protected Void doInBackground(Contact... notes) {
            contactDao.insert(notes[0]);
            return null;
        }
    }
    public static class updateTask extends AsyncTask<Contact,Void,Void> {
        private  ContactDao contactDao;

        public updateTask(ContactDao contactDao) {
            this.contactDao = contactDao;
        }

        @Override
        protected Void doInBackground(Contact... notes) {
            contactDao.update(notes[0]);
            return null;
        }
    }
    public static class deleteTask extends AsyncTask<Contact,Void,Void> {
        private  ContactDao contactDao;

        public deleteTask(ContactDao contactDao) {
            this.contactDao = contactDao;
        }

        @Override
        protected Void doInBackground(Contact... notes) {
            contactDao.delete(notes[0]);
            return null;
        }
    }
    public static class deleteAllTask extends AsyncTask<Contact,Void,Void> {
        private  ContactDao contactDao;

        public deleteAllTask(ContactDao contactDao) {
            this.contactDao = contactDao;
        }

        @Override
        protected Void doInBackground(Contact... notes) {
            contactDao.deleteAll();
            return null;
        }
    }

}
