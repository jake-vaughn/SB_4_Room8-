package edu.iastate.room8;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.*;

import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import edu.iastate.room8.Home.HomeActivity;
import edu.iastate.room8.List.SubtaskActivity;
import edu.iastate.room8.Schedule.DayActivity;
import edu.iastate.room8.Schedule.ScheduleMVP.ScheduleActivity;
import edu.iastate.room8.Settings.RoomSettings.RoomSettingsActivity;
import edu.iastate.room8.Schedule.ScheduleMVP.DateParser;
import edu.iastate.room8.Settings.UserSettings.UserSettingsActivity;
import edu.iastate.room8.utils.Sessions.SessionManager;


/**
 * PaulMockitoTest
 * Tests using Mockito for the project
 * @author pndegnan
 */
public class JakeMockitoTest {
    @Mock
    Context mockContext;
    @Mock
    SharedPreferences mockPrefs;
    @Mock
    SharedPreferences.Editor mockEditor;

    private SessionManager sessionManager;

    @Before
    public void before() {
        MockSharedPreference mockSharedPrefs;
        //MockSharedPreference.Editor mockPrefsEditor;

        mockSharedPrefs = new MockSharedPreference();
        //mockPrefsEditor = mockSharedPrefs.edit();

        Mockito.when(mockContext.getSharedPreferences(anyString(), anyInt())).thenReturn(mockSharedPrefs);

        sessionManager = new SessionManager(mockContext);
    }
    /**
     * Used so Mockito can be used in JUNIT tests
     */
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    /**
     * This is a test to confirm the functionality of the UserSettingsActivity.
     */
    @Test
    public void UserSettingsActivityTest()  {
        // create and configure mock
        UserSettingsActivity test = Mockito.mock(UserSettingsActivity.class);

        JSONObject JSONRequest = new JSONObject();

        when(test.jsonNameRequest()).thenReturn(JSONRequest);

        try {
            JSONRequest.put("ID", "4");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONRequest.put("Name", "TestName");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONRequest.put("Email", "TestEmail");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONRequest.put("Password", "123Test");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            Assert.assertEquals(JSONRequest.getString("ID"), test.jsonNameRequest().getString("ID"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            Assert.assertEquals(JSONRequest.getString("Name"), test.jsonNameRequest().getString("Name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            Assert.assertEquals(JSONRequest.getString("Email"), test.jsonNameRequest().getString("Email"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            Assert.assertEquals(JSONRequest.getString("Password"), test.jsonNameRequest().getString("Password"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * This is a test to confirm the functionality of the UserSettingsActivity.
     */
    @Test
    public void UserSettingsSession()  {
        // create and configure mock
        UserSettingsActivity test = Mockito.mock(UserSettingsActivity.class);
        test.sessionManager.createSession("Jack", "Jack@email.com", "35");

        Assert.assertEquals(test.sessionManager.getID(), "35");
        Assert.assertEquals(test.sessionManager.getName(), "Jack");
        Assert.assertEquals(test.sessionManager.getEmail(), "Jack@email.com");

    }
}
