package com.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.admin.entity.AbstractEntity;

/**
 * @author fengxiang
 * @date 2018-11-13
 */
public interface BaseRepository<T extends AbstractEntity, ID> extends JpaRepository<T, ID>{
	public default T createEntity(T entity) {
		entity.createEntity();
		return save(entity);
	}
	
	public default List<T> createAllEntities(Iterable<T> entities) {
		for (T entity : entities) {
			entity.createEntity();
		}
		return saveAll(entities);
	}
	
	public default T updateEntity(T entity) {
		entity.updateEntity();
		return save(entity);
	}
	
	public default List<T> updateAllEntities(Iterable<T> entities) {
		for (T entity : entities) {
			entity.updateEntity();
		}
		return saveAll(entities);
	}
	
	public default void deleteEntity(T entity) {
		entity.deleteEntity();
		delete(entity);
	}
	
	public default void deleteEntityById(ID id) {
		T entity = getOne(id);
		deleteEntity(entity);
	}
	
	public default void deleteAllEntities(Iterable<T> entities) {
		for (T entity : entities) {
			entity.deleteEntity();
		}
		deleteAll(entities);
	}
}
