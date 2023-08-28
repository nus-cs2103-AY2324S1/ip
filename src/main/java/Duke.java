import java.util.*;
public class Duke {
    protected static String H_LINE = "____________________________________________________________\n";
    protected static boolean botInUse = true;
    protected static TaskList taskList = new TaskList();
    protected static Ui ui = new Ui();
    protected static Storage storage = new Storage();

    public static void updateSaveFile() {
        storage.saveFile(taskList.outputNumberedList());
    }
    public static void listen(String input) throws InvalidUserInputException {
        if (input.equals("bye")) {
            botInUse=false;
            ui.bye();
        } else if (input.equals("list")) {
            String outputList = taskList.outputNumberedList();
            ui.list(outputList);
        } else if (input.contains("unmark")) {
            int a = Integer.parseInt(input.substring(7));
            Task t = taskList.getTask(a-1);
            t.markAsUndone();
            ui.unmarkTask(t);
            updateSaveFile();
        } else if (input.contains("mark")) {
            int a = Integer.parseInt(input.substring(5));
            Task t = taskList.getTask(a-1);
            t.markAsDone();
            ui.markTask(t);
            updateSaveFile();
        } else if (input.contains("todo")) {
            try {
                String toDoDescription = input.split("todo ")[1];
                ToDo toDoTask = new ToDo(toDoDescription);
                taskList.addTask(toDoTask);
                ui.toDoAdded(toDoTask, taskList);
                updateSaveFile();
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.toDoMissingContent();
            }
        } else if (input.contains("deadline")) {
            try {
                String by = input.split("/by ")[1];
                String deadlineDescription = input.split("deadline ")[1].split(" /by")[0];
                Deadline deadlineTask = new Deadline(deadlineDescription, by);
                taskList.addTask(deadlineTask);
                ui.deadlineAdded(deadlineTask, taskList);
                updateSaveFile();
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.deadlineMissingContent();
            }
            } else if (input.contains("event")) {
            try {
                String from = input.split("/from ")[1].split(" /to")[0];
                String to = input.split("/to ")[1];
                String eventDescription = input.split("event ")[1].split(" /from")[0];
                Event eventTask = new Event(eventDescription, from, to);
                taskList.addTask(eventTask);
                ui.eventAdded(eventTask, taskList);
                updateSaveFile();
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.eventMissingContent();
            }
        } else if (input.contains("delete")) {
            int a = Integer.parseInt(input.substring(7));
            Task toBeRemoved = taskList.getTask(a-1);
            taskList.deleteTask(a-1);
            ui.taskDeleted(toBeRemoved, taskList);
            updateSaveFile();
        } else {
            throw new InvalidUserInputException();
        }
    }
    public static void main(String[] args) {
        storage.loadFileToTaskManager(taskList);
        ui.greetings();
        Scanner sc = new Scanner(System.in);
        while(botInUse) {
            String input = sc.nextLine();
            try {
                listen(input);
            } catch (InvalidUserInputException e) {
                ui.invalidInputRes();
            }
        }

    }


}
