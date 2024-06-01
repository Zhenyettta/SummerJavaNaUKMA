package com.ZhenyokAndVityok;

import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        // Завдання 1: Створити 3 хеш результати з використанням MessageDigest і 3-ох різних алгоритмів
        String input = "Hello, World!";
        String[] algorithms = {"MD5", "SHA-1", "SHA-256"};
        for (String algorithm : algorithms) {
            String hash = generateMessageDigestHash(input, algorithm);
            System.out.println("MessageDigest (" + algorithm + "): " + hash);
        }
        System.out.println("--------------------");

        // Завдання 2: Створити 3 хеш результати SecureRandom і 3-ох різних алгоритмів
        String[] secureAlgorithms = {"SHA1PRNG", "Windows-PRNG", "DRBG"};
        for (String algorithm : secureAlgorithms) {
            try {
                String random = generateSecureRandomHash(algorithm);
                System.out.println("SecureRandom (" + algorithm + "): " + random);
            } catch (NoSuchAlgorithmException e) {
                System.out.println("SecureRandom algorithm " + algorithm + " is not available.");
            }
        }
        System.out.println("--------------------");

        // Завдання 3: Створити 2 власні класи з визначеними equals() i hashCode()
        HashMap<CorrectClass, String> correctMap = new HashMap<>();
        correctMap.put(new CorrectClass(1, "One"), "Value 1");
        correctMap.put(new CorrectClass(2, "Two"), "Value 2");

        HashMap<IncorrectClass, String> incorrectMap = new HashMap<>();
        incorrectMap.put(new IncorrectClass(1, "One"), "Value 1");
        incorrectMap.put(new IncorrectClass(2, "Two"), "Value 2");

        System.out.println("Correct Map: " + correctMap);
        System.out.println("Incorrect Map: " + incorrectMap);
        System.out.println("--------------------");

        // Завдання 4: Зберегти результати хешів у текстовий файл в репозиторії
        try (FileWriter writer = new FileWriter("hash_results.txt")) {
            writer.write("MessageDigest Hashes:\n");
            for (String algorithm : algorithms) {
                String hash = generateMessageDigestHash(input, algorithm);
                writer.write(algorithm + ": " + hash + "\n");
            }

            writer.write("\nSecureRandom Results:\n");
            for (String algorithm : secureAlgorithms) {
                try {
                    String random = generateSecureRandomHash(algorithm);
                    writer.write(algorithm + ": " + random + "\n");
                } catch (NoSuchAlgorithmException e) {
                    writer.write("SecureRandom algorithm " + algorithm + " is not available.\n");
                }
            }
        }

        // Завдання 5: Завантажити на GitHub та прикріпити посилання на репозиторій
        // Цей крок виконується вручну після створення репозиторію та завантаження файлів
    }

    private static String generateMessageDigestHash(String input, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        byte[] hashBytes = md.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private static String generateSecureRandomHash(String algorithm) throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance(algorithm);
        byte[] randomBytes = new byte[16];
        sr.nextBytes(randomBytes);
        StringBuilder sb = new StringBuilder();
        for (byte b : randomBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}

class CorrectClass {
    private int id;
    private String name;

    public CorrectClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CorrectClass that = (CorrectClass) o;
        return id == that.id && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(id);
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CorrectClass{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}

class IncorrectClass {
    private int id;
    private String name;

    public IncorrectClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        return true;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "IncorrectClass{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
