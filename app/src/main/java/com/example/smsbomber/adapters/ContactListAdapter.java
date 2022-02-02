package com.example.smsbomber.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.smsbomber.R;
import com.example.smsbomber.models.Contact;
import com.example.smsbomber.models.ContactListModel;

import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView number;
        private final TextView name;
        private final TextView messageNumber;
        private Contact contact;

        public ViewHolder(View view, ContactListModel model) {
            super(view);
            // Define click listener for the ViewHolder's View

            number = (TextView) view.findViewById(R.id.contactnumber);
            name = (TextView) view.findViewById(R.id.contactname);
            messageNumber = (TextView) view.findViewById(R.id.contactmsgnumber);
            contact = null;
        }

        public void setContact(Contact contact) {
            this.contact = contact;
            this.number.setText(contact.getNumber());
            this.name.setText(contact.getName());
            this.messageNumber.setText(Integer.toString(contact.getNumberMsg()));
        }
    }

    private final List<Contact> contactList;

    // Pass in the contact array into the constructor
    public ContactListAdapter(List<Contact> contactList) {
        this.contactList = contactList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.contact_item, viewGroup, false);

        return new ViewHolder(view, new ContactListModel().setAdapter(this));
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ContactListAdapter.ViewHolder viewHolder, final int position) {
        viewHolder.setContact(contactList.get(position));
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
}
