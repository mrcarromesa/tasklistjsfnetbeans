/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.task.entity;

/**
 *
 * @author carlosrodolfosantos
 */
public class Task {

    private Integer id;
    private String titulo;
    private boolean concluido;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }
    
    public String getNameIsConcluido(){
        return (this.isConcluido() ? "Concluído" : "Não Concluído");
    }
}
