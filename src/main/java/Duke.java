import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Three Wolves B.\n" + "What can I do for you?\n");
        Scanner sc = new Scanner(System.in);
        ArrayList<String> lst = new ArrayList<>();
        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            if (cmd.equals("list")) {
                int counter = 1;
                lst.forEach(x -> System.out.println(lst.indexOf(x) + 1 + ". " + x + "\n"));
            } else {
                lst.add(cmd);
                System.out.println("added: " + cmd + "\n");
            }
            cmd = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}
