package com.example.extendedassignment;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/* A Table with name @contacts_info
 * it implements Parcelable interface too, so we can send custom objects too
 * from one fragment to another
 * it has three field @name, @emailId, @phoneNo
 */
@Entity(tableName="contacts_info")
public class Contact implements Parcelable {

    private String name;
    private String email;
    private String phoneNo;
    @PrimaryKey(autoGenerate=true)
    int id=0;

    protected Contact(Parcel in) {
        name = in.readString();
        email = in.readString();
        phoneNo = in.readString();
        id = in.readInt();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public int getId() {
        return id;
    }

    public Contact(String name, String email, String phoneNo) {
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(phoneNo);
        parcel.writeInt(id);
    }

    public int setId(int id) {
        return this.id;
    }
}
