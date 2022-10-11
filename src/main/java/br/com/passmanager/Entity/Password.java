package br.com.passmanager.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "password", schema = "public")
public class Password {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", nullable = false, length = 90)
    private String login;

    @Column(name = "url", nullable = false, length = 90)
    private String url;

    @Column(name = "password", nullable = false, length = 90)
    private String password;

    @Column(name = "descricao", nullable = false, length = 90)
    private String descricao;
}

