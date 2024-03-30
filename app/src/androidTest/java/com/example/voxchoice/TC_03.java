package com.example.voxchoice;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TC_03 {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void tC_03() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.username_log_in),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.linearLayout),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("admin"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.password_log_in),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.linearLayout),
                                        2),
                                2),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("1234"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.log_in_button), withText("Log In"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                4),
                        isDisplayed()));
        materialButton.perform(click());

        SystemClock.sleep(1000);

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.button_dashboard), withText("View Dashboard"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                2),
                        isDisplayed()));
        materialButton2.perform(click());

        SystemClock.sleep(1000);

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.titleButton), withText("Lunch Poll"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.viewPollRecyclerView),
                                        0),
                                0),
                        isDisplayed()));
        materialButton3.perform(click());

        SystemClock.sleep(1000);
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
