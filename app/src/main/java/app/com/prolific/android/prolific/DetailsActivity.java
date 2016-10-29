package app.com.prolific.android.prolific;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import app.com.prolific.android.prolific.presenters.SetDetailsPage;
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
    @BindView(R.id.detailsBookCheckoutBy)
    TextView mCheckoutBy;
    @BindView(R.id.detailsBookCheckoutDate)
    TextView mCheckoutDate;
    @BindView(R.id.fab) FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);


        SetDetailsPage.SetDetailsPage(this, getIntent().getIntExtra("Position", 0), mTitle, mAuthor, mPublisher, mCategories, mCheckoutBy, mCheckoutDate);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
