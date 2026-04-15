package nicolagraziani.U5_W2_D3.controllers;

import nicolagraziani.U5_W2_D3.entities.Author;
import nicolagraziani.U5_W2_D3.payloads.AuthorPayload;
import nicolagraziani.U5_W2_D3.services.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    //    GET ALL
    @GetMapping
    public Page<Author> findAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "surname") String sortBy) {
        return this.authorService.findAll(page, size, sortBy);
    }

    //    POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author saveAuthor(@RequestBody AuthorPayload body) {
        return this.authorService.saveAuthor(body);
    }

    //    GET BY ID
    @GetMapping("/{authorId}")
    public Author findById(@PathVariable UUID authorId) {
        return this.authorService.findAuthorById(authorId);
    }

    //    PUT
    @PutMapping("/{authorId}")
    public Author getAuthorByIdAndUpdate(@PathVariable UUID authorId, @RequestBody AuthorPayload body) {
        return this.authorService.findAuthorByIdAndUpdate(authorId, body);
    }

    //    DELETE
    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getAuthorByIdAndDelete(@PathVariable UUID authorId) {
        this.authorService.findAuthorByIdAndDelete(authorId);
    }
}
