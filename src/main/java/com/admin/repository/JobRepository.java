package com.admin.repository;

import javax.persistence.Entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;
import org.springframework.stereotype.Repository;

import com.admin.entity.Constant;
import com.admin.entity.Job;

import lombok.Getter;
import lombok.Setter;

@Repository
public interface JobRepository extends BaseRepository<Job, Long> {
}
