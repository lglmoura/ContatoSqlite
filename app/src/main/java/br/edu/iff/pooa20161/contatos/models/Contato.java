package br.edu.iff.pooa20161.contatos.models;

import android.graphics.Bitmap;

/**
 * Created by lglmoura on 03/09/16.
 */
public class Contato {
    private long id;
    private String nome;
    private String email;
    private String telefone;
    private Bitmap foto;

    public Contato() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Contato(long id, String nome, String email, String telefone, Bitmap foto) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }
}
