package br.ibmec.cloudcomputing.prova_ap1.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NotBlank(message = "Campo do autor do comentario não pode ser vazio")
    private String autor;
    
    @Column(nullable = false)
    @NotBlank(message = "Campo do texto do comentario não pode ser vazio")
    private String texto;

    @Column(nullable = false)
    private LocalDateTime dtComment;

    @ManyToOne
    @JsonIgnore
    private Post post;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    } 
    
    public LocalDateTime getDtComment() {
        return dtComment;
    }

    public void setDtComment(LocalDateTime dtComment) {
        this.dtComment = dtComment;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    
}
