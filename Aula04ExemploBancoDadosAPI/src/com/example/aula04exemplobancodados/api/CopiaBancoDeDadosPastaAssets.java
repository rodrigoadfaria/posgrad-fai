package com.example.aula04exemplobancodados.api;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.example.aula04exemplobancodados.RepositorioCarro;
import com.example.aula04exemplobancodados.utils.IOUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Toast;

/**
 * Copia o banco de dados da pasta assets para a pasta
 * /data/data/pacote_da_applicacao/databases
 * 
 */
public class CopiaBancoDeDadosPastaAssets extends RepositorioCarro {

	private static final String TAG = "posgrad-fai";
	
	// Pasta onde o banco de dados é salvo. Contém o nome do pacote da aplicação.
	@SuppressLint("SdCardPath")
	private static String DB_PATH = "/data/data/com.example.aula04exemplobancodados.api/databases/";

	private static String NOME_BANCO = "banco_carros";

	private final Context context;

	public CopiaBancoDeDadosPastaAssets(Context context) {
		this.context = context;
	}

	/**
	 * Cria o banco de dados se precisar. Se ele não existir, o banco da pasta assets é copiado para a aplicação
	 */
	public void criarBancoDeDados() {

		try {
			boolean existe = verificaSeBancoDeDadosExiste();

			if (existe) {
				// Banco de dados existe, então não fazemos nada
			} else {

				// Força a criação do banco de dados padrão, para depois fazermos a cópia e sobrescrever
				// Ao chamar isso o método onCreate(db) será chamado, mas não vamos implementar nada lá.
				SQLiteDatabase db = context.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
				db.close();

				// Copia o banco da pasta /assets para a pasta da aplicação em /data/data/pacote_da_applicacao/databases
				copiaBancoDeDados();
			}
		} catch (Exception e) {
			Log.e(TAG,"Erro ao copiar: " + e.getMessage(), e);
		}
	}

	/**
	 * Verifica se o banco de dados já foi criado.
	 */
	private boolean verificaSeBancoDeDadosExiste() {
		SQLiteDatabase db = null;
		try {
			// RepositorioCarroAssets
			String path = DB_PATH + NOME_BANCO;
			db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
		} catch (SQLiteException e) {
			// Opa, se deu erro é porque ainda não existe
		}

		if (db != null) {
			db.close();
		}

		boolean existe = db != null ? true : false;
		return existe;
	}

	/**
	 * Copia o arquivo /assets/banco_carros para a pasta interna do banco de dados da aplicação
	 * */
	private void copiaBancoDeDados() throws IOException {

		// Abre o arquivo da pasta /assets para ler e copiar para a aplicação
		InputStream in = context.getAssets().open(NOME_BANCO);

		String arquivo = DB_PATH + NOME_BANCO;

		// Cria a OutputStream para escrever 
		OutputStream out = new FileOutputStream(arquivo);

		// Lê a inputstream
		byte[] bytes = IOUtils.toBytes(in);
		
		// Escreve na OutputStream (para criar o arquivo)
		out.write(bytes);
		out.flush();
		out.close();
		
		Toast.makeText(context, "Banco copiado com sucesso", Toast.LENGTH_SHORT).show();
	}

	public SQLiteDatabase getDatabase() {
		SQLiteDatabase db = context.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);		
		return db;
	}
}
