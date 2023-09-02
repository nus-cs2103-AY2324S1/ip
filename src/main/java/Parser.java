//import java.util.ArrayList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    private String input;
    private TaskList taskList;

    private enum ChatFunction {
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        FIND
    }

    public Parser(String input, TaskList taskList) {
        this.input = input;
        this.taskList = taskList;
    }

    public void parse() {
        String[] inputArray = input.split(" ");

        try {
            ChatFunction function = ChatFunction.valueOf(inputArray[0].toUpperCase());
            int firstSpaceIndex = input.indexOf(" ");
            String secondHalfInput = input.substring(firstSpaceIndex+1);

            switch (function) {
                case LIST:
                    listCommandHandler(taskList);
                    break;

                case MARK:
                    markCommandHandler(inputArray, taskList);
                    break;

                case UNMARK:
                    unmarkCommandHandler(inputArray, taskList);
                    break;

                case DELETE:
                    deleteCommandHandler(inputArray, taskList);
                    break;

                case TODO:
                    toDoCommandHandler(taskList, secondHalfInput);
                    break;

                case DEADLINE:
                    deadlineCommandHandler(taskList, secondHalfInput);
                    break;

                case EVENT:
                    eventCommandHandler(taskList, input, secondHalfInput);
                    break;

                case FIND:
                    findCommandHandler(taskList, secondHalfInput);

                default:
                    break;
            }
        } catch (IllegalArgumentException e) {
            // If task inserted not an ENUM
            System.out.println("Oops!!! I'm sorry but I don't know what that means :-(");
            System.out.println("Please use one of the following commands: list, mark, unmark," +
                    " delete, todo, deadline, event, bye");
        }
    }

    public void listCommandHandler(TaskList taskList) {
        for (int i=0; i<taskList.size(); i++) {
            String message = String.format("%d. %s", i+1, taskList.get(i).getTaskAsString());
            System.out.println(message);
        };
    }

    public void markCommandHandler(String[] inputArray, TaskList taskList) {
        try {
            int targetTaskIdx = Integer.parseInt(inputArray[1]) -1;
            Task task = taskList.get(targetTaskIdx);
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(task.getTaskAsString());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number provided. Please provide in the form of 'mark {task number}'. Eg: 'mark 1' to mark task 1.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task number provided. Please provide in the form of 'mark {task number}'. Eg: 'mark 1' to mark task 1.");
        }
    }

    public void unmarkCommandHandler(String[] inputArray, TaskList taskList) {
        try {
            int targetTaskIdx = Integer.parseInt(inputArray[1]) -1;
            Task task = taskList.get(targetTaskIdx);
            task.markAsUndone();
            System.out.println("Ok! I've marked this task as not done yet:");
            System.out.println(task.getTaskAsString());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number provided. Please provide in the form of 'unmark {task number}'. Eg: 'unmark 1' to unmark task 1.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task number provided. Please provide in the form of 'unmark {task number}'. Eg: 'unmark 1' to unmark task 1.");
        }
    }

    public void deleteCommandHandler(String[] inputArray, TaskList taskList) {
        try {
            int targetTaskIdx = Integer.parseInt(inputArray[1]) -1;
            Task task = taskList.get(targetTaskIdx);
            taskList.remove(targetTaskIdx);
            System.out.println("Noted: I've removed this task:");
            System.out.println(task.getTaskAsString());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number provided. Please provide in the form of 'mark {task number}'. Eg: 'mark 1' to mark task 1.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task number provided. Please provide in the form of 'mark {task number}'. Eg: 'mark 1' to mark task 1.");
        }
    }

    public void toDoCommandHandler(TaskList taskList, String description) {
        if (description.strip().isEmpty() || description.matches("todo")) {
            System.out.println("OOPS! The description of a todo cannot be empty.");
            return;
        }
        ToDo newTodo = new ToDo(description);
        taskList.add(newTodo);
        System.out.println("Added: " + newTodo.getTaskAsString());
    }

    public void deadlineCommandHandler(TaskList taskList, String secondHalfInput) {

        try {
            String[] deadlineInputArray = secondHalfInput.split("/");
            String deadlineDescription = deadlineInputArray[0].substring(0,deadlineInputArray[0].length()-1);
            String deadlineDateString = deadlineInputArray[1].substring(3);

            LocalDateTime deadlineDate = parseDateTime(deadlineDateString);
            if (deadlineDate == null) {
                return;
            }

            Deadline newDeadline = new Deadline(deadlineDescription, deadlineDate);
            taskList.add(newDeadline);
            System.out.println("Added: " + newDeadline.getTaskAsString());
        } catch (Exception e ) {
            System.out.println("Sorry, I did not understand that. Please enter in the following format: \n" +
                    "deadline {description} /by {deadline}.");
        }

    }

    public void eventCommandHandler(TaskList taskList, String input, String secondHalfInput) {

        try {
            int fromDateStartIdx = secondHalfInput.indexOf("/from") + 6;
            int toDateStartIdx = secondHalfInput.indexOf("/to") + 4;
            int fromDateEndIdx = secondHalfInput.indexOf("/to") - 1;
            int descriptionStartIdx = 0;
            int descriptionEndIdx = secondHalfInput.indexOf("/from") - 1;
            String eventDescription = secondHalfInput.substring(descriptionStartIdx, descriptionEndIdx);
            String fromDateString = secondHalfInput.substring(fromDateStartIdx, fromDateEndIdx);
            String toDateString = secondHalfInput.substring(toDateStartIdx);
            LocalDateTime fromDate = parseDateTime(fromDateString);
            LocalDateTime toDate = parseDateTime(toDateString);
            if (fromDate == null || toDate == null) {
                return;
            }
            Event newEvent = new Event(eventDescription, fromDate, toDate);
            taskList.add(newEvent);
            System.out.println("Added: " + newEvent.getTaskAsString());
        } catch (Exception e) {
            System.out.println("Sorry, I did not understand that. Please enter in the following format: \n" +
                    "event {description} /from {start datetime} /to {end datetime}.");
        }
    }

    public LocalDateTime parseDateTime(String dateTimeString) {
        String[] possibleFormats = {"yyyy-MM-dd HHmm", "yyyy/MM/dd HHmm","dd-MM-yyyy HHmm","dd/MM/yyyy HHmm",
                "yyyy-MM-dd HH:mm", "yyyy/MM/dd HH:mm","dd-MM-yyyy HH:mm","dd/MM/yyyy HH:mm"};
        LocalDateTime dateTime = null;
        for (String format : possibleFormats) {
            try {
                dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern(format));
                return dateTime;
            } catch (DateTimeParseException e) {
                // do nothing, try the next format
            }
        }
        System.out.println("DateTime in an invalid format. Please enter datetime in the following format: \n" +
                "YYYY/MM/DD HH:MM");
        return null;
    }

    public void findCommandHandler(TaskList taskList, String secondHalfInput) {
        TaskList matchingTasks = new TaskList();

        for (Task task: taskList.getTaskList()) {
            if (task.description.contains(secondHalfInput)) {
                matchingTasks.add(task);
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println("Here are the matching tasks in your list:");
        listCommandHandler(matchingTasks);
        System.out.println("____________________________________________________________");
    }
}
