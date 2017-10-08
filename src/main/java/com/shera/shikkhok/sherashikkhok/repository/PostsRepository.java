package com.shera.shikkhok.sherashikkhok.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shera.shikkhok.sherashikkhok.model.Posts;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long>{

	/*@Query("SELECT p FROM Posts p LEFT JOIN FETCH p.author ORDER BY p.date DESC")
	List<Posts> findLatest5Posts(Pageable pageable);
	
	Posts findByUsersEmail(String email);*/
	
	List<Posts> findByAuthorId(Long id);
	
	
	
}
