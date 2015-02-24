package com.example.aula02exemplo03.brasileirao;

import com.example.aula02exemplo03.R;

public class Clube {
	public static final int CRUZEIRO 	= 1;
	public static final int INTERNACIONAL 	= 2;
	public static final int SAO_PAULO 	= 3;
	public static final int GREMIO 	= 4;

	public String nome;
	private final int tipo;

	public Clube(String nome,  int tipo) {
		this.nome = nome;
		this.tipo = tipo;
	}

	/**
	 * Retorna a imagem com o escudo do Clube.
	 * 
	 * As imagens foram inseridas no /res/drawable-mdpi
	 * 
	 * @return
	 */
	public int getImagem() {
		switch (tipo) {
			case CRUZEIRO:
				return R.drawable.cruzeiro;
			case INTERNACIONAL:
				return R.drawable.internacional;
			case SAO_PAULO:
				return R.drawable.sao_paulo;
			case GREMIO:
				return R.drawable.gremio;

		}

		return R.drawable.not_found;
	}
	
	@Override
	public String toString() {
		return nome;
	}
}
