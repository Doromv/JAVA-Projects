package com.doromv.dao;

import com.doromv.pojo.Books;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Doromv
 * @create 2022-03-04-11:02
 */
public interface BookMapper{
    int addBook(Books books);
    int deleteBookById(@Param("bookID") int id);
    int updateBook(Books books);
    Books queryBookById(@Param("bookID") int id);
    List<Books> queryAllBook();
    List<Books> queryBookByName(@Param("bookName") String bookName);
}
