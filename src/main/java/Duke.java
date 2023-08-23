import java.util.Scanner;
public class Duke {
    private static final String[] lst = new String[100];
    private static int index = 0;
    public static void addList(String str){
        if (!str.equals("list")) {
            lst[index] = str;
            index++;
            System.out.println("____________________________________________________________\n"
                    + "added: " + str + "\n"
                    + "____________________________________________________________\n"
            );
        } else {
            System.out.println("____________________________________________________________");
            for (int i = 1; i <= index; i++) {
                String s = lst[i - 1];
                System.out.println(i + ". " + s);
            }
            System.out.println("____________________________________________________________");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "____________________________________________________________\n"
                + "Hello! I'm ET\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n"
        );

        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            addList(input);
            input = scanner.nextLine();
        }

        System.out.println("____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n"
        );
    }
}
