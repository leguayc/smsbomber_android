package com.example.smsbomber.models;

import com.example.smsbomber.adapters.ContactListAdapter;
import com.example.smsbomber.adapters.SmsListAdapter;

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

    public void removeContact(Sms sms) {
        int index = contactList.indexOf(sms);
        contactList.remove(sms);

        if (adapter != null)
            adapter.notifyItemRemoved(index);
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public ContactListModel setAdapter(ContactListAdapter adapter)
    {
        this.adapter = adapter;

        return this;
    }
}
