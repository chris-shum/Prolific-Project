package app.com.prolific.android.prolific;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import app.com.prolific.android.prolific.presenters.DetailsActivitySetup;
import app.com.prolific.android.prolific.presenters.DialogCreator;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.detailsBookTitle)
    TextView mTitle;
    @BindView(R.id.detailsBookAuthor)
    TextView mAuthor;
    @BindView(R.id.detailsBookPublisher)
    TextView mPublisher;
    @BindView(R.id.detailsBookCategories)
    TextView mCategories;
    @BindView(R.id.checkout)
    TextView mCheckout;
    @BindView(R.id.detailsBookCheckoutBy)
    TextView mCheckoutBy;
    @BindView(R.id.detailsBookCheckoutDate)
    TextView mCheckoutDate;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    android.support.v7.widget.ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        DetailsActivitySetup.setDetailsPage(this, getIntent().getIntExtra(getResources().getString(R.string.intent_id), 0), mTitle, mAuthor, mPublisher, mCategories, mCheckoutBy, mCheckoutDate);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogCreator.createCheckoutDialog((Activity) view.getContext(), getIntent().getIntExtra(getResources().getString(R.string.intent_id), 0)).show();
            }
        });

        if (!mCheckoutBy.getText().toString().equals("")) {
            mCheckout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isNetworkConnected()) {
            fab.setEnabled(true);
        } else {
            fab.setEnabled(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        MenuItem item = menu.findItem(R.id.action_share);
        mShareActionProvider = (android.support.v7.widget.ShareActionProvider) MenuItemCompat.getActionProvider(item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (isNetworkConnected()) {
            switch (id) {
                case R.id.action_share:
                    setShareIntent(createShareIntent());
                    break;
                case android.R.id.home:
                    onBackPressed();
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }

    private Intent createShareIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        StringBuilder sb = new StringBuilder("Checkout this book:\n");
        sb.append(mTitle.getText().toString() + "\n");
        sb.append("by " + mAuthor.getText().toString());
        shareIntent.putExtra(Intent.EXTRA_TEXT, sb.toString());
        startActivity(Intent.createChooser(shareIntent, "Share this book!"));
        return shareIntent;
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}
