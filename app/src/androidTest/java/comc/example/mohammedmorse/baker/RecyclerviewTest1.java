package comc.example.mohammedmorse.baker;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.atomic.AtomicBoolean;

import comc.example.mohammedmorse.baker.View.MainActivity;

/**
 * Created by Mohammed Morse on 17/09/2018.
 */
 @RunWith(AndroidJUnit4.class)
public class RecyclerviewTest1  {
  @Rule
     public ActivityTestRule<MainActivity> rule=new ActivityTestRule<MainActivity>(MainActivity.class);
     private IdlingResource idlingResource;
  @Before
  public void RegisterIdlingResource(){
      idlingResource=rule.getActivity().getIdlingResource();
      Espresso.registerIdlingResources(idlingResource);
  }
   @Test
   public void TestIdling(){
      Espresso.onView(ViewMatchers.withId(R.id.Recyclerview))
              .perform(RecyclerViewActions.scrollToPosition(0),ViewActions.click());
   }
  @After
    public void unRegisterIdling(){
      Espresso.unregisterIdlingResources(idlingResource);
  }
}
