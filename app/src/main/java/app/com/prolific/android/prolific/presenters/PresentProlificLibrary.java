package app.com.prolific.android.prolific.presenters;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
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

//all methods for calls made to the flask API are made through here
//each method updates the realm database

public class PresentProlificLibrary {
    static boolean mSuccess = true;

    public static void getProlificLibrary(final Context context) {
        final Resources resources = context.getResources();
        RetrofitCallsToProlificLibrary.Factory.getInstance()
                .getLibrary(resources.getString(R.string.user_number),
                        resources.getString(R.string.books_path))
                .enqueue(new Callback<List<Book>>() {
                    @Override
                    public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                        PresentRealm.convertProlificLibraryToRealmLibrary(response.body(), (Activity) context);
                    }

                    @Override
                    public void onFailure(Call<List<Book>> call, Throwable t) {
                        connectionFailureToast(context);
                    }
                });
    }

    public static void getProlificBookDetails(final Context context, int ID) {
        Resources resources = context.getResources();
        RetrofitCallsToProlificLibrary.Factory.getInstance()
                .getBook(resources.getString(R.string.user_number),
                        resources.getString(R.string.books_path),
                        ID)
                .enqueue(new Callback<Book>() {
                    @Override
                    public void onResponse(Call<Book> call, Response<Book> response) {
                    }

                    @Override
                    public void onFailure(Call<Book> call, Throwable t) {
                    }
                });
    }

    public static void checkoutBook(final Context context, Book book, int ID) {
        final Resources resources = context.getResources();
        RetrofitCallsToProlificLibrary.Factory.getInstance()
                .checkoutBook(resources.getString(R.string.user_number),
                        resources.getString(R.string.books_path),
                        ID,
                        book)
                .enqueue(new Callback<Book>() {
                    @Override
                    public void onResponse(Call<Book> call, Response<Book> response) {
                        if (response.code() == 200) {
                            Toast.makeText(context, resources.getString(R.string.confirm_checkout), Toast.LENGTH_SHORT).show();
                            getProlificLibrary(context);
                        } else {
                            networkErrorToast(context);
                        }
                    }

                    @Override
                    public void onFailure(Call<Book> call, Throwable t) {
                        connectionFailureToast(context);
                    }
                });
    }

    public static void addBook(final Context context, Book book) {
        final Resources resources = context.getResources();
        RetrofitCallsToProlificLibrary.Factory.getInstance()
                .addBook(resources.getString(R.string.user_number),
                        resources.getString(R.string.books_path),
                        book)
                .enqueue(new Callback<Book>() {
                    @Override
                    public void onResponse(Call<Book> call, Response<Book> response) {
                        if (response.code() == 200) {
                            Toast.makeText(context, resources.getString(R.string.confirm_book_added), Toast.LENGTH_SHORT).show();
                            getProlificLibrary(context);
                        } else {
                            networkErrorToast(context);
                        }
                    }

                    @Override
                    public void onFailure(Call<Book> call, Throwable t) {
                        connectionFailureToast(context);
                    }
                });
    }

    public static boolean deleteAllBooks(final Context context) {
        final Resources resources = context.getResources();
        RetrofitCallsToProlificLibrary.Factory.getInstance()
                .deleteAll(resources.getString(R.string.user_number),
                        resources.getString(R.string.delete_path))
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200) {
                            Toast.makeText(context, resources.getString(R.string.confirm_delete_all), Toast.LENGTH_SHORT).show();
                            getProlificLibrary(context);
                            mSuccess = true;
                        } else {
                            networkErrorToast(context);
                            mSuccess = false;
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        connectionFailureToast(context);
                        mSuccess = false;
                    }
                });
        return mSuccess;
    }

    public static boolean deleteSelectedBook(final Context context, int ID) {
        final Resources resources = context.getResources();
        RetrofitCallsToProlificLibrary.Factory.getInstance()
                .deleteBook(
                        resources.getString(R.string.user_number),
                        resources.getString(R.string.books_path),
                        ID)
                .enqueue(new Callback<Book>() {
                    @Override
                    public void onResponse(Call<Book> call, Response<Book> response) {
                        if (response.code() == 204) {
                            Toast.makeText(context, resources.getString(R.string.confirm_book_deleted), Toast.LENGTH_SHORT).show();
                            getProlificLibrary(context);
                            mSuccess = true;
                        } else {
                            networkErrorToast(context);
                            mSuccess = false;
                        }
                    }

                    @Override
                    public void onFailure(Call<Book> call, Throwable t) {
                        connectionFailureToast(context);
                        mSuccess = false;
                    }
                });
        
        return mSuccess;
    }

    public static void networkErrorToast(Context context) {
        Toast.makeText(context, context.getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
    }

    public static void connectionFailureToast(Context context) {
        Toast.makeText(context, context.getResources().getString(R.string.connection_failure), Toast.LENGTH_SHORT).show();
    }
}

