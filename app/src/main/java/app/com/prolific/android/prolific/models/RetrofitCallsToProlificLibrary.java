package app.com.prolific.android.prolific.models;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ShowMe on 10/28/16.
 */

public interface RetrofitCallsToProlificLibrary {

    String BASE_URL = "https://http://prolific-interview.herokuapp.com/";

    @GET("{userID}/{path1}")
    Call<Book> getBooks(@Path("userID") String id, @Path("path1") String hmm);


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
