package com.example.aula05.fragments;

import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Este é um fragment que mostra os detalhes de um item em particular.
 */

public class DetailsFragment extends Fragment {
    /**
     * Cria uma instancia de DetailsFragment para mostrar o texto
     * no dado 'index'.
     */
    public static DetailsFragment newInstance(int index) {
        DetailsFragment f = new DetailsFragment();

        // Insere o index como um argumento do fragment
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);

        return f;
    }

    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	if (container == null) {
    		return null;
    	}

    	ScrollView scroller = new ScrollView(getActivity());
    	TextView text = new TextView(getActivity());
    	
    	int padding = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                4, getActivity().getResources().getDisplayMetrics());
        text.setPadding(padding, padding, padding, padding);
        
        scroller.addView(text);
        text.setText(AndroidContent.DIALOGUE[getShownIndex()]);
        
    	return scroller;
    }
}
