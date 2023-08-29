import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Cupid {

    private static String saveFilePath = "cupid.txt";

    public enum ChatFunction {
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("____________________________________________________________");
        System.out.println("Initializing...");

        ArrayList<Task> taskList = null;

        try {
            Load load = new Load(saveFilePath);
            taskList = load.load();
        } catch (IOException e) {
            System.out.println("File not found. Terminating operation...");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, I'm Cupid.");
        System.out.println("What can I do for you?");

        while (true) {
            String input = scanner.nextLine();

            if (input.toLowerCase().equals("bye")) {
                break;
            }

            String[] inputArray = input.split(" ");

            try {
                ChatFunction function = ChatFunction.valueOf(inputArray[0].toUpperCase());
                int firstSpaceIndex = input.indexOf(" ");
                String secondHalfInput = input.substring(firstSpaceIndex+1);
                switch (function) {
                    case LIST:
                        listCommandHandler(taskList);
                        continue;

                    case MARK:
                        markCommandHandler(inputArray, taskList);
                        continue;

                    case UNMARK:
                        unmarkCommandHandler(inputArray, taskList);
                        continue;

                    case DELETE:
                        deleteCommandHandler(inputArray, taskList);
                        continue;

                    case TODO:
                        toDoCommandHandler(taskList, secondHalfInput);
                        continue;

                    case DEADLINE:
                        deadlineCommandHandler(taskList, secondHalfInput);
                        continue;

                    case EVENT:
                        eventCommandHandler(taskList, input, secondHalfInput);
                        continue;

                    default:
                        break;
                }
            } catch (IllegalArgumentException e) {
                // If task inserted not an ENUM
                System.out.println("Oops!!! I'm sorry but I don't know what that means :-(");
                System.out.println("Please use one of the following commands: list, mark, unmark, todo, deadline, event, bye");
            }
        }

        try {
            Save save = new Save(taskList);
            System.out.println("Save successful");
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("Save unsuccessful");
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void listCommandHandler(ArrayList<Task> taskList) {
        for (int i=0; i<taskList.size(); i++) {
            String message = String.format("%d. %s", i+1, taskList.get(i).getTaskAsString());
            System.out.println(message);
        };
    }

    public static void markCommandHandler(String[] inputArray, ArrayList<Task> taskList) {
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

    public static void unmarkCommandHandler(String[] inputArray, ArrayList<Task> taskList) {
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

    public static void deleteCommandHandler(String[] inputArray, ArrayList<Task> taskList) {
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
    public static void toDoCommandHandler(ArrayList<Task> taskList, String description) {
        if (description.strip().isEmpty() || description.matches("todo")) {
            System.out.println("OOPS! The description of a todo cannot be empty.");
            return;
        }
        ToDo newTodo = new ToDo(description);
        taskList.add(newTodo);
        System.out.println("Added: " + newTodo.getTaskAsString());
    }

    public static void deadlineCommandHandler(ArrayList<Task> taskList, String secondHalfInput) {
        String[] deadlineInputArray = secondHalfInput.split("/");
        String deadlineDescription = deadlineInputArray[0].substring(0,deadlineInputArray[0].length()-1);
        String deadlineDate = deadlineInputArray[1].substring(3);

        Deadline newDeadline = new Deadline(deadlineDescription, deadlineDate);
        taskList.add(newDeadline);
        System.out.println("Added: " + newDeadline.getTaskAsString());
    }

    public static void eventCommandHandler(ArrayList<Task> taskList, String input, String secondHalfInput) {
        int firstEventSlashIndex = input.indexOf("/");
        String[] inputSplitBySlash = secondHalfInput.split("/");
        String eventDescription = inputSplitBySlash[0].substring(0, inputSplitBySlash[0].length()-1);
        String eventDatesFull = secondHalfInput.substring(firstEventSlashIndex);
        String[] eventDatesArray = eventDatesFull.split("/");
        String eventStartDate = eventDatesArray[0].substring(0, eventDatesArray[0].length()-1);
        String eventEndDate = eventDatesArray[1].substring(3, eventDatesArray[1].length());
        Event newEvent = new Event(eventDescription, eventStartDate, eventEndDate);
        taskList.add(newEvent);
        System.out.println("Added: " + newEvent.getTaskAsString());
    }

}
