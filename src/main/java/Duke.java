import Tasks.DeadlineTask;
import Tasks.EventTask;
import Tasks.TodoTask;

import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    private static final String SEPARATOR_LINE = "____________________________________________________________";
    private static TaskList listContainer = new TaskList();

    private static String NAME_EMPTY = "\uD83D\uDE21 Your item name cannot be empty!";
    private static String UNKNOWN_COMMAND = "\uD83D\uDE21 This command is not something I can handle!";
    private static String DEADLINE_EMPTY = "\uD83D\uDE21 Missing deadline!";

    private static String FROM_EMPTY = "\uD83D\uDE21 Missing from!";
    private static String TO_EMPTY = "\uD83D\uDE21 Missing to!";
    private static String TIME_FORMAT_ERROR = "\uD83D\uDE21 Time format invalid!";

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println(SEPARATOR_LINE);
        String entranceMsg = "Hello! I'm Elon Musk.\n" +
                "What can I do for you?";
        System.out.println(entranceMsg);
        System.out.println(SEPARATOR_LINE);


        String inputString = "";

        Scanner keyboard = new Scanner(System.in);

        while (true) {
            try {
                inputString = keyboard.nextLine();

                if (Objects.equals(inputString, "bye")) {
                    break;
                }
//            System.out.println(inputString);
                System.out.println(SEPARATOR_LINE);

                if (inputString.equals("list")) {
                    // output the list
                    System.out.println(listContainer.toString());
                } else if (inputString.startsWith("mark ")) {
                    // mark the index
                    int index = Integer.parseInt(inputString.split(" ")[1]);
                    listContainer.markAsDone(index);

                } else if (inputString.startsWith("unmark ")) {
                    // unmark the index
                    int index = Integer.parseInt(inputString.split(" ")[1]);
                    listContainer.markAsUnDone(index);
                } else if (inputString.startsWith("todo ")) {
                    // add a todo
                    String itemName = inputString.replace("todo ", "");

                    if (itemName.isEmpty()) {
                        // no item name
                        throw new DukeException(NAME_EMPTY);
                    }


                    TodoTask todoTask = new TodoTask(itemName);

                    listContainer.addToList(todoTask);
                } else if (inputString.startsWith("deadline ")) {
                    // TODO: potential error handling here
                    // format of entry: "deadline return book /by Sunday"
                    String itemName = inputString.replace("deadline ", "").split("/by ")[0];

                    if (itemName.isEmpty()) {
                        // no item name
                        throw new DukeException(NAME_EMPTY);
                    }

                    String[] inputArgs = inputString.replace("deadline ", "").split("/by ");
                    if (inputArgs.length < 2) {
                        // missing deadline
                        throw new DukeException(DEADLINE_EMPTY);
                    }
                    String deadline = inputArgs[1];

                    if (deadline.isEmpty()) {
                        // no item name
                        throw new DukeException(DEADLINE_EMPTY);
                    }
                    DeadlineTask deadlineTask = new DeadlineTask(itemName, deadline);

                    listContainer.addToList(deadlineTask);
                } else if (inputString.startsWith("event ")) {
                    String inputArgs = inputString.replace("event ", "");

                    // sample format: event project meeting /from Mon 2pm /to 4pm
                    // get the name
                    String itemName = inputArgs.split("/from ")[0];

                    if (itemName.isEmpty()) {
                        // no item name
                        throw new DukeException(NAME_EMPTY);
                    }

                    // get the 'from...to'
                    // @see https://stackoverflow.com/questions/4662215/how-to-extract-a-substring-using-regex
                    Pattern patternFrom = Pattern.compile("(/from )(.*?)( /to)");
                    Matcher matcherFrom = patternFrom.matcher(inputArgs);

                    String from = "";
                    if (matcherFrom.find()) {
                        // yes, formatted correctly
                        from = matcherFrom.group(2);
                    } else {
//                        System.out.println("ERROR: no pattern found");
                        throw new DukeException(TIME_FORMAT_ERROR);
                    }

                    // get the to...
                    String to = inputArgs.split("/to ")[1];

                    if (to.isEmpty()) {
                        throw new DukeException(TO_EMPTY);
                    }

                    EventTask eventTask = new EventTask(itemName, from, to);

                    listContainer.addToList(eventTask);
                } else {
                    throw new DukeException(UNKNOWN_COMMAND);
                }
                System.out.println("Now you have " + listContainer.getSize() + " tasks!");
//                listContainer.addToList();

            } catch (DukeException e) {
                System.out.println(e.printError());
            } catch (Exception e) {
                System.out.println("some other exception " + e.getMessage());
            }

            System.out.println(SEPARATOR_LINE);
        }

        System.out.println(SEPARATOR_LINE);
        String exitMsg = "Bye! Hope to see you again soon.";
        System.out.println(exitMsg);
        System.out.println(SEPARATOR_LINE);


    }
}
