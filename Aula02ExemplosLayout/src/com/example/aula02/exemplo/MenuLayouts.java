package com.example.aula02.exemplo;

import com.example.aula02.exemplo.viewgroup.ExemploScrollView;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuLayouts extends ListActivity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		String[] items = new String[] { 
				"FrameLayout background",
				"LinearLayout horizontal",
				"LinearLayout vertical",
				"LinearLayout Form (weight)",
				"TableLayout shrink",
				"TableLayout stretch",
				"RelativeLayout Form",
				"AbsoluteLayout Form",
				"ScrollView"};

		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		switch (position) {
			case 0:
				startActivity(R.layout.frame_layout_background);
				break;
			case 1:
				//LinearLayout (default)
				startActivity(R.layout.linear_layout_horizontal);
				break;
			case 2:
				//LinearLayout Vertical
				startActivity(R.layout.linear_layout_vertical);
				break;
			case 3:
				startActivity(R.layout.linear_layout_form);
				break;
			case 4:
				startActivity(R.layout.table_layout_shrink);
				break;
			case 5:
				startActivity(R.layout.table_layout_stretch);
				break;
			case 6:
				//Formulário
				startActivity(R.layout.relative_layout_form);
				break;
			case 7:
				//Formulário
				startActivity(R.layout.absolute_layout_form);
				break;
			case 8:
				startActivity(new Intent(this, ExemploScrollView.class));
				break;
		}
	}

	private void startActivity(int layoutId) {
		Intent intent = new Intent(this,ActivityLayoutIdGenerica.class);
		intent.putExtra("layoutResId", layoutId);
		startActivity(intent);
	}

}