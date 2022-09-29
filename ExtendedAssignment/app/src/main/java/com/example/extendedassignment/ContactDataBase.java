package com.example.extendedassignment;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/* DataBase class, it is abstract means, there will be only one instance of dataBase throughout tha application
*  contact_database having only one table whose entities are @Contact class type
*/
@Database(entities = Contact.class,version = 1)
public abstract class ContactDataBase extends RoomDatabase {
    public static  ContactDataBase instance;
    public  abstract ContactDao contactDao();
    public static  synchronized ContactDataBase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    ContactDataBase.class,"contact_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
