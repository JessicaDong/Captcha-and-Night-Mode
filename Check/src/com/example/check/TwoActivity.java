package com.example.check;

import android.app.Activity;
import android.os.Bundle;

public class TwoActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		NightModeUtils.onActivityCreateSetTheme(this);
		setContentView(R.layout.activity_two);
	}
}
