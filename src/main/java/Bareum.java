import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Bareum {
    static TaskList taskList = new TaskList();
    static File loadSavedTaskList() {
        File data = new File("./data");
        if (!data.exists()) {
            try {
                data.mkdirs();
            } catch (SecurityException e) {
                System.out.println(e.getMessage());
            }
        }

        File storedTasks = new File("./data/storedTasks.txt");
        if (!storedTasks.exists()) {
            try {
                storedTasks.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return storedTasks;
    }

    static void readFromSavedTaskList(File storedTasks) {
        StringBuilder savedTasks = new StringBuilder();
        int currChar;
        try {
            FileReader fr = new FileReader(storedTasks);
            while ((currChar = fr.read()) != -1) {
                savedTasks.append((char) currChar);
            }

            String[] allTaskInputs = savedTasks.toString(). split("\n");
            for (int i = 0; i < allTaskInputs.length; i++) {
                String[] taskInputs = allTaskInputs[i].split("\\|");
                if (taskInputs[0].equals("T")) {
                    taskList.addTask(TodoTask.makeTodo(taskInputs));
                } else if (taskInputs[0].equals("D")) {
                    taskList.addTask(DeadlineTask.makeDeadline(taskInputs));
                    // enum: done not done to prevent invalid input
                } else if (taskInputs[0].equals("E")) {
                    taskList.addTask(EventTask.makeEvent(taskInputs));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static void saveTasks(File storedTasks, TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(storedTasks);
            for (int i = 0; i < taskList.size(); i++) {
                fw.write(taskList.get(i).toSavedString());
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static void reply(String reply) {
        String botName = "Bareum: ";
        String fullReply = botName + reply;
        System.out.println(fullReply);
    }

    public static void main(String[] args) {
        File storedTasks = loadSavedTaskList();
        readFromSavedTaskList(storedTasks);

        String introduction = "Hello! I'm Bareum! What can I do for you? ^^";
        String goodbye = "Bye! Hope to see you again soon ^^";
        String line = "________________________";

        reply(introduction);
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println(line);
            String input = sc.next();
            if (input.equals("bye")) {
                saveTasks(storedTasks, taskList);
                break;
            } else if (input.equals("list")) {
                reply("Here are your current tasks!");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ". " + taskList.get(i).toString());
                }
            } else if (input.equals("mark")) {
                int index = sc.nextInt() - 1;
                taskList.markAsDone(index);
                saveTasks(storedTasks, taskList);
                reply("Well done! I've marked this task as done:\n" + taskList.get(index).toString());
            } else if (input.equals("unmark")) {
                int index = sc.nextInt() - 1;
                taskList.markAsUndone(index);
                saveTasks(storedTasks, taskList);
                reply("Okay, I've marked this task as not done yet:\n" + taskList.get(index).toString());
            } else if (input.equals("todo")) {
                String description = sc.nextLine();
                try {
                    TodoTask task = TodoTask.makeTodo(description);
                    taskList.addTask(task);
                    saveTasks(storedTasks, taskList);
                    String added = "I have added this task:\n" + task + "\nYou now have "
                            + taskList.size() + " task(s) in your list.";
                    reply(added);
                } catch (IllegalArgumentException e) {
                    reply("Oops! The description of a todo cannot be empty.\n" +
                            "Correct format: todo <description>");
                }
            } else if (input.equals("deadline")){
                String allDetails = sc.nextLine();
                try {
                    DeadlineTask task = DeadlineTask.makeDeadline(allDetails);
                    taskList.addTask(task);
                    saveTasks(storedTasks, taskList);
                    String added = "I have added this task:\n" + task + "\nYou now have "
                            + taskList.size() + " task(s) in your list.";
                    reply(added);
                } catch (IllegalArgumentException e) {
                    reply("Oops! The description of a deadline cannot be empty.\n" +
                            "Correct format: deadline <description> /by <due date>");
                }
            } else if (input.equals("event")) {
                String allDetails = sc.nextLine();
                try {
                    EventTask task = EventTask.makeEvent(allDetails);
                    taskList.addTask(task);
                    saveTasks(storedTasks, taskList);
                    String added = "I have added this task:\n" + task + "\nYou now have "
                            + taskList.size() + " task(s) in your list.";
                    reply(added);
                } catch (IllegalArgumentException e) {
                    reply("Oops! The description of an event cannot be empty.\n" +
                            "Correct format: event <description> /from <start time> /to <end time>");
                }
            } else if (input.equals("delete")) {
                int index = sc.nextInt() - 1;
                String deletedTask = taskList.get(index).toString();
                taskList.delete(index);
                saveTasks(storedTasks, taskList);
                // exception for if index doesn't exist
                reply("Okay, I've deleted this task from the list:\n" + deletedTask
                        + "\nYou now have " + taskList.size() + " tasks in your list.");
            } else {
                reply("Oops! I'm sorry but I don't know what that means :(");
            }
        }

        reply(goodbye);
    }
}
