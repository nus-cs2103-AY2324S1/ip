import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String horizontal = "____________________________________________________________";

        Bob bob = new Bob();
        bob.greet();

        while (true) {
            String ans = sc.nextLine();
            if (ans.equalsIgnoreCase("bye")) {
                break;
            } else if (ans.equalsIgnoreCase("list")) {
                bob.listOut();
            } else {
                bob.addTask(ans);
            }
        }

        bob.bye();
        sc.close();
    }
}

