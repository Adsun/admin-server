package com.admin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.admin.entity.Edit;

@Repository
public interface EditRepository extends BaseRepository<Edit, Long> {
	@Query(value="select u.id,u.title,u.sub_title,u.time,u.detail,u.img_url from ssm_edit u "
			+ "where u.constant_id = ?1% order by u.update_datetime desc")
	public List<Object[]> findConstantId(String constantId);
	@Query(value="select u.id,u.title,u.sub_title,u.time,u.detail,u.img_url from ssm_edit u "
			+ "where u.constant_id = ?1% order by u.update_datetime desc limit 5")
	public List<Object[]> findConstantIdLimit(String constantId);
	public Page<Edit> findByConstantIdInOrderByUpdateDatetimeDesc(List<String> constantIds, Pageable pageable);
}
