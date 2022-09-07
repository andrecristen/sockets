package models;

import java.util.Date;

public class Team {

    protected String nome;

    protected String setor;

    protected Date dataFundacao;

    protected People lider;

    public Team(String nome, String setor, Date dataFundacao, People lider) {
        this.nome = nome;
        this.setor = setor;
        this.dataFundacao = dataFundacao;
        this.lider = lider;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public Date getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public People getLider() {
        return lider;
    }

    public void setLider(People lider) {
        this.lider = lider;
    }

}
