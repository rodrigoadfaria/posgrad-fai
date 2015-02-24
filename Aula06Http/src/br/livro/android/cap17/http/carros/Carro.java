package br.livro.android.cap17.http.carros;

import java.io.DataInputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * @author ricardo
 *
 */
public class Carro {
	private static final String CATEGORIA = "livro";
	private Long id;
	private String nome;
	private byte[] imagem;
	private Bitmap bitmap;
	public void deserialize(DataInputStream in) throws IOException {
		id = in.readLong();
		nome = in.readUTF();
		Log.i(CATEGORIA,"Lendo carro id: " + id);
		//le os bytes da imagem
		int tamanho = in.readInt();
		imagem = new byte[tamanho];
		in.readFully(imagem);
		//cria o Bitmap para exibir no ImageView
		bitmap = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
	}
	public Bitmap getBitmap() {
		return bitmap;
	}
	public String getNome() {
		return nome;
	}
}