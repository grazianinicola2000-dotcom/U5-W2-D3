package nicolagraziani.U5_W2_D3.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "blog_posts")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BlogPost {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID blogPostId;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String title;
    @Column(name = "cover_img_url")
    private String coverUrl;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private int readingTime; //Minute
    @ManyToOne
    @JoinColumn(nullable = false)
    private Author author;

    public BlogPost(String category, String title, String content, int readingTime, Author author) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.readingTime = readingTime;
        this.coverUrl = "https://picsum.photos/300/300";
        this.author = author;
    }

}
