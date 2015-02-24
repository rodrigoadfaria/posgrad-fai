package br.livro.android.cap17.http.carros;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.livro.android.cap17.http.R;

/**
 * Adapter customizado que exibe o layoyt definido em carro_row.xml
 * 
 * As imagens são exibidas no widget ImageView
 * 
 * @author ricardo
 * 
 */
public class CarroAdapter extends BaseAdapter {
	private Context context;
	private List<Carro> carros;

	public CarroAdapter(Context context, List<Carro> carros) {
		this.context = context;
		this.carros = carros;
	}

	public int getCount() {
		return carros != null ? carros.size() : 0;
	}

	public Object getItem(int position) {
		return carros.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// Recupera o Carro da posição atual
		Carro carro = carros.get(position);

		// Layout XML
		int arquivoLayout = R.layout.detalhes_carro;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(arquivoLayout, null);

		// Atualiza o valor do Text para o nome do Carro
		TextView textNome = (TextView) v.findViewById(R.id.nome);
		textNome.setText(carro.getNome());

		ImageView imagem = (ImageView) v.findViewById(R.id.img);

		// Atualiza a imagem para a imagem do Carro
		Bitmap bitmap = carro.getBitmap();
		imagem.setImageBitmap(bitmap);

		return v;
	}
}
