package com.example.aula04exemplobancodados.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.aula04exemplobancodados.model.Carro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CarrosDAO {

	private SQLiteDatabase db;

	/**
	 * Context.MODE_PRIVATE indica que o banco de dados só pode ser acessado por esta app
	 * Context.MODE_WORLD_READABLE e Context.MODE_WORLD_WRITEABLE para que outras apps
	 * possam ler e escrever neste banco de dados 
	 * @param ctx
	 */
	public CarrosDAO (Context ctx) {
		if (db == null)
			db = ctx.openOrCreateDatabase("posgrad_app", Context.MODE_PRIVATE, null);
	}

	public void insert(Carro carro) {
		if (carro == null)
			return;

		ContentValues values = new ContentValues();
		values.put("nome", carro.getNome());
		values.put("placa", carro.getPlaca());
		values.put("ano", carro.getAno());

		db.insert("carro", null, values);
	}

	public void update(Carro carro) {
		if (carro == null)
			return;

		ContentValues values = new ContentValues();
		values.put("nome", carro.getNome());
		values.put("placa", carro.getPlaca());
		values.put("ano", carro.getAno());

		//Pode ser feito assim
		//db.update("carro", values, "_id="+ carro.getId(), null);

		// Ou informando os parâmetros no terceiro parâmetro [array]
		db.update("carro", values, "_id=?", new String[]{
				String.valueOf(carro.getId())});
	}

	public void delete(Carro carro) {
		if (carro == null)
			return;

		//Pode ser feito assim
		//db.delete("carro", "_id="+ carro.getId(), null);

		// Ou informando os parâmetros no terceiro parâmetro [array]
		db.delete("carro", "_id=?", new String[]{
				String.valueOf(carro.getId())});
	}

	public List<Carro> readAll() {
		List<Carro> list = new ArrayList<Carro>();

		Cursor c = db.query("carro", new String[]{"_id",  "nome",  "placa",  "ano"}, null, null, null, null, "nome");
		if (c.moveToFirst()) {
			do {
				Carro carro = new Carro();
				carro.setId(c.getLong(0));
				carro.setNome(c.getString(1));
				carro.setPlaca(c.getString(2));
				carro.setAno(c.getInt(3));

				list.add(carro);
			} while (c.moveToNext());
		}

		return list;
	}

	public void fechar() {
		if (db != null)
			db.close();
	}

}
