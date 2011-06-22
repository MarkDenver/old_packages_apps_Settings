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

public class InterfaceSettings extends PreferenceActivity {
		
    private static final String TAG = "InterfaceSettings";
	
	private static final String TRACKBALL_WAKE = "trackball_wake_screen";

	private CheckBoxPreference mTrackballWake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContentResolver resolver = getContentResolver();

        addPreferencesFromResource(R.xml.interface_settings);
		
		mTrackballWake = (CheckBoxPreference)findPreference(TRACKBALL_WAKE);
		mTrackballWake.setChecked(Settings.System.getInt(resolver,
								  Settings.System.TRACKBALL_WAKE_SCREEN, 1) == 1);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
	
    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
		if (preference == mTrackballWake) {
			Settings.System.putInt(getContentResolver(),
					Settings.System.TRACKBALL_WAKE_SCREEN,
					mTrackballWake.isChecked() ? 1 : 0);
		}
		
        return true;
    }
	
}
