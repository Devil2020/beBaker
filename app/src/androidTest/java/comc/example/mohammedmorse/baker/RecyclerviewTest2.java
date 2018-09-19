package comc.example.mohammedmorse.baker;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import comc.example.mohammedmorse.baker.View.MainActivity;

/**
 * Created by Mohammed Morse on 17/09/2018.
 */

public class RecyclerviewTest2 {
    @Rule
    public ActivityTestRule<MainActivity> rule=new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void TestIdling(){
        Espresso.onView(ViewMatchers.withId(R.id.Recyclerview))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

}
