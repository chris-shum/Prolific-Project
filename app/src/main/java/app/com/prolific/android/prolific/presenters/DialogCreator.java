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
                            //  TODO: 10/30/16 checkout time
//                            book.setLastCheckedOut();
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

    public static Dialog createAddBookDialog(final Activity activity, final EditText... editTexts) {
        StringBuilder sb = new StringBuilder("Title: " + editTexts[0].getText().toString() + "\n");
        sb.append("Author: " + editTexts[1].getText().toString() + "\n");
        sb.append("Publisher: " + editTexts[2].getText().toString() + "\n");
        sb.append("Categories: " + editTexts[3].getText().toString());
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Add book?").setMessage(sb)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
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
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return builder.create();
    }

    public static Dialog createDeleteAllDialog(final Activity activity, final RecyclerViewAdapter recyclerViewAdapter, final int size) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Are you sure you want to delete all books?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        PresentProlificLibrary.deleteAll(activity);
                        if (PresentProlificLibrary.deleteAll(activity)) {
                            PresentRealm.deleteAllRealmBooks(activity);
                            recyclerViewAdapter.notifyItemRangeRemoved(0, size);
                        }
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return builder.create();
    }

    public static Dialog createDeleteSelectedDialog(final Activity activity, final int ID, final RecyclerViewAdapter recyclerViewAdapter, final int position, final int size) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Delete selected book?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        PresentProlificLibrary.deleteSelectedBook(activity, ID);
                        if (PresentProlificLibrary.deleteSelectedBook(activity, ID)) {
                            PresentRealm.deleteRealmBook(activity, ID);
                            recyclerViewAdapter.notifyItemRemoved(position);
                            recyclerViewAdapter.notifyItemRangeChanged(position, size - 1);
                        }
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return builder.create();
    }

    public static Dialog createNoInternetDialog(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        // TODO: 10/30/16 cleanup this text
        builder.setTitle("No internet").setMessage("Internet connection not found.  Continue in offline mode?  Cannot add, delete, or checkout in offline mode.").setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return builder.create();
    }

    public static Dialog createExitAddActivity(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        // TODO: 10/30/16 cleanup this text
        builder.setTitle("Exit?").setMessage("You seem to have started something, still exit?").setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.finish();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return builder.create();
    }

}