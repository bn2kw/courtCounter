package com.example.abhishek.courtcounter;
import android.content.pm.ActivityInfo;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.abhishek.courtcounter.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;





/**
 * Created by abhishek on 20/8/18.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public final ActivityTestRule<MainActivity> activity = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void court() {
        press(R.id.PlusthreeTeamA);
        press(R.id.PlustwoTeamA);
        checkResult("5");
        press(R.id.PlusthreeTeamB);
        press(R.id.FreeThrowTeamB);
        checkResult1("4");
        activity.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        checkResult("5");
        checkResult1("4");
        //press(R.id.resetButton);
    }

    private void press(int id){
        onView(withId(id)).perform(click());
        //onData(withId(id)).perform(click());
    }

    private void checkResult(String desired){
        onView(withId(R.id.team_a_score)).check(matches(withText(desired)));
    }

    private void checkResult1(String desired){
        onView(withId(R.id.team_b_score)).check(matches(withText(desired)));
    }

}
