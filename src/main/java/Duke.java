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

        while (!str.equals("bye") && !str.equals("Bye")) {
            if (str.equals("list")){
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                System.out .println("Here are the tasks in your list:");
                for (int j = 0; j < i; j++){
                    System.out.println(j+1 +"." + task[j].getStatusIcon());
                }
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                str = sc.nextLine();
            } else if (str.equals("mark")) {
                int k = sc.nextInt() - 1;
                task[k].markAsDone();
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                System.out.println("Nice! I've marked this task as done:\n" + task[k].getStatusIcon());
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                str = sc.nextLine();
            } else if (str.equals("unmark")) {
                int k = sc.nextInt() - 1;
                task[k].unmark();
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                System.out.println("OK, I've marked this task as not done yet:\n" + task[k].getStatusIcon());
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                str = sc.nextLine();
            } else {
                if (str.equals("")) {
                    str = sc.nextLine();
                } else {
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("Added: " + str);
                    task[i] = new Task(str);
                    i++;
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                    str = sc.nextLine();
                }
            }
        }

        sc.close();
        System.out.println("\nBye. Hope to see you again soon!");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
    }
}
