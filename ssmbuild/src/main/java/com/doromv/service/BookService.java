package com.doromv.service;

import com.doromv.pojo.Books;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Doromv
 * @create 2022-03-04-11:22
 */
public interface BookService {
    int addBook(Books books);
    int deleteBookById(int id);
    int updateBook(Books books);
    Books queryBookById(int id);
    List<Books> queryAllBook();
    List<Books> queryBookByName(String bookName);
}
