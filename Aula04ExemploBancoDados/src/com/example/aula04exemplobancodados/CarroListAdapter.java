package com.example.aula04exemplobancodados;

import java.util.List;

import com.example.aula04exemplobancodados.model.Carro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Adapter customizado que exibe o layout definido em carro_linha_tabela.xml
 * 
 */
public class CarroListAdapter extends BaseAdapter {
	private List<Carro> lista;
	private LayoutInflater inflater;

	public CarroListAdapter(Context context, List<Carro> lista) {
		this.lista = lista;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		return lista.size();
	}

	public Object getItem(int position) {
		return lista.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// Recupera o Carro da posição atual
		Carro c = lista.get(position);

		View view = inflater.inflate(R.layout.carro_linha_tabela, null);

		// Atualiza o valor do TextView
		TextView nome = (TextView) view.findViewById(R.id.nome);
		nome.setText(c.getNome());

		TextView placa = (TextView) view.findViewById(R.id.placa);
		placa.setText(c.getPlaca());

		TextView ano = (TextView) view.findViewById(R.id.ano);
		ano.setText(String.valueOf(c.getAno()));

		return view;
	}
}