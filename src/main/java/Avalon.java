import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Avalon {

    public static void main(String[] args) {
        List<Task> tasks = loadTasks();
        int taskCount = tasks.size();
        Scanner scanner = new Scanner(System.in);

        //greet
        System.out.println(
                "   ____________________________________________________________\n" +
                        "    Hello! I'm Arthur Pendragon.\n    What can I do for you?\n" +
                        "   ____________________________________________________________\n"
        );

        while (true) {
            String userInput = scanner.nextLine();
            try {
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
                                "   Here are the quests in thy list:");
                        for (int i = 0; i < taskCount; i++) {
                            System.out.println("    " + (i + 1) + "." + tasks.get(i));
                        }
                        System.out.println("   ____________________________________________________________");
                    }

                //mark task
                } else if (userInput.toLowerCase().startsWith("mark ")) {
                    int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
                    if (taskIndex >= 0 && taskIndex < taskCount) {
                        tasks.get(taskIndex).markDone();
                        System.out.println("   ____________________________________________________________\n" +
                                "   Very well. I have marked this task as accomplished:\n  " + "  " +
                                tasks.get(taskIndex).getStatusIcon() + " " +
                                tasks.get(taskIndex).description +
                                "\n   ____________________________________________________________");
                    } else {
                        throw new IllegalArgumentException("Invalid task number to be marked.");
                    }
                //unmark task
                } else if (userInput.toLowerCase().startsWith("unmark ")) {
                    int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
                    if (taskIndex >= 0 && taskIndex < taskCount) {
                        tasks.get(taskIndex).markNotDone();
                        System.out.println("   ____________________________________________________________\n" +
                                "   By the heavens! I have declared this task as yet to be completed:\n  " + "  " +
                                tasks.get(taskIndex).getStatusIcon() + " " +
                                tasks.get(taskIndex).description +
                                "\n   ____________________________________________________________");
                    } else {
                        throw new IllegalArgumentException("Invalid task number to be unmarked.");
                    }
                //create activity
                } else if (userInput.toLowerCase().startsWith("todo ")) {
                    String description = userInput.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new IllegalArgumentException("The description of a todo cannot be empty.");
                    }

                    ToDo todo = new ToDo(description);
                    tasks.add(todo);
                    taskCount++;

                    System.out.println("   ____________________________________________________________\n" +
                            "   Understood. I have included this quest:\n  " + "  " + tasks.get(taskCount - 1));
                    System.out.println("   Now you have " + taskCount + " task(s) in the list.");
                    System.out.println("   ____________________________________________________________");
                } else if (userInput.toLowerCase().startsWith("deadline ")) {
                    String[] parts = userInput.substring(9).split(" /by ");
                    if (parts.length != 2) {
                        throw new IllegalArgumentException("Please provide a description and a deadline (use /by).");
                    }
                    String description = parts[0];
                    String by = parts[1];

                    Deadline deadline = new Deadline(description, by);
                    tasks.add(deadline);
                    taskCount++;

                    System.out.println("   ____________________________________________________________\n" +
                            "   Understood. I have included this quest:\n  " + "  " + tasks.get(taskCount - 1));
                    System.out.println("   Now you have " + taskCount + " task(s) in the list.");
                    System.out.println("   ____________________________________________________________");
                } else if (userInput.toLowerCase().startsWith("event ")) {
                    String[] parts = userInput.substring(6).split(" /from | /to ");
                    if (parts.length != 3) {
                        throw new IllegalArgumentException("Please provide a description, a starting time, and an ending time (use /from and /to).");
                    }
                    String description = parts[0];
                    String from = parts[1];
                    String to = parts[2];

                    Event event = new Event(description, from, to);
                    tasks.add(event);
                    taskCount++;

                    System.out.println("   ____________________________________________________________\n" +
                            "   Understood. I have included this quest:\n  " + "  " + tasks.get(taskCount - 1));
                    System.out.println("   Now you have " + taskCount + " task(s) in the list.");
                    System.out.println("   ____________________________________________________________");
                } else if (userInput.toLowerCase().startsWith("delete ")) {
                    int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
                    if (taskIndex >= 0 && taskIndex < taskCount) {
                        Task deletedTask = tasks.get(taskIndex);
                        tasks.remove(taskIndex);
                        taskCount--;

                        System.out.println("   ____________________________________________________________");
                        System.out.println("   Noted. I've removed this quest:");
                        System.out.println("    " + deletedTask);
                        System.out.println("   Now you have " + taskCount + " task(s) in the list.");
                        System.out.println("   ____________________________________________________________");
                    } else {
                        throw new IllegalArgumentException("Invalid task number to be deleted.");
                    }
                } else {
                    throw new IllegalArgumentException("I humbly apologize, but thy words remain a mystery to me...");
                }

            } catch(IllegalArgumentException e){
                System.out.println("☹ OOPS!!! " + e.getMessage());
            }
        }

        scanner.close();
        saveTasks(tasks);
    }

    private static List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        try {
            File file = new File("src/main/data/Avalon.txt");
            Scanner scanner = new Scanner(file);
            System.out.println("Loading...");
            while (scanner.hasNext()) {
                String description = scanner.nextLine();
                Task task = TaskParser.parse(description);
                tasks.add(task);
            }
            scanner.close();
        } catch (IOException e) {
            // Handle the case where the file doesn't exist or other IO errors
            System.out.println("No existing tasks file found. Starting with an empty list.");
        }

        return tasks;
    }

    private static void saveTasks(List<Task> tasks) {
        try {
            FileWriter writer = new FileWriter("src/main/data/Avalon.txt");
            for (Task task : tasks) {
                String taskData = TaskParser.serialize(task);
                writer.write(taskData + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to the file.");
        }
    }
}
