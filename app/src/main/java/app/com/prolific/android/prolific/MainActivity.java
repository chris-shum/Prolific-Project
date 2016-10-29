package app.com.prolific.android.prolific;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import app.com.prolific.android.prolific.presenters.PresentProlificLibrary;
import app.com.prolific.android.prolific.presenters.RealmConvertor;
import app.com.prolific.android.prolific.presenters.RecyclerViewAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bookDisplayRecyclerView)
    RecyclerView mBookDisplayRecyclerView;
    RecyclerViewAdapter mRecyclerViewAdapter;
    LinearLayoutManager mLinearLayoutManager;
    @BindView(R.id.fab) FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);


        mLinearLayoutManager = new LinearLayoutManager(this);
        mBookDisplayRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerViewAdapter = new RecyclerViewAdapter(RealmConvertor.RealmBookToBookArrayList(this));
        mBookDisplayRecyclerView.setAdapter(mRecyclerViewAdapter);


        // TODO: 10/28/16 surround with internet check
        PresentProlificLibrary.getProlificLibrary(MainActivity.this, mRecyclerViewAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 10/28/16 intent to add screen

            }
        });
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
