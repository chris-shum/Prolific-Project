package app.com.prolific.android.prolific;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import app.com.prolific.android.prolific.presenters.AddActivityCheck;
import app.com.prolific.android.prolific.presenters.DialogCreator;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AddActivity extends AppCompatActivity {
    @BindView(R.id.bookTitleInput)
    EditText mTitleInput;
    @BindView(R.id.bookAuthorInput)
    EditText mAuthorInput;
    @BindView(R.id.bookPublisherInput)
    EditText mPublisherInput;
    @BindView(R.id.bookCategoriesInput)
    EditText mCategoriesInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AddActivityCheck.checkEditTexts(mTitleInput, mAuthorInput, mPublisherInput, mCategoriesInput) == 4) {
                    DialogCreator.createAddBookDialog(AddActivity.this, mTitleInput, mAuthorInput, mPublisherInput, mCategoriesInput).show();
                } else {
                    Toast.makeText(AddActivity.this, getResources().getString(R.string.editText_error), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (AddActivityCheck.checkEditTexts(mTitleInput, mAuthorInput, mPublisherInput, mCategoriesInput) > 0) {
            DialogCreator.createExitAddActivityDialog(this).show();
        } else {
            switch (id) {
                case R.id.action_done:
                    onBackPressed();
                    break;
                case android.R.id.home:
                    onBackPressed();
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
