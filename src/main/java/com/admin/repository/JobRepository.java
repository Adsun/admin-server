package com.admin.repository;

import org.springframework.stereotype.Repository;

import com.admin.entity.Job;

@Repository
public interface JobRepository extends BaseRepository<Job, Long> {
}
