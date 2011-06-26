/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings;

import java.util.ArrayList;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.provider.Settings;
import android.util.Log;

public class InterfaceSettings extends PreferenceActivity implements
	Preference.OnPreferenceChangeListener {
		
    private static final String TAG = "InterfaceSettings";
	
	private static final String MUSIC_CONTROLS = "lockscreen_music_controls";
	private static final String PERSISTENT_MUSIC_CONTROLS = "lockscreen_always_music_controls";
	private static final String TRACKBALL_WAKE = "trackball_wake_screen";
	private static final String EXP_WIDGET = "expanded_power_widget";
	private static final String EXP_HIDE_ONCHANGE = "expanded_hide_onchange";
	private static final String POWER_PICKER = "power_picker";
	private static final String POWER_ORDER = "widget_order";

	private CheckBoxPreference mMusicControls;
	private CheckBoxPreference mPersistentMusicControls;
	private CheckBoxPreference mTrackballWake;
	private CheckBoxPreference mExpWidget;
	private CheckBoxPreference mExpHideOnchange;
	private PreferenceScreen mPowerPicker;
	private PreferenceScreen mPowerOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContentResolver resolver = getContentResolver();

        addPreferencesFromResource(R.xml.interface_settings);

		mMusicControls = (CheckBoxPreference)findPreference(MUSIC_CONTROLS);
		mMusicControls.setChecked(Settings.System.getInt(resolver,
								  Settings.System.LOCKSCREEN_MUSIC_CONTROLS, 1) == 1);
		mPersistentMusicControls = (CheckBoxPreference)findPreference(PERSISTENT_MUSIC_CONTROLS);
		mPersistentMusicControls.setChecked(Settings.System.getInt(resolver,
											Settings.System.LOCKSCREEN_ALWAYS_MUSIC_CONTROLS, 1) == 1);
		
		mTrackballWake = (CheckBoxPreference)findPreference(TRACKBALL_WAKE);
		mTrackballWake.setChecked(Settings.System.getInt(resolver,
								  Settings.System.TRACKBALL_WAKE_SCREEN, 1) == 1);

		mExpWidget = (CheckBoxPreference)findPreference(EXP_WIDGET);
		mExpWidget.setChecked(Settings.System.getInt(resolver,
							  Settings.System.EXPANDED_VIEW_WIDGET, 1) == 1);
		
		mExpHideOnchange = (CheckBoxPreference)findPreference(EXP_HIDE_ONCHANGE);
		mExpHideOnchange.setChecked(Settings.System.getInt(resolver,
									Settings.System.EXPANDED_HIDE_ONCHANGE, 0) == 1);
		
		mPowerPicker = (PreferenceScreen)findPreference(POWER_PICKER);
		mPowerOrder = (PreferenceScreen)findPreference(POWER_ORDER);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
	
    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
		if (preference == mMusicControls) {
			Settings.System.putInt(getContentResolver(),
					Settings.System.LOCKSCREEN_MUSIC_CONTROLS,
					mMusicControls.isChecked() ? 1 : 0);
        } else if (preference == mPersistentMusicControls) {
			Settings.System.putInt(getContentResolver(),
					Settings.System.LOCKSCREEN_ALWAYS_MUSIC_CONTROLS,
					mPersistentMusicControls.isChecked() ? 1 : 0);
        } else if (preference == mTrackballWake) {
			Settings.System.putInt(getContentResolver(),
					Settings.System.TRACKBALL_WAKE_SCREEN,
					mTrackballWake.isChecked() ? 1 : 0);
        } else if (preference == mExpWidget) {
			Settings.System.putInt(getContentResolver(),
					Settings.System.EXPANDED_VIEW_WIDGET,
					mExpWidget.isChecked() ? 1 : 0);
        } else if (preference == mExpHideOnchange) {
			Settings.System.putInt(getContentResolver(),
					Settings.System.EXPANDED_HIDE_ONCHANGE,
					mExpHideOnchange.isChecked() ? 1 : 0);
        } else if (preference == mPowerPicker) {
			startActivity(mPowerPicker.getIntent());
		} else if (preference == mPowerOrder) {
			startActivity(mPowerOrder.getIntent());
		}
		
        return true;
    }
	
	@Override
	public boolean onPreferenceChange(Preference preference, Object objValue) {
		final String key = preference.getKey();
	return true;
	}
}
