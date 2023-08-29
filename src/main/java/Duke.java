
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    // Array storing the tasks
    static ArrayList<Task> taskArr = new ArrayList<>();
    static String saveFilePath = "./data/duke.txt";

    // Function that encapsulates message into a message Card template
    private static String messageCard(String message) {
        String horizontalLine = "\t____________________________________________________________\n";
        return horizontalLine + "\t " + message + "\n" + horizontalLine;
    }

    // Displays list of items
    private static String displayList(ArrayList<? extends Task> list) {
        String str = "Here are the tasks in your list:\n\t ";
        int count = 0;
        for (Task task : taskArr) {
            str += count + 1 + "." + task.toString() + "\n\t ";
            count++;
        }
        return messageCard(str.substring(0, str.length() - 3));
    }

    private static void markTaskAsDone(int index) {
        taskArr.get(index).markAsDone();
    }

    private static void markTaskAsUndone(int index) {
        taskArr.get(index).markAsUndone();
    }

    private static String addToDo(String description) {
        Todo todo = new Todo(description);
        taskArr.add(todo);
        return todo.toString();
    }

    private static String addDeadline(String description) {
        String descriptionText = description.substring(0, description.indexOf("/by"));
        String dateTime = description.substring(description.indexOf("/by") + 4).trim();
        Deadline deadline = null;
        try {
            DateTimeFormatter altInputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime byDateTime = LocalDateTime.parse(dateTime, altInputFormatter);
            deadline = new Deadline(descriptionText, byDateTime);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid Date Time: " + e.getMessage());
        }

        taskArr.add(deadline);
        return deadline.toString();

    }


    private static String addEvent(String description) {
        int indexFrom = description.indexOf("/from");
        int indexTo = description.indexOf("/to");

        String eventDescription = description.substring(0, indexFrom).trim();
        String startTime = description.substring(indexFrom + "/from".length(), indexTo).trim();
        String endTime = description.substring(indexTo + "/to".length()).trim();

        Event eventTask = new Event(eventDescription,
                LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        taskArr.add(eventTask);

        return eventTask.toString();
    }

    private static void deleteTask(int index) {
        Task task = taskArr.get(index);
        taskArr.remove(task);
        System.out.println(messageCard("Noted. I've removed this task:\n\t\t"
                + task
                + "\n\tNow you have " + taskArr.size() + " tasks in the list."));
    }

    private static void readFileContents(String filePath) throws IOException {
        File f = new File(filePath);
        if (!f.exists()) {
            f.getParentFile().mkdirs(); // Creates parent directories if they don't exist
            f.createNewFile(); // Creates the file itself
        }
        if (f.exists()) {
            Scanner s = new Scanner(f);
            int count = 0;

            while (s.hasNext()) {
                String str = s.nextLine();
                String[] task = str.split(" \\| ");
                switch (task[0]) {
                    case "T":
                        addToDo(task[2]);
                        if (task[1].equals("1")) {
                            markTaskAsDone(count);
                        }
                        break;
                    case "D":
                        addDeadline(task[2] + " /by " + task[3]);
                        if (task[1].equals("1")) {
                            markTaskAsDone(count);
                        }
                        break;
                    case "E":
                        String[] time = task[3].split(" to ");
                        addEvent(task[2] + " /from " + time[0] + " /to " + time[1]);
                        if (task[1].equals("1")) {
                            markTaskAsDone(count);
                        }
                        break;
                }
                count++;
            }
        } else {
            f.createNewFile();
        }
    }

    private static void updateFileContents(String filePath) throws IOException {
        FileWriter file = new FileWriter(filePath);
        file.write("");
        FileWriter fw = new FileWriter(filePath);
        for (Task task : taskArr) {
            if (task instanceof Todo) {
                String taskType = task.toString().substring(1, 2);  // Extract "T"
                String taskStatus = task.toString().substring(4, 5); // Extract " "
                String description = task.toString().substring(7);
                String convertedTask = taskType + " | " + (taskStatus.equals(" ") ? "0" : "1") + " | " + description;
                fw.write(convertedTask + "\n");
            } else if (task instanceof Deadline) {
                String originalTask = task.writeFileString();
                String taskType = originalTask.substring(1, 2);  // Extract "D"
                String taskStatus = originalTask.substring(4, 5); // Extract "X"
                String description = originalTask.substring(7, originalTask.indexOf(" (by:"));
                String date = originalTask.substring(originalTask.indexOf("(by: ") + 5, originalTask.indexOf(")"));

                String convertedTask = taskType + " | " + (taskStatus.equals("X") ? "1" : "0") + " | " + description + " | " + date;
                fw.write(convertedTask + "\n");
            } else if (task instanceof Event) {
                String originalTask = task.writeFileString();
                String taskType = originalTask.substring(1, 2);  // Extract "E"
                String taskStatus = originalTask.substring(4, 5); // Extract " "
                String description = originalTask.substring(7, originalTask.indexOf(" (from:"));
                String startTime = originalTask.substring(originalTask.indexOf("(from: ") + 7, originalTask.indexOf(" to:"));
                String endTime = originalTask.substring(originalTask.indexOf("to: ") + 4, originalTask.indexOf(")"));

                String convertedTask = taskType + " | " + (taskStatus.equals(" ") ? "0" : "1") + " | " + description + " | " + startTime + " to " + endTime;
                fw.write(convertedTask + "\n");
            }
        }
        fw.close();
    }



    // Main function
    public static void main(String[] args) {
        String CHATBOTNAME = "Carl";
        System.out.println(messageCard("Hello! I'm " + CHATBOTNAME
            + "\n\t What can I do for you?"));

        try {
            readFileContents(saveFilePath);

            Scanner SC = new Scanner(System.in);
            while (true) {
                String userInput = SC.nextLine();
                try {
                    if (userInput.equals("bye")) {
                        System.out.println(messageCard("Bye. Hope to see you again soon!"));
                        break;
                    } else if (userInput.equals("list")) {
                        System.out.println(displayList(taskArr));
                    } else if (userInput.contains("mark") && userInput.substring(0, 4).equals("mark")) {
                        if (!userInput.equals("mark")) {
                            int index = Integer.parseInt(userInput.substring(5)) - 1;
                            if (index >= taskArr.size() || index < 0) {
                                System.out.println(messageCard("Invalid mark task"));
                            } else {
                                markTaskAsDone(index);
                                System.out.println(messageCard("Nice! I've marked this task as done:\n\t\t" + taskArr.get(index)));
                                updateFileContents(saveFilePath);
                            }
                        } else {
                            System.out.println(messageCard("Invalid mark task"));
                        }
                    } else if (userInput.contains("unmark") && userInput.substring(0, 6).equals("unmark")) {
                        if (!userInput.equals("unmark")) {
                            int index = Integer.parseInt(userInput.substring(7)) - 1;
                            if (index >= taskArr.size() || index < 0) {
                                System.out.println(messageCard("Invalid unmark task"));
                            } else {
                                markTaskAsUndone(index);
                                System.out.println(messageCard("OK, I've marked this task as not done yet:\n\t\t" + taskArr.get(index)));
                                updateFileContents(saveFilePath);
                            }
                        } else {
                            System.out.println(messageCard("Invalid unmark task"));
                        }
                    } else if (userInput.contains("delete") && userInput.substring(0, 6).equals("delete")) {
                        if (!userInput.equals("delete")) {
                            int index = Integer.parseInt(userInput.substring(7)) - 1;
                            if (index >= taskArr.size() || index < 0) {
                                System.out.println(messageCard("Invalid task index to be deleted"));
                            } else {
                                deleteTask(index);
                                updateFileContents(saveFilePath);
                            }
                        } else {
                            System.out.println(messageCard("Invalid task index to be deleted"));
                        }
                    } else {
                        if (userInput.contains("todo") && userInput.substring(0, 4).equals("todo")) {
                            // Add a task
                            if (userInput.equals("todo")) { // checks if description is empty
                                throw new DukeException("todo");
                            } else {
                                String description = userInput.substring(5);
                                String todo = addToDo(description);
                                System.out.println(messageCard("Got it. I've added this task:\n\t\t"
                                        + todo
                                        + "\n\tNow you have " + taskArr.size() + " tasks in the list."));
                                updateFileContents(saveFilePath);
                            }
                        } else if (userInput.contains("deadline") && userInput.substring(0, 8).equals("deadline")) {
                            // Add a deadline
                            if (userInput.equals("deadline") || !userInput.contains("/by")) { // checks if description is invalid
                                throw new DukeException("deadline");
                            } else {
                                String description = userInput.substring(9);
                                String deadline = addDeadline(description);
                                System.out.println(messageCard("Got it. I've added this task:\n\t\t"
                                        + deadline
                                        + "\n\tNow you have " + taskArr.size() + " tasks in the list."));
                                updateFileContents(saveFilePath);
                            }
                        } else if (userInput.contains("event") && userInput.substring(0, 5).equals("event")) {
                            // Add an event
                            if (userInput.equals("event") || !userInput.contains("/from") || !userInput.contains("/to")) { // checks if description is invalid
                                throw new DukeException("event");
                            } else {
                                String description = userInput.substring(6);
                                String event = addEvent(description);
                                System.out.println(messageCard("Got it. I've added this task:\n\t\t"
                                        + event
                                        + "\n\tNow you have " + taskArr.size() + " tasks in the list."));
                                updateFileContents(saveFilePath);
                            }
                        } else {
                            System.out.println(messageCard("OOPS!!! I'm sorry, but I don't know what that means :-("));
                        }
                    }
                } catch (DukeException e) {
                    System.out.println(messageCard(e.getMessage()));
                }
            }
            SC.close();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            System.out.println("Working Directory = " + System.getProperty("user.dir"));
            e.printStackTrace();
        }
    }
}