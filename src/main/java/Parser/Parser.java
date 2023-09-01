package Parser;
import Task.*;
import Duke.*;
import Command.*;


import java.io.FileNotFoundException;

public class Parser {

    public static Task parseTask(String taskData) throws FileNotFoundException, DukeException {
        try {
            String taskType = taskData.substring(1, 2);
            boolean isDone = taskData.charAt(4) == 'X';
            String taskInfo = taskData.substring(7);
            Task taskToAdd = null;

            switch (taskType) {
                case "A":
                    taskToAdd = new Add(taskInfo);
                    break;
                case "T":
                    taskToAdd = new ToDo(taskInfo);
                    break;
                case "D":
                    taskInfo = taskInfo.substring(0, taskInfo.indexOf("(") - 1);
                    String xtraInfo = taskData.substring(taskData.indexOf("(") + 1, taskData.indexOf(")"));
                    String[] deadLineInfo = xtraInfo.split(": ");
                    String by = deadLineInfo[1];
                    taskToAdd = new DeadLine(taskInfo, by);
                    break;
                case "E":
                    taskInfo = taskInfo.substring(0, taskInfo.indexOf('(') - 1);
                    String addInfo = taskData.substring(taskData.indexOf('(') + 1, taskData.indexOf(')'));
                    String[] eventInfo = addInfo.split(": ");
                    String from = eventInfo[1].substring(0, eventInfo[1].length() - 2).trim();
                    String to = eventInfo[2].trim();
                    taskToAdd = new Event(taskInfo, from, to);
                    break;
                default:
                    throw new DukeException("File is corrupted!");
            }
            if (taskToAdd != null) {
                if (isDone) {
                    taskToAdd.isCompleted();
                }
                return taskToAdd;
            }
        } catch (Exception e) {
            return new Task("(CORRUPTED) " + taskData);
        }
        return null;
    }


    public static Command parse(String input) throws DukeException {
        String[] words = input.split(" ");
        String command = words[0].toLowerCase();

        if (words.length < 2 && !command.equals("bye") && !command.equals("list") && !command.equals("help")) {
            throw new DukeException("The description of the command cannot be empty.");
        }

        switch (command) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            case "todo":
                String todoDesc = input.substring(5).trim(); // Extract the description
                Task todoTask = new ToDo(todoDesc);
                return new ToDoCommand(todoTask);
            case "add":
                String addDesc = input.substring(4).trim();
                Task addTask = new ToDo(addDesc);
                return new AddCommand(addTask);
            case "deadline":
                String deadlineDesc = input.substring(8).trim(); // Extract the description
                if (!deadlineDesc.contains("/by")) {
                    throw new DukeException("The deadline command should include '/by'.");
                }
                String[] deadlineParts = deadlineDesc.split("/by");
                String description = deadlineParts[0].trim();
                String by = deadlineParts[1].trim();
                return new DeadLineCommand(description, by);
            case "event":
                String eventDesc = input.substring(5).trim(); // Extract the description
                if (!eventDesc.contains("/from") || !eventDesc.contains("/to")) {
                    throw new DukeException("The event command should include '/from' and '/to'.");
                }
                String[] eventParts = eventDesc.split("/from");
                String eventDescription = eventParts[0].trim();
                String[] dateParts = eventParts[1].split("/to");
                String from = dateParts[0].trim();
                String to = dateParts[1].trim();
                return new EventCommand(eventDescription, from, to);
            case "unmark":
                if (words.length < 2) {
                    throw new DukeException("Please specify a task number to unmark.");
                }
                int taskNumberToUnmark = Integer.parseInt(words[1]) - 1; // Assuming tasks are 1-based
                return new UnmarkCommand(taskNumberToUnmark);
            case "mark":
                if (words.length < 2) {
                    throw new DukeException("Please specify a task number to mark as done.");
                }
                int taskNumberToMark = Integer.parseInt(words[1]) - 1; // Assuming tasks are 1-based
                return new MarkCommand(taskNumberToMark);
            case "echo":
                if (words.length < 2) {
                    throw new DukeException("Please provide text for the echo command.");
                }
                String echoText = input.substring(5).trim(); // Extract the text
                return new EchoCommand(echoText);
            case "delete":
                int taskNumToDel = Integer.parseInt(words[1]) - 1;
                return new DeleteCommand(taskNumToDel);
            case "help":
                return new HelpCommand();
            default:
                throw new DukeException("I'm sorry, but I don't understand that command.");
        }
    }
}
