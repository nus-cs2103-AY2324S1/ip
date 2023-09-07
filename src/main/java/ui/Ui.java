package ui;

import customexceptions.WrongCommandException;
import parser.Parser;
import storage.Storage;
import tasks.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Ui {
    private Parser parser;
    private TaskList tasks;
    private Storage store;

    public String takeCommands(Storage storage, TaskList task, Parser parse, String command) throws IOException {
        parser = parse;
        tasks = task;
        store = storage;

        ArrayList<String> commands = new ArrayList<>();
        String[] commandList = {"todo", "deadline", "event", "mark", "unmark", "bye"};
        Collections.addAll(commands, commandList);

        if (command.equalsIgnoreCase("bye")) {
            return command + " " + command + "...please come back soon :(";
        } else {
            if (command.equalsIgnoreCase("list")) {
                return listCommand(tasks);
            } else if (command.toLowerCase().contains("unmark")) {
                return unmarkCommand(command, tasks, store);
            } else if (command.toLowerCase().contains("mark")) {
                return markCommand(command, tasks, store);
            } else if (command.toLowerCase().contains("delete")) {
                return deleteCommand(command, tasks, store);
            } else if (command.toLowerCase().contains("find ")) {
                return findCommand(command, tasks);
            } else {
                return processTask(command, tasks, parser, commands);
            }
        }
    }

    private String listCommand(TaskList tasks) {
        StringBuilder result = new StringBuilder();
        result.append(tasks.printList()).append("\n");
        return result.toString();
    }

    private String unmarkCommand(String input, TaskList tasks, Storage store) throws IOException {
        StringBuilder result = new StringBuilder();
        int number = parser.findNum(input);
        try {
            result.append(tasks.retrieve(number - 1).unmarkDone());
        } catch (IndexOutOfBoundsException e) {
            result.append(number).append(" is too high! List size is only ").append(tasks.size()).append("\n");
        } finally {
            store.overwrite();
        }
        return result.toString();
    }

    private String markCommand(String input, TaskList tasks, Storage store) throws IOException {
        StringBuilder result = new StringBuilder();
        int number = parser.findNum(input);
        try {
            result.append(tasks.retrieve(number - 1).markDone());
        } catch (IndexOutOfBoundsException e) {
            result.append(number).append(" is too high! List size is only ").append(tasks.size()).append("\n");
        } finally {
            store.overwrite();
        }
        return result.toString();
    }

    private String deleteCommand(String input, TaskList tasks, Storage store) throws IOException {
        StringBuilder result = new StringBuilder();
        int number = parser.findNum(input);
        try {
            Task index = tasks.retrieve(number - 1);
            tasks.remove(index);
            result.append("I have deleted the following task:\n").append(index.toString()).append("\nYour list has ")
                    .append(tasks.size()).append(" items left\n\n");
        } catch (IndexOutOfBoundsException e) {
            result.append(number).append(" is too high! List size is only ").append(tasks.size()).append("\n");
        }
        store.overwrite();
        return result.toString();
    }

    private String findCommand(String input, TaskList tasks) {
        StringBuilder result = new StringBuilder();
        result.append("Here are the matching items in your list:\n").append("\n");
        result.append(tasks.find(parser.find(input))).append("\n");
        return result.toString();
    }

    private String processTask(String input, TaskList tasks, Parser parser, ArrayList<String> commands) throws IOException {
        StringBuilder result = new StringBuilder();
        if (input.toLowerCase().contains("todo ")) {
            Task newTask = new ToDos(parser.taskName(input), false);
            tasks.add(newTask);
            store.write(newTask);
            result.append("Okay! I have added the following task\n").append(newTask).append("\n");
        } else if (input.toLowerCase().contains("deadline ")) {
            Task newTask = new Deadlines(parser.taskName(input), parser.taskBy(input), false);
            tasks.add(newTask);
            result.append("Okay! I have added the following task\n").append(newTask).append("\n");
            store.write(newTask);
        } else if (input.toLowerCase().contains("event ")) {
            Task newTask = new Events(parser.taskName(input), parser.taskFrom(input), parser.taskTo(input), false);
            tasks.add(newTask);
            result.append("Okay! I have added the following task\n").append(newTask.toString()).append("\n");
            store.write(newTask);
        } else {
            try {
                if (!commands.contains(input.split(" ")[0].toLowerCase())) {
                    throw new WrongCommandException(input);
                }
            } catch (WrongCommandException e) {
                result.append(e.getMessage()).append("\n");
            }
        }
        return result.toString();
    }

}
