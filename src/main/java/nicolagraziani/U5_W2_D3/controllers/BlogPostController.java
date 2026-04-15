package nicolagraziani.U5_W2_D3.controllers;

import nicolagraziani.U5_W2_D3.entities.BlogPost;
import nicolagraziani.U5_W2_D3.payloads.BlogPostPayload;
import nicolagraziani.U5_W2_D3.services.BlogPostService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/blogposts")
public class BlogPostController {
    private final BlogPostService blogPostService;

    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping
    public Page<BlogPost> findAll(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "category") String sortBy) {
        return this.blogPostService.findAll(page, size, sortBy);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BlogPost saveBlogPost(@RequestBody BlogPostPayload body) {
        return this.blogPostService.saveBlogPost(body);
    }

    @GetMapping("/{blogPostId}")
    public BlogPost findById(@PathVariable UUID blogPostId) {
        return this.blogPostService.findById(blogPostId);
    }
}
