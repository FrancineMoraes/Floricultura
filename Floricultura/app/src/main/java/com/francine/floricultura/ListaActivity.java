package com.francine.floricultura;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListaActivity extends AppCompatActivity {

    private ListView listFlores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        listFlores = findViewById(R.id.listFlores);

        FlorDB db = new FlorDB(this);
        Cursor cursor = db.listar();

        String[] nomeCampos = new String[]{"_id", "nome", "duracao", "estacao", "cor", "tipo", "preco"};
        int[] idViews = new int[]{R.id.txtId, R.id.txtNome, R.id.txtDuracao, R.id.txtEstacao, R.id.txtCor, R.id.txtTipo, R.id.txtPreco};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.item_flor, cursor, nomeCampos, idViews, 0);
        listFlores.setAdapter(adapter);
    }
}
