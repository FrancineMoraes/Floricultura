package com.francine.floricultura;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FlorDB extends SQLiteOpenHelper {

    public FlorDB(Context context) {

        super(context, "floricultura.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table if not exists flores(\n" +
                "_id integer primary key autoincrement,\n" +
                "nome text,\n" +
                "duracao text,\n" +
                "estacao text,\n" +
                "cor text\n," +
                "tipo text,\n" +
                "preco real\n" +
                ");";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long incluir(Flor flor){
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("nome", flor.getNome());
            values.put("duracao", flor.getDuracao());
            values.put("estacao", flor.getEstacao());
            values.put("cor", flor.getCor());
            values.put("tipo", flor.getTipo());
            values.put("preco", flor.getPreco());
            long id = db.insert("flores","",values);
            return id;


        }finally {
            db.close();
        }
    }

    public Flor buscar(int id){
        SQLiteDatabase db = getReadableDatabase();
        try{
            Cursor c = db.query("flores",null,"_id=?",new String[]{String.valueOf(id)},null,null,null);
            if(c.getCount() > 0){
                c.moveToFirst();

                //getString(nº da coluna da tabela
                String nome = c.getString(1);
                String duracao = c.getString(2);
                String estacao = c.getString(3);
                String cor = c.getString(4);
                String tipo = c.getString(5);
                double preco = c.getDouble(6);
                return new Flor(id, nome, duracao, estacao, cor, tipo, preco);
            }else {
                return new Flor(0, "", "", "", "", "", 0.00);
            }
        }finally {
            db.close();
        }

    }

    public long alterar(Flor flor){
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("nome", flor.getNome());
            values.put("duracao", flor.getDuracao());
            values.put("estacao", flor.getEstacao());
            values.put("cor", flor.getCor());
            values.put("tipo", flor.getTipo());
            values.put("preco", flor.getPreco());
            long id = db.update("flores",values,"_id=?", new String[]{String.valueOf(flor.getId())});
            return id;


        }finally {
            db.close();
        }

    }

    public long excluir(int id){
        SQLiteDatabase db = getWritableDatabase();
        try {

            return db.delete("flores","_id=?", new String[]{String.valueOf(id)});



        }finally {
            db.close();
        }

    }

    public Cursor listar(){
        Cursor cursor;
        String campos[] = {"_id", "nome", "duracao", "estacao", "cor", "tipo" ,"preco"};
        SQLiteDatabase db = getReadableDatabase();
        cursor = db.query("flores", campos, null, null, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }


    public Cursor pesqFlor(String palavra) {
        Cursor cursor;

        String campos[] = {"_id", "nome", "duracao", "estacao", "cor", "tipo", "preco"};
        SQLiteDatabase db = getReadableDatabase();

        cursor = db.query("flores", campos,"nome LIKE ?",new String[]{"%"+palavra+"%"},null,null,null,null);

        if(cursor!=null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

}
