package nicolagraziani.U5_W2_D3.controllers;

import nicolagraziani.U5_W2_D3.entities.BlogPost;
import nicolagraziani.U5_W2_D3.payloads.BlogPostDTO;
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

    //    GET ALL
    @GetMapping
    public Page<BlogPost> findAll(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "category") String sortBy) {
        return this.blogPostService.findAll(page, size, sortBy);
    }

    //   POST
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BlogPost saveBlogPost(@RequestBody BlogPostDTO body) {
        return this.blogPostService.saveBlogPost(body);
    }

    //    GET BY ID
    @GetMapping("/{blogPostId}")
    public BlogPost findById(@PathVariable UUID blogPostId) {
        return this.blogPostService.findBlogPostById(blogPostId);
    }

    //    PUT
    @PutMapping("/{blogPostId}")
    public BlogPost getBlogPostByIdAndUpdate(@PathVariable UUID blogPostId, @RequestBody BlogPostDTO body) {
        return this.blogPostService.findBlogPostByIdAndUpdate(blogPostId, body);
    }

    //   DELETE
    @DeleteMapping("/{blogPostId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getBlogPostByIdAndDelete(@PathVariable UUID blogPostId) {
        this.blogPostService.findByIdAndDelete(blogPostId);
    }
}
