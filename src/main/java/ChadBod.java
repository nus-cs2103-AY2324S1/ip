package main.java;
import java.util.Scanner;
import java.util.ArrayList;

public class ChadBod {

    private static final ArrayList<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello! I'm ChadBod.\n");
        System.out.println("What can I do for you?\n");
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < ChadBod.tasks.size(); i ++) {
                    System.out.printf("%d: %s\n", i + 1, ChadBod.tasks.get(i));
                }
            } else {
                ChadBod.tasks.add(input);
                System.out.printf("added: %s\n", input);
            }
        }
    }
}
