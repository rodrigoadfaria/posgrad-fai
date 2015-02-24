package com.example.aula02.exemplo.viewgroup;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Adaptador de imagem para os exemplos de GridView, Gallery e ImageSwitcher
 * 
 */
public class AdaptadorImagem extends BaseAdapter {
	
	private Context ctx;
	private final int[] imagens;
	
	public AdaptadorImagem(Context c, int[] imagens) {
		this.ctx = c;
		this.imagens = imagens;
	}
	
	public int getCount() {
		return imagens.length;
	}
	
	public Object getItem(int posicao) {
		return posicao;
	}
	
	public long getItemId(int posicao) {
		return posicao;
	}
	
	public View getView(int posicao, View convertView, ViewGroup parent) {
		ImageView img = new ImageView(ctx);
		img.setImageResource(imagens[posicao]);
		img.setAdjustViewBounds(true);
		return img;
	}
}