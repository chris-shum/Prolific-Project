package app.com.prolific.android.prolific;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import app.com.prolific.android.prolific.presenters.DialogCreator;
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

        SetDetailsPage.setDetailsPage(this, getIntent().getIntExtra("ID", 0), mTitle, mAuthor, mPublisher, mCategories, mCheckoutBy, mCheckoutDate);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogCreator.createCheckoutDialog((Activity) view.getContext(), getIntent().getIntExtra("ID", 0)).show();
            }
        });
    }

    // TODO: 10/29/16 add up carrot 

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        // TODO: 10/29/16 add sharing
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
