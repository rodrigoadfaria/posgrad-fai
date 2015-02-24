package com.example.aula05.media.video;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Simples teste do VideoView
 * 
 */
public class ExemploVideoView extends Activity {
	
	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);

		VideoView v = new VideoView(this);
		setContentView(v);

		File sdcardDir = android.os.Environment.getExternalStorageDirectory();
		File file = new File(sdcardDir, "gglass.mp4");
		// "/mnt/sdcard/gglass.mp4"
		String path = file.getAbsolutePath();
		v.setVideoPath(path);
		v.setMediaController(new MediaController(this));
		v.requestFocus();
	}
}
