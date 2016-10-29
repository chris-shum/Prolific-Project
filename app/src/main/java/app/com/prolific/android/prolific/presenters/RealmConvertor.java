package app.com.prolific.android.prolific.presenters;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import app.com.prolific.android.prolific.models.Book;
import app.com.prolific.android.prolific.models.RealmBook;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by ShowMe on 10/28/16.
 */

public class RealmConvertor {

    public static void JSonToRealmBook(List<Book> bookList, Activity activity) {
        Realm realm = Realm.getInstance(activity);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<RealmBook> result = realm.where(RealmBook.class).findAll();
                result.clear();
            }
        });

        for (int i = 0; i < bookList.size(); i++) {
            realm.beginTransaction();
            RealmBook realmBook = realm.createObject(RealmBook.class);
            realmBook.setAuthor(bookList.get(i).getAuthor());
            realmBook.setCategories(bookList.get(i).getCategories());
            realmBook.setId(bookList.get(i).getId());
            realmBook.setLastCheckedOut(bookList.get(i).getLastCheckedOut());
            realmBook.setLastCheckedOutBy(bookList.get(i).getLastCheckedOutBy());
            realmBook.setPublisher(bookList.get(i).getPublisher());
            realmBook.setTitle(bookList.get(i).getTitle());
            realmBook.setUrl(bookList.get(i).getUrl());
            realm.commitTransaction();
        }
    }

    public static ArrayList<Book> RealmBookToBookArrayList(Activity activity) {
        Realm realm = Realm.getInstance(activity);
        final RealmResults<RealmBook> realmBooks = realm.where(RealmBook.class).findAll();
        ArrayList<Book> bookArrayList = new ArrayList<>();
        for (int i = 0; i < realmBooks.size(); i++) {
            RealmBook realmBook = realmBooks.get(i);
            bookArrayList.add(new Book(realmBook.getAuthor(), realmBook.getCategories(), realmBook.getId(), realmBook.getLastCheckedOut(), realmBook.getLastCheckedOutBy(), realmBook.getPublisher(), realmBook.getTitle(), realmBook.getUrl()));
        }
        return bookArrayList;
    }
}
