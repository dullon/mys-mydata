package com.learn.chaptertest.mapper;

import com.learn.chaptertest.po.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    /**
     * 根据id查询role
     * @param id
     * @return
     */
    public Role getRole(Long id);

    /**
     * 写入role
     * @param role
     * @return
     */
    public int insertRole(Role role);

    /**
     * 修改role
     * @param id
     * @return
     */
    public int updateRoleById(Long id);

    /**
     * 根据id 删除role
     * @param id
     * @return
     */
    public int deleteRoleById(Long id);

    /**
     * 列表查询
     * @return
     */
    public List<Role> find(@Param("id") Long id);
}
