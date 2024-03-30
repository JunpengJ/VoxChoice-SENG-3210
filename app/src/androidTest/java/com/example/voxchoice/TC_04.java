package com.example.voxchoice;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
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
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TC_04 {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void tC04() {
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

        ViewInteraction textView = onView(
                allOf(withId(R.id.voxChoiceTitle), withText("VoxChoice"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView.check(matches(withText("VoxChoice")));

//        ViewInteraction textView2 = onView(
//                allOf(withId(R.id.register), withText("Welcome, Admin"),
//                        withParent(withParent(withId(android.R.id.content))),
//                        isDisplayed()));
//        textView2.check(matches(isDisplayed()));

        ViewInteraction button = onView(
                allOf(withId(R.id.button_manage_polls), withText("Manage Polls"),
                        withParent(allOf(withId(R.id.linearLayout),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.button_dashboard), withText("View Dashboard"),
                        withParent(allOf(withId(R.id.linearLayout),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction button3 = onView(
                allOf(withId(R.id.button_log_out), withText("Log Out"),
                        withParent(allOf(withId(R.id.linearLayout),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        button3.check(matches(isDisplayed()));

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.button_manage_polls), withText("Manage Polls"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                0),
                        isDisplayed()));
        materialButton2.perform(click());

        SystemClock.sleep(1000);

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.voxChoiceTitle), withText("VoxChoice"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView3.check(matches(withText("VoxChoice")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.managePollsTextView), withText("Manage Polls"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView4.check(matches(withText("Manage Polls")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.textView3), withText("Delete An Existing Poll"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView5.check(matches(withText("Delete An Existing Poll")));

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.deletePollRecyclerView),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        recyclerView.check(matches(isDisplayed()));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.textView2), withText("or"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView6.check(matches(withText("or")));

        ViewInteraction button4 = onView(
                allOf(withId(R.id.button_create_poll), withText("Create a New Poll"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        button4.check(matches(isDisplayed()));

        pressBack();

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.button_dashboard), withText("View Dashboard"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                2),
                        isDisplayed()));
        materialButton3.perform(click());

        SystemClock.sleep(1000);

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.voxChoiceTitle), withText("VoxChoice"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView7.check(matches(withText("VoxChoice")));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.textViewChoosePoll), withText("Choose Poll to View"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView8.check(matches(withText("Choose Poll to View")));

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.viewPollRecyclerView),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        recyclerView2.check(matches(isDisplayed()));

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.titleButton), withText("Lunch Poll"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.viewPollRecyclerView),
                                        0),
                                0),
                        isDisplayed()));
        materialButton4.perform(click());

        SystemClock.sleep(1000);

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.voxChoiceTitle), withText("VoxChoice"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView9.check(matches(withText("VoxChoice")));

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.pollTitleTextView), withText("Lunch Poll"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView10.check(matches(withText("Lunch Poll")));

        ViewInteraction textView11 = onView(
                allOf(withId(R.id.questionTextView), withText("Where should we go for lunch on Friday?"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView11.check(matches(withText("Where should we go for lunch on Friday?")));

        ViewInteraction recyclerView3 = onView(
                allOf(withId(R.id.dashboardItemRecyclerView),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        recyclerView3.check(matches(isDisplayed()));

        pressBack();

        pressBack();

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.button_log_out), withText("Log Out"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                4),
                        isDisplayed()));
        materialButton5.perform(click());

        SystemClock.sleep(1000);

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.username_log_in), withText("admin"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.linearLayout),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText3.perform(click());

        SystemClock.sleep(1000);

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.username_log_in), withText("admin"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.linearLayout),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("voter"));

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.username_log_in), withText("voter"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.linearLayout),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText5.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.password_log_in),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.linearLayout),
                                        2),
                                2),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("1234"), closeSoftKeyboard());

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.log_in_button), withText("Log In"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                4),
                        isDisplayed()));
        materialButton6.perform(click());

        SystemClock.sleep(1000);

        ViewInteraction textView12 = onView(
                allOf(withId(R.id.voxChoiceTitle), withText("VoxChoice"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView12.check(matches(withText("VoxChoice")));

        ViewInteraction textView13 = onView(
                allOf(withId(R.id.register), withText("Welcome, Voter"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView13.check(matches(withText("Welcome, Voter")));

        ViewInteraction button5 = onView(
                allOf(withId(R.id.button_view_polls), withText("View Polls"),
                        withParent(allOf(withId(R.id.linearLayout),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        button5.check(matches(isDisplayed()));

        ViewInteraction button6 = onView(
                allOf(withId(R.id.button_log_out), withText("Log Out"),
                        withParent(allOf(withId(R.id.linearLayout),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        button6.check(matches(isDisplayed()));

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.button_view_polls), withText("View Polls"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                0),
                        isDisplayed()));
        materialButton7.perform(click());

        SystemClock.sleep(1000);

        ViewInteraction textView14 = onView(
                allOf(withId(R.id.voxChoiceTitle), withText("VoxChoice"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView14.check(matches(withText("VoxChoice")));

        ViewInteraction textView15 = onView(
                allOf(withId(R.id.textViewChoosePoll), withText("Choose Poll to View"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView15.check(matches(withText("Choose Poll to View")));

        ViewInteraction recyclerView4 = onView(
                allOf(withId(R.id.viewPollRecyclerView),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        recyclerView4.check(matches(isDisplayed()));

        pressBack();

        ViewInteraction materialButton8 = onView(
                allOf(withId(R.id.button_log_out), withText("Log Out"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                2),
                        isDisplayed()));
        materialButton8.perform(click());

        SystemClock.sleep(1000);

        ViewInteraction textView16 = onView(
                allOf(withId(R.id.voxChoiceTitle), withText("VoxChoice"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView16.check(matches(withText("VoxChoice")));

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.linearLayout),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

        ViewInteraction button7 = onView(
                allOf(withId(R.id.log_in_button), withText("Log In"),
                        withParent(allOf(withId(R.id.linearLayout),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        button7.check(matches(isDisplayed()));

        ViewInteraction textView17 = onView(
                allOf(withId(R.id.register_clickable), withText("Register"),
                        withParent(allOf(withId(R.id.linearLayout),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        textView17.check(matches(withText("Register")));
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
