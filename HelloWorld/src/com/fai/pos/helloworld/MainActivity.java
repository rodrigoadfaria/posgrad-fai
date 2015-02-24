package com.fai.pos.helloworld;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity  implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("POS_FAI", "Começando o onCreate");
		setContentView(R.layout.activity_main);
		
		Button btn = (Button)findViewById(R.id.button_pos_android);
		btn.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View view) {
		if (view.getId() == R.id.button_pos_android) {
			Toast.makeText(this, "Alguém clicou no botão", Toast.LENGTH_LONG).show();
		}
		
	}

}
