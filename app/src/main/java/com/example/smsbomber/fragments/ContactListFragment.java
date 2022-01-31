package com.example.smsbomber.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smsbomber.R;
import com.example.smsbomber.adapters.ContactListAdapter;
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
        viewModel.setAdapter(adapter);
        this.contactList.setAdapter(adapter);
        this.contactList.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}