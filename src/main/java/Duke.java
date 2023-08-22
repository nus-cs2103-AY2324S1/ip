import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String horizontal = "____________________________________________________________";
        String chatBotName = "bob";

        String ln1 = String.format("Hello! I'm %s", chatBotName);
        String ln2 = "What can I do for you?";
        String ln3 = "Bye. Hope to see you again soon!";

        System.out.println(horizontal);
        System.out.println(ln1);
        System.out.println(ln2);
        System.out.println(horizontal);
        System.out.println(ln3);
        System.out.println(horizontal);

        sc.close();
    }
}
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
