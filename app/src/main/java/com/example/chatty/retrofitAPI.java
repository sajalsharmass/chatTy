package com.example.chatty;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface retrofitAPI {
    @GET
    Call<msg_modal> getMessage(@Url String url);
}
