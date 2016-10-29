package app.com.prolific.android.prolific.models;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by ShowMe on 10/28/16.
 */

public interface RetrofitCallsToProlificLibrary {

    String BASE_URL = "http://prolific-interview.herokuapp.com/";

    @GET("{userID}/{path}/")
    Call<List<Book>> getBooks(@Path("userID") String id, @Path("path") String path);

    @GET("{userID}/{path}/{idNumber}/")
    Call<Book> getBookDetails(@Path("userID") String id, @Path("path") String path,
                              @Path("idNumber") int idNumber);

    @PUT("{userID}/{path}/{idNumber}/")
    Call<Book> checkoutBook(@Path("userID") String id, @Path("path") String path,
                            @Path("idNumber") int idNumber, @Body Book book);

    @POST("{userID}/{path}/")
    Call<Book> addBook(@Path("userID") String id, @Path("path") String path, @Body Book book);

    @DELETE("{userID}/{path}/")
    Call<Book> deleteAll(@Path("userID") String id, @Path("path") String path);

    @DELETE("{userID}/{path}/{idNumber}/")
    Call<Book> deleteSelectedBook(@Path("userID") String id, @Path("path") String path,
                            @Path("idNumber") int idNumber);

    class Factory {
        private static RetrofitCallsToProlificLibrary service;

        public static RetrofitCallsToProlificLibrary getInstance() {
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder().
                        addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build();
                service = retrofit.create(RetrofitCallsToProlificLibrary.class);
                return service;
            } else {
                return service;
            }
        }
    }
}
