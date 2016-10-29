package app.com.prolific.android.prolific.presenters;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

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

    public static void getProlificLibrary(final Context context) {
        Resources resources = context.getResources();
        RetrofitCallsToProlificLibrary.Factory.getInstance()
                .getBooks(resources.getString(R.string.user_number), resources.getString(R.string.books_path))
                .enqueue(new Callback<List<Book>>() {
                    @Override
                    public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                        PresentRealm.convertJSonToRealmBook(response.body(), (Activity) context);
                    }

                    @Override
                    public void onFailure(Call<List<Book>> call, Throwable t) {
                        // TODO: 10/29/16 toast the failure
                    }
                });
    }

    public static void getProlificBookDetails(final Context context, int ID){
        Resources resources = context.getResources();
        RetrofitCallsToProlificLibrary.Factory.getInstance()
                .getBookDetails(resources.getString(R.string.user_number), resources.getString(R.string.books_path), ID)
                .enqueue(new Callback<Book>() {
                    @Override
                    public void onResponse(Call<Book> call, Response<Book> response) {

                    }

                    @Override
                    public void onFailure(Call<Book> call, Throwable t) {

                    }
                });
    }

    public static void checkoutBook(final Context context, Book book, int ID){
        Resources resources = context.getResources();
        RetrofitCallsToProlificLibrary.Factory.getInstance()
                .checkoutBook(resources.getString(R.string.user_number), resources.getString(R.string.books_path), ID, book)
        .enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                Log.d("test", t.toString());

            }
        });
    }
}

