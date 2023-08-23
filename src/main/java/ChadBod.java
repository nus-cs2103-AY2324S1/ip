package main.java;
import java.util.Scanner;

public class ChadBod {
    public static void main(String[] args) {
        System.out.println("Hello! I'm ChadBod.\n");
        System.out.println("What can I do for you?\n");
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            } else {
                System.out.println(input);
            }
        }
    }
}
