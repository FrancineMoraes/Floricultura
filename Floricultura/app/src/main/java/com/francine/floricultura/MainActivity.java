package com.francine.floricultura;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtId;
    private EditText edtNome;
    private EditText edtDuracao;
    private EditText edtCor;
    private EditText edtEstacao;
    private EditText edtTipo;
    private EditText edtPreco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtId = findViewById(R.id.edtId);
        edtNome = findViewById(R.id.edtNome);
        edtDuracao = findViewById(R.id.edtDuracao);
        edtEstacao = findViewById(R.id.edtEstacao);
        edtCor = findViewById(R.id.edtCor);
        edtTipo = findViewById(R.id.edtTipo);
        edtPreco = findViewById(R.id.edtPreco);


    }


    public void incluir(View view) {
        String nome = edtNome.getText().toString();
        String duracao = edtDuracao.getText().toString();
        String estacao = edtEstacao.getText().toString();
        String cor = edtCor.getText().toString();
        String tipo = edtTipo.getText().toString();
        String precoS = edtPreco.getText().toString();

        System.out.println("Preço"+precoS);

        if(nome.trim().isEmpty() || duracao.trim().isEmpty() || cor.trim().isEmpty()
                || estacao.trim().isEmpty() || tipo.trim().isEmpty() || precoS.trim().isEmpty()){
            Toast.makeText(this,"Informe os dados",Toast.LENGTH_LONG).show();

            edtNome.requestFocus();
            return;
        }

        double preco = Double.parseDouble(precoS);
        System.out.println(preco);

        FlorDB florDB = new FlorDB(this);

        long id = florDB.incluir(new Flor(0,nome ,duracao ,estacao , cor, tipo, preco));

        if (id > 0){
            Toast.makeText(this,"Ok Flor Cadastrada com codigo: "+ id,Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Erro...não cadastrado",Toast.LENGTH_LONG).show();
        }
        edtId.setText("");
        edtNome.setText("");
        edtDuracao.setText("");
        edtEstacao.setText("");
        edtCor.setText("");
        edtTipo.setText("");
        edtPreco.setText("");
        edtNome.requestFocus();

    }



    public void alterar(View view) {

        String idS = edtId.getText().toString();
        String nome = edtNome.getText().toString();
        String duracao = edtDuracao.getText().toString();
        String estacao = edtEstacao.getText().toString();
        String cor = edtCor.getText().toString();
        String tipo = edtTipo.getText().toString();
        String precoS = edtPreco.getText().toString();

        System.out.println("Preço"+precoS);

        if(nome.trim().isEmpty() || duracao.trim().isEmpty() || cor.trim().isEmpty()
                || estacao.trim().isEmpty() || tipo.trim().isEmpty() || precoS.trim().isEmpty()){
            Toast.makeText(this,"Informe os dados",Toast.LENGTH_LONG).show();

            edtNome.requestFocus();
            return;
        }

        double preco = Double.parseDouble(precoS);
        System.out.println(preco);

        int id = Integer.parseInt(idS);

        FlorDB florDB = new FlorDB(this);

        if(florDB.alterar(new Flor(id, nome, duracao, estacao, cor, tipo, preco)) > 0)

            if (id > 0){
                Toast.makeText(this,"Ok FLor Cadastrada com codigo: "+ id,Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"Erro...não cadastrado",Toast.LENGTH_LONG).show();
            }

        edtId.setText("");
        edtNome.setText("");
        edtDuracao.setText("");
        edtEstacao.setText("");
        edtCor.setText("");
        edtTipo.setText("");
        edtPreco.setText("");

        edtNome.requestFocus();

    }

    public void excluir(View view) {

        String idS = edtId.getText().toString();
        String nome = edtNome.getText().toString();
        String duracao = edtDuracao.getText().toString();
        String estacao = edtEstacao.getText().toString();
        String cor = edtCor.getText().toString();
        String tipo = edtTipo.getText().toString();
        String precoS = edtPreco.getText().toString();

        System.out.println("Preço"+precoS);

        if(nome.trim().isEmpty() || duracao.trim().isEmpty() || cor.trim().isEmpty()
                || estacao.trim().isEmpty() || tipo.trim().isEmpty() || precoS.trim().isEmpty()){
            Toast.makeText(this,"Informe os dados",Toast.LENGTH_LONG).show();

            edtNome.requestFocus();
            return;
        }

        double preco = Double.parseDouble(precoS);
        System.out.println(preco);

        int id = Integer.parseInt(idS);

        FlorDB florDB = new FlorDB(this);

        if (florDB.excluir(id) > 0) {

            if (id > 0) {
                Toast.makeText(this, "Ok Flor excluída: " + id, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Erro...flor não excluída", Toast.LENGTH_LONG).show();
            }

            edtId.setText("");
            edtNome.setText("");
            edtDuracao.setText("");
            edtCor.setText("");
            edtEstacao.setText("");
            edtTipo.setText("");
            edtPreco.setText("");

            edtNome.requestFocus();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cad_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnListagem){
            Intent it = new Intent(this, ListaActivity.class);
            startActivity(it);
        }
        else if(item.getItemId() == R.id.mnPesquisa){
            Intent it = new Intent(this, PesquisaActivity.class);
            startActivity(it);
        }
        return true;
    }

    public void consultar(View view) {
        String idS = edtId.getText().toString();

        if (idS.trim().isEmpty()){
            Toast.makeText(this,"Informe o código",Toast.LENGTH_LONG).show();

            edtId.requestFocus();
            return;
        }
        int id = Integer.parseInt(idS);

        FlorDB florDB = new FlorDB(this);

        Flor flor = florDB.buscar(id);

        if (flor.getId() == 0){
            Toast.makeText(this,"codigo nao cadastrado",Toast.LENGTH_LONG).show();
        }

        edtNome.setText(flor.getNome());
        edtDuracao.setText(flor.getDuracao());
        edtEstacao.setText(flor.getEstacao());
        edtCor.setText(flor.getCor());
        edtTipo.setText(flor.getTipo());
        edtPreco.setText(String.valueOf(flor.getPreco()));

        edtId.requestFocus();
    }

}
