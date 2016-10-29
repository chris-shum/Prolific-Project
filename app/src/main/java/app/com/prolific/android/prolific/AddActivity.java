package app.com.prolific.android.prolific;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 10/29/16 checks for edittextbox empty
                DialogCreator.createAddBookDialog(AddActivity.this, mTitleInput, mAuthorInput, mPublisherInput, mCategoriesInput).show();
            }
        });
    }
}
