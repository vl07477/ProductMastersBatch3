package Homework.seven;
import java.util.Locale;
import java.util.Objects;

/**
 * User — модель пользователя
 */
class User {
    private final String name;

    public User(String name) {
        this.name = Objects.requireNonNull(name).trim();
    }

    public String getName() {
        return name;
    }

    // Сравниваем пользователей по имени (упрощённо)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return name.equalsIgnoreCase(user.name);
    }

    @Override
    public int hashCode() {
        return name.toLowerCase(Locale.ROOT).hashCode();
    }
}