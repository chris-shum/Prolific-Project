package app.com.prolific.android.prolific.presenters;

import android.content.Context;
import android.content.res.Resources;

import app.com.prolific.android.prolific.R;
import app.com.prolific.android.prolific.models.Book;
import app.com.prolific.android.prolific.models.RetrofitCallsToProlificLibrary;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ShowMe on 10/28/16.
 */

public class PresentProlificLibrary {

    public static void getProlificLibary(final Context context) {
        Resources resources = context.getResources();
        RetrofitCallsToProlificLibrary.Factory.getInstance()
                .getBooks(resources.getString(R.string.user_number),
                        resources.getString(R.string.books_path))
                .enqueue(new Callback<Book>() {
                    @Override
                    public void onResponse(Call<Book> call, Response<Book> response) {
//                        add data to display via recyclerview
                    }

                    @Override
                    public void onFailure(Call<Book> call, Throwable t) {
//                        toast of some sort
                    }
                });
    }
}

