package com.stanford.anglishwordbook.network;

import com.google.gson.JsonObject;
import com.stanford.anglishwordbook.network.requests.IRequest;
import com.stanford.anglishwordbook.network.responses.ResponseLogin;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by m.stanford on 3/3/15.
 * @since v.1
 * @version 1
 */
public interface NetworkWebservice {

    public static final String APP_ID               = "ApuxkukQC9mFuLIdIjG3qC27ms5kZ4XZbopxUohp";
    public static final String REST_API_KEY         = "N6r8fxsert4JrvaGMcavcTtaYPed6Vl9qNDE8mqb";

    public static final String PARSE_DOMAIN         = "https://api.parse.com";


    @POST("/1/classes/{className}")
    void parsePost(@Path("className") String className, @Body IRequest body, Callback<JsonObject> callback);

    @GET("/1/classes/{className}/{objectId}")
    void parseGet(@Path("className") String className, @Path("objectId") String objectId, Callback<JsonObject> callback);

    @PUT("/1/classes/{className}/{objectId}")
    void parsePut(@Path("className") String className, @Path("objectId") String objectId, Callback<JsonObject> callback);

    @GET("/1/classes/{className}")
    void parseQuery(@Path("className") String className, @Query("where") String queryObject, Callback<JsonObject> callback);

    @POST("/1/users")
    void parseUserSignUp(@Body IRequest body, Callback<JsonObject> callback);

    @GET("/1/login")
    void parseUserLogin(@Query("username") String userName, @Query("password") String password, Callback<ResponseLogin> callback);

    @GET("/1/login")
    ResponseLogin parseUserLogin(@Query("username") String userName, @Query("password") String password);

    @GET("/1/logout")
    void parseUserLogout(@Body IRequest body, Callback<JsonObject> callback);

    @GET("/1/login/{user}")
    void parseUserGet(@Path("user") String userId, Callback<JsonObject> callback);

    @GET("/1/login/me")
    void parseUserMe(@Query("token") String token, Callback<JsonObject> callback);

    @POST("/1/requestPasswordReset")
    void parseUserPassReset();
}
