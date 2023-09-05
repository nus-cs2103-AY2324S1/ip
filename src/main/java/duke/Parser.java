package duke;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteComand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;

import duke.task.Event;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

public class Parser {

    public static Command parse(String strCommand) throws DukeException{
        int firstSpaceIndex = strCommand.indexOf(" ");
        Command command = null;
        String commandType = firstSpaceIndex != -1
                ? strCommand.substring(0, firstSpaceIndex)
                : strCommand;
        ArrayList<String> commandDetailList = new ArrayList<>();
        switch (commandType) {
        case "list":
            commandDetailList.add(strCommand);
            command = new ListCommand(commandDetailList);
            break;
        case "bye":
            commandDetailList.add(strCommand);
            command = new ByeCommand(commandDetailList);
            break;
        case "todo":
            if (firstSpaceIndex == -1 || strCommand.length() < 6) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            String todoDesc = strCommand.substring(firstSpaceIndex + 1);
            commandDetailList.add(todoDesc);
            command = new AddCommand(commandDetailList, "T");
            break;
        case "event":
            int fromIndex = strCommand.indexOf("/from");
            int toIndex = strCommand.indexOf("/to");
            if (firstSpaceIndex == -1 || fromIndex == -1 || toIndex == -1 || toIndex < fromIndex) {
                throw new DukeException("☹ OOPS!!! The format of the event command is invalid.\n"
                        + "Here is an example of a valid format:"
                        + " event coding /from 2023-01-01 /to 2023-12-31");
            }
            String eventDesc = strCommand.substring(firstSpaceIndex + 1, fromIndex - 1);
            String from = strCommand.substring(fromIndex + 6, toIndex - 1);
            String to = strCommand.substring(toIndex + 4);
            if (eventDesc.isBlank() || from.isBlank() || to.isBlank()) {
                throw new DukeException("☹ OOPS!!! The format of the event command is invalid.\n"
                        + "Here is an example of a valid format:"
                        + " event coding /from 2023-01-01 /to 2023-12-31");
            }
            commandDetailList.add(eventDesc);
            commandDetailList.add(from);
            commandDetailList.add(to);
            command = new AddCommand(commandDetailList, "E");
            break;
        case "deadline":
            int byIndex = strCommand.indexOf("/by");
            if (byIndex == -1 || firstSpaceIndex == -1) {
                throw new DukeException("☹ OOPS!!! The format of the deadline command is invalid.\n"
                        + "Here is an example of a valid format:"
                        + " deadline coding /by 2023-09-04");
            }
            String deadlineDesc = strCommand.substring(firstSpaceIndex + 1, byIndex - 1);
            String by = strCommand.substring(byIndex + 4);
            if (deadlineDesc.isBlank() || by.isBlank()) {
                throw new DukeException("☹ OOPS!!! The format of the deadline command is invalid.\n"
                        + "Here is an example of a valid format:"
                        + " deadline coding /by 2023-09-04");
            }
            commandDetailList.add(deadlineDesc);
            commandDetailList.add(by);
            command = new AddCommand(commandDetailList, "D");
            break;
        case "mark":
            if (firstSpaceIndex == -1 || strCommand.length() < 6) {
                throw new DukeException("☹ OOPS!!! The task number to mark cannot be empty.");
            }
            String taskToMark = strCommand.substring(firstSpaceIndex + 1);
            if (taskToMark.isBlank()) {
                throw new DukeException("☹ OOPS!!! The task number to mark cannot be empty.");
            }
            commandDetailList.add(taskToMark);
            command = new MarkCommand(commandDetailList);
            break;
        case "unmark":
            if (firstSpaceIndex == -1 || strCommand.length() < 8) {
                throw new DukeException("☹ OOPS!!! The task number to unmark cannot be empty.");
            }
            String taskToUnmark = strCommand.substring(firstSpaceIndex + 1);
            if (taskToUnmark.isBlank()) {
                throw new DukeException("☹ OOPS!!! The task number to unmark cannot be empty.");
            }
            commandDetailList.add(taskToUnmark);
            command = new UnmarkCommand(commandDetailList);
            break;
        case "delete":
            if (firstSpaceIndex == -1 || strCommand.length() < 8) {
                throw new DukeException("☹ OOPS!!! The task number to delete cannot be empty.");
            }
            String taskToDelete = strCommand.substring(firstSpaceIndex + 1);
            if (taskToDelete.isBlank()) {
                throw new DukeException("☹ OOPS!!! The task number to delete cannot be empty.");
            }
            commandDetailList.add(taskToDelete);
            command = new DeleteComand(commandDetailList);
            break;
        case "find":
            if (firstSpaceIndex == -1 || strCommand.length() < 8) {
                throw new DukeException("☹ OOPS!!! The find keyword cannot be empty.");
            }
            String keyword = strCommand.substring(firstSpaceIndex + 1);
            if (keyword.isBlank()) {
                throw new DukeException("☹ OOPS!!! The find keyword cannot be empty.");
            }
            commandDetailList.add(keyword);
            command = new FindCommand(commandDetailList);
            break;
        default:
            throw new DukeException("☹ OOPS!!! This command is invalid.");
        }
        return command;
    }

    public static Task dataToTask(String data) throws DukeException {
        String taskType = data.substring(0, 1);
        String taskData = data.substring(4);
        Task task = null;
        int firstSplitIndex = -1;
        int secondSplitIndex = -1;
        int thirdSplitIndex = -1;
        String desc = "";
        boolean isDone = false;

        switch (taskType) {
        case "T":
            firstSplitIndex = taskData.indexOf("|");
            isDone = taskData.substring(0, firstSplitIndex - 1).equals("1");
            desc = taskData.substring(firstSplitIndex + 2);
            task = new Todo(desc);
            if (isDone) {
                task.markDone();
            }
            break;
        case "D":
            firstSplitIndex = taskData.indexOf("|");
            secondSplitIndex = taskData.indexOf("|", firstSplitIndex + 1);
            isDone = taskData.substring(0, firstSplitIndex - 1).equals("1");
            desc = taskData.substring(firstSplitIndex + 2, secondSplitIndex - 1);
            String by = taskData.substring(secondSplitIndex + 2);
            task =  new Deadline(desc, by);
            if (isDone) {
                task.markDone();
            }
            break;
        case "E":
            firstSplitIndex = taskData.indexOf("|");
            secondSplitIndex = taskData.indexOf("|", firstSplitIndex + 1);
            thirdSplitIndex = taskData.indexOf("|", secondSplitIndex + 1);
            isDone = taskData.substring(0, firstSplitIndex - 1).equals("1");
            desc = taskData.substring(firstSplitIndex + 2, secondSplitIndex - 1);
            String from = taskData.substring(secondSplitIndex + 2, thirdSplitIndex - 1);
            String to = taskData.substring(thirdSplitIndex + 2);
            task = new Event(desc, from, to);
            if (isDone) {
                task.markDone();
            }
            break;
        }
        return task;
    }
}
