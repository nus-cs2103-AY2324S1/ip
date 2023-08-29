package Parser;

import Evaluator.*;
import TaskList.TaskList;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import Exception.*;

public class Parser {
    public QueryObject prepareArguments(String userInput) throws KevinException {
        String[] querySplit = userInput.trim().split(" ", 2);

        if (querySplit.length == 0) {
            throw new KevinException("Please input a command.");
        }
        // get the command
        Commands command;
        ArrayList<String> args = new ArrayList<>();

        try {
            command = Commands.valueOf(querySplit[0].toUpperCase());
        } catch (IllegalArgumentException err) {
            throw new KevinException("Command is invalid.");
        }

        if (command == Commands.BYE || command == Commands.LIST) {
            if (querySplit.length > 1) {
                throw new KevinException(command.name() + " command does not take any input.");
            }
        } else if (command == Commands.MARK || command == Commands.UNMARK || command == Commands.DELETE) {
            String[] argumentSplit = querySplit[1].split(" ");
            if (argumentSplit.length > 1) {
                throw new KevinException(command.name() + " command only takes one input.");
            }
            try {
                Integer.parseInt(argumentSplit[0]);
            } catch (NumberFormatException err) {
                throw new KevinException(command.name() + " input must be an integer.");
            }
            args.add(argumentSplit[0]);
        } else if (command == Commands.TODO) {
            if (querySplit.length != 2) {
                throw new KevinException("Todo command needs to follow \"todo {todo name}.\"");
            }
            args.add(querySplit[1]);
        } else if (command == Commands.DEADLINE) {
            String[] argumentSplit = querySplit[1].split(" /by");
            if (argumentSplit.length != 2) {
                throw new KevinException("Deadline command needs to follow " +
                        "\"deadline {deadline name} /by {deadline date}\".");
            }
            args.add(argumentSplit[0]);
            args.add(argumentSplit[1]);
        } else if (command == Commands.EVENT) {
            String[] argumentGetName = querySplit[1].split(" /from", 2);
            if (argumentGetName.length != 2) {
                throw new KevinException("Event command needs to follow " +
                        "\"event {deadline name} /from {event start time} /to {event end time}\".");
            }
            String[] argumentGetDate = argumentGetName[1].split(" /to", 2);
            if (argumentGetDate.length != 2) {
                throw new KevinException("Event command needs to follow " +
                        "\"event {deadline name} /from {event start time} /to {event end time}\".");
            }
            args.add(argumentGetName[0]);
            args.add(argumentGetDate[0]);
            args.add(argumentGetDate[1]);
        }
        return new QueryObject(command, args);

    }

//    [todo, args]
//    [list]
//    [bye]
//    [deadline, name, by]
//    [event, name, from, to]
//    public Evaluator getNextEvaluator() throws KevinException {
//        String query = scanner.nextLine();
//        String[] querySplit = query.split(" ", 2);
//        if (querySplit.length == 0) {
//            throw new KevinException("Please input a command.");
//        }
//
////        deadline do homework /by no idea :-p
//        String command = querySplit[0];
//        switch (command) {
//            case "bye":
//
//                if (querySplit.length > 1) {
//                    throw new KevinException("Bye command does not take any input.");
//                }
//                return new ByeEvaluator(taskList);
//            case "list":
//                if (querySplit.length > 1) {
//                    throw new KevinException("List command does not take any input.");
//                }
//                return new ListEvaluator(taskList);
//            case "mark":
//                String[] argumentSplit =  querySplit[1].split(" ");
//                if (argumentSplit.length > 1) {
//                    throw new KevinException("Mark command only takes one input.");
//                }
//                return new MarkEvaluator(taskList, argumentSplit[0]);
//            case "todo":
//                String[] argumentSplit =  querySplit[1].split(" ");
//                return new ToDoEvaluator(taskList, arguments);
//            default:
//                throw new KevinException("Unknown command");
//        }
//    }

//    public TaskList getTaskList() {
//        TaskList taskList = new TaskList();
//        while (true) {
//            String fullCommand = scanner.nextLine();
//            String[] commandSplitted = fullCommand.split(" ");
//            if (commandSplitted.length == 0) {
//                throw
//            }
//
//
//            try {
//                if (command.equals("bye")) {
//                    break;
//                } else if (command.equals("list")) {
//
//                } else if (command.equals("mark")) {
//                    String[] splitted = fullCommand.split(" ");
//                    if (splitted.length < 2) {
//                        throw new TaskException("You need to specify which task is done!");
//                    }
//                    int toDoIndex = Integer.parseInt(splitted[1]);
//                    wrapInHorizontalLines(taskList.mark(toDoIndex));
//                } else if (command.equals("unmark")) {
//                    String[] splitted = fullCommand.split(" ");
//                    if (splitted.length < 2) {
//                        throw new TaskException("You need to specify which task is not done!");
//                    }
//                    int toDoIndex = Integer.parseInt(splitted[1]);
//                    wrapInHorizontalLines(taskList.unmark(toDoIndex));
//                } else if (command.equals("todo")) {
//                    String[] splitted = fullCommand.split(" ", 2);
//                    if (splitted.length < 2) {
//                        throw new ToDoException("The description of a todo cannot be empty.");
//                    }
//                    String name = splitted[1];
//
//                } else if (command.equals("event")) {
//                    String[] splitted = fullCommand.split(" ", 2);
//                    if (splitted.length < 2) {
//                        throw new EventException("The description of an event cannot be empty.");
//                    }
//                    String eventInfo = splitted[1];
//                    String[] splitEventInfo = eventInfo.split(" /");
//                    if (splitEventInfo.length < 3) {
//                        throw new EventException("The description of the event should be complete.");
//                    }
//                    String name = splitEventInfo[0];
//                    String startTime = splitEventInfo[1].replace("from ", "");
//                    String endTime = splitEventInfo[2].replace("to ", "");
//                    wrapInHorizontalLines(taskList.addEvent(name, startTime, endTime));
//                } else if (command.equals("deadline")) {
//                    String[] splitted = fullCommand.split(" ", 2);
//                    if (splitted.length < 2) {
//                        throw new DeadlineException("The description of a deadline cannot be empty.");
//                    }
//                    String deadlineInfo = splitted[1];
//                    String[] splitDeadlineInfo = deadlineInfo.split(" /");
//                    if (splitDeadlineInfo.length < 2) {
//                        throw new DeadlineException("You should provide a deadline.");
//                    }
//                    String name = splitDeadlineInfo[0];
//                    String deadline = splitDeadlineInfo[1].replace("by", "");
//                    wrapInHorizontalLines(taskList.addDeadline(name, deadline));
//                } else if (command.equals("delete")) {
//                    String[] splitted = fullCommand.split(" ");
//                    if (splitted.length < 2) {
//                        throw new TaskException("You need to specify which task wants to be deleted!");
//                    }
//                    int deleteIndex = Integer.parseInt(splitted[1]);
//                    wrapInHorizontalLines(taskList.delete(deleteIndex));
//                } else {
//                    throw new CommandException(" I'm sorry, but I don't know what that means :-(");
//                }
//            } catch (KevinException ke) {
//                this.wrapInHorizontalLines(ke.getMessage());
//            }

//        }
//        return taskList;
//    }
}
