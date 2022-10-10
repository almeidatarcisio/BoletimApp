package br.com.fasj.unibrasapp;

public class Nota {
    private String disciplina;
    private String turma;
    private String a1;
    private String a2;
    private String sub;
    private String a3;
    private int faltasA1;
    private int faltasA2;

    public Nota(String disciplina, String turma, String a1, String a2, String sub, String a3, int faltasA1, int faltasA2) {
        this.disciplina = disciplina;
        this.turma = turma;
        this.a1 = a1;
        this.a2 = a2;
        this.sub = sub;
        this.a3 = a3;
        this.faltasA1 = faltasA1;
        this.faltasA2 = faltasA2;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getA3() {
        return a3;
    }

    public void setA3(String a3) {
        this.a3 = a3;
    }

    public int getFaltasA1() {
        return faltasA1;
    }

    public void setFaltasA1(int faltasA1) {
        this.faltasA1 = faltasA1;
    }

    public int getFaltasA2() {
        return faltasA2;
    }

    public void setFaltasA2(int faltasA2) {
        this.faltasA2 = faltasA2;
    }
}
