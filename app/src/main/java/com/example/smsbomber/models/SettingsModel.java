package com.example.smsbomber.models;

import com.example.smsbomber.adapters.AutoMessagesAdapter;
import com.example.smsbomber.adapters.ContactListAdapter;

import java.util.ArrayList;
import java.util.List;

public class SettingsModel {
    private final static List<String> messages = new ArrayList<String>();
    private static AutoMessagesAdapter adapter;

    public void addMessage(String message) {
        messages.add(message);

        if (adapter != null)
            adapter.notifyItemInserted(messages.size()-1);
    }

    public boolean canSendAutomaticMessage() {
        return this.messages.size() > 0;
    }

    public String getRandomMessage() {
        int random = (int) (Math.random() * (messages.size()-1));
        return this.messages.get(random);
    }

    public void removeMessage(String message) {
        int index = messages.indexOf(message);
        messages.remove(message);

        if (adapter != null)
            adapter.notifyItemRemoved(index);
    }

    public List<String> getMessages() {
        return messages;
    }

    public SettingsModel setAutoMessagesAdapter(AutoMessagesAdapter adapter)
    {
        this.adapter = adapter;

        return this;
    }


}
