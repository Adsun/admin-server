package com.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.admin.entity.UserInfo;

/**
 * @author fengxiang
 * @date 2018-10-22
 */
public interface UserInfoRepository extends BaseRepository<UserInfo, Long>{
	public List<UserInfo> findByUserName(String userName);
	public List<UserInfo> findByUserNameContaining(String userName);
	public List<UserInfo> findByFullNameContaining(String fullName);
	
	/**
	 * 查询当前用户的所有子用户
	 * @param userId
	 * @return
	 */
	@Query(value="select * from tb_user_info where id in (select t3.user_id from ( " + 
			"select t1.user_id, if(find_in_set(parent_id, @pids) > 0, @pids \\:= concat(@pids, ',', user_id), 0) as ischild " + 
			"              from ( " + 
			"                   select user_id,parent_id from tb_user_parent t order by parent_id, user_id " + 
			"                  ) t1, " + 
			"                  (select @pids \\:=:userId) t2 " + 
			"             ) t3 where ischild != 0) " ,
			nativeQuery=true)
	public List<UserInfo> findAllSonUser(@Param("userId") Long userId);
	
	/**
	 * 查询当前用户的所有父用户
	 * @param userId
	 * @return
	 */
	@Query(value="select * from tb_user_info where id in (select t3.parent_id from (  " + 
			"select t1.parent_id, if(parent_id is not null, @pid \\:= parent_id, 0) as ischild " + 
			"              from ( " + 
			"                   select user_id,parent_id from tb_user_parent t order by parent_id, user_id " + 
			"                  ) t1, " + 
			"                  (select @pid \\:=:userId) t2 " + 
			"             ) t3 where ischild != 0)" ,
			nativeQuery=true)
	public List<UserInfo> findAllParentUser(@Param("userId") Long userId);
	
}
