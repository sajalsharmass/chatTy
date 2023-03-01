package com.example.chatty;

import static java.lang.System.out;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainPage extends AppCompatActivity {

    private RecyclerView chatsRV;
    private EditText userMsgEdt;
    private FloatingActionButton sendMsgFAB;
    private final String bot_key = "bot";
    private final String user_key = "user";
    private ArrayList<chats_modal> chats_modal_array;
    private chatRVadapter chat_rv_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chatsRV = findViewById(R.id.RVchats);
        userMsgEdt = findViewById(R.id.edt_message);
        sendMsgFAB = findViewById(R.id.FABsend);

        chats_modal_array = new ArrayList<>();
        chat_rv_adapter = new chatRVadapter(chats_modal_array,this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        chatsRV.setLayoutManager(manager);
        chatsRV.setAdapter(chat_rv_adapter);

        sendMsgFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userMsgEdt.getText().toString().isEmpty()){
                    Toast.makeText(MainPage.this,"Please enter your message",Toast.LENGTH_SHORT).show();
                    return;
                }
                getResponse(userMsgEdt.getText().toString());
                userMsgEdt.setText("");
            }
        });
    }
    private void getResponse(String message){

        chats_modal_array.add(new chats_modal(message,user_key));
        chat_rv_adapter.notifyDataSetChanged();

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        String url = "http://api.brainshop.ai/get?bid=173057&key=E0kXXfje4T75aemu&uid="+uid+"&msg="+message;
        System.out.println("url:"+url);

        String base_url = "http://api.brainshop.ai/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitAPI retrofit_API = retrofit.create(retrofitAPI.class);

        Call<msg_modal> call = retrofit_API.getMessage(url);
        System.out.println("call msg" + call.toString());


        call.enqueue(new Callback<msg_modal>() {
            @Override
            public void onResponse(Call<msg_modal> call, Response<msg_modal> response) {
            //    System.out.println("getting response");

                if(response.isSuccessful()){
                    msg_modal modal = response.body();
                    chats_modal_array.add(new chats_modal(modal.getCnt(),bot_key));
                    chat_rv_adapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onFailure(Call<msg_modal> call, Throwable t) {

                System.out.println("no response");

                chats_modal_array.add(new chats_modal("Please revert your question",bot_key));
                chat_rv_adapter.notifyDataSetChanged();
            }
        });
    }
}
