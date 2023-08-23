import java.util.ArrayList;
import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");

        ArrayList<String> list = new ArrayList<String>();

        while (true) {
            Scanner obj = new Scanner(System.in);
            String input = obj.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (input.equals("list")) {
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println(i + ". " + list.get(i - 1));
                }
            } else {
                list.add(input);
                System.out.println("added: " + input);
            }
        }
    }

}
