package jp.stakaya.urlschemetester;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	private Button button;
	private EditText editText;
	private WebView webview;
	private SharedPreferences preference;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		preference = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		webview = (WebView) findViewById(R.id.webView1);
		editText = (EditText) findViewById(R.id.editText1);
		button = (Button) findViewById(R.id.button1);
		
		editText.setText(preference.getString("text", ""));
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String text = editText.getText().toString();
				Editor editor = preference.edit();
				editor.putString("text", text);
				editor.commit();

				String html = "<!DOCTYPE html><html lang=\"ja\"><head><meta charset=\"UTF-8\" /><title></title></head><body>"
							+ "<div align=\"center\"><a href=\"" + text + "\">>>>URLスキーマ実行<<<<</a></div>"
							+ "</body></html>";
				webview.loadDataWithBaseURL("file:///android_asset/", html, "text/html", "utf-8", null);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
