import java.util.Scanner;
public class Avalon {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int taskCount = 0;
        Scanner scanner = new Scanner(System.in);

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
                        System.out.println("    " + (i + 1) + "." +
                                            tasks[i].getStatusIcon() + tasks[i].description);
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
            //add task
            } else {
                tasks[taskCount] = new Task(userInput);
                taskCount++;
                System.out.println(
                        "   ____________________________________________________________\n" +
                                "    added: " +
                                userInput +
                                "\n   ____________________________________________________________\n"
                );
            }
        }

        scanner.close();
    }
}
