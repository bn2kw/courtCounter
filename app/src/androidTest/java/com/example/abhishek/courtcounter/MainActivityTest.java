package com.example.abhishek.courtcounter;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.WindowManager;

import com.example.abhishek.courtcounter.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.content.Context.KEYGUARD_SERVICE;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;





/**
 * Created by abhishek on 20/8/18.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public final ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @UiThreadTest
    @Before
    public void setUp() throws Exception {
        final Activity activity = mActivityRule.getActivity();
        try {
            mActivityRule.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    KeyguardManager mKG = (KeyguardManager) activity.getSystemService(KEYGUARD_SERVICE);
                    KeyguardManager.KeyguardLock mLock = mKG.newKeyguardLock(KEYGUARD_SERVICE);
                    mLock.disableKeyguard();
    
                    //turn the screen on
                    activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                            | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                            | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                            | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                            | WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void court() throws InterruptedException {
        Thread.sleep(1000);
        press(R.id.PlusthreeTeamA);
        press(R.id.PlustwoTeamA);
        checkResult("5");
        press(R.id.PlusthreeTeamB);
        press(R.id.FreeThrowTeamB);
        checkResult1("4");
        //press(R.id.resetButton);
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        checkResult("5");
        checkResult1("4");
       /* press(R.id.PlusthreeTeamA);
        press(R.id.PlustwoTeamA);
        press(R.id.PlusthreeTeamA);
        checkResult("13");
        press(R.id.PlusthreeTeamB);
        press(R.id.FreeThrowTeamB);
        checkResult1("8");*/
    }

    private void press(int id){
        try {
            Thread.sleep(1000);
            onView(withId(id)).perform(click());
        } catch (InterruptedException e) {
            e.printStackTrace();

        }


        //onData(withId(id)).perform(click());
    }

    private void checkResult(String desired){
        onView(withId(R.id.team_a_score)).check(matches(withText(desired)));
    }

    private void checkResult1(String desired){
        onView(withId(R.id.team_b_score)).check(matches(withText(desired)));
    }


}


