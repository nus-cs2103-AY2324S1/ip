import java.util.Scanner;
public class Avalon {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int taskCount = 0;
        Scanner scanner = new Scanner(System.in);
        //greet
        System.out.println(
                "   ____________________________________________________________\n" +
                "    Hello! I'm Arthur Pendragon.\n" +
                "    What can I do for you?\n" +
                "   ____________________________________________________________\n"
        );

        while (true){
            String userInput = scanner.nextLine();
            //exit
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(
                        "   ____________________________________________________________\n" +
                                "    Farewell. We will meet again!\n" +
                                "   ____________________________________________________________"
                );
                break;
            //display list
            } else if (userInput.equalsIgnoreCase("list")) {
                if (taskCount == 0) {
                    System.out.println(
                            "   ____________________________________________________________\n" +
                                    "    You haven't added anything, my sire.\n" +
                                    "   ____________________________________________________________"
                    );
                } else {
                    System.out.println("   ____________________________________________________________\n" +
                                       "   Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println("    " + (i + 1) + "." + tasks[i]);
                    }
                    System.out.println("   ____________________________________________________________");
                }

            //mark task
            } else if (userInput.toLowerCase().startsWith("mark ")){
                int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    tasks[taskIndex].markDone();
                    System.out.println("   ____________________________________________________________\n" +
                            "   Nice! I've marked this task as done:\n  " + "  " +
                            tasks[taskIndex].getStatusIcon() + " " +
                            tasks[taskIndex].description +
                            "\n   ____________________________________________________________");
                } else {
                    System.out.println("   ____________________________________________________________\n" +
                            "   Invalid task index." +
                            "\n   ____________________________________________________________");
                }
            //unmark task
            } else if (userInput.toLowerCase().startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    tasks[taskIndex].markNotDone();
                    System.out.println("   ____________________________________________________________\n" +
                            "   Nice! I've marked this task as not done yet:\n  " + "  " +
                            tasks[taskIndex].getStatusIcon() + " " +
                            tasks[taskIndex].description +
                            "\n   ____________________________________________________________");
                } else {
                    System.out.println("   ____________________________________________________________\n" +
                            "   Invalid task index." +
                            "\n   ____________________________________________________________");
                }
            } else if (userInput.toLowerCase().startsWith("todo ")) {
                String description = userInput.substring(5);
                tasks[taskCount] = new ToDo(description);
                taskCount++;
                System.out.println("   ____________________________________________________________\n" +
                        "   Got it. I've added this task:\n  " + "  " + tasks[taskCount - 1]);
                System.out.println("   Now you have " + taskCount + " tasks in the list.");
                System.out.println("   ____________________________________________________________");
            } else if (userInput.toLowerCase().startsWith("deadline ")) {
                String[] parts = userInput.substring(9).split(" /by ");
                String description = parts[0];
                String by = parts[1];
                tasks[taskCount] = new Deadline(description, by);
                taskCount++;
                System.out.println("   ____________________________________________________________\n" +
                        "   Got it. I've added this task:\n  " + "  " + tasks[taskCount - 1]);
                System.out.println("   Now you have " + taskCount + " tasks in the list.");
                System.out.println("   ____________________________________________________________");
            } else if (userInput.toLowerCase().startsWith("event ")) {
                String[] parts = userInput.substring(6).split(" /from | /to ");
                String description = parts[0];
                String from = parts[1];
                String to = parts[2];
                tasks[taskCount] = new Event(description, from, to);
                taskCount++;
                System.out.println("   ____________________________________________________________\n" +
                        "   Got it. I've added this task:\n  " + "  " + tasks[taskCount - 1]);
                System.out.println("   Now you have " + taskCount + " tasks in the list.");
                System.out.println("   ____________________________________________________________");
            } else {
                System.out.println("   ____________________________________________________________");
                System.out.println("    Error 404");
                System.out.println("   ____________________________________________________________");
            }
        }

        scanner.close();
    }
}
