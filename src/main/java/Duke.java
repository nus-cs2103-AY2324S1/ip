import java.io.File;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Duke {
    private static final String line = "______________________________________________________________________________________\n";
    private static Ui ui;
    private static TaskList tasks;
    private static Storage storage;
    private static Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("./src/main/data/tasklist.txt").startChat();
    }

    public void startChat() {
        ui.greet();
//        ArrayList<Task> tasks = new ArrayList<>();
        File taskList = new File("./src/main/data/tasklist.txt");
        taskList.deleteOnExit();

        int taskCount = 0;
        int taskId = 1;

        Scanner scanner = new Scanner(System.in);
        String userInput= scanner.nextLine();

        while (!userInput.equals("bye")){
            try {
                if (userInput.equals("list")){
                    tasks.printFileContents();
                } else if (userInput.startsWith("mark ")) {
                    tasks.mark(userInput);
                } else if (userInput.startsWith("unmark ")) {
                    tasks.unMark(userInput);
                } else if (userInput.startsWith("delete ")) {
                    tasks.delete(userInput);
                } else if (userInput.startsWith("todo ")) {
                    String nameOfTask = userInput.substring(5);
                    ToDos task = new ToDos(nameOfTask);
                    addToList(task, tasks, taskCount);
                    if (taskCount < tasks.size()) {
                        taskCount++;
                        taskId++;
                    }
                    tasks.writeToFile();
                } else if (userInput.startsWith("deadline ")) {
                    String[] parts = userInput.split("/by ");
                    String nameOfTask = parts[0].trim().substring(9);
                    try {
                        LocalDate deadline = LocalDate.parse(parts[1].trim());
                        Deadlines task = new Deadlines(nameOfTask, deadline);
                        addToList(task, tasks, taskCount);
                        if (taskCount < tasks.size()) {
                            taskCount++;
                            taskId++;
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid Date Format! Follow: YYYY-MM-DD");
                    }
                    tasks.writeToFile();
                } else if (userInput.startsWith("event ")) {
                    String[] taskAndTime = userInput.split("/from ");
                    String[] fromAndTo = taskAndTime[1].split("/to ");
                    try {
                        LocalDate start = LocalDate.parse(fromAndTo[0].trim());
                        LocalDate end = LocalDate.parse(fromAndTo[1].trim());
                        String nameOfTask = taskAndTime[0].trim().substring(6);
                        Events task = new Events(nameOfTask, start, end);
                        addToList(task, tasks, taskCount);
                        if (taskCount < tasks.size()) {
                            taskCount++;
                            taskId++;
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid Date Format! Follow: YYYY-MM-DD");
                    }
                    tasks.writeToFile();
                } else {
                    throw new DukeException("Error: Invalid Command!");
                }
            } catch (DukeException exception) {
                System.out.println(Ui.line + exception.getMessage() + "\n" + Ui.line);
            }
            userInput = scanner.nextLine();
        }
        ui.bye();
        scanner.close();
    }

    private static void printFileContents(File taskList) throws FileNotFoundException {
        Scanner s = new Scanner(taskList);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    private static void writeToFile(File taskList, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(taskList.getPath());
        fw.write(textToAdd);
        fw.close();
    }

    private static String displayList(ArrayList<Task> tasks, int taskCount) {
        StringBuilder res = null;
        try {
            if (taskCount == 0) {
                throw new DukeException("Error: There are no items in the list!");
            }
            res = new StringBuilder(line);
            for (int i = 0; i < taskCount; i++) {
                Task task = tasks.get(i);
                int index = i + 1;
                res.append(index).append(task.getTask()).append("\n");
            }
            res.append(line);
        } catch (DukeException emptyList) {
            res = new StringBuilder(line + emptyList.getMessage() + "\n" + line);
        }
        return res.toString();
    }

    public static void delete(String input, ArrayList<Task> tasks, int taskCount) {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        try {
            if (taskIndex > taskCount || taskIndex < 0) {
                throw new DukeException("Error: Invalid Task Index!");
            } else {
                int remainingTasks = taskCount - 1;
                String response = line + "Got it! I've removed this task:" + "\n" + tasks.get(taskIndex).toString() + "\n"
                        + "You now have " + remainingTasks + " task(s) in the list" + "\n" + line;
                tasks.remove(taskIndex);
                System.out.println(response);
            }
        } catch (DukeException exception) {
            System.out.println(line + exception.getMessage() + "\n" + line);
        }
    }

    public static void mark(String input, ArrayList<Task> tasks, int taskCount) {
        int taskIndex = Integer.parseInt(input.substring(5)) - 1;
        try {
            if (taskIndex > taskCount || taskIndex < 0) {
                throw new DukeException("Error: Invalid Task Index!");
            } else if (tasks.get(taskIndex).isMarked()) {
                throw new DukeException("Error: Task is already completed!");
            } else {
                tasks.get(taskIndex).mark();
            }
        } catch (DukeException exception) {
            System.out.println(line + exception.getMessage() + "\n" + line);
        }
    }

    public static void unMark(String input, ArrayList<Task> tasks, int taskCount) {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        try {
            if (taskIndex > taskCount || taskIndex < 0) {
                throw new DukeException("Error: Invalid Task Index!");
            } else if (!tasks.get(taskIndex).isMarked()) {
                throw new DukeException("Error: Task is already marked as incomplete!");
            } else {
                tasks.get(taskIndex).unMark();
            }
        } catch (DukeException exception) {
            System.out.println(line + exception.getMessage() + "\n" + line);
        }
    }

    private static void addToList(Task task, ArrayList<Task> tasks, int taskId) {
        int taskCount = taskId + 1;
        String response = line + "Got it! I've added this task:" + "\n" + task.toString() + "\n"
                + "You now have " + taskCount + " task(s) in the list" + "\n" + line;
        tasks.add(taskId, task);
        System.out.println(response);
    }
}
