package com.company;

import javax.imageio.IIOException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;


public class Main {
    public static void main(String[] args) {
        Task1();
        try {
            Task2();
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }

        Task3();

        try {
            Task4();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            Task5();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            Task6();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            Task7();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    static void Task1() {
        try (FileReader reader = new FileReader("input_task1.txt")) {
            // читаем посимвольно
            int c;
            int maxmax = 0;
            int max = 0;
            while ((c = reader.read()) != -1) {
                if (c == 48) {
                    max++;
                    if (max > maxmax) {
                        maxmax = max;
                    }
                } else {
                    max = 0;
                }
            }

            System.out.println(maxmax);

            try {
                WriteToFile(String.valueOf(maxmax), "output_task_1.txt", false);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

    static void Task2() throws IOException {
        Path inputFile = Path.of("input_task2.txt");
        String inputString = Files.readString(inputFile);
        System.out.println(inputString);

        String left = "<--<<";
        String right = ">>-->";
        int counter = 0;

        for (int i = 0; i < inputString.length() - 5; i++) {
            String sub = inputString.substring(i, i + 5);
            if (left.equals(sub) || right.equals(sub)) {
                counter++;
            }
        }

        try {
            WriteToFile(String.valueOf(counter), "output_task_2.txt", false);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void Task3() { // aaaa
        try (FileReader reader = new FileReader("input_task3.txt")) {
            // читаем посимвольно
            int c;
            String main = "";
            String store = "";
            int pos = 0;

            while ((c = reader.read()) != -1) {
                char ch = (char) c;

                if (main.length() == 0) {
                    main += ch;
                    continue;
                }

                store += ch;
                if (ch == main.charAt(pos)) {
                    if (pos + 1 == main.length()) {
                        pos = 0;
                        continue;
                    }
                    pos++;
                } else {
                    main += store.charAt(0);
                    store = store.substring(1);

                }
            }

            System.out.println(main.length() + " = " + main + " _ " + store);


            if (!main.contains(store)) {
                main += store;
            }


            try {
                WriteToFile(String.valueOf(main.length()), "output_task_3.txt", false);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

    static void Task4() throws IOException {
        Scanner scanner = new Scanner(new File("input_task4.txt"));
        int n = scanner.nextInt();
        System.out.println(n);
        int[] arr = new int[n];

        int i = 0;
        int first = scanner.nextInt();
        int max = first;
        int min = first;
        int maxPos = i;
        int minPos = i;
        int sum = 0;
        int mult = 0;
        if (first > 0) sum += first;
        arr[i] = first;
        i++;

        while (scanner.hasNextInt()) {
            int next = scanner.nextInt();

            if (next > max) {
                max = next;
                maxPos = i;
            }
            if (next < min) {

                min = next;
                minPos = i;
            }

            if (next > 0) {
                sum += next;
            }

            arr[i++] = next;
        }

        int firstPos;
        int secondPos;

        if (maxPos < minPos) {
            firstPos = maxPos + 1;
            secondPos = minPos - 1;
        } else {
            firstPos = minPos + 1;
            secondPos = maxPos - 1;
        }

        if (secondPos - firstPos > 0) {
            mult = 1;
        }

        for (int j = firstPos; j <= secondPos; j++) {
            mult *= arr[j];
        }

        try {
            WriteToFile(sum + " " + mult, "output_task_4.txt", false);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void Task5() throws IOException {
        Scanner scanner = new Scanner(new File("input_task5.txt"));
        int n = scanner.nextInt();
        int[] arr = new int[n];

        int i = 0;
        while (scanner.hasNextInt()) {
            int next = scanner.nextInt();
            arr[i++] = next;
        }

        int res = 0;
        int dv = 1;
        for (int j = 0; j < n; j++) {
            int max = arr[j];
            for (int k = j + 1; k < n; k++) {
                if (max < arr[k]) {
                    max = arr[k];
                    continue;
                }
            }

            if (arr[j] == max) {
                res += arr[j] * dv;
                dv = 1;
                continue;
            }
            dv++;

        }

        try {
            WriteToFile(String.valueOf(res), "output_task_5.txt", false);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void Task6() throws IOException {
        Path inputFile = Path.of("input_task6.txt");
        String inputString = Files.readString(inputFile);
        System.out.println(inputString);
        char max = (char) max((int) inputString.charAt(0), (int) inputString.charAt(1));
        char min = (char) Math.min((int) inputString.charAt(0), (int) inputString.charAt(1));
        String res = inputString.substring(0, 2);
        int change = -1;

        for (int i = 2; i < inputString.length(); i++) {
            System.out.println(inputString.charAt(i));


            if (change != -1) {
                System.out.println(inputString.charAt(i) + " changed");
                if (change == 1) res += min;
                else res += max;
                continue;
            }

            if (inputString.charAt(i) == max || inputString.charAt(i) == min) {
                res += inputString.charAt(i);
                continue;
            }

            if (abs((int) max - (int) inputString.charAt(i)) > abs((int) min - (int) inputString.charAt(i))) {
                res += min;
                if ((int) min - (int) inputString.charAt(i) > 0) {
                    change = 1;
                } else {
                    change = 0;
                }
            } else {
                res += max;
                if ((int) max - (int) inputString.charAt(i) > 0) {
                    change = 1;
                } else {
                    change = 0;
                }
            }
        }

        try {
            WriteToFile(res, "output_task_6.txt", false);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void WriteToFile(String s, String fileName, Boolean append) throws IOException {
        FileWriter writer = new FileWriter(fileName, append);
        writer.write(s);
        writer.flush();
    }

    static void RecTask7(int i, int n, String str) {
//        System.out.println(i + " " + n + " | " + str);
        if (n == 0) {
            str += "\n";
            try {
                WriteToFile(str, "output_task_7.txt", true);
            } catch (IOException ex) {

            }
        }
        for (int j = i; j <= n; j++) {
            str += " " + j;
            RecTask7(j, n - j, str);
            str = str.substring(0, str.length() - 2);
        }

    }

    static void Task7() throws IOException {
        try {
            WriteToFile("", "output_task_7.txt", false);
        } catch (IOException ex) {}

        Scanner scanner = new Scanner(new File("input_task7.txt"));
        int n = scanner.nextInt();
        String str = "";
        System.out.println(n);

        RecTask7(1, n, str);
    }


//    i = 1;
//    v = 5;
//    x = 10;
//    l = 50;
//    c = 100;
//    d = 500;
//    m = 1000;
//
//    static int RimTo10(String rim){
//        int n = 0;
//
//        return n;
//    }
//
//    static int Nod(int a, int b) {
//        int nod = 0;
//        return nod;
//    }
//
//    static void Task8() throws IOException {
//        Path inputFile = Path.of("input_task6.txt");
//        String inputString = Files.readString(inputFile);
//        System.out.println(inputString);
//    }

}

