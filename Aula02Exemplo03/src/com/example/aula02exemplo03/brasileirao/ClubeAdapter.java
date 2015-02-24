package com.example.aula02exemplo03.brasileirao;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aula02exemplo03.R;

/**
 * Adaptador customizado que exibe o layout definido em clube_list_item.xml
 * 
 * As imagens são exibidas utilizando um ImageView
 * 
 */
public class ClubeAdapter extends BaseAdapter {
	
	protected static final String TAG = "posgrad-fai";
	
	private Context context;
	private List<Clube> lista;
	
	public ClubeAdapter(Context context, List<Clube> lista) {
		this.context = context;
		this.lista = lista;
	}
	
	public int getCount() {
		return lista.size();
	}
	
	public Object getItem(int posicao) {
		Clube clube = lista.get(posicao);
		Log.i(TAG, "ClubeAdapter.getItem("+posicao+") > " + clube);
		return clube;
	}
	
	public long getItemId(int posicao) {
		Log.i(TAG, "ClubeAdapter.getItemId("+posicao+") > " + posicao);
		return posicao;
	}
	
	public View getView(int posicao, View convertView, ViewGroup parent) {
		Log.i(TAG, "ClubeAdapter.getView("+posicao+")");

		// Recupera o Clube da posição atual
		Clube clube = lista.get(posicao);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.clube_list_item, null);

		// Atualiza o valor do Text para o nome do Clube
		TextView textNome = (TextView) v.findViewById(R.id.nome);
		textNome.setText(clube.nome);

		// Atualiza a imagem para a imagem do Clube
		// A imagem é definda por um recurso no @drawable
		ImageView img = (ImageView) v.findViewById(R.id.img);
		img.setImageResource(clube.getImagem());
		return v;
	}
}
