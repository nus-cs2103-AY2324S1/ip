import java.util.*;
public class GrumpyGordon {
    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Oi! I'm Grumpy Gordon.");
        System.out.println("     Why are you bothering me?");
        System.out.println("    ____________________________________________________________");

        int taskIndex;
        int taskCount = 0;
        Task[] tasks = new Task[100];

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        while (!str.equals("bye")) {
            switch (str.split(" ")[0]) {
                case "list":
                    if (taskCount == 0) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     The list is empty, you donkey!");
                        System.out.println("    ____________________________________________________________");
                    } else {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Stop wasting time, go get it done!");
                        for (int i = 0; i < taskCount; i++) {
                            System.out.println("     " + (i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
                        }
                        System.out.println("    ____________________________________________________________");
                    }
                    str = sc.nextLine();
                    break;
                case ("mark"):
                    taskIndex = Integer.parseInt(str.split(" ")[1]) - 1;
                    tasks[taskIndex].markAsDone();
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Took you long enough!");
                    System.out.println("       [" + tasks[taskIndex].getStatusIcon() + "] " + tasks[taskIndex].description);
                    System.out.println("    ____________________________________________________________");
                    str = sc.nextLine();
                    break;
                case ("unmark"):
                    taskIndex = Integer.parseInt(str.split(" ")[1]) - 1;
                    tasks[taskIndex].markAsUndone();
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     My grandmother does it faster than you!");
                    System.out.println("       [" + tasks[taskIndex].getStatusIcon() + "] " + tasks[taskIndex].description);
                    System.out.println("    ____________________________________________________________");
                    str = sc.nextLine();
                    break;
                default:
                    tasks[taskCount] = new Task(str);
                    taskCount++;
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     added: " + str);
                    System.out.println("    ____________________________________________________________");
                    str = sc.nextLine();
            }
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to never see you again.");
        System.out.println("    ____________________________________________________________");
    }
}
