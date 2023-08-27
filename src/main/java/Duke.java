import java.util.Scanner;

public class Duke {
    public static final int DEADLINEOFFSET = 9;
    public static final int EVENTOFFSET = 6;

    private Storage storage;
    private TaskList taskList;
    public Duke(String filePath) {
        this.storage = Storage.createStorage(filePath);
        this.taskList = new TaskList(filePath);
    }


    public void run() {
        Scanner scanner = new Scanner(System.in);
        int totalItemNumber = this.taskList.length();
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
                            for (int i = 0; i < this.taskList.length(); i++) {
                                System.out.println(i + 1 + ". " + this.taskList.getTask(i));
                            }
                            break;
                        case UNMARK:
                            // Order matters, if we check for marks first, unmark falls under mark loop
                            // Split the string based on spaces
                            // The input after the mark word should be task no (which should be index 1)
                            int taskNumberUnmark = Integer.parseInt(splitString[1]);
                            // The above should throw a NumberFormatException
                            this.taskList.unmarkTask(taskNumberUnmark - 1, storage);
                            break;
                        case MARK:
                            // The input after the mark word should be task no (which should be index 1)
                            int taskNumberMark = Integer.parseInt(splitString[1]);
                            // The above should throw a NumberFormatException if not the right number
                            this.taskList.markTask(taskNumberMark - 1, storage);
                            break;
                        case TODO:
                            // Test whether a ToDos input is valid
                            ToDos.taskValidator(input);
                            // for To-Dos anything after the command is task name
                            Task toDo = new ToDos(input.substring(5));
                            totalItemNumber++;
                            this.taskList.addTask(toDo, totalItemNumber, storage);
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
                            DateTime deadlineDateTime = DateTime.createDateTime(deadline);
                            Task deadlineTask = new Deadline(taskNameDeadline, deadlineDateTime);
                            totalItemNumber++;
                            this.taskList.addTask(deadlineTask, totalItemNumber, storage);
                            break;
                        case EVENT:
                            // Test whether a event's input is valid
                            Event.taskValidator(input);
                            // Event, split string twice to get relevant component
                            String[] segmentedViaBy = input.split(" /from ");
                            String taskNameEvent = segmentedViaBy[0].substring(EVENTOFFSET);
                            String[] segmentedViaTo = segmentedViaBy[1].split(" /to ");
                            String start = segmentedViaTo[0];
                            DateTime startDateTime = DateTime.createDateTime(start);
                            String end = segmentedViaTo[1];
                            DateTime endDateTime = DateTime.createDateTime(end);
                            Task event = new Event(taskNameEvent, startDateTime, endDateTime);
                            totalItemNumber++;
                            this.taskList.addTask(event, totalItemNumber, storage);
                            break;
                        case DELETE:
                            int taskNumberDelete = Integer.parseInt(splitString[1]);
                            totalItemNumber--;
                            this.taskList.deleteTask(taskNumberDelete - 1, totalItemNumber, storage);
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

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
