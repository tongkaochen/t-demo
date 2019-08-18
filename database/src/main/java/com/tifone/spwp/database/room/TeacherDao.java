package com.tifone.spwp.database.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * Create by Tifone on 2019/8/11.
 * 数据实体，必须为接口
 * 将SQL语句与方法相关联
 */

@Dao
public interface TeacherDao {

    @Insert
    void insert(Teacher teacher);

    @Query("select * from teacher_table order by tc_num asc")
    List<Teacher> queryAll();

    @Query("delete from teacher_table")
    void deleteAll();
}
