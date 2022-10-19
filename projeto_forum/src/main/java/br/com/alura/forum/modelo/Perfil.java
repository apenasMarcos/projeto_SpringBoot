package br.com.alura.forum.modelo;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
public class Perfil implements GrantedAuthority {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return nome;
    }
}
