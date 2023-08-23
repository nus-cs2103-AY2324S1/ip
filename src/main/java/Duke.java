import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String name = "Harry Potter";
        String question = "What can I do for you?";
        System.out.println("Hello! I'm " + name + "\n" + question);
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!str.equals("bye")) {
            System.out.println("\t" + str);
            str = sc.nextLine();
        }
        System.out.println("\t" + "Bye. Hope to see you again soon!");
    }
}
