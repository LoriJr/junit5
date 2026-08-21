package com.viratech.domain;

import com.viratech.domain.exceptions.ValidationException;

public class Usuario {
    private Long id;
    private String nome;
    private String email;
    private String senha;

    public Usuario(Long id, String nome, String email, String senha) {
        if(nome == null || nome.isBlank()) throw new ValidationException("Nome é obrigatório");
        if(email == null || email.isBlank()) throw new ValidationException("Email é obrigatório");
        if(senha == null || senha.isBlank()) throw new ValidationException("Senha é obrigatória");

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(){}

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha(){
        return senha;
    }
}
