package com.example.magnumintern;

import android.content.Context;
import android.icu.text.UnicodeSetSpanner;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class adapterr extends RecyclerView.Adapter<adapterr.viewholder> {

    ArrayList<message_format> messages;
    Context context;

    public adapterr(ArrayList<message_format> messages, Context context) {
        this.messages = messages;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {

        if(messages.get(position).msg_of==1)
            return  R.layout.receivers_message;
        else if(messages.get(position).msg_of==2)
            return  R.layout.middle_msg;
        else if(messages.get(position).msg_of==3)
            return  R.layout.senders_message;
        else if(messages.get(position).msg_of==4)
            return  R.layout.senders_imgmsg;
        else return  R.layout.receiver_imgmsg;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
            view = LayoutInflater.from(viewGroup.getContext()).inflate(i,viewGroup,false);
            return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder viewholder, int i) {
        if(messages.get(i).getMsg_of()==1 || messages.get(i).getMsg_of()==2 || messages.get(i).getMsg_of()==3 )
            viewholder.txtmsg.setText(messages.get(i).getMessage());
        else {
            int imageresource = viewholder.itemView.getResources().getIdentifier("@drawable/"+messages.get(i).getMessage(), "drawable","com.example.magnumintern");
            viewholder.image.setImageResource(imageresource);
        }

    }


    @Override
    public int getItemCount() {
        return messages.size();
    }

    class viewholder extends RecyclerView.ViewHolder{
        TextView txtmsg;
        ImageView image;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            txtmsg = itemView.findViewById(R.id.textMessage);
            image = itemView.findViewById(R.id.image);

        }
    }
}
