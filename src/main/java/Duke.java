import java.util.Scanner;
public class Duke {
    private static final Task[] lst = new Task[100];
    private static int index = 0;

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
            checkInput(input);
            input = scanner.nextLine();
        }

        System.out.println("____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n"
        );
    }

    public static void checkInput(String str) {
        if (str.startsWith("mark")){
            String num = str.substring(5);
            int i = Integer.parseInt(num);
            Task t = lst[i - 1];
            t.markAsDone();
            System.out.println("____________________________________________________________\n"
                    + "Nice! I've marked this task as done:\n"
                    + "[" + t.getStatusIcon() + "] " + t.getDescription() + "\n"
                    + "____________________________________________________________\n"
            );
        } else if (str.startsWith("unmark")) {
            String num = str.substring(7);
            int i = Integer.parseInt(num);
            Task t = lst[i - 1];
            t.markAsNotDone();
            System.out.println("____________________________________________________________\n"
                    + "OK, I've marked this task as not done yet:\n"
                    + "[" + t.getStatusIcon() + "] " + t.getDescription() + "\n"
                    + "____________________________________________________________\n"
            );
        } else {
            addList(str);
        }
    }
    public static void addList(String str){
        if (!str.equals("list")) {
            lst[index] = new Task(str);
            index++;
            System.out.println("____________________________________________________________\n"
                    + "added: " + str + "\n"
                    + "____________________________________________________________\n"
            );
        } else {
            System.out.println("____________________________________________________________");
            for (int i = 1; i <= index; i++) {
                Task t = lst[i - 1];
                String s = t.getDescription();
                System.out.println(i + ". [" + t.getStatusIcon() + "] " + s);
            }
            System.out.println("____________________________________________________________");
        }
    }
}
