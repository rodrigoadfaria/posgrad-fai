package com.example.aula04exemplobancodados;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;

import com.example.aula04exemplobancodados.dao.CarrosDAO;

public class CadastroCarrosActivity extends ListActivity {

	CarrosDAO dao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		dao = new CarrosDAO(this);
		setListAdapter(new CarroListAdapter(this, dao.readAll()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastro_carros, menu);
		return true;
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		dao.fechar();
	}

}
