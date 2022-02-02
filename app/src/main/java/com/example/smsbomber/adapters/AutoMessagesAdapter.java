package com.example.smsbomber.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.smsbomber.R;
import com.example.smsbomber.models.Contact;
import com.example.smsbomber.models.ContactListModel;
import com.example.smsbomber.models.SettingsModel;

import java.util.List;

public class AutoMessagesAdapter extends RecyclerView.Adapter<AutoMessagesAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView message;
        private final Button deleteBtn;
        private final SettingsModel viewModel;

        public ViewHolder(View view, SettingsModel model) {
            super(view);
            // Define click listener for the ViewHolder's View

            message = (TextView) view.findViewById(R.id.autoMessage);
            deleteBtn = (Button) view.findViewById(R.id.deleteAutoMessage);

            viewModel = new SettingsModel();

            deleteBtn.setOnClickListener(new Button.OnClickListener() {

                public void onClick(View v) {
                    viewModel.removeMessage(message.getText().toString());
                }
            });
        }

        public void setMessage(String message) {
            this.message.setText(message);
        }
    }

    private final List<String> messages;

    // Pass in the contact array into the constructor
    public AutoMessagesAdapter(List<String> messages) {
        this.messages = messages;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AutoMessagesAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.automessage_item, viewGroup, false);

        return new AutoMessagesAdapter.ViewHolder(view, new SettingsModel().setAutoMessagesAdapter(this));
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(AutoMessagesAdapter.ViewHolder viewHolder, final int position) {
        viewHolder.setMessage(messages.get(position));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
