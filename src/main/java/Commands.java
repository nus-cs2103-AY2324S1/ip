import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;

/**
 * The Command Class encapsulates the functions to check the input string with the available commands.
 */
public class Commands {
    /**
     * The Enum Class encapsulates all the available commands.
     */
    enum CommandType {
        MARK,
        UNMARK,
        LIST,
        EVENT,
        DEADLINE,
        TODO,
        BYE,
        DELETE,

    }


    /**
     * This method Run the Scanner to begin taking inputs from user, and check to see which commands to run.
     *
     */
    public static void Run(File file) {
        Scanner sc = new Scanner(System.in);
        ItemList items = new ItemList(file);
        try {
            items.loadAll();
        } catch (FileNotFoundException e) {
            System.out.println(Greeting.linebreak);
            System.out.println("List not found, empty list will be created");
            System.out.println(Greeting.linebreak);
        }
        boolean isRunning = true;
        do {
            if(!sc.hasNextLine()) {
                break;
            }
            try {
                String echo = sc.nextLine();
                String command = echo.split(" ")[0];
                CommandType given = CommandType.valueOf(command.toUpperCase());

                switch(given) {
                    case BYE:
                        isRunning = false;
                        break;
                    case LIST:
                        items.showitems();
                        break;
                    case MARK:
                        Pattern markpattern = Pattern.compile("mark (\\d+).*");
                        Matcher matcher = markpattern.matcher(echo);
                        if(matcher.matches()) {
                            String digitString = matcher.group(1);
                            int number = Integer.parseInt(digitString);
                            items.markDone(number);
                        }
                        break;
                    case UNMARK:
                        Pattern unmarkpattern = Pattern.compile("unmark (\\d+).*");
                        matcher = unmarkpattern.matcher(echo);
                        if(matcher.matches()) {
                            String digitString = matcher.group(1);
                            int number = Integer.parseInt(digitString);
                            items.markUndone(number);
                        }
                        break;
                    case DELETE:
                        Pattern deletepattern = Pattern.compile("delete (\\d+).*");
                        matcher = deletepattern.matcher(echo);
                        if(matcher.matches()) {
                            String digitString = matcher.group(1);
                            int number = Integer.parseInt(digitString);
                            items.delete(number);
                        }
                        break;
                    case DEADLINE:
                        Pattern deadlinepattern = Pattern.compile( "deadline (.*?)/by (.*)");
                        matcher = deadlinepattern.matcher(echo);
                        if(matcher.matches()) {
                            String task = matcher.group(1);
                            String by = matcher.group(2);
                            items.addDeadline(task, by);
                        } else {
                            throw new DeadlineException();
                        }
                        break;
                    case TODO:
                        Pattern todopattern = Pattern.compile( "todo (.*)");
                        matcher = todopattern.matcher(echo);
                        if(matcher.matches()) {
                            String task = matcher.group(1);
                            items.addTodo(task);
                        } else {
                            throw new ToDoException();
                        }
                        break;
                    case EVENT:
                        Pattern eventpattern = Pattern.compile( "event (.*?)/from (.*?) /to (.*)");
                        matcher = eventpattern.matcher(echo);
                        if(matcher.matches()) {
                            String task = matcher.group(1);
                            String from = matcher.group(2);
                            String to = matcher.group(3);
                            items.addEvent(task, from, to);
                        } else {
                            throw new EventException();
                        }
                        break;
                    default:
                        throw new DukeException();

                }
            } catch (DukeException e) {
                System.out.println(Greeting.linebreak);
                System.out.println(e.toString());
                System.out.println(Greeting.linebreak);
            } catch (IllegalArgumentException e) {
                System.out.println(Greeting.linebreak);
                System.out.println("Invalid input");
                System.out.println(Greeting.linebreak);
            }


        } while (isRunning);
        Greeting.bye();
    }


}
