package com.example.smsbomber.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smsbomber.MainActivity;
import com.example.smsbomber.R;
import com.example.smsbomber.adapters.ContactListAdapter;
import com.example.smsbomber.models.Contact;
import com.example.smsbomber.models.ContactListModel;

import java.util.ArrayList;

public class ContactListFragment extends Fragment {
    private ContactListModel viewModel;
    private RecyclerView contactList;

    public ContactListFragment() {
        // Required empty public constructor
        viewModel = new ContactListModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts_fragment , container , false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.contactList = (RecyclerView) view.findViewById(R.id.contactList);

        ContactListAdapter adapter = new ContactListAdapter(viewModel.getContactList());
        viewModel.setAdapter(adapter);
        this.contactList.setAdapter(adapter);
        this.contactList.setLayoutManager(new LinearLayoutManager(getContext()));

        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            this.readContacts();

            if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED) {
                this.readSms();
            } else {
                ActivityCompat.requestPermissions(getActivity(),new String[] { Manifest.permission.READ_SMS },MainActivity.REQUEST_READ_SMS);
            }

        } else {
            ActivityCompat.requestPermissions(getActivity(),new String[] { Manifest.permission.READ_CONTACTS },MainActivity.REQUEST_READ_CONTACTS);
        }
    }

    public void readContacts() {
        ContentResolver contentResolver = getContext().getContentResolver();

        Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_ALTERNATIVE,
                        ContactsContract.CommonDataKinds.Phone.NUMBER}, null, null, null);

        if (cursor == null) {
            Log.d("recuperation", "*************** erreur cursor ********************");
        } else {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_ALTERNATIVE));
                @SuppressLint("Range") String phone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                viewModel.addContact(new Contact(phone, name));
            }
            cursor.close();
        }
    }

    public void readSms() {
        Cursor cursor = getActivity().getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);

        ArrayList<String> numbers = new ArrayList<String>();

        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                String number = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                numbers.add(number);
                //viewModel.countMessageOfNumber(number);
            } while (cursor.moveToNext());
        } else {
            // empty box, no SMS
        }

        cursor.close();

        for(int i = 0; i < numbers.size(); i++) {
            viewModel.countMessageOfNumber(numbers.get(i));
        }
    }
}