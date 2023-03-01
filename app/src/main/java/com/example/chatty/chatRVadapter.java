package com.example.chatty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class chatRVadapter extends RecyclerView.Adapter {

    private ArrayList<chats_modal> chats_modal_array;
    private Context context;

    public chatRVadapter(ArrayList<chats_modal> chats_modal_array, Context context) {
        this.chats_modal_array = chats_modal_array;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        switch (viewType){
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_msg_rv_item,parent,false);
                return new userViewHolder(view);

            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bot_msg_rv_item,parent,false);
                return new botViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        chats_modal chatsModal = chats_modal_array.get(position);

        switch (chatsModal.getSender()){
            case "user":
                ((userViewHolder)holder).userTV.setText(chatsModal.getMessage());
                break;

            case "bot":
                ((botViewHolder)holder).botMsgTV.setText(chatsModal.getMessage());
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (chats_modal_array.get(position).getSender()){
            case "user":
                return 0;
            case "bot":
                return 1;
            default:
                return -1;
        }
    }

    @Override
    public int getItemCount() {
        return chats_modal_array.size();
    }

    public static class userViewHolder extends RecyclerView.ViewHolder{

        TextView userTV;

        public userViewHolder(@NonNull View itemView) {
            super(itemView);

            userTV = itemView.findViewById(R.id.TVuser);
        }
    }

    public static class botViewHolder extends RecyclerView.ViewHolder{

        TextView botMsgTV;

        public botViewHolder(@NonNull View itemView) {
            super(itemView);

            botMsgTV = itemView.findViewById(R.id.TVbot);
        }
    }
}
