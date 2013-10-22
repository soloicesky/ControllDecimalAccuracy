package com.soloicesky.controlldecimalaccuracy;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;

/**
 * 
 * @filname MainActivity.java
 * @description test controll decimal accuracy
 * @author soloicesky
 * @data 2013-10-22 @time ÏÂÎç1:25:54
 */

public class MainActivity extends Activity {

	private EditText etCurracy = null;
	private EditText etDecimal = null;
	private int accuracy = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etCurracy = (EditText) findViewById(R.id.curracy);
		etDecimal = (EditText) findViewById(R.id.decimal);

		etCurracy.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (s.length() <= 0) {
					accuracy = 0;
				} else {
					accuracy = Integer.parseInt(s.toString(), 10);
				}

				etDecimal.setText(etDecimal.getText());
			}
		});

		etDecimal.addTextChangedListener(new TextWatcher() {
			private boolean validChange = true;
			private StringBuffer amt;
			private int tempLen = 0;

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

				if (validChange) {
					validChange = false;

					amt = new StringBuffer(s);

					System.out.println("amt length = " + amt.length());
					System.out.println("accuracy = " + accuracy);

					tempLen = amt.length();

					if (tempLen <= accuracy && accuracy != 0) {

						for (int i = 0; i < (accuracy - tempLen + 2); i++) {

							if ((i + tempLen) == (accuracy)) {
								amt.insert(0, '.');
							} else {
								amt.insert(0, '0');
							}

							System.out.println("i= " + i);
							System.out.println(amt);
						}
					}

					if (amt.charAt(amt.length() - (accuracy + 1)) != 0) {

						if (amt.indexOf(".") > 0) {
							amt.replace(amt.indexOf("."), amt.length(),
									amt.substring(amt.indexOf(".") + 1));

						}

						if (accuracy != 0) {
							amt.insert(amt.length() - accuracy, '.');
						}

					}

					while (amt.charAt(0) == '0') {
						amt.delete(0, 1);
					}

					if (amt.length() < accuracy + 1 + 1 && accuracy != 0) {
						amt.insert(0, '0');
					}

					etDecimal.setText(amt);
				}

				if (s.length() > 0) {
					Selection.setSelection(s, s.length());
				}

				validChange = true;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		this.finish();
		super.onPause();
	}

	
}
