package com.example.smsbomber.fragments;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smsbomber.R;
import com.example.smsbomber.adapters.ContactListAdapter;
import com.example.smsbomber.models.Contact;
import com.example.smsbomber.models.ContactListModel;

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
        System.out.println(adapter);
        viewModel.setAdapter(adapter);
        this.contactList.setAdapter(adapter);
        this.contactList.setLayoutManager(new LinearLayoutManager(getContext()));

        this.recupContacts();
    }

    public void recupContacts() {
        ContentResolver contentResolver = getContext().getContentResolver();

        // Récup des contacts dans un curseur
        Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_ALTERNATIVE,
                        ContactsContract.CommonDataKinds.Phone.NUMBER}, null, null, null);

        if (cursor == null) {
            Log.d("recuperation", "*************** erreur cursor ********************");
        } else {
            // Parcours des contacts
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_ALTERNATIVE));
                @SuppressLint("Range") String phone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                // Affiche la recup
                // ajouter une list adapter
                viewModel.addContact(new Contact(phone, name));
            }
            // fermer le curseur
            cursor.close();
        }
    }
}