import java.util.*;
public class GrumpyGordon {
    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Oi! I'm Grumpy Gordon.");
        System.out.println("     Why are you bothering me?");
        System.out.println("    ____________________________________________________________");

        int taskCount = 0;
        String[] tasks = new String[100];
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        while (!str.equals("bye")) {
            if (!str.equals("list")) {
                tasks[taskCount] = str;
                taskCount++;
                System.out.println("    ____________________________________________________________");
                System.out.println("     added: " + str);
                System.out.println("    ____________________________________________________________");
                str = sc.nextLine();
            } else if (str.equals("list") && taskCount == 0) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     The list is empty, you donkey!");
                System.out.println("    ____________________________________________________________");
                str = sc.nextLine();
            } else {
                System.out.println("    ____________________________________________________________");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("     " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println("    ____________________________________________________________");
                str = sc.nextLine();
            }
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to never see you again.");
        System.out.println("    ____________________________________________________________");
    }
}
