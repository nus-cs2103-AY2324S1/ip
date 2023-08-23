import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tasklist = new String[100];
        int count = 0;

        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Charlie\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("____________________________________________________________\n" +
                        " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n");
                break;
            } else if (input.equals("list")) {
                printlist(tasklist, count);
                continue;
            }

            tasklist[count++] = input;

            System.out.println("____________________________________________________________\n" +
                    "added: " +
                    input +
                    "\n____________________________________________________________\n");
        }

        scanner.close();


    }
    private static void printlist(String[] arr, int count) {
        System.out.println("____________________________________________________________\n");
        for (int i =0; i < count; i++) {
            System.out.printf("%d.) %s%n", i + 1, arr[i]);
        }
        System.out.println("____________________________________________________________\n");
    }

}

