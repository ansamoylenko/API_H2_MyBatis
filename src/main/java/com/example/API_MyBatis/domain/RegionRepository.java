package com.example.API_MyBatis.domain;

import com.example.API_MyBatis.domain.Region;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RegionRepository
{
    @Select("select * from region")
    public List<Region> showAll();

    @Select("select exists(select name from region where name = #{name})")
    public boolean isExists(String name);

    @Select("select * from region where id = #{id}")
    public Region findById(int id);

    @Select("select * from region where name = #{name} limit 1")
    public Region findByName(String name);

    @Delete("delete from region where id = #{id}")
    public int deleteById(int id);

    @Delete("delete distinct from region where name = #{name}")
    public int[] deleteByName(String name);


    @Insert("insert into region(name, fullname) " + "values (#{name}, #{fullname})")
    public int insert(Region region);


//    @Insert("insert into region(id, name, fullname) " + "values (, #{name}, #{fullname})")
//    public int insert(Region region);
    @Update("update region set name = #{region.name}, fullname = #{region.fullname} where name = #{oldName} limit 1")
    public int update(String oldName, Region region);


}
