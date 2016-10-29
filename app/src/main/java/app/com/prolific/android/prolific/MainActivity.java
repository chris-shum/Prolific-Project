package app.com.prolific.android.prolific;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import app.com.prolific.android.prolific.presenters.RecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {

    RecyclerView mEmojiDisplayRecyclerView;
    RecyclerViewAdapter mRecyclerViewAdapter;
    LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        retrofitcall
//        PresentProlificLibrary.getProlificLibary(this);

//        recyclerView
//        mLinearLayoutManager = new LinearLayoutManager(this);
//        mEmojiDisplayRecyclerView = (RecyclerView) findViewById(R.id.bookDisplayRecyclerView);
//        mEmojiDisplayRecyclerView.setLayoutManager(mLinearLayoutManager);
//        mRecyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<Book>());
//        mEmojiDisplayRecyclerView.setAdapter(mRecyclerViewAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
