import java.util.*;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Auntie Maggie " +
                "\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        Tasks t = new Tasks();
        while (!s.equals("bye")) {
            if (s.equals("list")) {
                t.list();
                s = sc.nextLine();
            }else if (s.matches(".*\\bmark\\b.*")) {
                String[] parts = s.split(" ");
                int number = Integer.parseInt(parts[1]);
                t.mark(number - 1);
                s = sc.nextLine();
            } else if (s.matches(".*\\bunmark\\b.*")) {
                String[] parts = s.split(" ");
                int number = Integer.parseInt(parts[1]);
                t.unmark(number - 1);
                s = sc.nextLine();
            } else {
                t.add(s);
                s = sc.nextLine();
            }
        }
        if (s.equals("bye")) {
            System.out.println("Bye! Auntie maggie see you later!");
        }
    }
}
