import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final int DEADLINEOFFSET = 9;
    public static final int EVENTOFFSET = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        int totalItemNumber = 0;
        String chatBotName = "Benedict Cucumber Badge";
        System.out.println("Hello! I'm " + chatBotName);
        System.out.println("What can I do for you?");

        boolean dontTerminate = true;
        while (dontTerminate) {
            String input = scanner.nextLine();
            try {
                Command command = Command.getCommand(input);
                // Split the string based on spaces
                String[] splitString = input.split(" ");
                try {
                    switch (command) {
                        case BYE:
                            System.out.println("Bye. Hope to see you again soon!");
                            dontTerminate = false;
                            break;
                        case LIST:
                            System.out.println("Here are the tasks in your list:");
                            for (int i = 0; i < totalItemNumber; i++) {
                                System.out.println(i + 1 + ". " + taskList.get(i).toString());
                            }
                            break;
                        case UNMARK:
                            // Order matters, if we check for marks first, unmark falls under mark loop
                            // Split the string based on spaces
                            // The input after the mark word should be task no (which should be index 1)
                            int taskNumberUnmark = Integer.parseInt(splitString[1]);
                            // The above should throw a NumberFormatException
                            Task selectedTaskUnmark = taskList.get(taskNumberUnmark - 1);
                            selectedTaskUnmark.undoTask();
                            break;
                        case MARK:
                            // The input after the mark word should be task no (which should be index 1)
                            int taskNumberMark = Integer.parseInt(splitString[1]);
                            // The above should throw a NumberFormatException
                            Task selectedTaskMark = taskList.get(taskNumberMark - 1);
                            selectedTaskMark.completeTask();
                            break;
                        case TODO:
                            // Test whether a ToDos input is valid
                            ToDos.taskValidator(input);
                            // for To-Dos anything after the command is task name
                            Task toDo = new ToDos(input.substring(5));
                            taskList.add(toDo);
                            totalItemNumber++;
                            toDo.taskAdded(totalItemNumber);
                            break;
                        case DEADLINE:
                            // Test whether a deadline's input is valid
                            Deadline.taskValidator(input);
                            // for Deadline is slightly more complex, we will split by /by
                            String[] segementedString = input.split(" /by ");
                            // We should only have 2 segments for the Array, before and after
                            String deadline = segementedString[1];
                            // Hardcoded because we know how words are positioned
                            String taskNameDeadline = segementedString[0].substring(DEADLINEOFFSET);
                            Task deadlineTask = new Deadline(taskNameDeadline, deadline);
                            taskList.add(deadlineTask);
                            totalItemNumber++;
                            deadlineTask.taskAdded(totalItemNumber);
                            break;
                        case EVENT:
                            // Test whether a event's input is valid
                            Event.taskValidator(input);
                            // Event, split string twice to get relevant component
                            String[] segmentedViaBy = input.split(" /from ");
                            String taskNameEvent = segmentedViaBy[0].substring(EVENTOFFSET);
                            String[] segmentedViaTo = segmentedViaBy[1].split(" /to ");
                            String start = segmentedViaTo[0];
                            String end = segmentedViaTo[1];
                            Task event = new Event(taskNameEvent, start, end);
                            taskList.add(event);
                            totalItemNumber++;
                            event.taskAdded(totalItemNumber);
                            break;
                        case DELETE:
                            int taskNumberDelete = Integer.parseInt(splitString[1]);
                            Task toDeleteTask = taskList.get(taskNumberDelete - 1);
                            taskList.remove(taskNumberDelete - 1);
                            totalItemNumber--;
                            toDeleteTask.taskDeleted(totalItemNumber);
                            break;

                    }
                } catch (NullPointerException e) {
                    // If we mark a task number outside the range
                    System.out.println(e.toString() + "\nPlease enter a valid task number from list");
                } catch (NumberFormatException e) {
                    // If we mark a non int task number
                    System.out.println("Enter a valid task number that is a integer shown in list");
                } catch (WrongInputTask e) {
                    System.out.println(e.toString());
                } catch (IndexOutOfBoundsException e) {
                    // To catch invalid number inputs for delete 
                    System.out.println("Please enter a valid task number from the range in  list");
                }
            } catch (InvalidInputException e) {
                String commandWord = input.split(" ")[0];
                System.out.println("You have entered a invalid command, "
                        + commandWord + " is not a valid command");
                String message = "valid commands: ";
                for (int i = 0; i < Command.validCommands.length; i++) {
                    message += ("\n" + Command.validCommands[i]);
                }
                System.out.println(message);
            }
        }





    }
}
