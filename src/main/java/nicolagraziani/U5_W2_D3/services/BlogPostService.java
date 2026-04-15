package nicolagraziani.U5_W2_D3.services;

import lombok.extern.slf4j.Slf4j;
import nicolagraziani.U5_W2_D3.entities.Author;
import nicolagraziani.U5_W2_D3.entities.BlogPost;
import nicolagraziani.U5_W2_D3.exceptions.NotFoundException;
import nicolagraziani.U5_W2_D3.payloads.BlogPostPayload;
import nicolagraziani.U5_W2_D3.repositories.BlogPostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class BlogPostService {
    private final AuthorService authorService;
    private final BlogPostRepository blogPostRepository;

    public BlogPostService(BlogPostRepository blogPostRepository, AuthorService authorService) {
        this.blogPostRepository = blogPostRepository;
        this.authorService = authorService;
    }

    public BlogPost saveBlogPost(BlogPostPayload body) {
        Author author = this.authorService.findAuthorById(body.getAuthorId());
        BlogPost newBlogPost = new BlogPost(body.getCategory(), body.getTitle(), body.getContent(), body.getReadingTime(), author);
        this.blogPostRepository.save(newBlogPost);
        log.info("Il Post {} è stato pubblicato", body.getTitle());
        return newBlogPost;
    }

    public Page<BlogPost> findAll(int page, int size, String sortBy) {
        if (size > 100 || size < 0) size = 10;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.blogPostRepository.findAll(pageable);
    }

    public BlogPost findBlogPostById(UUID blogPostId) {
        return this.blogPostRepository.findById(blogPostId).orElseThrow(() -> new NotFoundException(blogPostId));
    }

    public BlogPost findBlogPostByIdAndUpdate(UUID blogPostId, BlogPostPayload body) {
        BlogPost found = this.findBlogPostById(blogPostId);
        Author author = this.authorService.findAuthorById(body.getAuthorId());
        found.setCategory(body.getCategory());
        found.setTitle(body.getTitle());
        found.setContent(body.getContent());
        found.setReadingTime(body.getReadingTime());
        found.setAuthor(author);

        BlogPost saved = this.blogPostRepository.save(found);

        log.info("Il Post {} è stato modificato con successo", saved.getTitle());

        return saved;
    }

    public void findByIdAndDelete(UUID blogPostId) {
        BlogPost found = this.findBlogPostById(blogPostId);
        this.blogPostRepository.delete(found);
        log.info("Il Post con id {} è stato eliminato correttamente", blogPostId);
    }
}
