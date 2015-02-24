package com.example.aula04exemplo.provider.provider.api.copy;

import com.example.aula04exemplo.provider.R;
import com.example.aula04exemplobancodados.Carro;
import com.example.aula04exemplobancodados.Carro.Carros;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

/**
 * Activity que utiliza o TableLayout para editar o carro
 * 
 */
public class EditarCarro extends Activity {
	static final int RESULT_SALVAR = 1;
	static final int RESULT_EXCLUIR = 2;

	// Campos texto
	private EditText campoNome;
	private EditText campoPlaca;
	private EditText campoAno;
	private Long id;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.form_editar_carro);

		campoNome = (EditText) findViewById(R.id.campoNome);
		campoPlaca = (EditText) findViewById(R.id.campoPlaca);
		campoAno = (EditText) findViewById(R.id.campoAno);

		id = null;

		Bundle extras = getIntent().getExtras();
		// Se for para Editar, recuperar os valores ...
		if (extras != null) {
			id = extras.getLong(Carros._ID);

			if (id != null) {
				// � uma edi��o, busca o carro...
				Carro c = buscarCarro(id);
				campoNome.setText(c.nome);
				campoPlaca.setText(c.placa);
				campoAno.setText(String.valueOf(c.ano));
			}
		}

		View btCancelar = findViewById(R.id.btCancelar);
		btCancelar.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				setResult(RESULT_CANCELED);
				// Fecha a tela
				finish();
			}
		});

		// Listener para salvar o carro
		View btSalvar = findViewById(R.id.btSalvar);
		btSalvar.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				salvar();
			}
		});

		View btExcluir = findViewById(R.id.btExcluir);

		if (id == null) {
			// Se id est� nulo, n�o pode excluir
			btExcluir.setVisibility(View.INVISIBLE);
		} else {
			// Listener para excluir o carro
			btExcluir.setOnClickListener(new OnClickListener() {
				public void onClick(View view) {
					excluir();
				}
			});
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		// Cancela para n�o ficar nada na tela pendente
		setResult(RESULT_CANCELED);

		// Fecha a tela
		finish();
	}

	public void salvar() {

		int ano = 0;
		try {
			ano = Integer.parseInt(campoAno.getText().toString());
		} catch (NumberFormatException e) {
			// ok neste exemplo, tratar isto em aplica��es reais
		}

		Carro carro = new Carro();
		if (id != null) {
			// � uma atualiza��o
			carro.id = id;
		}
		carro.nome = campoNome.getText().toString();
		carro.placa = campoPlaca.getText().toString();
		carro.ano = ano;

		// Salvar
		salvarCarro(carro);

		// OK
		setResult(RESULT_OK, new Intent());

		// Fecha a tela
		finish();
	}

	public void excluir() {
		if (id != null) {
			excluirCarro(id);
		}

		// OK
		setResult(RESULT_OK, new Intent());

		// Fecha a tela
		finish();
	}

	// Buscar o carro pelo id
	protected Carro buscarCarro(long id) {
		return CadastroCarros.repositorio.buscarCarro(id);
	}

	// Salvar o carro
	protected void salvarCarro(Carro carro) {
		CadastroCarros.repositorio.salvar(carro);
	}

	// Excluir o carro
	protected void excluirCarro(long id) {
		CadastroCarros.repositorio.deletar(id);
	}
}
