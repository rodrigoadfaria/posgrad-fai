package br.livro.android.cap17.http;

import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import br.livro.android.cap17.http.helper.Http;

/**
 * Download the imagem de um servidor
 * 
 * @author ricardo
 * 
 */
public class BuscarImagemWeb extends Activity implements OnClickListener,Runnable {
	private Handler handler = new Handler();
	private ProgressDialog dialog;
	protected static final String CATEGORIA = "livro";
	protected static final String URL = "http://livroandroid.com.br/imagens/livro02.png";
	protected static final int ATUALIZAR_TELA = 1;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.exemplo_imagem_web);
		Button b = (Button) findViewById(R.id.btDownload);
		b.setOnClickListener(this);
	}
	public void onClick(View v) {
		dialog = ProgressDialog.show(this,"Exemplo", "Buscando imagem, por favor aguarde...", false,true);
		//Faz download em outra Thread
		new Thread(this).start();
	}
	public void run() {
		try {
			final Bitmap imagem = download(URL);

			//Utiliza um Handler para atualizar a View em outra Thread
			handler.post(new Runnable() {
				public void run() {
					ImageView imgView = (ImageView) findViewById(R.id.imagem);
					imgView.setScaleType(ScaleType.CENTER);
					imgView.setImageBitmap(imagem);
				}
			});

		} catch (Throwable e) {
			Log.i(CATEGORIA, e.getMessage(), e);
		} finally {
			dialog.dismiss();
		}
	}
	//Faz o download da Imagem
	protected Bitmap download(String url) throws IOException {

//		URL u = new URL(url);
//		InputStream in = u.openStream();
//		Bitmap img = BitmapFactory.decodeStream(in);
//		in.close();

		byte[] bytes = Http.getInstance(Http.NORMAL).downloadImagem(url);
		Log.i(CATEGORIA, "Criando Bitmap com BitmapFactory " + bytes);
		Bitmap imagem = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
	
		// tela cheia
		// BitmapDrawable drawable = new BitmapDrawable(in);
		// imgView.setBackgroundDrawable(new BitmapDrawable(drawable));

		return imagem;
	}
}
