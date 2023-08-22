import java.util.*;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Maggie " +
                "\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        while (!s.equals("bye")) {
            System.out.println(s);
            s = sc.next();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
