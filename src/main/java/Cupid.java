import java.util.Scanner;
import java.util.ArrayList;

public class Cupid {

    public enum ChatFunction {
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT
    }

    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<>();

        System.out.println("____________________________________________________________");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, I'm Cupid.");
        System.out.println("What can I do for you?");

        while (true) {
            String input = scanner.nextLine();
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
                        if (inputArray.length < 2) {
                            System.out.println("Invalid mark task provided.");
                            break;
                        }

                        try {
                            int targetTaskIdx = Integer.parseInt(inputArray[1]) -1;
                            Task task = taskList.get(targetTaskIdx);
                            task.markAsDone();
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(task.getTaskAsString());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number provided.");
                            break;
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Invalid task number provided.");
                            break;
                        }

                    case UNMARK:
                        if (inputArray.length < 2) {
                            System.out.println("Invalid unmark task provided.");
                            break;
                        }

                        try {
                            int targetTaskIdx = Integer.parseInt(inputArray[1]) -1;
                            Task task = taskList.get(targetTaskIdx);
                            task.markAsUndone();
                            System.out.println("Ok! I've marked this task as not done yet:");
                            System.out.println(task.getTaskAsString());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number provided.");
                            break;
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Invalid task number provided.");
                            break;
                        }

                    case TODO:
                        ToDo newTodo = new ToDo(secondHalfInput);
                        taskList.add(newTodo);
                        System.out.println("Added: " + newTodo.getTaskAsString());
                        break;

                    case DEADLINE:
                        String[] deadlineInputArray = secondHalfInput.split("/");
                        String deadlineDescription = deadlineInputArray[0].substring(0,deadlineInputArray[0].length()-1);
                        String deadlineDate = deadlineInputArray[1].substring(3);

                        Deadline newDeadline = new Deadline(deadlineDescription, deadlineDate);
                        taskList.add(newDeadline);
                        System.out.println("Added: " + newDeadline.getTaskAsString());
                        break;

                    case EVENT:
                        int firstEventSlashIndex = input.indexOf("/");
                        String eventDescription = secondHalfInput.substring(0, firstEventSlashIndex-1);
                        String eventDatesFull = secondHalfInput.substring(firstEventSlashIndex+1);
                        String[] eventDatesArray = eventDatesFull.split("/");
                        String eventStartDate = eventDatesArray[0].substring(5, eventDatesArray[0].length());
                        String eventEndDate = eventDatesArray[1].substring(3, eventDatesArray[1].length());

                        Event newEvent = new Event(eventDescription, eventStartDate, eventEndDate);
                        break;

                    default:
                        break;
                }
            } catch (IllegalArgumentException e) {
                // If task inserted not an ENUM
                Task newTask = new Task(input);
                taskList.add(newTask);
                System.out.println("Added: " + input);
            }


            if (input.toLowerCase().equals("bye")) {
                break;
            }
        }



        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void listCommandHandler(ArrayList<Task> taskList) {
        for (int i=0; i<taskList.size(); i++) {
            String message = String.format("%d. [%s] %s", i+1, taskList.get(i).getStatusIcon(), taskList.get(i).getDescription());
            System.out.println(message);
        };
    }

}
