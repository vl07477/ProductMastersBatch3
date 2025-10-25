package Homework.seven;
import java.util.Objects;
import java.util.Locale;


class Account {
    private final String name;

    public Account(String name) {
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

        Account user = (Account) o;
        return name.equalsIgnoreCase(user.name);
    }

    @Override
    public int hashCode() {
        return name.toLowerCase(Locale.ROOT).hashCode();
    }
}
