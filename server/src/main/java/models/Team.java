package models;

import java.util.Date;
import java.util.HashMap;

public class Team {

    protected String nome;

    protected String setor;

    protected Date dataFundacao;

    protected People lider;

    protected HashMap<String, People> integrantes;

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

    public HashMap<String, People> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(HashMap<String, People> integrantes) {
        this.integrantes = integrantes;
    }

    public void addIntegrante(People integrante) {
        this.integrantes.put(integrante.getCpf(), integrante);
    }

    public void removeIntegrante(People integrante) {
        this.integrantes.remove(integrante.getCpf());
    }

    @Override
    public String toString() {
        return "Team{" +
                "nome='" + nome + '\'' +
                ", setor='" + setor + '\'' +
                ", dataFundacao=" + dataFundacao +
                ", lider=" + lider +
                ", integrantes=" + integrantes +
                '}';
    }
}
