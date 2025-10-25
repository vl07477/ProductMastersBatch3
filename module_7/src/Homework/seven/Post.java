package Homework.seven;
import java.util.concurrent.atomic.AtomicLong;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Post — модель поста
 */
class Post {
    private static final AtomicLong ID_GENERATOR = new AtomicLong(0);

    private final long id;
    private final Account author;
    private final String content;
    private final LocalDateTime createdAt;
    private int likes;
    private int reposts;

    public Post(Account author, String content) {
        this.id = ID_GENERATOR.incrementAndGet();
        this.author = author;
        this.content = content;
        this.likes = 0;
        this.reposts = 0;
        this.createdAt = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public Account getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public int getLikes() {
        return likes;
    }

    public int getReposts() {
        return reposts;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void incrementLikes() {
        likes++;
    }

    public void incrementReposts() {
        reposts++;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("Post{id=%d, author=%s, content='%s', likes=%d, reposts=%d, createdAt=%s}",
                id, author.getName(), content, likes, reposts, createdAt.format(fmt));
    }
}