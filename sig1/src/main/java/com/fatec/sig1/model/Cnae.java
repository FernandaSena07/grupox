package com.fatec.sig1.model;

public class Cnae {
    private Long id;
    private String descricaoi;
    private String observacao;


    public Cnae() {
        super();
    }


    public Cnae(Long id, String descricao, String observacao) {
        super();
        this.id = id;
        this.descricaoi= descricao;
        this.observacao = observacao;
        }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricaoi;
    }
    public void setDescricao(String descricaoi) {
        this.descricaoi = descricaoi;
    }
    public String getObservacao() {
        return observacao;
    }
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }



}