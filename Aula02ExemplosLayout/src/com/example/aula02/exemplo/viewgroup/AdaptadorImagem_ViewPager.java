package com.example.aula02.exemplo.viewgroup;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Adaptador de imagem para os exemplos de GridView, Gallery e ImageSwitcher
 * 
 */
public class AdaptadorImagem_ViewPager extends PagerAdapter {
	private Context ctx;
	private final int[] imagens;
	
	public AdaptadorImagem_ViewPager(Context c, int[] imagens) {
		this.ctx = c;
		this.imagens = imagens;
	}
	@Override
	public int getCount() {
		return imagens.length;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		// Determina se a view informada é igual ao object retornado pelo instantiateItem
		return view == ((MeuObjeto) object).view;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		ImageView img = new ImageView(ctx);
		img.setImageResource(imagens[position]);
		img.setAdjustViewBounds(true);

		// Adiciona no layout ViewGroup
		((ViewGroup) container).addView(img);

		MeuObjeto obj = new MeuObjeto();
		obj.view = img;
		return obj;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object view) {
		// Remove do layout
		((ViewPager) container).removeView((View) view);
		super.destroyItem(container, position, view);
	}
	
	// Apenas para demosntrar que você pode retornar um Objeto qualquer que contém a view
	class MeuObjeto {
		View view;
	}
}