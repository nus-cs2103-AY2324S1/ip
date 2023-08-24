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
                    + t + "\n"
                    + "____________________________________________________________\n"
            );
        } else if (str.startsWith("unmark")) {
            String num = str.substring(7);
            int i = Integer.parseInt(num);
            Task t = lst[i - 1];
            t.markAsNotDone();
            System.out.println("____________________________________________________________\n"
                    + "OK, I've marked this task as not done yet:\n"
                    + t + "\n"
                    + "____________________________________________________________\n"
            );
        } else {
            addList(str);
        }
    }
    public static void addList(String str){
        if (str.equals("list")) {
            System.out.println("____________________________________________________________\n"
                    + "Here are the tasks in your list:");
            for (int i = 1; i <= index; i++) {
                Task t = lst[i - 1];
                System.out.println(i + ". " + t.toString());
            }
            System.out.println("____________________________________________________________");
        } else {
            if (str.startsWith("todo")) {
                String des = str.substring(5);
                Todo todo = new Todo(des);
                lst[index] = todo ;

            } else if (str.startsWith("deadline")) {
                String[] words = str.split("/");
                String des = words[0].substring(9);
                String by = words[1].substring(3);
                Deadline dl = new Deadline(des, by);
                lst[index] = dl ;

            } else if (str.startsWith("event")) {
                String[] words = str.split("/");
                String des = words[0].substring(6);
                String from = words[1].substring(5);
                String to = words[2].substring(3);
                Event event = new Event(des, from, to);
                lst[index] = event ;

            }

            index++;
            System.out.println("____________________________________________________________\n"
                    + "Got it. I've added this task:\n"
                    + lst[index - 1].toString() + "\n"
                    + "Now you have " + index + " tasks in the list.\n"
                    + "____________________________________________________________\n"
            );
        }
    }
}
