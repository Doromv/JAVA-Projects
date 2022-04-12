package com.doromv.controller;

import com.doromv.pojo.Books;
import com.doromv.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.print.Book;
import java.util.List;

/**
 * @author Doromv
 * @create 2022-03-04-16:54
 */
@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    @Qualifier("BookServiceImpl")
    private BookService bookService;
    @RequestMapping("/allBooks")
    public String list(Model model){
        List<Books> list = bookService.queryAllBook();
        model.addAttribute("list",list);
        return "allBook";
    }
    @RequestMapping("/toAddBook")
    public String toAddPaper(){
        return "addBook";
    }
    @RequestMapping("/addBook")
    public String addPaper(Books books){
        System.out.println("addBook=>"+books);
        bookService.addBook(books);
        return "redirect:/book/allBooks";
    }
    @RequestMapping("/toUpdateBook/{id}")
    public String toUpdatePaper (@PathVariable int id,Model model){
        Books books = bookService.queryBookById(id);
        model.addAttribute("Ubooks",books);
        return "updateBook";
    }
    @RequestMapping("/updateBook")
    public String updatePaper(Books books){
        System.out.println("updateBook=>"+books);
        bookService.updateBook(books);
        return "redirect:/book/allBooks";
    }
    @RequestMapping("/deleteBook/{id}")
    public String deletePaper(@PathVariable int id){
        bookService.deleteBookById(id);
        return "redirect:/book/allBooks";
    }
    @RequestMapping("/queryByName")
    public String queryBookByName(String bookName,Model model){
        List<Books> list = bookService.queryBookByName(bookName);
        model.addAttribute("list",list);
        return "allBook";
    }
}
