package com.example.voxchoice;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
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
public class TC_02 {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void tC_02() {
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

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.titleButton), withText("Vote for food"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.deletePollRecyclerView),
                                        3),
                                0),
                        isDisplayed()));
        materialButton3.perform(click());

        SystemClock.sleep(1000);

        ViewInteraction materialButton4 = onView(
                allOf(withId(android.R.id.button1), withText("Delete"),
                        childAtPosition(
                                childAtPosition(
                                        withId(androidx.appcompat.R.id.buttonPanel),
                                        0),
                                3)));
        materialButton4.perform(scrollTo(), click());

        SystemClock.sleep(1000);

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.button_create_poll), withText("Create a New Poll"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        materialButton5.perform(click());

        SystemClock.sleep(1000);

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.titleEditText),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("Vote for food"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.questionEditText),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatEditText4.perform(click());

        SystemClock.sleep(1000);

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.questionEditText),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("Choose your topping"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.optionEditText),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout2),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                5)),
                                0),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("Banana"), closeSoftKeyboard());

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.button_add_option), withText("Add Option"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout2),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                5)),
                                2),
                        isDisplayed()));
        materialButton6.perform(click());

        SystemClock.sleep(1000);

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.optionEditText),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout2),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                5)),
                                0),
                        isDisplayed()));
        appCompatEditText7.perform(replaceText("Olive"), closeSoftKeyboard());

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.button_add_option), withText("Add Option"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout2),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                5)),
                                2),
                        isDisplayed()));
        materialButton7.perform(click());

        SystemClock.sleep(1000);

        ViewInteraction materialButton8 = onView(
                allOf(withId(R.id.button_create_poll), withText("Create Poll"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialButton8.perform(click());

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
