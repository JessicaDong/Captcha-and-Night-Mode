package com.example.check;

import com.example.check.NightModeUtils;
import android.nfc.tech.MifareClassic;
import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity {
	private CheckView mMyView = null;
	private char[] res = new char[4]; // 获取每次更新的验证码，可用于判断用户输入是否正确
	private Button button;
	private TextView checkvalue;
	private CheckView checkView;
	private TextView mNightView;
	private WindowManager mWindowManager;
	private Button button2;
	private Button button3;
	private Button button4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		NightModeUtils.onActivityCreateSetTheme(this);
		int temp = NightModeUtils.getDayNightMode(MainActivity.this);
		Log.i("temp", temp + "");
		setContentView(R.layout.activity_main);
		//
		mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

		mMyView = (CheckView) findViewById(R.id.checkView);
		// 初始化验证码
		res = mMyView.getValidataAndSetImage();
		mMyView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				res = mMyView.getValidataAndSetImage();
			}
		});
		button = (Button) findViewById(R.id.btn);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				putdialog();
			}
		});
		button2 = (Button) findViewById(R.id.night);
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				night();
			}
		});
		button3 = (Button) findViewById(R.id.themebtn);
		button3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				int theme = NightModeUtils.getDayNightMode(MainActivity.this);
				Context context = getApplicationContext();
				if (theme == NightModeUtils.THEME_SUN)
					NightModeUtils.setDayNightMode(context,
							NightModeUtils.THEME_NIGHT);
				else
					NightModeUtils.setDayNightMode(context,
							NightModeUtils.THEME_SUN);
				// 注意改过主题后一定要，把activity finish在重开一遍，因为更改主题智能在oncreat中进行
				MainActivity.this.finish();
				MainActivity.this.startActivity(new Intent(MainActivity.this, MainActivity.this.getClass()));
			}
		});
		button4 = (Button) findViewById(R.id.next);
		button4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, TwoActivity.class);
				startActivity(intent);
			}
		});
	}
	private void putdialog() {
		LayoutInflater inflater = LayoutInflater.from(this);
		View layout = inflater.inflate(R.layout.check_dialog, null);
		checkvalue = (EditText) layout.findViewById(R.id.captcha_value2);
		checkView = (CheckView) layout.findViewById(R.id.checkview2);
		res = checkView.getValidataAndSetImage();
		checkView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				res = checkView.getValidataAndSetImage();
			}
		});
		AlertDialog.Builder builder = new Builder(MainActivity.this);
		builder.setView(layout);
		builder.setTitle("请输入验证码");
		
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int arg1) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int arg1) {
				// TODO Auto-generated method stub
				String scheck = new String(res);
				String string = checkvalue.getText().toString();
				boolean b = string.equals(scheck);
				if (b) {
					Toast.makeText(MainActivity.this, "验证码输入正确",
							Toast.LENGTH_SHORT).show();
					dialog.dismiss();
				} else {
					Toast.makeText(MainActivity.this, "验证码输入有误，请重新输入",
							Toast.LENGTH_SHORT).show();
					putdialog();
				}

			}
		});
		builder.create().show();
		// dialog.show();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	private void night() {
		if (mNightView == null) {
			mNightView = new TextView(this);
			mNightView.setBackgroundColor(0xaa000000);
		}

		WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,
				WindowManager.LayoutParams.TYPE_APPLICATION,
				WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
						| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
				PixelFormat.TRANSLUCENT);
		lp.gravity = Gravity.BOTTOM;
		lp.y = 10;

		try {
			mWindowManager.addView(mNightView, lp);
		} catch (Exception ex) {
		}
	}

}
