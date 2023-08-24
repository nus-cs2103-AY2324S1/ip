import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasklist = new Task[100];
        int count = 0;

        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Charlie\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                exitBot();
                break;
            }   else if (input.equals("list")) {
                    printlist(tasklist, count);

            }   else if (input.startsWith("mark")) {
                    markResponse(input, tasklist);

            }   else if (input.startsWith("unmark")) {
                    unmarkResponse(input, tasklist);

            } else {
                tasklist[count++] = new Task(input);

                System.out.println("____________________________________________________________\n" +
                        "added: " +
                        input +
                        "\n____________________________________________________________\n");
            }


        }

        scanner.close();


    }
    private static void printlist(Task[] arr, int count) {
        System.out.println("____________________________________________________________\n");
        for (int i =0; i < count; i++) {
            System.out.printf("%d.) %s%n", i + 1, arr[i].toString());
        }
        System.out.println("____________________________________________________________\n");
    }

    private static void markResponse(String input, Task[] tasklist) {
        int spaceIndex = input.indexOf(" ");
        int result = Integer.parseInt(input.substring(spaceIndex + 1)) - 1;
        tasklist[result].mark();
        System.out.println("____________________________________________________________\n" +
                "Nice! I've marked this task as done: \n" +
                tasklist[result].toString() +
                "\n____________________________________________________________\n");
    }

    private static void unmarkResponse(String input, Task[] tasklist) {
        int spaceIndex = input.indexOf(" ");
        int result = Integer.parseInt(input.substring(spaceIndex + 1)) - 1;
        tasklist[result].unmark();
        System.out.println("____________________________________________________________\n" +
                "OK, I've marked this task as not done yet: \n" +
                tasklist[result].toString() +
                "\n____________________________________________________________\n");
    }
    

    private static void exitBot() {
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }
}

