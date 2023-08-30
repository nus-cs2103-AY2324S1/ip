import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Bongo {
    ArrayList<Task> tasks;
    Scanner inputScanner;

    public Bongo() {
        this.tasks = new ArrayList<>();
        this.inputScanner = new Scanner(System.in);
    }

    public void greet() {
        String welcomeMessage = "____________________________________________________________\n" +
                " Hello! I'm Bongo!\n" +
                " What can I do for you?\n" +
                "____________________________________________________________";
        System.out.println(welcomeMessage);
    }

    public void bye() {
        String goodbyeMessage = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";
        System.out.println(goodbyeMessage);
        inputScanner.close();
    }

    public void loadTasksFromStorage() throws FileNotFoundException {
        File file = new File("data/bongo.txt");
        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] arr = line.split("\\|");
            for (int i = 0; i < arr.length; i++) {
                arr[i] = arr[i].trim();
            }
            switch (arr[0]) {
                case "T":
                    tasks.add(new Todo(arr[2], arr[1].equals("1")));
                    break;
                case "D":
                    tasks.add(new Deadline(arr[2], arr[1].equals("1"), arr[3]));
                    break;
                case "E":
                    tasks.add(new Event(arr[2], arr[1].equals("1"), arr[3], arr[4]));
                    break;
            }
        }
        fileScanner.close();
    }

    public void listAllTasks() {
        StringBuilder allTasks = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            allTasks.append(String.format(" %d.%s\n", i + 1, tasks.get(i)));
        }
        String tasksList = "____________________________________________________________\n" +
                " Here are the tasks in your list:\n" +
                allTasks +
                "____________________________________________________________";
        System.out.println(tasksList);
    }

    public void addTodo(String[] input) throws BongoException {
        if (input.length <= 1)
            throw new BongoException("OOPS!!! The description of a todo cannot be empty.");
        String newTaskDesc = String.join(" ", input).substring(input[0].length() + 1);
        this.addTask(new Todo(newTaskDesc));
    }

    public void addDeadline(String[] input) throws BongoException {
        if (input.length <= 1)
            throw new BongoException("OOPS!!! The description of a deadline cannot be empty.");
        String taskInput = String.join(" ", input).substring(input[0].length() + 1);
        int index = taskInput.indexOf("/by");
        if (index == -1) throw new BongoException("OOPS!!! Please include the deadline.");
        String taskDesc = taskInput.substring(0, index - 1);
        String taskDeadline = taskInput.substring(index + 4);
        this.addTask(new Deadline(taskDesc, taskDeadline));
    }

    public void addEvent(String[] input) throws BongoException {
        if (input.length <= 1)
            throw new BongoException("OOPS!!! The description of an event cannot be empty.");
        String taskInput = String.join(" ", input).substring(input[0].length() + 1);
        int fromIndex = taskInput.indexOf("/from");
        int toIndex = taskInput.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1)
            throw new BongoException("OOPS!!! Please include the to and from date/time of the event.");
        String taskDesc = taskInput.substring(0, fromIndex - 1);
        String taskFrom = taskInput.substring(fromIndex + 6, toIndex - 1);
        String taskTo = taskInput.substring(toIndex + 4);
        this.addTask(new Event(taskDesc, taskFrom, taskTo));
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
        String echoMessage = "____________________________________________________________\n" +
                " Got it. I've added this task:\n" +
                String.format("  %s\n", newTask) +
                String.format(" Now you have %d tasks in the list.\n", tasks.size()) +
                "____________________________________________________________";
        System.out.println(echoMessage);
    }

    public void markTaskDone(Task chosenTask) {
        chosenTask.markAsDone();
        String taskStatusMessage = " Nice! I've marked this task as done:\n" +
                String.format("  %s\n", chosenTask);
        printTaskUpdateMessage(taskStatusMessage);
    }

    public void markTaskUndone(Task chosenTask) {
        chosenTask.markAsUndone();
        String taskStatusMessage = " OK, I've marked this task as not done yet:\n" +
                String.format("  %s\n", chosenTask);
        printTaskUpdateMessage(taskStatusMessage);
    }

    public void deleteTask(int taskIndex, Task chosenTask) {
        tasks.remove(taskIndex);
        String taskStatusMessage = " Noted. I've removed this task:\n" +
                String.format("  %s\n", chosenTask) +
                String.format(" Now you have %d tasks in the list.\n", tasks.size());
        printTaskUpdateMessage(taskStatusMessage);
    }

    public void printTaskUpdateMessage(String taskStatusMessage) {
        String finalMessage = "____________________________________________________________\n" +
                taskStatusMessage +
                "____________________________________________________________";
        System.out.println(finalMessage);
    }

    public void processUserInput() {
        label:
        while (this.inputScanner.hasNext()) {
            String[] input = inputScanner.nextLine().split(" ");
            try {
                switch (input[0]) {
                    case "bye":
                        this.bye();
                        break label;
                    case "list":
                        this.listAllTasks();
                        break;
                    case "mark":
                    case "unmark":
                    case "delete":
                        if (tasks.size() == 0) throw new BongoException("OOPS!!! There are currently no tasks.");
                        if (input.length < 2) throw new BongoException("OOPS!!! Please include the task index.");
                        int taskIndex = Integer.parseInt(input[1]) - 1;
                        if (taskIndex < 0 || taskIndex >= tasks.size())
                            throw new BongoException("OOPS!!! Task does not exist.");
                        Task chosenTask = tasks.get(taskIndex);
                        if (input[0].equals("mark")) {
                            this.markTaskDone(chosenTask);
                        } else if (input[0].equals("unmark")) {
                            this.markTaskUndone(chosenTask);
                        } else {
                            this.deleteTask(taskIndex, chosenTask);
                        }
                        continue;
                    case "todo":
                        this.addTodo(input);
                        break;
                    case "deadline": {
                        this.addDeadline(input);
                        break;
                    }
                    case "event": {
                        this.addEvent(input);
                        break;
                    }
                    default:
                        throw new BongoException();
                }
            } catch (BongoException e) {
                System.out.println(printError(e.getMessage()));
            }
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        Bongo bongo = new Bongo();
        bongo.greet();
        bongo.loadTasksFromStorage();
        bongo.processUserInput();
    }

    public static String printError(String error) {
        return "____________________________________________________________\n" +
                String.format(" %s\n", error) +
                "____________________________________________________________";
    }
}

