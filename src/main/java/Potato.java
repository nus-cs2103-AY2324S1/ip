import java.util.Scanner;
public class Potato {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] store = new Task[100];
        int size = 0;

        System.out.println("-----------------------------------------\n" +
                "Hello! I'm Potato\n" + "What can I do for you?\n" +
                "-----------------------------------------\n");

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("-----------------------------------------\n" +
                        "Bye. Hope to see you again soon!\n" +
                        "-----------------------------------------\n");
                break;
            } else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                store[index].setStatus(true);
                System.out.println("-----------------------------------------\n" +
                        "Nice! I've marked this task as done:\n" +
                        "[" + store[index].getStatus() + "] " +
                        store[index].getDescription() + "\n" +
                        "-----------------------------------------\n");
            } else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.substring(7a)) - 1;
                store[index].setStatus(false);
                System.out.println("-----------------------------------------\n" +
                        "Ok, I've marked this task as not done yet:\n" +
                        "[" + store[index].getStatus() + "] " +
                        store[index].getDescription() + "\n" +
                        "-----------------------------------------\n");
            } else if (input.equals("list")) {
                int count = 0;
                System.out.println("-----------------------------------------\n" +
                        "Here are the tasks in your list: ");
                for (Task t : store) {
                    if (t == null) break;
                    count ++;
                    System.out.println(String.valueOf(count) + "." +
                            "[" + t.getStatus() + "] " +
                            t.getDescription());
                }
                System.out.println("-----------------------------------------\n");
            } else {
                store[size] = new Task(input);
                size++;
                System.out.println("-----------------------------------------\n" +
                        "added: " + input + "\n" +
                        "-----------------------------------------\n");
            }
        }
    }
}
