package Homework.six.medium;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        // Получаем файл words.txt из ресурсов
        InputStream inputStream = AppMain.class.getClassLoader().getResourceAsStream("words.txt");

        if (inputStream == null) {
            System.out.println("Файл words.txt не найден.");
            return;
        }

        HashSet<String> uniqueWords = new HashSet<>();
        HashMap<String, Integer> wordFrequency = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] words = line.toLowerCase().replaceAll("[^a-zа-яё0-9 ]", "").split("\\s+");

                for (String word : words) {
                    if (word.isEmpty()) continue;

                    uniqueWords.add(word);
                    wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                }
            }

            System.out.println("Уникальные слова:");
            for (String word : uniqueWords) {
                System.out.println(word);
            }

            System.out.println("\nЧастота слов:");
            for (String word : wordFrequency.keySet()) {
                System.out.println(word + ": " + wordFrequency.get(word));
            }

        } catch (Exception e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}


