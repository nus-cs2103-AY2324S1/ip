import java.util.Scanner;
public class Qi {
    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Qi");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.println();
        Scanner sc = new Scanner(System.in);
        String[] tasks = new String[100];
        int numTasks = 0;
        while (true) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                break;
            }

            if (!str.equals("list")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     added: " + str);
                System.out.println("    ____________________________________________________________");
                System.out.println();
                tasks[numTasks] = str;
                numTasks++;

            } else {
                System.out.println("    ____________________________________________________________");
                for (int i = 0; i < numTasks; i++) {
                    System.out.println("     " + i + ". " + tasks[i]);
                }
                System.out.println("    ____________________________________________________________");
                System.out.println();
            }
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
