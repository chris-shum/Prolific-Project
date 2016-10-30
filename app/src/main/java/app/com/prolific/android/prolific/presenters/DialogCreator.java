package app.com.prolific.android.prolific.presenters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import app.com.prolific.android.prolific.R;
import app.com.prolific.android.prolific.models.Book;

/**
 * Created by ShowMe on 10/29/16.
 */


public class DialogCreator extends DialogFragment {
    public static Dialog createCheckoutDialog(final Activity activity, final int ID) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.checkout_dialog, null);
        builder.setView(dialogView);
        final EditText editText = (EditText) dialogView.findViewById(R.id.checkoutEditText);
        builder.setMessage(getStringResources(activity, R.string.book_checkout))
                .setPositiveButton(getStringResources(activity, R.string.checkout), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (editText.getText().toString().replaceAll("\\s+", "").equals("")) {
                            createCheckoutDialog(activity, ID).show();
                        } else {
                            Book book = new Book();
                            book.setLastCheckedOutBy(editText.getText().toString());
                            Calendar cal = Calendar.getInstance();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss zzz");
                            String timeNow = sdf.format(cal.getTime());
                            book.setLastCheckedOut(timeNow);
                            PresentProlificLibrary.checkoutBook(activity, book, ID);
                            activity.finish();
                        }
                    }
                })
                .setNegativeButton(getStringResources(activity, R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        return builder.create();
    }

    public static Dialog createAddBookDialog(final Activity activity, final EditText... editTexts) {
        StringBuilder sb = new StringBuilder("Title: " + editTexts[0].getText().toString() + "\n");
        sb.append("Author: " + editTexts[1].getText().toString() + "\n");
        sb.append("Publisher: " + editTexts[2].getText().toString() + "\n");
        sb.append("Categories: " + editTexts[3].getText().toString());
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(getStringResources(activity, R.string.add_book)).setMessage(sb)
                .setPositiveButton(getStringResources(activity, R.string.add), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Book book = new Book();
                        book.setTitle(editTexts[0].getText().toString());
                        book.setAuthor(editTexts[1].getText().toString());
                        book.setPublisher(editTexts[2].getText().toString());
                        book.setCategories(editTexts[3].getText().toString());
                        PresentProlificLibrary.addBook(activity, book);
                        for (int j = 0; j < editTexts.length; j++) {
                            editTexts[j].setText("");
                        }
                    }
                })
                .setNegativeButton(getStringResources(activity, R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        return builder.create();
    }

    public static Dialog createDeleteAllDialog(final Activity activity, final RecyclerViewAdapter recyclerViewAdapter, final int size) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(getStringResources(activity, R.string.delete_all))
                .setPositiveButton(getStringResources(activity, R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (PresentProlificLibrary.deleteAll(activity)) {
                            PresentRealm.deleteAllRealmBooks(activity);
                            recyclerViewAdapter.notifyItemRangeRemoved(0, size);
                        }
                    }
                })
                .setNegativeButton(getStringResources(activity, R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        return builder.create();
    }

    public static Dialog createDeleteSelectedDialog(final Activity activity, final int ID, final RecyclerViewAdapter recyclerViewAdapter, final int position, final int size) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(getStringResources(activity, R.string.delete_selected))
                .setPositiveButton(getStringResources(activity, R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (PresentProlificLibrary.deleteSelectedBook(activity, ID)) {
                            PresentRealm.deleteRealmBook(activity, ID);
                            recyclerViewAdapter.notifyItemRemoved(position);
                            recyclerViewAdapter.notifyItemRangeChanged(position, size - 1);
                        }
                    }
                })
                .setNegativeButton(getStringResources(activity, R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        return builder.create();
    }

    public static Dialog createNoInternetDialog(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(getStringResources(activity, R.string.no_internet))
                .setMessage(getStringResources(activity, R.string.offline_mode_message))
                .setPositiveButton(getStringResources(activity, R.string.okay), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        return builder.create();
    }

    public static Dialog createExitAddActivityDialog(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(getStringResources(activity, R.string.exit)).setMessage(getStringResources(activity, R.string.exit_message))
                .setPositiveButton(getStringResources(activity, R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        activity.finish();
                    }
                })
                .setNegativeButton(getStringResources(activity, R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        return builder.create();
    }

    public static String getStringResources(Activity activity, int stringPath) {
        return activity.getResources().getString(stringPath);
    }

}