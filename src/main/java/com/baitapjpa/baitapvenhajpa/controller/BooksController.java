package com.baitapjpa.baitapvenhajpa.controller;

import com.baitapjpa.baitapvenhajpa.entity.Books;
import com.baitapjpa.baitapvenhajpa.request.SaveBooksRequest;
import com.baitapjpa.baitapvenhajpa.response.BaseResponse;
import com.baitapjpa.baitapvenhajpa.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BooksService booksService;

    @GetMapping
    public ResponseEntity<?> getAllBooks()  {
        List<Books> books = booksService.getAllBooks();

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(books);

        if(books.isEmpty()){
            baseResponse.setCode(404);
            baseResponse.setMessage("No books found");
        }else {
            baseResponse.setCode(200);
            baseResponse.setMessage("ok");
        }
        return  ResponseEntity.ok().body(baseResponse);
    }

    @PostMapping
    public ResponseEntity<?> saveBooks(@RequestBody SaveBooksRequest saveBooksRequest) {
        Books books = booksService.saveBooks(saveBooksRequest);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(books);
        if(books!=null){
            baseResponse.setCode(200);
            baseResponse.setMessage("ok");
        }else {
            baseResponse.setCode(404);
            baseResponse.setMessage("No books found");
        }
        return ResponseEntity.ok().body(baseResponse);
    }

    @GetMapping(value={"/search"}, params = {"author"})
    public ResponseEntity<?> getBooksByAuthor(@RequestParam String author) {
        List<Books> books = booksService.getBooksByAuthor(author);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(books);
        if(books.isEmpty()){
            baseResponse.setCode(404);
            baseResponse.setMessage("No books found");
        }else{
            baseResponse.setCode(200);
            baseResponse.setMessage("ok");
        }

        return ResponseEntity.ok().body(baseResponse);
    }

    @GetMapping(value="/search" ,params = {"minPrice", "maxPrice"})
    public ResponseEntity<?> getBooksByPriceBetween(@RequestParam Double minPrice, @RequestParam Double maxPrice) {
        List<Books> books = booksService.getBooksByPriceBetween(minPrice, maxPrice);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(books);
        if(books!=null){
            baseResponse.setCode(200);
            baseResponse.setMessage("ok");
        }else{
            baseResponse.setCode(404);
            baseResponse.setMessage("No books found");
        }

        return ResponseEntity.ok().body(baseResponse);
    }
}
