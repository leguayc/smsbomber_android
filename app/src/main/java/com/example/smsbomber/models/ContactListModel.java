package com.example.smsbomber.models;

import com.example.smsbomber.adapters.ContactListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ContactListModel {
    private static final List<Contact> contactList = new ArrayList<Contact>();
    private static ContactListAdapter adapter;

    public ContactListModel() {
        super();
    }

    public void addContact(Contact contact) {
        contactList.add(contact);

        if (adapter != null)
            adapter.notifyItemInserted(contactList.size()-1);
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void countMessageOfNumber(String number) {
        for(int i = 0; i < contactList.size(); i++) {
            if (number.equals(contactList.get(i).getNumber())) {
                contactList.get(i).addMessageToCount();

                if (adapter != null)
                    adapter.notifyItemChanged(i);

                break;
            }
        }
    }

    public ContactListModel setAdapter(ContactListAdapter adapter)
    {
        this.adapter = adapter;

        return this;
    }
}
