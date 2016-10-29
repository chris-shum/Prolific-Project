package app.com.prolific.android.prolific.presenters;

import android.app.Activity;
import android.widget.TextView;

import app.com.prolific.android.prolific.models.Book;

/**
 * Created by ShowMe on 10/28/16.
 */

public class SetDetailsPage {
    public static void SetDetailsPage(Activity activity, int position, TextView... textViews) {

        Book book = RealmConvertor.RealmBookToBookArrayList(activity).get(position);
        textViews[0].setText(book.getTitle());
        textViews[1].setText(book.getAuthor());
        textViews[2].setText(book.getPublisher());
        textViews[3].setText(book.getCategories());
        textViews[4].setText(book.getLastCheckedOutBy());
        textViews[5].setText(book.getLastCheckedOut());
    }
}
