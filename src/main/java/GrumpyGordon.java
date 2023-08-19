import java.util.*;
public class GrumpyGordon {
    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Oi! I'm Grumpy Gordon.");
        System.out.println("     Why are you bothering me?");
        System.out.println("    ____________________________________________________________");

        int taskIndex;
        String taskArgument;
        int taskCount = 0;
        Task[] tasks = new Task[100];

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        while (!str.equals("bye")) {
            switch (str.split(" ")[0]) {
                case ("list"):
                    if (taskCount == 0) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     The list is empty, you donkey!");
                        System.out.println("    ____________________________________________________________");
                    } else {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Stop wasting time, go get it done!");
                        for (int i = 0; i < taskCount; i++) {
                            System.out.println("     " + (i + 1) + "." + tasks[i].toString());
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
                    System.out.println("       " + tasks[taskIndex].toString());
                    System.out.println("    ____________________________________________________________");
                    str = sc.nextLine();
                    break;
                case ("unmark"):
                    taskIndex = Integer.parseInt(str.split(" ")[1]) - 1;
                    tasks[taskIndex].markAsUndone();
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     My grandmother does it faster than you!");
                    System.out.println("       " + tasks[taskIndex].toString());
                    System.out.println("    ____________________________________________________________");
                    str = sc.nextLine();
                    break;
                case ("todo"):
                    taskArgument = str.split(" ", 2)[1];
                    String todoDescription = taskArgument;
                    tasks[taskCount] = new Todo(todoDescription);
                    taskCount++;
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + tasks[taskCount - 1].toString());
                    System.out.println("     Now you have " + taskCount + (taskCount > 1 ? " tasks" : " task") + " in the list.");
                    System.out.println("    ____________________________________________________________");
                    str = sc.nextLine();
                    break;
                case ("deadline"):
                    taskArgument = str.split(" ", 2)[1];
                    String deadlineDescription = taskArgument.split(" /by ")[0];
                    String deadlineBy = taskArgument.split(" /by ")[1];
                    tasks[taskCount] = new Deadline(deadlineDescription, deadlineBy);
                    taskCount++;
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + tasks[taskCount - 1].toString());
                    System.out.println("     Now you have " + taskCount + (taskCount > 1 ? " tasks" : " task") + " in the list.");
                    System.out.println("    ____________________________________________________________");
                    str = sc.nextLine();
                    break;
                case ("event"):
                    taskArgument = str.split(" ", 2)[1];
                    String eventDescription = taskArgument.split(" /from ")[0];
                    String eventFrom = taskArgument.split(" /from ")[1].split("/to")[0];
                    String eventTo = taskArgument.split("/to")[1];
                    tasks[taskCount] = new Event(eventDescription, eventFrom, eventTo);
                    taskCount++;
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + tasks[taskCount - 1].toString());
                    System.out.println("     Now you have " + taskCount + (taskCount > 1 ? " tasks" : " task") + " in the list.");
                    System.out.println("    ____________________________________________________________");
                    str = sc.nextLine();
                    break;
                default:
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Command doesn't exist: " + str);
                    System.out.println("    ____________________________________________________________");
                    str = sc.nextLine();
            }
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to never see you again.");
        System.out.println("    ____________________________________________________________");
    }
}
