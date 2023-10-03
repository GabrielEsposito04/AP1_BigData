package br.ibmec.cloudcomputing.prova_ap1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ibmec.cloudcomputing.prova_ap1.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> { }