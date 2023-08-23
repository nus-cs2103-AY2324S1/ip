import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] tasks = new String[100];
        String name = "Chaty";
        int taskNo = 0;

        System.out.println("Hello! I'm " + name + "\n" +
                "What can I do for you?" + "\n\n");
        String next = scan.nextLine();
        while (!next.equals("bye")) {
            if (next.equals("list")) {
                for (int i = 0; i < taskNo ; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }} else {
                    tasks[taskNo] = next;
                    taskNo++;
                    System.out.println("added: " + next);
            }
            next = scan.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}