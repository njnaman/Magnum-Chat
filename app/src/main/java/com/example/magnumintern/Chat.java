package com.example.magnumintern;


import android.content.Context;
import android.icu.text.UnicodeSetSpanner;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import com.example.magnumintern.R;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Chat extends Fragment {
    ArrayList<message_format> messages;
    int count=0;
    ArrayList<message_format> messagesforadapterr;
    RecyclerView recyclerView;
    adapterr adapter;
    public Chat() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        messages = new ArrayList<>();
        messagesforadapterr = new ArrayList<>();
        adapter = new adapterr(messagesforadapterr, getActivity());
        recyclerView = view.findViewById(R.id.recycler_view);
        getMessages();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);


        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:{
                            if(!isNetworkAvailable()){
                                Fragment noconnection = new no_connectivity();
                                FragmentManager fragmentManager = getFragmentManager();
                                fragmentManager.beginTransaction().replace(R.id.frame,noconnection).commit();
                            } else {
                                if (count < messages.size()) {
                                    messagesforadapterr.add(messages.get(count));
                                    count++;
                                    adapter.notifyDataSetChanged();
                                } else
                                    Toast.makeText(getActivity(), "NO MORE MESSAGES", Toast.LENGTH_SHORT).show();
                                if (!isLastVisible())
                                    recyclerView.smoothScrollToPosition(messagesforadapterr.size()-1);

                            }
                        break;}
                }
                return false;
            }


        });



        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isNetworkAvailable()){
                    Fragment noconnection = new no_connectivity();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame,noconnection).commit();
                } else {
                    if(count<messages.size()){
                    messagesforadapterr.add(messages.get(count));
                    count++;
                    adapter.notifyDataSetChanged();
                    }
                    else Toast.makeText(getActivity(),"NO MORE MESSAGES",Toast.LENGTH_SHORT).show();
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

    private void getMessages(){
        messages.add(new message_format("Hey its Piyush! We met at orientation?", 1));
        messages.add(new message_format("Conversation", 2));
        messages.add(new message_format("Oh yeo, I remember", 3));
        messages.add(new message_format("Hi", 3));
        messages.add(new message_format("Conversation Continuess", 2));
        messages.add(new message_format("Hi so are you coming to college tomorrow?", 1));
        messages.add(new message_format("Yea, I'm planning on being kinda regular", 3));
        messages.add(new message_format("Thats cool i like being regular too", 1));
        messages.add(new message_format("You want to sit together tomorrow ?", 3));
        messages.add(new message_format("Of course . I'm new to this city", 1));
        messages.add(new message_format("me too! HaHa", 3));
        messages.add(new message_format("See you in the morning", 3));
        messages.add(new message_format("you can bet on it", 1));
        messages.add(new message_format("By the way do you know Shaw", 1))
        messages.add(new message_format("shaw", 4));
        messages.add(new message_format("yeo , he is my favourite", 1));
        messages.add(new message_format("but i like axle more", 3));
        messages.add(new message_format("axle", 5));
        messages.add(new message_format("ohh! lets meet tomorrow bbie", 1));
        messages.add(new message_format("bbiee", 3));

        messages.add(new message_format("Omg you totally blanked on him", 1));
        messages.add(new message_format("i did not", 3));
        messages.add(new message_format("I was such in a thinking space", 3));
        messages.add(new message_format("Hahaha zaroor zaroor", 1));
        messages.add(new message_format("Shshshsh lol", 3));
        messages.add(new message_format("I am waiting outside the class", 3));
        messages.add(new message_format("Jldi aa vrna dono ko andar aane ko nhi milega", 3));
        messages.add(new message_format("Bss gate pr hoo running towards you", 1));
        messages.add(new message_format("Why are you always so late?", 3));
        messages.add(new message_format("I like thinking about you in the morning", 1));
        messages.add(new message_format("Keeps me distracted", 1));
        messages.add(new message_format("Haha very funny", 3));
        messages.add(new message_format("Now run!", 3));
        messages.add(new message_format("I am ", 1));
        messages.add(new message_format("okayy", 1));
        messages.add(new message_format("okieee", 3));
        messages.add(new message_format("I am missing you", 3));
        messages.add(new message_format("I am too", 1));
        messages.add(new message_format("summer vacation sucks", 3));
        messages.add(new message_format("Hamare ghr diff. cities me kyu h", 3));
        messages.add(new message_format("I know right!!!!!!!", 1));

        messages.add(new message_format("Just two more weeks ", 1));
        messages.add(new message_format("Then we can go out and have fun", 1));
        messages.add(new message_format("I finally get to meet you", 3));
        messages.add(new message_format("omg", 3));
        messages.add(new message_format("father is calling me", 1));
        messages.add(new message_format("gotta go", 1));
        messages.add(new message_format("biee", 3));
        messages.add(new message_format("bbiee", 1));
    }

    boolean isLastVisible() {
        LinearLayoutManager layoutManager = ((LinearLayoutManager) recyclerView.getLayoutManager());
        int pos = layoutManager.findLastCompletelyVisibleItemPosition();
        int numItems = recyclerView.getAdapter().getItemCount();
        return (pos >= numItems);
    }
}
