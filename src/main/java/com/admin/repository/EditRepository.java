package com.admin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.admin.entity.Edit;

@Repository
public interface EditRepository extends BaseRepository<Edit, Long> {
	public List<Edit> findByConstantId(String constantId);
	public List<Edit> findFirst5ByConstantIdInOrderByUpdateDatetimeDesc(String constantId);
	public Page<Edit> findByConstantIdInOrderByUpdateDatetimeDesc(List<String> constantIds, Pageable pageable);
}
