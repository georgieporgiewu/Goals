package com.georgeqwu.goals.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.georgeqwu.goals.R;
import com.georgeqwu.goals.fragment.CreateGoalFragment;


/**
 * Created by glookogeorge on 5/29/17.
 */

public class CreateGoalsAndTasksActivity extends AppCompatActivity
                implements CreateGoalFragment.OnFragmentInteractionListener {

    //
    // Enum of fragments
    //
    public enum Tab {
        CREATE_GOAL, CREATE_TASK,
    }

    CreateGoalFragment mCreateGoalFragment;
    Tab mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_goal);

        instantiateFragments();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.create_goal);

        goToFragment(Tab.CREATE_GOAL, null);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onFragmentInteraction(Uri uri) {
        // TODO update this with fragment interaction
    }

    public void goToFragment(Tab nextFragment, Bundle bundle) {
        String existingName;
        String requestedName;
        Fragment requestedFragment;
        Fragment existingFragment;
        FragmentManager fragmentManager;
        mCurrentFragment = nextFragment;

        switch (nextFragment) {
            case CREATE_GOAL:
                requestedFragment = mCreateGoalFragment;
                break;
            default:
                requestedFragment = mCreateGoalFragment;
                break;
        }

        if (bundle != null) {
            Bundle prevBundle = requestedFragment.getArguments();
            if (prevBundle == null) {
                //
                // requestedFragment is new. Call setArguments to set bundle.
                //
                requestedFragment.setArguments(bundle);

            } else {
                //
                // requestedFragment is already active. Cannot set bundle. Rather, we
                // can update the values in its bundle.
                //
                prevBundle.putAll(bundle);
            }
        }

        if (nextFragment.equals(Tab.CREATE_GOAL)) {
            getSupportActionBar().setTitle(R.string.create_goal);
        }

        fragmentManager = getSupportFragmentManager();
        existingFragment = fragmentManager.findFragmentById(R.id.main_content);
        existingName = "";

        if (existingFragment != null) {
            existingName = existingFragment.getClass().getSimpleName();
        }

        requestedName = requestedFragment.getClass().getSimpleName();

        FragmentTransaction fragmentTransaction;

        try {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_content, requestedFragment);
            fragmentTransaction.commit();

        } catch (Exception e) {
            Log.e("CreateGoalsAndTasks","Error switching fragments from " + existingName + " to " + requestedName, e);

            //
            // Try a new transaction but commit allowing state loss.
            //
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_content, requestedFragment);
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    private void instantiateFragments() {
        mCreateGoalFragment = new CreateGoalFragment();
    }

}
