package app.com.prolific.android.prolific;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import app.com.prolific.android.prolific.presenters.DialogCreator;
import app.com.prolific.android.prolific.presenters.PresentProlificLibrary;
import app.com.prolific.android.prolific.presenters.PresentRealm;
import app.com.prolific.android.prolific.presenters.RecyclerViewAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bookDisplayRecyclerView)
    RecyclerView mBookDisplayRecyclerView;
    LinearLayoutManager mLinearLayoutManager;
    RecyclerViewAdapter mRecyclerViewAdapter;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mBookDisplayRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerViewAdapter = new RecyclerViewAdapter(PresentRealm.getRealmLibrary(this));
        mBookDisplayRecyclerView.setAdapter(mRecyclerViewAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddActivity();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isNetworkConnected()) {
            PresentProlificLibrary.getProlificLibrary(MainActivity.this);
            mRecyclerViewAdapter.notifyDataSetChanged();
            // TODO: 10/29/16 after disable, enable all fabs
        } else {
            Toast.makeText(this, "Internet Unavailable", Toast.LENGTH_SHORT).show();
            // TODO: 10/29/16 no internet dialogbox
            // TODO: 10/29/16 sharedprefernce?  disable all fabs.
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        // TODO: 10/28/16 add add and delete all options
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add:
                goToAddActivity();
                break;
            case R.id.action_delete_all:
                DialogCreator.createDeleteAllDialog(this).show();
                break;
        }
        return true;
}

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public void goToAddActivity() {
        Intent intent = new Intent(MainActivity.this, AddActivity.class);
        startActivity(intent);
    }
}
