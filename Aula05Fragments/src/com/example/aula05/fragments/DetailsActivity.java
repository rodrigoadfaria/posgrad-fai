package com.example.aula05.fragments;

import android.content.res.Configuration;
import android.os.Bundle;

import android.support.v4.app.FragmentActivity;

/**
 * Esta activity mostra os detalhes usando um DetailsFragment. Esta activity
 * � instanciada por um TitlesFragment quando um title � selecionado na lista.
 * A activity somente � utilizada se n�o houver um DetailsFragment na tela.
 */

public class DetailsActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {
            // Se a tela est� em landscape mode, n�s podemos
            // mostrar o fragment com o conte�do na mesma tela
        	// e n�o precisaremos dessa activity
            finish();
            return;
        }

        if (savedInstanceState == null) {
            // Se � a primeira vez que a activity � criada,
        	// insere o fragment na tela
            DetailsFragment details = new DetailsFragment();
            details.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, details).commit();
        }
    }
}



