import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
public class Duke {
    private static final String LINE = "_".repeat(60);
    private static ArrayList<Task> tasks;
    private static final String FILE_PATH = "data/duke.txt";

    enum Instruction {
        bye,
        list,
        mark,
        unmark,
        todo,
        deadline,
        event,
        delete
    }

    public static void main(String[] args) {
        tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String userInput;
        boolean loop = true;

        System.out.println(LINE + "\nHello! I'm Dommi\nWhat can I do for you?\n" + LINE);

        while (loop) {
            String task = "";
            String deadlineTask = "";
            String deadline = "";
            String eventTask = "";
            String from = "";
            String to = "";

            if (!scanner.hasNextLine()) {
                break;
            }
            userInput = scanner.nextLine();  // Read user input
            String userInstruction = userInput.split(" ", 2)[0];

            if (userInput.contains(" ")) {
                task = userInput.split(" ", 2)[1];
            }

            System.out.println(LINE);

            try {
                Instruction instruction = Instruction.valueOf(userInstruction);
                switch (instruction) {
                    case bye:
                        exit();
                        loop = false;
                        break;
                    case list:
                        displayList();
                        break;
                    case mark:
                        int markIndex = Integer.parseInt(task);
                        markTaskDone(markIndex, true);
                        break;
                    case unmark:
                        int unmarkIndex = Integer.parseInt(task);
                        markTaskDone(unmarkIndex, false);
                        break;
                    case todo:
                        Todo newTodo = new Todo(task);
                        createNewTask(newTodo);
                        break;
                    case deadline:
                        if (task.contains(" /by ")) {
                            deadlineTask = task.split(" /by ")[0];
                            deadline = task.split(" /by ")[1];
                        }
                        Deadline newDeadline = new Deadline(deadlineTask, deadline);
                        createNewTask(newDeadline);
                        break;
                    case event:
                        if (task.contains(" /from ") && task.contains(" /to ")) {
                            eventTask = task.split(" /from ")[0];
                            from = task.split(" /from ")[1].split(" /to ")[0];
                            to = task.split(" /from ")[1].split(" /to ")[1];
                        }
                        Event newEvent = new Event(eventTask, from, to);
                        createNewTask(newEvent);
                        break;
                    case delete:
                        int deleteIndex = Integer.parseInt(task);
                        deleteTask(deleteIndex);
                        break;
                }
            } catch (NumberFormatException exception) {
                System.out.println("☹ OOPS!!! I'm sorry, but Task ID is invalid!");
                System.out.println(LINE);
            } catch (IllegalArgumentException exception) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(LINE);
            } catch (IOException exception) {
                System.out.println("☹ OOPS!!! " + exception.getMessage());
                System.out.println(LINE);
            } catch (DukeException exception) {
                System.out.println(exception.getMessage());
                System.out.println(LINE);
            }
        }
    }

    private static void createNewTask(Task task) throws IOException {
        tasks.add(task);
        appendToFile(FILE_PATH, task.toWrite());
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void displayList() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks have been created.");
        } else {
            for (int i = 0; i < tasks.size(); i++)
                System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.println(LINE);
    }

    private static void markTaskDone(int taskID, boolean done) throws DukeException, IOException {
        if (taskID <= 0 || taskID > tasks.size()) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but task not found.");
        }
        Task task = tasks.get(taskID - 1);
        if (task.isDone == done) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but task is already marked / unmarked");
        }
        if (done) {
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
        } else {
            task.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
        }
        rewriteFile(FILE_PATH, tasks);
        System.out.println(task);
        System.out.println(LINE);
    }

    private static void deleteTask(int taskID) throws DukeException, IOException {
        if (taskID <= 0 || taskID > tasks.size()) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but task not found.");
        }
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(taskID - 1));
        tasks.remove(taskID - 1);
        rewriteFile(FILE_PATH, tasks);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    private static void appendToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void rewriteFile(String filePath, ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task: tasks)
            fw.write(task.toWrite());
        fw.close();
    }
}
