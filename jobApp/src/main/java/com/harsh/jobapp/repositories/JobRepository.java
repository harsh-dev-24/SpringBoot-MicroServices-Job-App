package com.harsh.jobapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.harsh.jobapp.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {

	List<Job> findByTitleContaining(String query);

	List<Job> findByMinSalaryGreaterThan(int salary);

	List<Job> findByLocation(String location);

	List<Job> findByStatus(String status);

	@Query(value = "SELECT * FROM Jobs WHERE LOWER(title) LIKE LOWER(CONCAT('%', :keyword, '%')) "
			+ "OR LOWER(description) LIKE LOWER(CONCAT('%', :keyword, '%'))", nativeQuery = true)
	List<Job> findByKeyword(@Param("keyword") String keyword);

}
