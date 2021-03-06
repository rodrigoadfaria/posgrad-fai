package com.example.aula04.arquivos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.example.aula04.arquivos.utils.SDCardUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Demonstra��o de como ler e gravar em arquivos
 * 
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 * 
 */
public class ExemploSalvarArquivoSdCard extends Activity {

	private static final String DIR = "posgrad-fai-dir";
	private static final String ARQUIVO = "arquivo.txt";
	private static final String TAG = "posgrad-fai";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.form_arquivo);

		// SALVAR
		Button b = (Button) findViewById(R.id.btSalvar);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				salvar();
			}
		});

		// DELETAR
		b = (Button) findViewById(R.id.btDeletar);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				deletar();
			}
		});

		visualizarArquivo();
	}

	protected void deletar() {
		File f = SDCardUtils.getSdCardFile(DIR, ARQUIVO);
		boolean ok = f.delete();

		Log.i(TAG, "Arquivo deletado ? " + ok);
		visualizarArquivo();
	}

	protected void salvar() {
		try {
			// Cria o arquivo no sdcard, e abre uma OutputStream
			File f = SDCardUtils.getSdCardFile(DIR, ARQUIVO);
			FileOutputStream out = new FileOutputStream(f, true);

			EditText texto = (EditText) findViewById(R.id.texto);

			String msg = texto.getText().toString();

			out.write("\n".getBytes());
			out.write(msg.getBytes());
			out.close();

			Log.i(TAG, msg + " - escrito com sucessso");

			visualizarArquivo();

		} catch (FileNotFoundException e) {
			Log.e(TAG, e.getMessage(), e);
		} catch (IOException e) {
			Log.e(TAG, e.getMessage(), e);
		}
	}

	private void visualizarArquivo() {
		TextView text = (TextView) findViewById(R.id.arquivo);
		try {
			File f = SDCardUtils.getSdCardFile(DIR, ARQUIVO);

			Log.i(TAG, "Abrindo arquivo: " + f.getAbsolutePath());

			if (f.exists()) {
				FileInputStream in = new FileInputStream(f);
				int tamanho = in.available();
				byte bytes[] = new byte[tamanho];
				in.read(bytes);
				String s = new String(bytes);
				text.setText(s);
				in.close();
			} else {
				Log.i(TAG, "Arquivo n�o existe ou exclu�do");
				text.setText("");
			}
		} catch (FileNotFoundException e) {
			Log.e(TAG, "Arquivo n�o encontrado: " + e.getMessage(), e);
		} catch (IOException e) {
			Log.e(TAG, e.getMessage(), e);
		}

	}
}