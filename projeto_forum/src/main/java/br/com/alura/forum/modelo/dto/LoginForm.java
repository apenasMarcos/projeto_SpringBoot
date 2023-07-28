package br.com.alura.forum.modelo.dto;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {
    private String nome;
    private String senha;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UsernamePasswordAuthenticationToken converter() {
    return new UsernamePasswordAuthenticationToken(this.nome, this.senha);
    }
}
