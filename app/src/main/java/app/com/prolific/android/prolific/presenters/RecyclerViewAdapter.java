package app.com.prolific.android.prolific.presenters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import app.com.prolific.android.prolific.R;
import app.com.prolific.android.prolific.models.Book;

/**
 * Created by ShowMe on 10/28/16.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    ArrayList<Book> bookArrayList;

    public RecyclerViewAdapter(ArrayList<Book> bookArrayList) {
        this.bookArrayList = bookArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_cardview, parent, false);
        ViewHolder rcv = new ViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bookTitle.setText(bookArrayList.get(position).getTitle());
        holder.bookAuthor.setText(bookArrayList.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        if (bookArrayList == null) {
            return 0;
        } else {
            return bookArrayList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bookTitle;
        TextView bookAuthor;

        public ViewHolder(View itemView) {
            super(itemView);
            bookTitle = (TextView) itemView.findViewById(R.id.bookTitle);
            bookAuthor = (TextView) itemView.findViewById(R.id.bookAuthor);
        }
    }
}
