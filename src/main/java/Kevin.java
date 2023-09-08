import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Kevin{
    private  static final String filePath = "src/main/java/Database.txt";
    private static final List<Task> taskList = new ArrayList<>();
    public static final String line = "____________________________________________________________";
    public static void main(String[] args) {
        Kevin.printGreetMessage();
        try {
            Kevin.loadData();
        } catch (FileNotFoundException fileException) {
            Kevin.createNewFile();
        }
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        label:
        while (true) {
            // Prints out the bye message
            String[] splitMessage = userInput.split(" ");
            String instruction = splitMessage[0];
            switch (instruction) {
                case "bye":
                    Kevin.printByeMessage();
                    break label;
                case "list":
                    // Prints out the list
                    Kevin.listAllTasks();
                    break;
                case "mark": {
                    Kevin.markTaskDone(splitMessage[1]);
                    break;
                }
                case "unmark": {
                    Kevin.unmarkTaskDone(splitMessage[1]);
                    break;
                }
                case "delete": {
                    try {
                        Kevin.deleteTask(splitMessage[1]);
                    } catch (TaskListEmptyException e) {
                        System.out.println(line);
                        System.out.println(e.getMessage());
                        System.out.println(line);
                    }
                    break;
                }
                default:
                    // Add new tasks to the task list
                    try {
                        Kevin.addNewTask(userInput);
                    } catch (DescriptionIncompleteException | IllegalCommandException e1) {
                        System.out.println(line);
                        System.out.println(e1.getMessage());
                        System.out.println(line);
                    } catch (DateTimeParseException e2) {
                        System.out.println(Kevin.line);
                        System.out.println("OOPS!!! You have entered a wrong date format.");
                        System.out.println("Please follow this format:");
                        System.out.println("yyyy-mm-dd");
                        System.out.println(Kevin.line);
                    }
            }
            userInput = scanner.nextLine();
        }
        scanner.close();
    }

    public static void printGreetMessage() {
        String greet = line + "\n"
                + "Hello! I'm Kevin.\n"
                + "What can I do for you?\n"
                + line;
        System.out.println(greet);
    }

    public static void printByeMessage() {
        String bye = line + "\n"
                + "Bye. Hope to see you again soon!\n"
                + line;
        System.out.println(bye);
    }

    public static void listAllTasks() {
        int count = 1;
        System.out.println(line + "\n" + "Here are the tasks in your list:");
        for (Task task: taskList) {
            String string = String.format("%d.%s", count, task);
            System.out.println(string);
            count++;
        }
        System.out.println(line);
    }

    public static void markTaskDone(String taskNumber) {
        Task currentTask = taskList.get(Integer.parseInt(taskNumber) - 1);
        currentTask.setIsDone();
        Kevin.rewriteFile();
        System.out.println(line);
        System.out.println("Nice! I've marked this task as done:\n" + currentTask);
        System.out.println(line);
    }

    public static void unmarkTaskDone(String taskNumber) {
        Task currentTask = taskList.get(Integer.parseInt(taskNumber) - 1);
        currentTask.setNotDone();
        System.out.println(line);
        Kevin.rewriteFile();
        System.out.println("Nice! I've unmarked this task as done:\n" + currentTask);
        System.out.println(line);
    }

    public static void addNewTask(String userInput) throws DescriptionIncompleteException, IllegalCommandException{
        String[] splitMessage = userInput.split(" ", 2);
        String instruction = splitMessage[0];
        Task task = null;

        if (!(instruction.equals("todo")||instruction.equals("deadline")||instruction.equals("event"))) {
            throw new IllegalCommandException("OOPS!!! I'm sorry, but I don't know what that means :-()");
        }

        if (splitMessage.length < 2) {
            throw new DescriptionIncompleteException("OOPS!!! The description of an instruction cannot be empty.");
        }

        switch (instruction) {
            case "todo":
                task = Todo.createNewTodoTask(splitMessage[1]);
                break;
            case "deadline":
                task = Deadline.createNewDeadlineTask(splitMessage[1]);
                break;
            case "event":
                task = Event.createNewEventTask(splitMessage[1]);
                break;
        }

        taskList.add(task);
        try {
            Kevin.writeToFile(task.toFileString());
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
        System.out.println(line);
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(line);
    }

    public static void deleteTask(String taskNumber) throws TaskListEmptyException {
        if (taskList.size() < 1) {
            throw new TaskListEmptyException("OOPS!!! You cannot delete an empty list.");
        }
        int number = Integer.parseInt(taskNumber);
        System.out.println(line);
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskList.get(number - 1));
        taskList.remove(number - 1);
        // Update the file
        Kevin.rewriteFile();
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(line);
    }

    public static void loadData() throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String nextLine = scanner.nextLine();
            Kevin.readString(nextLine);
        }
        scanner.close();
    }

    public static void readString(String string) {
        String string1 = string.substring(0,6);
        char taskType = string1.charAt(1);
        boolean isDone = string1.charAt(4) == 'X';

        if (taskType == 'T') {
            String description = string.substring(7);
            taskList.add(new Todo(description, isDone));
        } else if (taskType == 'D') {
            String[] split = string.split(" \\(by: ");
            String description = split[0].substring(7);
            LocalDate day = LocalDate.parse(split[1].substring(0, split[1].length() - 1));
            taskList.add(new Deadline(description, day, isDone));
        } else {
            String[] split1 = string.split(" \\(from: ");
            String[] split2 = split1[1].split(" to: ");
            String description = split1[0].substring(7);
            String start = split2[0];
            String end = split2[1].substring(0, split2[1].length() - 1);
            taskList.add(new Event(description, LocalDate.parse(start), LocalDate.parse(end), isDone));
        }
    }

    public static void writeToFile(String taskDetail) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, true);
        fileWriter.write(taskDetail + "\n");
        fileWriter.close();
    }

    public static void createNewFile(){
        System.out.println("We have detected that you're missing the database file");
        File file = new File(filePath);
        try {
            if (file.createNewFile()) {
                System.out.println("File created successfully");
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static void rewriteFile() {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task task: taskList) {
                fileWriter.write(task.toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
