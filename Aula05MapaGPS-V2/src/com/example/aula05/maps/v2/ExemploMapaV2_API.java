package com.example.aula05.maps.v2;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.google.android.gms.maps.MapFragment;

/**
 * Exemplo de MapFragment por API
 * 
 */
public class ExemploMapaV2_API extends Activity {
	
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.exemplo_mapa_v2_api);

		// Este exemplo demonstra como adicionar o fragment com o mapa
		// dinamicamente ao layout.
		// Neste caso, não há nenhuma animação (tilt, zoom, bearing)
		MapFragment map = new MapFragment();
		FragmentTransaction t = getFragmentManager().beginTransaction();
		t.add(R.id.layoutMap, map, null);
		t.commit();
	}
}