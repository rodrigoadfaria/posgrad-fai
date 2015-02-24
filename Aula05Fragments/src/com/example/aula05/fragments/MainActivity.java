package com.example.aula05.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Esta � a main activity da app. Dependendo do tamanho da tela e da
 * orienta��o do dispositivo, ele mostra o titles panel e, se a largura da tela
 * permite, um outro panel com os detalhes � mostrado com o texto selecionado.
 */

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.fragment_layout);
    }

}
