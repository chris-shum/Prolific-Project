package app.com.prolific.android.prolific.presenters;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.com.prolific.android.prolific.DetailsActivity;
import app.com.prolific.android.prolific.R;
import app.com.prolific.android.prolific.models.RealmBook;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * Created by ShowMe on 10/28/16.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    RealmResults<RealmBook> bookArrayList;

    public RecyclerViewAdapter(RealmResults<RealmBook> bookArrayList) {
        this.bookArrayList = bookArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_cardview, parent, false);
        ViewHolder rcv = new ViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.bookTitle.setText(bookArrayList.get(position).getTitle());
        holder.bookAuthor.setText(bookArrayList.get(position).getAuthor());
        holder.bookCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailsActivity.class);
                intent.putExtra("ID", bookArrayList.get(position).getId());
                view.getContext().startActivity(intent);
            }
        });
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
        @BindView(R.id.bookTitle)
        TextView bookTitle;
        @BindView(R.id.bookAuthor)
        TextView bookAuthor;
        @BindView(R.id.bookCardView)
        CardView bookCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
