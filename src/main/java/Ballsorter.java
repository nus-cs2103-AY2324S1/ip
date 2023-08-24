import java.util.Scanner;

public class Ballsorter {
    public static void main(String[] args) {
        String line = "____________________________________________________________";

        System.out.println(line);
        System.out.println("Hello! I'm Ballsorter\nWhat can I do for you?");
        System.out.println(line);

        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println(input);
            System.out.println(line);
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
