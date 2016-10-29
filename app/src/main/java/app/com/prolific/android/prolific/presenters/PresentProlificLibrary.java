package app.com.prolific.android.prolific.presenters;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

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

    public static void getProlificLibrary(final Context context, final RecyclerViewAdapter recyclerViewAdapter) {
        Resources resources = context.getResources();
        RetrofitCallsToProlificLibrary.Factory.getInstance()
                .getBooks(resources.getString(R.string.user_number), resources.getString(R.string.books_path))
                .enqueue(new Callback<List<Book>>() {
                    @Override
                    public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                        RealmConvertor.JSonToRealmBook(response.body(), (Activity) context);
                        recyclerViewAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<Book>> call, Throwable t) {
//                        toast of some sort
                        Log.d("test", t.toString());
                    }
                });
    }
}

