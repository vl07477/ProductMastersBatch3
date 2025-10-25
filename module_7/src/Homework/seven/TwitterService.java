package Homework.seven;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;
import java.util.Comparator;
import java.util.stream.Collectors;


class TwitterService {
    private final List<Post> posts = new ArrayList<>();

    // Create
    public Post createPost(Account author, String content) {
        Post p = new Post(author, content);
        posts.add(p);
        return p;
    }

    // Like by id
    public boolean likePost(long id) {
        Optional<Post> op = getPostById(id);
        op.ifPresent(Post::incrementLikes);
        return op.isPresent();
    }

    // Repost: increments repost count on original and creates a new post from current user
    public Post repostPost(long originalId, Account repostingUser) {
        Optional<Post> op = getPostById(originalId);
        if (op.isEmpty()) {
            throw new NoSuchElementException("Post not found");
        }
        Post original = op.get();
        original.incrementReposts();

        String newContent = String.format("Репост: %s", original.getContent());
        Post newPost = new Post(repostingUser, newContent);
        posts.add(newPost);
        return newPost;
    }

    // Get by id
    public Optional<Post> getPostById(long id) {
        return posts.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    // Return all posts sorted by date desc (новые -> старые)
    public List<Post> getAllPosts() {
        return posts.stream()
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }

    // Top N by likes (descending)
    public List<Post> getTopNByLikes(int n) {
        return posts.stream()
                .sorted(Comparator.comparingInt(Post::getLikes).reversed()
                        .thenComparing(Comparator.comparing(Post::getCreatedAt).reversed()))
                .limit(n)
                .collect(Collectors.toList());
    }

    // Posts by user (новые -> старые)
    public List<Post> getPostsByUser(Account user) {
        return posts.stream()
                .filter(p -> p.getAuthor().equals(user))
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }

    // Add some posts from users
    public void loadInitialPosts() {
        Account tima = new Account("Tima");
        Account toty = new Account("Toty");
        Account knyaz = new Account("Knyaz");
        Account vlada = new Account("Vlada");

        createPost(tima, "Привет, мир!");
        createPost(toty, "На обед будет курица");
        createPost(knyaz, "Я работаю в банке");
        createPost(vlada, "Домашняя работа 7 неделя вроде");
    }
}