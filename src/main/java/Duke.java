import Tasks.DeadlineTask;
import Tasks.EventTask;
import Tasks.Task;
import Tasks.TodoTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private static String FILE_PARSE_ERROR = "\uD83D\uDE21 Error parsing save file!";


    private static Path SAVE_FILE_LOCATION = Paths.get("./src/data/duke.txt");

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
        try {
            // Load in existing file if it exists
            if (Files.exists(SAVE_FILE_LOCATION)) {
                // it exists, so let's read it
                try {
                    Scanner sc = new Scanner(SAVE_FILE_LOCATION);
                    while (sc.hasNextLine()) {
                        String inputLine = sc.nextLine();

                        Task task = parseTask(inputLine);

                        listContainer.addToList(task, true);



                    }
                } catch (FileNotFoundException e) {
                    throw new DukeException(FILE_PARSE_ERROR);
                } catch (IOException e) {
                    throw new DukeException(FILE_PARSE_ERROR);
                }
            } else {
                System.out.println("No save file!");
                System.out.println(SAVE_FILE_LOCATION);
            }


            String inputString = "";

            Scanner keyboard = new Scanner(System.in);

            loop:
            while (true) {

                inputString = keyboard.nextLine();


                String inputCommandString = (inputString.split(" ")[0].toUpperCase());

                if (!Commands.contains(inputCommandString)) {
                    throw new DukeException(UNKNOWN_COMMAND);
                }

                Commands inputCommand = Commands.valueOf(inputCommandString);

                System.out.println(SEPARATOR_LINE);
                switch (inputCommand) {
                    case BYE:
                        break loop;
                    case LIST: {
                        System.out.println(listContainer.toString());
                        break;
                    }
                    case MARK: {
                        String userInputIndex = inputString.split(" ")[1];
                        // check if is number
                        int index = Integer.parseInt(inputString.split(" ")[1]);
                        listContainer.markAsDone(index);
                        break;
                    }
                    case UNMARK: {
                        int index = Integer.parseInt(inputString.split(" ")[1]);
                        listContainer.markAsUnDone(index);
                        break;
                    }
                    case DELETE: {
                        int index = Integer.parseInt(inputString.split(" ")[1]);
                        listContainer.removeFromList(index);
                        break;
                    }
                    case TODO: {
                        // add a todo
                        String itemName = inputString.replace("todo ", "");

                        if (itemName.isEmpty()) {
                            // no item name
                            throw new DukeException(NAME_EMPTY);
                        }


                        TodoTask todoTask = new TodoTask(itemName);

                        listContainer.addToList(todoTask, false);
                        break;
                    }
                    case DEADLINE: {
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

                        listContainer.addToList(deadlineTask, false);
                        break;
                    }
                    case EVENT: {
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

                        listContainer.addToList(eventTask, false);
                        break;
                    }

                    default:
                        throw new DukeException(UNKNOWN_COMMAND);
                }
                System.out.println(SEPARATOR_LINE);

//            System.out.println(inputString);


            }

        } catch (DukeException e) {
            System.out.println(e.printError());
        } catch (Exception e) {
            System.out.println("some other exception " + e.getMessage());
        }

        String exitMsg = "Bye! Hope to see you again soon.";
        System.out.println(exitMsg);
        System.out.println(SEPARATOR_LINE);


    }

    private static Task parseTask(String inputLine) throws IOException {
        String[] split = inputLine.split(" \\| ");
        String taskType = split[0];
        String isDoneStr = split[1];
        String taskDescription = split[2];
        Boolean isDone = false;
        if (Objects.equals(isDoneStr, "1") || Objects.equals(isDoneStr, "0")) {
            isDone = isDoneStr.equals("1");
        }

        Task task;
        switch (taskType) {
            case "T": {
                task = new TodoTask(taskDescription);
                break;
            }
            case "D": {
                // get the deadline, which is 4th element
                String deadline = split[3];
                task = new DeadlineTask(taskDescription, deadline);
                break;
            }
            case "E": {
                // get the start date, which is 4th element
                // get the end date, which is 5th element
                String start = split[3];
                String end = split[4];
                task = new EventTask(taskDescription, start, end);
                break;
            }
            default: throw new IOException();
        }

        if (isDone) {
            task.setDone();
        }
        return task;
    }
}
