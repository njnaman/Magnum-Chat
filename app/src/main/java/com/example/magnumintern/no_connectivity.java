package com.example.magnumintern;


import android.content.Context;
import android.icu.text.UnicodeSetSpanner;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class no_connectivity extends Fragment {

    private TextView noconnection;
    private Button retry;
    public no_connectivity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_no_connectivity, container, false);
        Toast.makeText(getActivity(),"NO Networl Available",Toast.LENGTH_SHORT).show();
        noconnection = view.findViewById(R.id.no_connection_label);
        retry = view.findViewById(R.id.retry);


        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isNetworkAvailable()){
                    noconnection.setText("NO CONNECTION");
                    Toast.makeText(getActivity(),"NO Connection", Toast.LENGTH_SHORT).show();
                }
                else {noconnection.setText("Conncection Available");
                    Toast.makeText(getActivity(),"Connection Available", Toast.LENGTH_SHORT).show();
                    Fragment chat = new Chat();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame,chat).commit();
                }
            }
        });
        return view;
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
