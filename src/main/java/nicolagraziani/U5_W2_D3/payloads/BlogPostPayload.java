package nicolagraziani.U5_W2_D3.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@AllArgsConstructor
@ToString
public class BlogPostPayload {
    private String category;
    private String title;
    private String content;
    private int readingTime;
    private UUID authorId;
}
