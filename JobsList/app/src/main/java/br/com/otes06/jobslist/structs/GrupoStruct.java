package br.com.otes06.jobslist.Structs;

public class GrupoStruct {

    private int id;

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static GrupoStruct SemGrupo() {
        GrupoStruct grupo = new GrupoStruct();
        grupo.setNome("Sem Grupo");
        return grupo;
    }

    @Override
    public String toString() {
        return getNome();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
