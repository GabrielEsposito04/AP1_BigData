package br.ibmec.cloudcomputing.prova_ap1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ibmec.cloudcomputing.prova_ap1.model.Post;
import br.ibmec.cloudcomputing.prova_ap1.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post create(Post post) {
        return this.postRepository.save(post);
    }

    public Optional<Post> getById(long id) {
        return this.postRepository.findById(id);
    }

    public List<Post> getAll() {
        return this.postRepository.findAll();
    }

    public void saveOrUpdate(Post item) {
        this.postRepository.save(item);
    }

    public Post update(long id, Post newData) throws Exception {
        Optional<Post> opPost = this.postRepository.findById(id);

        if (opPost.isPresent() == false) {
            throw new Exception("Não encontrei o post a ser atualizado");
        }

        Post post = opPost.get();
        post.setAutor(newData.getAutor());
        post.setLivro(newData.getLivro());

        this.postRepository.save(post);

        return post;
    }

    public void delete(long id) throws Exception {
        Optional<Post> opPost = this.postRepository.findById(id);

        if (opPost.isPresent() == false) {
            throw new Exception("Não encontrei o post a ser atualizado");
        }

        this.postRepository.delete(opPost.get());
    }
}