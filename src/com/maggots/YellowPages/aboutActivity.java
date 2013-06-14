package com.maggots.YellowPages;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: maity
 * Date: 5/13/13
 * Time: 2:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class aboutActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        setContentView(R.layout.about);
        TextView changeLog = (TextView)findViewById(R.id.textViewChangeLog);
        TextView about = (TextView)findViewById(R.id.textViewAbout);

        changeLog.setMovementMethod(LinkMovementMethod.getInstance());
        changeLog.setText(Html.fromHtml(getString(R.string.changeLog)));
        about.setMovementMethod(LinkMovementMethod.getInstance());
        about.setText(Html.fromHtml(getString(R.string.about)));

    }
}