import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String tasks[] = new String[100];


        System.out.println("____________________________________________________________\n" +
                "Hello! I'm CarrotCake\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n");

        String input = scanner.nextLine();
        int count = 0;

        while (!input.toLowerCase().equals("bye")) {
            System.out.println("____________________________________________________________\n");
            if (input.toLowerCase().equals("list")) {
                //Print tasks
                for (int i = 0; i < count; i++) {
                    System.out.print(Integer.toString(i+1) + ". " + tasks[i] + "\n");
                }
                System.out.println("____________________________________________________________\n");
                input = scanner.nextLine();
                continue;
            }

            System.out.println("added: " + input +
                    "\n____________________________________________________________\n");

            tasks[count] = input;
            input = scanner.nextLine();
            count++;
        }

        System.out.println("____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }
}
