package com.github.snowdream.android.apps.autoupdater;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.github.snowdream.android.app.UpdateFormat;
import com.github.snowdream.android.app.UpdateManager;
import com.github.snowdream.android.app.UpdateOptions;
import com.github.snowdream.android.app.UpdatePeriod;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main2, container, false);
            return rootView;
        }
    }

    public void onCheckUpdateClick(View v) {

        UpdateManager manager = new UpdateManager(this);

        UpdateOptions options = new UpdateOptions.Builder(this)
                .checkUrl("https://raw.github.com/snowdream/android-autoupdate/master/docs/test/updateinfo.xml")
                .updateFormat(UpdateFormat.XML)
                .updatePeriod(new UpdatePeriod(UpdatePeriod.EACH_TIME))
                .checkPackageName(true)
                .build();
//
//        UpdateOptions options = new UpdateOptions.Builder(this)
//                .checkUrl("https://raw.github.com/snowdream/android-autoupdate/master/docs/test/updateinfo.json")
//                .updateFormat(UpdateFormat.JSON)
//                .updatePeriod(new UpdatePeriod(UpdatePeriod.EACH_TIME))
//                .checkPackageName(true)
//                .build();

        manager.check(this, options);
    }
}