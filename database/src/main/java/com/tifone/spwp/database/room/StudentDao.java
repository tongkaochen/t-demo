package com.tifone.spwp.database.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * Create by Tifone on 2019/8/11.
 * 数据实体，必须为接口
 * 将SQL语句与方法相关联
 */

@Dao
public interface StudentDao {

    @Insert
    void insert(Student student);

    @Query("select * from std_table order by std_num asc")
    List<Student> queryAll();

    @Query("delete from std_table")
    void deleteAll();
}
