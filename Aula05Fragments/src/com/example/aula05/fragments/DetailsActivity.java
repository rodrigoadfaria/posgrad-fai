package com.example.aula05.fragments;

import android.content.res.Configuration;
import android.os.Bundle;

import android.support.v4.app.FragmentActivity;

/**
 * Esta activity mostra os detalhes usando um DetailsFragment. Esta activity
 * é instanciada por um TitlesFragment quando um title é selecionado na lista.
 * A activity somente é utilizada se não houver um DetailsFragment na tela.
 */

public class DetailsActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {
            // Se a tela está em landscape mode, nós podemos
            // mostrar o fragment com o conteúdo na mesma tela
        	// e não precisaremos dessa activity
            finish();
            return;
        }

        if (savedInstanceState == null) {
            // Se é a primeira vez que a activity é criada,
        	// insere o fragment na tela
            DetailsFragment details = new DetailsFragment();
            details.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, details).commit();
        }
    }
}



