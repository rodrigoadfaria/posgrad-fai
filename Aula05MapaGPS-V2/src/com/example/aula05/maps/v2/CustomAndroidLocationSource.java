package com.example.aula05.maps.v2;

import android.location.Location;
import android.location.LocationManager;

import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.model.LatLng;

/**
 * Classe para determinar o local onde o dispositivo est�.
 * Implementa {@link LocationSource} que � uma camada visual que 
 * fica sobre o mapa.
 * Quando ela est� ativa, o Android exibe uma "bolinha azul" que
 * identifica o local e, tamb�m, um bot�o que fica no canto superior direito
 * do mapa que permite o usu�rio interagir com essa Layer e centralizar
 * o mapar no local onde est� dispositivo.
 * 
 *
 */
public class CustomAndroidLocationSource implements LocationSource {
	
	private OnLocationChangedListener listener;
	
	@Override
	public void activate(OnLocationChangedListener listener) {
		this.listener = listener;
	}
	
	@Override
	public void deactivate() {
		this.listener = null;
	}
	
	public void setLocation(Location location) {
		if(this.listener != null) {
			this.listener.onLocationChanged(location);
		}
	}
	
	public void setLocation(LatLng latLng) {
		Location location = new Location(LocationManager.GPS_PROVIDER);
		location.setLatitude(latLng.latitude);
		location.setLongitude(latLng.longitude);
		if(this.listener != null) {
			this.listener.onLocationChanged(location);
		}
	}
}