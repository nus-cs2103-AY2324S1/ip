import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    private final String line = "____________________________________________________________";
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private File fileToBeSaved;
    private static final String PATH = "./data/tasks.txt";

    private void greet() {
        System.out.println(line);
        System.out.println("Hello! I'm Fong!");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    private void bye() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    private void explainException(DukeException e) {
        System.out.println(line);
        System.out.println(e.getMessage());
        System.out.println(line);
    }

    private void printTasks() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;

            System.out.println(index + "." + this.tasks.get(i).toString());
        }
        System.out.println(line);
    }

    private String handleTodo(String task) throws DukeException {
        String[] preprocessedTask = task.split("todo ");

        if (preprocessedTask.length <= 1) {
            throw new DukeException("Please enter a valid todo description.");
        }


        Todo nextTodo = new Todo(preprocessedTask[1]);
        this.tasks.add(nextTodo);
        return nextTodo.toString();
    }

    private String handleDeadline(String task) throws DukeException {
        String[] temp = task.split("deadline ");

        if (temp.length <= 1) {
            throw new DukeException("You are missing both a valid deadline description and a deadline. "
                    + "Please enter a valid deadline description and deadline.");
        }

        String[] preprocessedTask = preprocessTask(temp[1]);

        if (preprocessedTask.length <= 1) {
            throw new DukeException("You are missing either a valid deadline description or deadline. "
                    + "Please enter a valid deadline description or deadline.");
        }

        Deadline nextDeadline = new Deadline(preprocessedTask[0], preprocessedTask[1]);
        this.tasks.add(nextDeadline);
        return nextDeadline.toString();
    }

    private String handleEvent(String task) throws DukeException {
        String[] temp = task.split("event ");

        if (temp.length <= 1) {
            throw new DukeException("You are missing both a valid event description and a start and end time. "
                    + "Please enter a valid event description and start and end time.");
        }

        String[] preprocessedTask = preprocessTask(temp[1]);

        if (preprocessedTask.length <= 2) {
            throw new DukeException("You are missing either a valid event description or start and end time. "
                    + "Please enter a valid event description or start and end time.");
        }

        Event nextEvent = new Event(preprocessedTask[0], preprocessedTask[1], preprocessedTask[2]);
        this.tasks.add(nextEvent);
        return nextEvent.toString();
    }

    private void handleInvalid() {
        System.out.println(line);
        System.out.println("You did not enter a valid task. Please enter either a todo, deadline or event.");
        System.out.println(line);
    }

    private void handleTask(String task) throws DukeException {
        String nextTaskString = null;

        if (task.startsWith("todo")) {
            nextTaskString = handleTodo(task);
        } else if (task.startsWith("deadline")) {
            nextTaskString = handleDeadline(task);
        } else if (task.startsWith("event")) {
            nextTaskString = handleEvent(task);
        }

        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(nextTaskString);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
        System.out.println(line);
    }

    private String[] preprocessTask(String temp) {
        String[] preprocessedTask = temp.split(" /by | /from | /to ");

        return preprocessedTask;
    }

    private void handleDelete(String nextTask) throws DukeException {
        String[] split = nextTask.split(" ");

        if (split.length <= 1) {
            throw new DukeException("You did not enter an index. Please enter a valid index to delete.");
        }

        String index = split[1];

        int taskIndex = -1;

        try {
            taskIndex = Integer.parseInt(index) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("You did not enter a valid index. Please enter a valid index to delete.");
        }

        if (taskIndex < 0 || taskIndex >= this.tasks.size()) {
            throw new DukeException("There is no such task index. Please enter a valid index.");
        }

        Task taskToRemove = this.tasks.get(taskIndex);
        this.tasks.remove(taskIndex);

        System.out.println(line);
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskToRemove.toString());
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
        System.out.println(line);
    }

    private void handleMark(String nextTask) throws DukeException {
        String[] split = nextTask.split(" ");

        if (split.length <= 1) {
            throw new DukeException("You did not enter an index. Please enter a valid index to mark or unmark.");
        }

        String action = split[0];
        String index = split[1];

        int taskIndex = -1;

        try {
            taskIndex = Integer.parseInt(index) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("You did not enter a valid index. Please enter a valid index to mark or unmark.");
        }


        if (taskIndex < 0 || taskIndex >= this.tasks.size()) {
            throw new DukeException("There is no such task index. Please enter a valid index.");
        }

        if (action.equals("mark")) {
            this.markTask(taskIndex);
        } else if (action.equals("unmark")) {
            this.unmarkTask(taskIndex);
        }
    }

    private void markTask(int taskIndex) {
        this.tasks.get(taskIndex).doTask();

        System.out.println(line);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.tasks.get(taskIndex).toString());
        System.out.println(line);
    }

    private void unmarkTask(int taskIndex) {
        this.tasks.get(taskIndex).undoTask();

        System.out.println(line);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.tasks.get(taskIndex).toString());
        System.out.println(line);
    }

    private void acceptTasks() {
        Scanner scanner = new Scanner(System.in);
        String nextTask = scanner.nextLine();
        CommandEnum taskEnum = CommandEnum.assignEnum(nextTask);

        while (!taskEnum.equals(CommandEnum.BYE)) {
            switch (taskEnum) {
            case LIST:
                this.printTasks();
                break;
            case MARK:
            case UNMARK:
                try {
                    this.handleMark(nextTask);
                } catch (DukeException e) {
                    this.explainException(e);
                }
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                try {
                    this.handleTask(nextTask);
                } catch (DukeException e) {
                    this.explainException(e);
                }
                break;
            case DELETE:
                try {
                    this.handleDelete(nextTask);
                } catch (DukeException e) {
                    this.explainException(e);
                }
                break;
            default:
                this.handleInvalid();
            }

            nextTask = scanner.nextLine();
            taskEnum = CommandEnum.assignEnum(nextTask);
        }

        scanner.close();

        try {
            this.saveTasks();
        } catch (IOException e) {
            System.out.println("Unable to save your tasks to a file. Try Again.");
        }

        this.bye();
    }

    /**
     * Imports tasks from ./data/tasks.txt.
     */
    private void loadTasks() {
        this.fileToBeSaved = new File(PATH);

        try {
            Scanner fileScanner = new Scanner(fileToBeSaved);

            while (fileScanner.hasNextLine()) {
                String nextTask = fileScanner.nextLine();
                String[] processedTask = nextTask.split(" \\| ");
                String typeOfTask = processedTask[0];
                String taskCompletionStatus = processedTask[1];
                String taskDescription = processedTask[2];

                switch (typeOfTask) {
                case "T":
                    Todo newTodo = new Todo(taskDescription);
                    this.tasks.add(newTodo);
                    if (taskCompletionStatus.equals("X")) {
                        newTodo.doTask();
                    }
                    break;
                case "D":
                    Deadline newDeadline = new Deadline(taskDescription, processedTask[3]);
                    this.tasks.add(newDeadline);
                    if (taskCompletionStatus.equals("X")) {
                        newDeadline.doTask();
                    }
                    break;
                case "E":
                    Event newEvent = new Event(taskDescription, processedTask[3], processedTask[4]);
                    this.tasks.add(newEvent);
                    if (taskCompletionStatus.equals("X")) {
                        newEvent.doTask();
                    }
                    break;
                default:
                    throw new DukeException("tasks.txt may have been corrupted.");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found :(");
        } catch (DukeException e) {
            this.explainException(e);
        }
    }

    /**
     * Saves tasks to ./data/tasks.txt.
     */
    private void saveTasks() throws IOException {
        FileWriter fw = new FileWriter(PATH);

        for (Task currTask : tasks) {
            StringBuilder currEntry = new StringBuilder();

            if (currTask instanceof Todo) {
                Todo todo = (Todo) currTask;
                currEntry.append("T | ");
                currEntry.append(todo.getMarkedIcon());
                currEntry.append(" | ");
                currEntry.append(todo.getTaskDescription());
            } else if (currTask instanceof Deadline) {
                Deadline deadline = (Deadline) currTask;
                currEntry.append("D | ");
                currEntry.append(deadline.getMarkedIcon());
                currEntry.append(" | ");
                currEntry.append(deadline.getTaskDescription());
                currEntry.append(" | ");
                currEntry.append(deadline.getDeadline());
            } else if (currTask instanceof Event) {
                Event event = (Event) currTask;
                currEntry.append("E | ");
                currEntry.append(event.getMarkedIcon());
                currEntry.append(" | ");
                currEntry.append(event.getTaskDescription());
                currEntry.append(" | ");
                currEntry.append(event.getStart());
                currEntry.append(" | ");
                currEntry.append(event.getEnd());
            }

            currEntry.append(System.lineSeparator());
            fw.write(currEntry.toString());
        }

        fw.close();
    }

    public static void main(String[] args) {
        Duke chatBot = new Duke();
        chatBot.loadTasks();
        chatBot.greet();
        chatBot.acceptTasks();
    }
}
