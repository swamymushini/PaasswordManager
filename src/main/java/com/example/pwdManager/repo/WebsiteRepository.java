package com.example.pwdManager.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pwdManager.Model.Website;

@Repository
public interface WebsiteRepository extends JpaRepository<Website, Long> {
	
	public Website findByName(String name);
}
