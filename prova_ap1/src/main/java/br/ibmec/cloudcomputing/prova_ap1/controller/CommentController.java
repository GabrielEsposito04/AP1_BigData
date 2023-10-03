package br.ibmec.cloudcomputing.prova_ap1.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ibmec.cloudcomputing.prova_ap1.model.Comment;
import br.ibmec.cloudcomputing.prova_ap1.model.Post;
import br.ibmec.cloudcomputing.prova_ap1.service.CommentService;
import br.ibmec.cloudcomputing.prova_ap1.service.PostService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/post/{idPost}/comment")
class resourceNameController {

    @Autowired
    CommentService commentService;

    @Autowired PostService postService;

    @GetMapping
    public ResponseEntity<List<Comment>> getAll(@PathVariable("idPost") long idPost) {
        try {
            Optional<Post> opPost = this.postService.getById(idPost);

            if (opPost.isPresent() == false) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);    
            }

            return new ResponseEntity<>(opPost.get().getComment(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Comment> getById(@PathVariable("id") long id) {
        Optional<Comment> existingItemOptional = commentService.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Comment> create(@PathVariable("idPost") long idPost,@Valid @RequestBody Comment item) {
        try {
            Comment savedItem = commentService.save(idPost, item);
            return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Comment> update(@PathVariable("id") long id,@Valid @RequestBody Comment item) {
        try {
            return new ResponseEntity<>(commentService.update(id, item), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        try {
            commentService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}