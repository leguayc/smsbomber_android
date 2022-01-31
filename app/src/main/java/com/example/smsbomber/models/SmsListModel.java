package com.example.smsbomber.models;

import com.example.smsbomber.adapters.SmsListAdapter;

import java.util.ArrayList;
import java.util.List;

public class SmsListModel {
    private static final List<Sms> smsList = new ArrayList<Sms>();
    private static SmsListAdapter adapter;

    public SmsListModel() {
        super();
    }

    public void addSms(Sms sms) {
        smsList.add(sms);

        if (adapter != null)
            adapter.notifyItemInserted(smsList.size()-1);
    }

    public void removeSms(Sms sms) {
        int index = smsList.indexOf(sms);
        smsList.remove(sms);

        if (adapter != null)
            adapter.notifyItemRemoved(index);
    }

    public List<Sms> getSmsList() {
        return smsList;
    }

    public SmsListModel setAdapter(SmsListAdapter adapter)
    {
        this.adapter = adapter;

        return this;
    }
}
