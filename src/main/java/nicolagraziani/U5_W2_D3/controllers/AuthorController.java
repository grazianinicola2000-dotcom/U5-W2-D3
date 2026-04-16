package nicolagraziani.U5_W2_D3.controllers;

import nicolagraziani.U5_W2_D3.entities.Author;
import nicolagraziani.U5_W2_D3.exceptions.ValidationException;
import nicolagraziani.U5_W2_D3.payloads.AuthorDTO;
import nicolagraziani.U5_W2_D3.services.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
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
    public Author saveAuthor(@RequestBody @Validated AuthorDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            List<String> errors = validation.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();
            throw new ValidationException(errors);
        }
        return this.authorService.saveAuthor(body);
    }

    //    GET BY ID
    @GetMapping("/{authorId}")
    public Author findById(@PathVariable UUID authorId) {
        return this.authorService.findAuthorById(authorId);
    }

    //    PUT
    @PutMapping("/{authorId}")
    public Author getAuthorByIdAndUpdate(@PathVariable UUID authorId, @RequestBody @Validated AuthorDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            List<String> errors = validation.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();
            throw new ValidationException(errors);
        }
        return this.authorService.findAuthorByIdAndUpdate(authorId, body);
    }

    //    DELETE
    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getAuthorByIdAndDelete(@PathVariable UUID authorId) {
        this.authorService.findAuthorByIdAndDelete(authorId);
    }

    //    ADD IMAGE
    @PatchMapping("/{authorId}/profileImg")
    public void uploadImage(@RequestParam("profile_img") MultipartFile file, @PathVariable UUID authorId) {
//        PAYLOAD DI TIPO MULTIPART(formato per l'upload dei file)
//        profile_img è il nome esatto che va usato al campo del FormData
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        System.out.println(file.getContentType());
        this.authorService.profileImgUpload(file, authorId);
    }
}
