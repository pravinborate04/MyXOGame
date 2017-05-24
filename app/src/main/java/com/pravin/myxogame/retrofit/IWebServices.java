package com.pravin.myxogame.retrofit;
import com.pravin.myxogame.SendFcmModel;
import com.pravin.myxogame.SendFcmResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Pravin Borate on 4/4/17.
 */

public interface IWebServices {

    @Headers("Authorization: key=AIzaSyA4sY7ny4-LeLMjAsnmY6vsOCin-23dOcU")
    @POST("send")
    Call<SendFcmResponseModel> sendMassage(@Body SendFcmModel model);


}
