package com.francine.floricultura;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class PesquisaActivity extends AppCompatActivity {

    private EditText edtPesq;
    private ListView listPesq;
    private ImageView imgFlor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);

        edtPesq = findViewById(R.id.edtPesq);
        listPesq = findViewById(R.id.listPesq);


    }

    public void pesquisar(View view) {
        String pesq = edtPesq.getText().toString();

        if(pesq == "") {
            Toast.makeText(this,"Informe a palavra chave de pesquisa",Toast.LENGTH_LONG).show();
            edtPesq.requestFocus();
            return;
        }

        FlorDB florDB = new FlorDB(this);
        Cursor cursor = florDB.pesqFlor(pesq);

        String[] nomeCampos = new String[]{"_id", "nome", "duracao", "estacao", "cor", "tipo", "preco"};
        int [] idViews = new int[] {R.id.txtId, R.id.txtNome, R.id.txtDuracao, R.id.txtEstacao, R.id.txtCor, R.id.txtTipo, R.id.txtPreco};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,R.layout.item_flor, cursor, nomeCampos,idViews,0
        );

        listPesq.setAdapter(adapter);
    }
}
