package com.example.smsbomber.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smsbomber.R;
import com.example.smsbomber.adapters.AutoMessagesAdapter;
import com.example.smsbomber.models.SettingsModel;

public class SettingsFragment extends Fragment {
    private EditText message;
    private final SettingsModel viewModel;
    private RecyclerView messagesList;
    private Button addButton;

    public SettingsFragment() {
        // Required empty public constructor

        viewModel = new SettingsModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings_fragment, container, false);

        message = (EditText) view.findViewById(R.id.autoMsg);
        addButton = (Button) view.findViewById(R.id.btnAddAutoMsg);

        addButton.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {
                viewModel.addMessage(message.getText().toString());
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.messagesList = (RecyclerView) view.findViewById(R.id.settingsMessages);

        AutoMessagesAdapter adapter = new AutoMessagesAdapter(viewModel.getMessages());
        viewModel.setAutoMessagesAdapter(adapter);
        this.messagesList.setAdapter(adapter);
        this.messagesList.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}