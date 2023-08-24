import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("Hello! I am Nila");
        System.out.println("What can I do for you?");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -\n");

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        Task[] task = new Task[100];
        int i = 0;

        while (!str.equals("bye")) {
            String[] input = str.split(" ", 2);
            if (input[0].equals("list")){
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                System.out .println(" Here are the tasks in your list:");
                for (int j = 0; j < i; j++){
                    System.out.println(j+1 +"." + task[j].getStatusIcon());
                }
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                str = sc.nextLine();
            } else if (input[0].equals("mark")) {
                int index = Integer.parseInt(input[1]) - 1;
                task[index].markAsDone();
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                System.out.println(" Nice! I've marked this task as done:\n" + "   " + task[index].getStatusIcon());
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                str = sc.nextLine();
            } else if (input[0].equals("unmark")) {
                int index = Integer.parseInt(input[1]) - 1;
                task[index].unmark();
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                System.out.println(" OK, I've marked this task as not done yet:\n" + "   " + task[index].getStatusIcon());
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                str = sc.nextLine();
            } else if (input[0].equals("todo")) {
                String description = input[1];
                task[i] = new Todo(description);
                i++;
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                System.out.println(" Got it. I've added this task:\n" + "   " + task[i-1].getStatusIcon());
                System.out.println(" Now you have " + i +" tasks in the list.");
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                str = sc.nextLine();
            } else if (input[0].equals("deadline")) {
                String[] full_desc = input[1].split(" /by ");
                String description = full_desc[0];
                String by = full_desc[1];
                task[i] = new Deadline(description, by);
                i++;
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                System.out.println(" Got it. I've added this task:\n" + "   " + task[i-1].getStatusIcon());
                System.out.println(" Now you have " + i +" tasks in the list.");
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                str = sc.nextLine();
            } else if (input[0].equals("event")) {
                String[] full_desc = input[1].split(" /from | /to ");
                String description = full_desc[0];
                String from = full_desc[1];
                String to = full_desc[2];
                task[i] = new Event(description, from, to);
                i++;
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                System.out.println(" Got it. I've added this task:\n" + "   " + task[i-1].getStatusIcon());
                System.out.println(" Now you have " + i +" tasks in the list.");
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                str = sc.nextLine();
            } else {
                if (input[0].equals("")) {
                    input[0] = sc.nextLine();
                } else {
                    task[i] = new Task(input[0]);
                    i++;
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println(" Added: " + input[0]);
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                    str = sc.nextLine();
                    input = str.split(" ", 2);
                }
            }
        }

        sc.close();
        System.out.println("\nBye. Hope to see you again soon!");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
    }
}
