package com.example.aula04exemplo.provider;

import com.example.aula04exemplobancodados.Carro.Carros;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * ListActivity que utiliza o Cursor recuperado do Content Provider 'CarroProvider"
 * 
 * Exemplo de ListActivity que utiliza o SimpleCursorAdapter
 * para enviar os dados do Cursor de carros para uma view 'nativa' do Android
 * 
 */
@SuppressWarnings("deprecation")
public class ListarCarros extends ListActivity {
	
	private ListAdapter mAdapter;

	private static final String TAG = "posgrad-fai";

	@Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        Log.i(TAG,"Uri carros: " + Carros.CONTENT_URI);

        //Recupera o cursor dos carros
        Cursor c = getContentResolver().query(Carros.CONTENT_URI, null, null, null, null);
        startManagingCursor(c);

        //Listar o nome e telefone do contato
        String[] columns = new String[]{Carros.NOME,Carros.PLACA};

        int[] to = new int[] { android.R.id.text1, android.R.id.text2 };

        //layout nativo para simplificar
        int layoutNativo = android.R.layout.simple_list_item_2;

        //Informa o adapter para ligar os valores ao XML da View
		mAdapter = new SimpleCursorAdapter(this, layoutNativo, c, columns, to);

        setListAdapter(mAdapter);
    }

    /**
     * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
     */
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        //recupera o cursor na posição selecionada
        Cursor c = (Cursor) mAdapter.getItem(position);

        //recupera o Nome e Telefone
        String nome 	= c.getString(c.getColumnIndexOrThrow(Carros.NOME));
        String placa 	= c.getString(c.getColumnIndexOrThrow(Carros.PLACA));
        int ano  		= c.getInt(c.getColumnIndexOrThrow(Carros.ANO));

        Toast.makeText(this, "Carro selecionado: Nome: " + nome + ", Placa: " + placa+ ", Ano: " + ano, Toast.LENGTH_SHORT).show();
    }
}
