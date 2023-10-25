package kviak.ru.tasks;

import java.util.Scanner;

public class TaskB {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        String modifiedString = modifyString(s);
        System.out.println(modifiedString);
    }

    public static String modifyString(String s) {
        int yandexIndex = s.toLowerCase().indexOf("yandex");

        if (yandexIndex != -1) {
            String modifiedString = s.substring(0, yandexIndex) + "YandexCup" + s.substring(yandexIndex + 6);
            return modifiedString;
        } else {
            return s + "YandexCup";
        }
    }
}
