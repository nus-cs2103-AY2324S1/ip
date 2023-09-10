import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static final String FILE_PATH = "../data/Duke.txt";

    private static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File f = new File(FILE_PATH);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String data = s.nextLine();
                String[] splitVariables = data.split(" \\| ");
                String taskType = splitVariables[0];
                String taskDescription = splitVariables[1];
                boolean taskIsDone = Boolean.parseBoolean(splitVariables[2]);
                switch (taskType) {
                    case "E":
                        String taskFrom = splitVariables[3];
                        String taskTo = splitVariables[4];
                        tasks.add(new Event(taskDescription, taskFrom, taskTo, taskIsDone));
                        break;
                    case "D":
                        String taskBy = splitVariables[3];
                        tasks.add(new Deadline(taskDescription, taskBy, taskIsDone));
                        break;
                    case "T":
                        tasks.add(new Todo(taskDescription, taskIsDone));
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. No existing tasks.");
        }

        return tasks;
    }

    private static void saveTasks(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                String taskData = task.parse();
                fw.write(taskData + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Save tasks failed.");
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        ArrayList<Task> tasks = loadTasks();
        int taskCount = tasks.size();
//        Task[] tasks =  new Task[100];
        System.out.println("Hello! I'm Chatty\nWhat can I do for you?");
        System.out.println("____________________________________________________________");
        while (isRunning) {
            try {
                String userInput = scanner.nextLine();
                System.out.println("____________________________________________________________");
                if (userInput.equals("bye")) {
                    saveTasks(tasks);
                    System.out.println("Bye. Hope to see you again soon!");
                    isRunning = false;
                } else if (userInput.equals("list")){
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println(i + 1 + "." + tasks.get(i));
                    }
                } else if (userInput.startsWith("mark ")) {
                    System.out.println("Nice! I've marked this task as done:");
                    int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
                    tasks.get(taskIndex).switchCheck();
                    System.out.println(tasks.get(taskIndex).toString());
                } else if (userInput.startsWith("unmark ")) {
                    System.out.println("OK, I've marked this task as not done yet:");
                    int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
                    tasks.get(taskIndex).switchCheck();
                    System.out.println(tasks.get(taskIndex).toString());
                } else if (userInput.startsWith("todo ")) {
                    if (userInput.length() <= 5) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    Task newToDo = new Todo(userInput.substring(5));
                    System.out.println("Got it. I've added this task:");
                    tasks.add(new Todo(userInput));
                    taskCount++;
                    System.out.println(newToDo.toString());
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                } else if (userInput.startsWith("deadline ")) {
                    System.out.println("Got it. I've added this task:");
                    String description = userInput.substring(9, userInput.indexOf("/by")).trim();
                    String by = userInput.substring(userInput.indexOf("/by") + 4).trim();
                    Task newDeadline = new Deadline(description, by);
                    tasks.add(newDeadline);
                    taskCount++;
                    System.out.println(newDeadline.toString());
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                } else if (userInput.startsWith("event ")) {
                    System.out.println("Got it. I've added this task:");
                    String description = userInput.substring(6, userInput.indexOf("/from")).trim();
                    String from = userInput.substring(userInput.indexOf("/from") + 6, userInput.indexOf("/to")).trim();
                    String to = userInput.substring(userInput.indexOf("/to") + 4).trim();
                    Task newEvent = new Event(description, from, to);
                    tasks.add(newEvent);
                    taskCount++;
                    System.out.println(newEvent.toString());
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                } else if (userInput.startsWith("delete ")) {
                    System.out.println("Noted. I've removed this task:");
                    int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
                    System.out.println(tasks.get(taskIndex));
                    tasks.remove(taskIndex);
                    taskCount--;
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                // Handle Duke-specific exceptions with meaningful error messages
                System.out.println(e.getMessage());
            }
            System.out.println("____________________________________________________________");
        }
        scanner.close();
    }
}
