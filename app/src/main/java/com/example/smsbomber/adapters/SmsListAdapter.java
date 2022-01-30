package com.example.smsbomber.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.smsbomber.models.Sms;
import com.example.smsbomber.models.SmsListModel;
import com.example.smsbomber.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class SmsListAdapter extends RecyclerView.Adapter<SmsListAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView number;
        private final TextView message;
        private Sms sms;

        public ViewHolder(View view, SmsListModel model) {
            super(view);
            // Define click listener for the ViewHolder's View

            number = (TextView) view.findViewById(R.id.smsnumber);
            message = (TextView) view.findViewById(R.id.smsnumber);
            this.sms = null;
        }

        public TextView getNumberTextView() {
            return number;
        }

        public TextView getMessageTextView() {
            return message;
        }

        public void setSms(Sms sms) {
            this.sms = sms;
        }
    }

    private final List<Sms> smsList;

    // Pass in the contact array into the constructor
    public SmsListAdapter(List<Sms> taskList) {
        this.smsList = taskList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.sms_item, viewGroup, false);

        return new ViewHolder(view, new SmsListModel().setAdapter(this));
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getNumberTextView().setText(smsList.get(position).getNumber());
        viewHolder.getMessageTextView().setText(smsList.get(position).getMessage());
        viewHolder.setSms(smsList.get(position));
    }

    @Override
    public int getItemCount() {
        return smsList.size();
    }
}
