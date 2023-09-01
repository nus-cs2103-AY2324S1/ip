//import java.util.ArrayList;

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
        DELETE
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
        String[] deadlineInputArray = secondHalfInput.split("/");
        String deadlineDescription = deadlineInputArray[0].substring(0,deadlineInputArray[0].length()-1);
        String deadlineDate = deadlineInputArray[1].substring(3);

        Deadline newDeadline = new Deadline(deadlineDescription, deadlineDate);
        taskList.add(newDeadline);
        System.out.println("Added: " + newDeadline.getTaskAsString());
    }

    public void eventCommandHandler(TaskList taskList, String input, String secondHalfInput) {
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
