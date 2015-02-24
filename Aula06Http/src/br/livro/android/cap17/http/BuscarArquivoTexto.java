package br.livro.android.cap17.http;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import br.livro.android.cap17.http.helper.Http;

/**
 * Faz download de um arquivo .txt do servidor
 * 
 * @author ricardo
 *
 */
public class BuscarArquivoTexto extends Activity implements OnClickListener,Runnable {
	protected static final String CATEGORIA = "livro";
	protected static final String URL = "http://www.livroandroid.com.br/arquivo.txt";
	//Handler utilizado para atualizar a View
	private Handler handler = new Handler();
	private ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.arquivo);

		Button b = (Button) findViewById(R.id.btDownload);
		b.setOnClickListener(this);
	}
	public void onClick(View view) {
		dialog = ProgressDialog.show(this,"Exemplo", "Buscando texto, por favor aguarde...", false,true);

		//faz download em uma Thread
		new Thread(this).start();
	}
	public void run() {
		try {
			// faz o download
			final String arquivo = Http.getInstance(Http.NORMAL).downloadArquivo(URL);

			Log.i(CATEGORIA,"Texto retornado: " + arquivo);

			//Precisa do Handler para atualizar a view de outra thread
			handler.post(new Runnable() {
				public void run() {
					TextView text = (TextView) findViewById(R.id.texto);
					text.setText(arquivo);
				}
			});
		} catch (Throwable e) {
			Log.i(CATEGORIA, e.getMessage(),e);
		} finally {
			dialog.dismiss();
		}
	}
}