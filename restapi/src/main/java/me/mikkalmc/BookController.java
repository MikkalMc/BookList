package me.mikkalmc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRespository;

    @GetMapping("/book")
    public List<Book> index(){
        return bookRespository.findAll();
    }

    @GetMapping("/book/{id}")
    public Book show(@PathVariable String id){
        int bookId = Integer.parseInt(id);
        return bookRespository.findOne(bookId);
    }

    @PostMapping("/book/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return bookRespository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
    }

    @PostMapping("/book")
    public Book create(@RequestBody Map<String, String> body){
        String title = body.get("title");
        String content = body.get("content");
        return bookRespository.save(new Book(title, content));
    }

    @PutMapping("/book/{id}")
    public Book update(@PathVariable String id, @RequestBody Map<String, String> body){
        int bookId = Integer.parseInt(id);
        // getting book
        Book book = bookRespository.findOne(bookId);
        book.setTitle(body.get("title"));
        book.setContent(body.get("content"));
        return bookRespository.save(book);
    }

    @DeleteMapping("book/{id}")
    public boolean delete(@PathVariable String id){
        int bookId = Integer.parseInt(id);
        bookRespository.delete(bookId);
        return true;
    }


}