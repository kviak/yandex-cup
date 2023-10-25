package kviak.ru.tasks;

import java.util.HashSet;
import java.util.Scanner;

public class TaskA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // Максимальное количество первичных баллов для каждого критерия
        int m = scanner.nextInt(); // Количество критериев
        int[] a = new int[m]; // Массив с оценками критериев

        for (int i = 0; i < m; i++) {
            a[i] = scanner.nextInt();
        }

        calcArtCriticue(a);
    }

    public static void calcArtCriticue(int[] nums){
        long amountPoint = 0;
        long amountNotNull = 0;
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            amountPoint = amountPoint + ((nums[i] * nums[i]) + (nums[i] * amountNotNull));
            if (nums[i] > 0 && set.add(nums[i])) amountNotNull++;
        }
        System.out.println(amountPoint);
    }
}
