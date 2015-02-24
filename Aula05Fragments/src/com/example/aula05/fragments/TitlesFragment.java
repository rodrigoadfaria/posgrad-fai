package com.example.aula05.fragments;

import java.util.Arrays;
import java.util.List;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Este é o fragment usado à esquerda, mostrando uma lista de itens que
 * o usuário pode tocar. Quando um item é selecionado, esta classe cuida de mostrar os dados ao 
 * usuário de forma apropriada, baseado no tamanho da tela atual e orientação
 */

public class TitlesFragment extends ListFragment {
	boolean mDualPane;
	int mCurCheckPosition = 0;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		setListAdapter (new CustomAdapter (getActivity(), R.layout.complex_list_item, 
				R.id.text1, Arrays.asList(AndroidContent.TITLES)));
		
		View detailsFrame = getActivity().findViewById(R.id.details);
		mDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;
		
		if (savedInstanceState != null) {
			mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
		}
		
		if (mDualPane) {
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			showDetails(mCurCheckPosition);
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("curChoice", mCurCheckPosition);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		showDetails(position);
	}

	/**
	 * Define se há espaço para mostrar os detalhes do item selecionado 
	 * na mesma tela atual ou abre uma nova activity para mostrar o conteúdo
	 * detalhado.
	 */
	void showDetails(int index) {
		mCurCheckPosition = index;

		if (mDualPane) {
			getListView().setItemChecked(index, true);
			
			DetailsFragment details = (DetailsFragment)
					getFragmentManager().findFragmentById(R.id.details);
			if (details == null || details.getShownIndex() != index) {
				details = DetailsFragment.newInstance(index);
				
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				ft.replace(R.id.details, details);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			}
		} else {
			Intent intent = new Intent();
			intent.setClass(getActivity(), DetailsActivity.class);
			intent.putExtra("index", index);
			startActivity(intent);
		}
	}


	/**
	 * CustomAdapter
	 *
	 */
	private class CustomAdapter extends ArrayAdapter<String> {

		private Context mContext;
		private int mLayoutId;

		public CustomAdapter(Context context, int layoutId, int textViewResourceId, List<String> items) {
			super(context, textViewResourceId, items);
			mContext = context;
			mLayoutId = layoutId;
		}

		/**
		 * getView
		 *
		 * Retorna uma view que mostra um item do array.
		 *
		 */
		public View getView (int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (v == null) {
				LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate (mLayoutId, null);
			}

			View itemView = v;

			TextView tv1 = (TextView) itemView.findViewById(R.id.text1);
			if (tv1 != null) tv1.setText(Integer.valueOf(position + 1).toString());

			String text2 = getItem(position);
			TextView tv2 = (TextView) itemView.findViewById(R.id.text2);
			if (tv2 != null) tv2.setText(text2);

			return itemView;
		}

	} // end class CustomAdapter

} // end class TitlesFragment
