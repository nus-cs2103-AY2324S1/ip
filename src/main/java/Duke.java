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
                break;
            }
            t.add(s);
            s = sc.nextLine();
        }
        if (s.equals("bye")) {
            System.out.println("Bye! Auntie maggie see you later!");
        }
    }
}
