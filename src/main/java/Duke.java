import java.io.FileNotFoundException;
import java.util.*;
public class Duke {
    protected static String H_LINE = "____________________________________________________________\n";
    protected static boolean botInUse = true;
    protected static TaskManager taskManager = new TaskManager();
    protected static ResponseGen responseGen = new ResponseGen();
    protected static FileManager fileManager = new FileManager();

    public static void updateSaveFile() {
        fileManager.saveFile(taskManager.outputNumberedList());
    }
    public static void listen(String input) throws InvalidUserInputException {
        if (input.equals("bye")) {
            botInUse=false;
            System.out.println(responseGen.bye());
        } else if (input.equals("list")) {
            String outputList = taskManager.outputNumberedList();
            System.out.println(responseGen.list(outputList));
        } else if (input.contains("unmark")) {
            int a = Integer.parseInt(input.substring(7));
            Task t = taskManager.getTask(a-1);
            t.markAsUndone();
            System.out.println(responseGen.unmarkTask(t));
            updateSaveFile();
        } else if (input.contains("mark")) {
            int a = Integer.parseInt(input.substring(5));
            Task t = taskManager.getTask(a-1);
            t.markAsDone();
            System.out.println(responseGen.markTask(t));
            updateSaveFile();
        } else if (input.contains("todo")) {
            try {
                String toDoDescription = input.split("todo ")[1];
                ToDo toDoTask = new ToDo(toDoDescription);
                taskManager.addTask(toDoTask);
                System.out.println(responseGen.toDoAdded(toDoTask, taskManager));
                updateSaveFile();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(responseGen.toDoMissingContent());
            }
        } else if (input.contains("deadline")) {
            try {
                String by = input.split("/by ")[1];
                String deadlineDescription = input.split("deadline ")[1].split(" /by")[0];
                Deadline deadlineTask = new Deadline(deadlineDescription, by);
                taskManager.addTask(deadlineTask);
                System.out.println(responseGen.deadlineAdded(deadlineTask, taskManager));
                updateSaveFile();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(responseGen.deadlineMissingContent());
            }
            } else if (input.contains("event")) {
            try {
                String from = input.split("/from ")[1].split(" /to")[0];
                String to = input.split("/to ")[1];
                String eventDescription = input.split("event ")[1].split(" /from")[0];
                Event eventTask = new Event(eventDescription, from, to);
                taskManager.addTask(eventTask);
                System.out.println(responseGen.eventAdded(eventTask, taskManager));
                updateSaveFile();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(responseGen.eventMissingContent());
            }
        } else if (input.contains("delete")) {
            int a = Integer.parseInt(input.substring(7));
            Task toBeRemoved = taskManager.getTask(a-1);
            taskManager.deleteTask(a-1);
            System.out.println(responseGen.taskDeleted(toBeRemoved, taskManager));
            updateSaveFile();
        } else {
            throw new InvalidUserInputException();
        }
    }
    public static void main(String[] args) {
        
        System.out.println(H_LINE
                            + "Hello! I'm ChadBob.\n"
                            + "What can I do for you?\n"
                            + H_LINE) ;
        Scanner sc = new Scanner(System.in);
        while(botInUse) {
            fileManager.loadFileToTaskManager(taskManager);
            String input = sc.nextLine();
            try {
                listen(input);
            } catch (InvalidUserInputException e) {
                System.out.println(H_LINE
                                    + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                                    + H_LINE);
            }
        }

    }


}
