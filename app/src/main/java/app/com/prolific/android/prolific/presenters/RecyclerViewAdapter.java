package app.com.prolific.android.prolific.presenters;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
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
    Activity mActivity;

    public RecyclerViewAdapter(RealmResults<RealmBook> bookArrayList, Activity activity) {
        this.bookArrayList = bookArrayList;
        this.mActivity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_cardview, parent, false);
        ViewHolder rcv = new ViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.bookTitle.setText(bookArrayList.get(position).getTitle());
        holder.bookAuthor.setText(bookArrayList.get(position).getAuthor());
//        click for details, using shared element transitions
        holder.bookCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailsActivity.class);
                intent.putExtra(view.getContext().getResources().getString(R.string.intent_id), bookArrayList.get(position).getId());
                Pair<View, String> p1 = Pair.create(view, "cardview");
                Pair<View, String> p2 = Pair.create((View) holder.bookTitle, "title");
                Pair<View, String> p3 = Pair.create((View) holder.bookAuthor, "author");
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity, p1, p2, p3);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    view.getContext().startActivity(intent, options.toBundle());
                }
            }
        });
//        long click to delete
        holder.bookCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                DialogCreator.createDeleteBookDialog((Activity) view.getContext(),
                        bookArrayList.get(position).getId(),
                        RecyclerViewAdapter.this,
                        position,
                        bookArrayList.size()).show();
                return true;
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
