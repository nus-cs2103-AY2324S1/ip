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

        while (!Objects.equals(inputString, "bye")) {
            inputString = keyboard.nextLine();

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
            } else {
                // add to the list

                // check the type - todo, deadline, or event
                if (inputString.startsWith("todo ")) {
                    // add a todo
                    String itemName = inputString.replace("todo ", "");
                    TodoTask todoTask = new TodoTask(itemName);

                    listContainer.addToList(todoTask);
                }
                if (inputString.startsWith("deadline ")) {
                    // TODO: potential error handling here
                    // format of entry: "deadline return book /by Sunday"
                    String itemName = inputString.replace("deadline ", "").split("/by ")[0];
                    String deadline = inputString.replace("deadline ", "").split("/by ")[1];
                    DeadlineTask deadlineTask = new DeadlineTask(itemName, deadline);

                    listContainer.addToList(deadlineTask);
                }

                if (inputString.startsWith("event ")) {
                    String inputArgs = inputString.replace("event ", "");

                    // sample format: event project meeting /from Mon 2pm /to 4pm
                    // get the name
                    String itemName = inputArgs.split("/from ")[0];

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
                    }

                    // get the to...
                    String to = inputArgs.split("/to ")[1];

                    EventTask eventTask = new EventTask(itemName, from, to);

                    listContainer.addToList(eventTask);
                }
                System.out.println("Now you have " + listContainer.getSize() + " tasks!");
//                listContainer.addToList();
            }
            System.out.println(SEPARATOR_LINE);
        }


        String exitMsg = "Bye! Hope to see you again soon.";
        System.out.println(exitMsg);
        System.out.println(SEPARATOR_LINE);


    }
}
