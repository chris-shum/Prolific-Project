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
}