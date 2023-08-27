import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Duke {

    /**
     * Formats chatbot output with borders, first line indentation and new line character at the end.
     * @param message Formatted message to be displayed to user.
     */
    public static void output(String message) {
        String line = "    ____________________________________________________________\n";
        String template = line + "     %s\n" + line;
        System.out.printf((template) + "%n", message);
    }

    /**
     * Formats chatbot output specifically for adding/removing tasks.
     * @param task Task added to list.
     */
    public static void taskOutput(Task task, String action) {
        String taskMessage = "I've %s the following task as requested:\n       %s\n     "
                + "There are currently %d tasks in your list.";
        Duke.output(String.format(taskMessage, action, task.toString(), Task.taskList.size()));
    }

    /**
     * Parses Date string input for Deadline/Event creation.
     * @param dateString A string in the form [day.month.year 24hrTime].
     * @return LocalDate object.
     * @throws DateTimeParseException To be handled in parseUserInput().
     */
    private static LocalDateTime parseDate(String dateString) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HHmm");
        return LocalDateTime.parse(dateString, formatter);
    }

    /**
     * Initialises chatbot by loading data from file, queries user for decision when file cannot be read.
     */
    public static void initialise() {
        if (!Storage.loadData()) {  // Error with loading data file, prompts for user input for next step
            boolean isUnresolved = true;
            Scanner userInput = new Scanner(System.in);
            while (isUnresolved) {
                String input = userInput.nextLine().toUpperCase();
                switch (input) {
                case "Y":
                    parseUserInput();
                    isUnresolved = false;
                    break;
                case "N":
                    isUnresolved = false;
                    Duke.output("Exiting program... Restore or delete the data file");
                    break;
                default:
                    Duke.output("Please reply with Y/N only");
                    break;
                }
            }
            userInput.close();
        } else {    // No error with reading data
            parseUserInput();
        }
    }

    /**
     * Parses and acts on user input for chatbot.
     */
    private static void parseUserInput() {
        boolean isRun = true;
        Scanner userInput = new Scanner(System.in);
        Duke.output("Hey its Jyuuni, your helpful assistant.\n     How can I help you?");

        // Requests user input until program is instructed to end.
        while (isRun) {
            String input = userInput.nextLine();
            String[] splitInput = input.split(" ", 2);

            switch (splitInput[0]) {
            case "mark":
                try {
                    int index = Integer.parseInt(splitInput[1]) - 1;
                    Task.changeStatusIndex(index, true);
                } catch (NumberFormatException e) {             // If argument of "mark" is not a number.
                    Duke.output("You need to provide a valid number");
                }
                break;
            case "unmark":
                try {
                    int index = Integer.parseInt(splitInput[1]) - 1;
                    Task.changeStatusIndex(index, false);
                } catch (NumberFormatException e) {             // If argument of "unmark" is not a number.
                    Duke.output("You need to provide a valid number");
                }
                break;
            case "delete":
                try {
                    int index = Integer.parseInt(splitInput[1]) - 1;
                    Task.deleteTask(index);
                } catch (NumberFormatException e) {             // If argument of "delete" is not a number.
                    Duke.output("You need to provide a valid number:\n" + "       eg. delete 1");
                }
                break;
            case "todo":
                if (splitInput.length != 2) {                   // Checks for description before creating task.
                    Duke.output("Wrong format, make sure your command is in the format:\n"
                            + "       todo [DESCRIPTION]");
                } else {
                    Task todo = Todo.addTodo(splitInput[1]);
                    Duke.taskOutput(todo, "added");
                }
                break;
            case "deadline":
                try {
                    String[] deadVar = splitInput[1].split(" /by ", 2);
                    Task deadline = Deadline.addDeadline(deadVar[0], Duke.parseDate(deadVar[1]));
                    Duke.taskOutput(deadline, "added");
                } catch (ArrayIndexOutOfBoundsException e) {    // String not split due to improper format
                    Duke.output("Wrong format, make sure your command is in the format:\n"
                            + "      deadline [DESCRIPTION] /by [dd.mm.yyyy]");
                } catch (DateTimeParseException e) {            // Date not formatted properly
                    Duke.output("Try the date format [dd.mm.yyyy tttt]:"
                            + "\n       eg. [05.08.2020 1500] for 5 Aug 2020, 3PM");
                }
                break;
            case "event":
                // Using 1 split statement that splits by /(from|to) allows wrong combos like "/to x /from x".
                try {
                    String[] eventVar = splitInput[1].split(" /from ", 2);
                    String[] times = eventVar[1].split(" /to ", 2);
                    LocalDateTime start = parseDate(times[0]);
                    LocalDateTime end = parseDate(times[1]);
                    if (start.isAfter(end)) {                   // Checks that event start <= end
                        Duke.output("An event cannot end before it starts... might wanna check your dates");
                    } else {
                        Task event = Event.addEvent(eventVar[0], start, end);
                        Duke.taskOutput(event, "added");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {    // String not split due to improper format
                    Duke.output("Wrong format, make sure your command is in the format:\n"
                            + "      event [DESCRIPTION] /from [dd.mm.yyyy] /to [dd.mm.yyyy]");
                } catch (DateTimeParseException e) {            // Date not formatted properly
                    Duke.output("Try the date format [dd.mm.yyyy tttt]:"
                            + "\n       eg. [05.08.2020 1500] for 5 Aug 2020, 3PM");
                }
                break;
            case "list":
                Duke.output(Task.listToString());
                break;
            case "end":
                isRun = false;
                Duke.output("Come back if you need anything else!");
                userInput.close();
                break;
            default:
                Duke.output("Sorry, I don't recognise this comment :(");
                break;
            }
            Storage.writeToFile(Task.taskList);
        }
    }

    public static void main(String[] args) {
        Duke.initialise();
    }
}
