package com.example.extendedassignment;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;



/* A class annotated with @Dao, means it will interact with dataBase
*  having CRUD functions to perform on dataBase
*/
@Dao
public interface ContactDao {
    @Insert
    public  void insert(Contact contact);
    @Update
    public  void update(Contact contact);
    @Delete
    public  void delete(Contact contact);

    @Query("DELETE FROM contacts_info")
    public  void deleteAll();

    @Query("SELECT * FROM contacts_info ORDER BY name ASC")
    public LiveData<List<Contact>> getAllData();

}
