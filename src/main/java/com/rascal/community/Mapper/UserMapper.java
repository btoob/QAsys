package com.rascal.community.Mapper;

import com.rascal.community.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface UserMapper {
    @Insert("insert into user (name, accountId, token, gmtCreate, gmtModified, avatar_url) " +
            "values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token=#{token}")
    User findByToken(String token);

    @Select("select * from user where id = #{id}")
    User getById(Integer id);

    @Select("select * from user where id=#{creator}")
    User selectByPrimaryKey(Integer creator);

    @Select("select * from user where accountId=#{accountId}")
    User findByAccountId(String accountId);

    @Update("update user set name=#{name}, token=#{token}, " +
            "gmtModified=#{gmtModified}, avatar_url=#{avatarUrl} " +
            "where accountId=#{accountId}")
    void update(User user);
}
