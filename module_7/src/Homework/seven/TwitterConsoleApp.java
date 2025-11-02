package Homework.seven;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.Optional;

@Getter
@Setter
public class TwitterConsoleApp {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Введите ваше имя: ");
        String name = scanner.nextLine().trim();
        while (name.isEmpty()) {
            System.out.print("Имя не может быть пустым. Введите ваше имя: ");
            name = scanner.nextLine().trim();
        }
        Account currentUser = new Account(name);

        TwitterService service = new TwitterService();
        service.loadInitialPosts(); // добавляем стартовые посты

        System.out.println("Добро пожаловать, " + currentUser.getName() + "!");
        System.out.println("Добавлены стартовые посты.");
        showMenu(service, currentUser);
    }

    private static void showMenu(TwitterService service, Account currentUser) {
        while (true) {
            System.out.println();
            System.out.println("=== Twitter Console ===");
            System.out.println("1. Написать пост");
            System.out.println("2. Лайкнуть пост");
            System.out.println("3. Сделать репост");
            System.out.println("4. Показать все посты");
            System.out.println("5. Показать популярные посты");
            System.out.println("6. Показать мои посты");
            System.out.println("7. Выход");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    createPostFlow(service, currentUser);
                    break;
                case "2":
                    likePostFlow(service);
                    break;
                case "3":
                    repostFlow(service, currentUser);
                    break;
                case "4":
                    showAllPosts(service);
                    break;
                case "5":
                    showPopularPostsFlow(service);
                    break;
                case "6":
                    showMyPosts(service, currentUser);
                    break;
                case "7":
                    System.out.println("Выход…");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static void createPostFlow(TwitterService service, Account user) {
        System.out.print("Введите текст поста (макс. 280 символов): ");
        String text = scanner.nextLine();
        if (text.length() > 280) {
            System.out.println("Ошибка: текст больше 280 символов. Пост не добавлен.");
            return;
        }
        Post post = service.createPost(user, text);
        System.out.println("Пост добавлен! ID=" + post.getId());
    }

    private static void likePostFlow(TwitterService service) {
        System.out.print("Введите ID поста, который хотите лайкнуть: ");
        String idStr = scanner.nextLine().trim();
        try {
            long id = Long.parseLong(idStr);
            boolean ok = service.likePost(id);
            if (ok) System.out.println("Пост " + id + " успешно лайкнут.");
            else System.out.println("Пост с таким ID не найден.");
        } catch (NumberFormatException e) {
            System.out.println("Неверный ID.");
        }
    }

    private static void repostFlow(TwitterService service, Account currentUser) {
        System.out.print("Введите ID поста, который хотите репостнуть: ");
        String idStr = scanner.nextLine().trim();
        try {
            long id = Long.parseLong(idStr);
            Optional<Post> original = service.getPostById(id);
            if (original.isEmpty()) {
                System.out.println("Пост с таким ID не найден.");
                return;
            }
            Post newPost = service.repostPost(id, currentUser);
            System.out.println("Репост создан с ID=" + newPost.getId() + ". Репосты оригинала: " + original.get().getReposts());
        } catch (NumberFormatException e) {
            System.out.println("Неверный ID.");
        }
    }

    private static void showAllPosts(TwitterService service) {
        System.out.println("Все посты:");
        List<Post> posts = service.getAllPosts();
        if (posts.isEmpty()) {
            System.out.println("(постов нет)");
            return;
        }
        posts.forEach(p -> System.out.println(p));
    }

    private static void showPopularPostsFlow(TwitterService service) {
        System.out.print("Сколько популярных постов показать? ");
        String nStr = scanner.nextLine().trim();
        try {
            int n = Integer.parseInt(nStr);
            if (n <= 0) {
                System.out.println("Число должно быть положительным.");
                return;
            }
            List<Post> popular = service.getTopNByLikes(n);
            if (popular.isEmpty()) {
                System.out.println("(постов нет)");
                return;
            }
            System.out.println("Популярные посты (по убыванию лайков):");
            popular.forEach(p -> System.out.println(p));
        } catch (NumberFormatException e) {
            System.out.println("Неверное число.");
        }
    }

    private static void showMyPosts(TwitterService service, Account currentUser) {
        System.out.println("Мои посты:");
        List<Post> mine = service.getPostsByUser(currentUser);
        if (mine.isEmpty()) {
            System.out.println("(у вас пока нет постов)");
            return;
        }
        mine.forEach(p -> System.out.println(p));
    }
}