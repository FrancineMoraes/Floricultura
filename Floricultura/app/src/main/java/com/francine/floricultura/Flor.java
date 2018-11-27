package com.francine.floricultura;

public class Flor {

    private int id;
    private String nome;
    private String duracao;
    private String estacao;
    private String cor;
    private String tipo;
    private double preco;

    public Flor(int id, String nome, String duracao, String estacao, String cor, String tipo, double preco) {
        this.id = id;
        this.nome = nome;
        this.duracao = duracao;
        this.estacao = estacao;
        this.cor = cor;
        this.tipo = tipo;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getEstacao() {
        return estacao;
    }

    public void setEstacao(String estacao) {
        this.estacao = estacao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
