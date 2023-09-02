import java.io.*;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private String filePath = "./data/tasks.txt";

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    public int getSize() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int taskIndex) throws BuddyException {
        if (tasks.size() == 0 ) {
            System.out.println("There are no tasks in your list:");
        } else if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task deletedTask = tasks.remove(taskIndex);
            System.out.println("Noted. I've removed this task:");
            System.out.println(deletedTask.toString());
            if (tasks.size() == 1) {
                System.out.println("Now you have 1 task in the list.");
            } else {
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            }
        } else {
            throw new BuddyException("Invalid task index.");
        }
    }

    public void markAsDone(int taskIndex) throws BuddyException {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.get(taskIndex).markTaskAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(taskIndex).toString());
        } else {
            throw new BuddyException("Invalid task index.");
        }
    }

    public void markAsNotDone(int taskIndex) throws BuddyException {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.get(taskIndex).markTaskAsUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks.get(taskIndex).toString());
        } else {
            throw new BuddyException("Invalid task index.");
        }
    }

    public void processCommand(String command) throws BuddyException {
        String[] parts = command.split(" ", 2);

        try {
            TaskType taskType = TaskType.valueOf(parts[0].toUpperCase());

            switch (taskType) {
            case TODO:
                if (parts.length < 2 || parts[1].isEmpty()) {
                    throw new BuddyException("OOPS!!! The description of a todo cannot be empty.");
                } else {
                    addTask(new Todo(parts[1], false));
                    System.out.println("Got it. I've added this task:\n"
                            + getTask(this.getSize() - 1));
                    System.out.println("Now you have " + this.getSize() + " tasks in the list.");
                }
                break;

            case DEADLINE:
                if (parts.length < 2 || parts[1].isEmpty()) {
                    throw new BuddyException("OOPS!!! Please include a description and deadline.");
                } else {
                    String[] deadlineParts = parts[1].split("/", 2);
                    if (deadlineParts.length < 2 || deadlineParts[1].isEmpty()) {
                        throw new BuddyException("OOPS!!! Please include a description and deadline.");
                    }
                    String deadlineBy = deadlineParts[1].replaceFirst("by ", "").trim();
                    addTask(new Deadline(deadlineParts[0].trim(), deadlineBy, false));
                    System.out.println("Got it. I've added this task:\n"
                            + getTask(this.getSize() - 1).toString());
                    System.out.println("Now you have " + this.getSize() + " tasks in the list.");
                }
                break;

            case EVENT:
                if (parts.length < 2 || parts[1].isEmpty()) {
                    throw new BuddyException(
                            "OOPS!!! Please include event description, start and end date or time."
                    );
                } else {
                    String[] eventParts = parts[1].split("/", 3);
                    if (eventParts.length < 3 || eventParts[1].isEmpty() || eventParts[2].isEmpty()) {
                        throw new BuddyException(
                                "OOPS!!! Please include event description, start and end date or time."
                        );
                    }
                    String eventStart = eventParts[1].replaceFirst("from ", "").trim();
                    String eventEnd = eventParts[2].replaceFirst("to ", "").trim();

                    addTask(new Event(eventParts[0].trim(), eventStart, eventEnd, false));
                    System.out.println("Got it. I've added this task:\n"
                            + getTask(this.getSize() - 1));
                    System.out.println("Now you have " + this.getSize() + " tasks in the list.");
                }
                break;
            }
        } catch (IllegalArgumentException e) {
            throw new BuddyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public void saveTasks() {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task task : tasks) {
                fileWriter.write(task.toSaveFileFormat() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public void loadTasks() {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    parseTask(line);
                }
                reader.close();
            }
        } catch (IOException | BuddyException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    private void parseTask(String line) throws BuddyException {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (taskType) {
            case "T":
                addTask(new Todo(description, isDone));
                break;

            case "D":
                String deadlineBy = parts[3];
                addTask(new Deadline(description, deadlineBy, isDone));
                break;

            case "E":
                String start = parts[3];
                String end = parts[4];
                addTask(new Event(description, start, end, isDone));
                break;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!tasks.isEmpty()) {
            sb.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(i + 1).append(".").append(tasks.get(i));
                sb.append("\n");
            }
        } else {
            sb.append("There are no tasks in your list.\n");
        }
        return sb.toString();
    }
}
