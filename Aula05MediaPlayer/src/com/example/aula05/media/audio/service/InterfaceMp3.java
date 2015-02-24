package com.example.aula05.media.audio.service;

/**
 * Interface para fazer o bind do Service MediaPlayerService que a implementa
 * 
 */
public interface InterfaceMp3 {
	public void start(String mp3);
	public void pause();
	public void stop();
}
