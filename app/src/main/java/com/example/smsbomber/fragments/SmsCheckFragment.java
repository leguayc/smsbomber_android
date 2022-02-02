package com.example.smsbomber.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smsbomber.MainActivity;
import com.example.smsbomber.R;

public class SmsCheckFragment extends Fragment {
    private EditText phone;
    private EditText message;
    private EditText numberMsg;
    private Button sendButton;

    public SmsCheckFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_smscheck_fragment , container , false);

        phone = (EditText) view.findViewById(R.id.txtPhone);
        message = (EditText) view.findViewById(R.id.txtMessage);
        sendButton = (Button) view.findViewById(R.id.btnEnvoi);
        numberMsg = (EditText) view.findViewById(R.id.nombreMgs);

        sendButton.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v){
                if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                    sendSms();
                } else {
                    ActivityCompat.requestPermissions(getActivity(),new String[] { Manifest.permission.SEND_SMS },MainActivity.REQUEST_SEND_SMS);
                }
            }

            private void sendSms()
            {
                String numberMessageString = numberMsg.getText().toString();
                if (!numberMessageString.isEmpty() && !phone.getText().toString().isEmpty()) {
                    int numberMsgInt = Integer.parseInt(numberMessageString);

                    Toast.makeText(getContext(), "Started sending messages !", Toast.LENGTH_SHORT).show();

                    for(int i = 0; i < numberMsgInt; i++ ){
                        SmsManager.getDefault().sendTextMessage(phone.getText().toString(), null, message.getText().toString(), null, null);
                    }

                    Toast.makeText(getContext(), "Finished sending messages !", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Invalid inputs", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }
}
