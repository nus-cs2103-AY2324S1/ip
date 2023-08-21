import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/

        // Hello! I'm [YOUR CHATBOT NAME]

        String print1 = "Hello! I'm Afro\n"
                        + "What can I do for you?\n";

        int currIndex = 1;
        String str;
        String[] strArr = new String[100];

        System.out.println(print1);

        Scanner sc = new Scanner(System.in);

        while (true) {
            str = sc.nextLine();

            if (str.equals("bye")) {
                break;
            } else if (str.equals("list")) {
                for (int i = 1; i < currIndex; i++) {
                    System.out.println(i + ". " + strArr[i]);
                }
                continue;
            }
            
            strArr[currIndex] = str;
            currIndex++;
            System.out.println("added: " + str);
        }

        sc.close();
        System.out.println("Bye. Hope to see you again soon!");

    }
}
