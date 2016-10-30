package app.com.prolific.android.prolific.presenters;

import android.widget.EditText;

/**
 * Created by ShowMe on 10/30/16.
 */

public class AddActivityCheck {
    public static int checkEditTexts(EditText... editTexts) {
        int count = 0;
        for (int i = 0; i < editTexts.length; i++) {
            if (editTexts[i].getText().toString().replaceAll("\\s+", "").equals("")) {
            } else {
                count++;
            }
        }
        return count;
    }
}
