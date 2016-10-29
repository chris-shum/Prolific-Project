package app.com.prolific.android.prolific.presenters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
        final EditText edt = (EditText) dialogView.findViewById(R.id.edit1);
        builder.setMessage("Enter your name to checkout this book.").
                setPositiveButton("Checkout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (edt.getText().toString().replaceAll("\\s+", "").equals("")) {
                            createCheckoutDialog(activity, ID).show();
                        } else {
                            Toast.makeText(activity, "CheckedOut", Toast.LENGTH_SHORT).show();
                            Book book = new Book();
                            book.setLastCheckedOutBy(edt.getText().toString());
                            PresentProlificLibrary.checkoutBook(activity, book, ID);
                            PresentProlificLibrary.getProlificLibrary(activity);
                        }
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(activity, "Book not checked out.", Toast.LENGTH_SHORT).show();
            }
        });
        return builder.create();
    }

    public static Dialog createAddBookDialog(final Activity activity, final EditText... editTexts) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Haaaay")
                .setPositiveButton("Womp", new DialogInterface.OnClickListener() {
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
                        PresentProlificLibrary.getProlificLibrary(activity);
                    }
                }).setNegativeButton("Bah", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        return builder.create();
    }

    public static Dialog createDeleteAllDialog(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Delete ALL?")
                .setPositiveButton("Womp", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        PresentProlificLibrary.deleteAll(activity);
                        PresentProlificLibrary.getProlificLibrary(activity);
                    }
                }).setNegativeButton("Bah", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return builder.create();
    }

    public static Dialog createDeleteSelectedDialog(final Activity activity, final int ID) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Delete Selected?")
                .setPositiveButton("Womp", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        PresentProlificLibrary.deleteSelectedBook(activity, ID);
                        PresentProlificLibrary.getProlificLibrary(activity);
                    }
                }).setNegativeButton("Bah", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return builder.create();
    }
}