import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DogeBot {
    private static ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        String logo = "    ___\n"
            + " __/_  `.  .-\"\"\"-."         + "           |                      |             |   \n"
            + " \\_,` | \\-'  /   )`-')"      + "    _` |   _ \\    _` |   _ \\  __ \\    _ \\   __| \n"
            + "  \"\") `\"`    \\  ((`\"`"    + "    (   |  (   |  (   |   __/  |   |  (   |  |   \n"
            + " ___Y  ,    .'7 /|"            + "      \\__,_| \\___/  \\__, | \\___| _.__/  \\___/  \\__| \n"
            + "(_,___/...-` (_/_/"            + "                    |___/";

        System.out.println(logo + "\nHi ! I'm DogeBot \nHow may I help you today ?\n");
        Scanner sc = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            try {
                switch (sc.next().toLowerCase()) {
                case "bye":
                    loop = false;
                    break;
                case "list":
                    list();
                    break;
                case "mark":
                    mark(sc.nextInt() - 1);
                    break;
                case "unmark":
                    unmark(sc.nextInt() - 1);
                    break;
                case "todo":
                    todo(sc.nextLine()); // sc.nextLine() to get the remaining words
                    break;
                case "deadline":
                    deadline(sc.nextLine());
                    break;
                case "event":
                    event(sc.nextLine());
                    break;
                case "delete":
                    delete(sc.nextInt() - 1);
                    break;
                default:
                    System.out.println("Wuff, I'm not sure what that means :(");
                }
            } catch (InputMismatchException e) {
                sc.nextLine(); // absorb remaining words so 'default' block doesn't act up
                System.out.println("Oops ! Integers only please :c");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Oh no :( I think that number is too big~");
            } catch (DogeBotException e) {
                System.out.println(e.getMessage());
            }

        }

        System.out.println("Bye~ See you again");
        sc.close();
    }

    public static void list() throws DogeBotException {
        if (tasks.size() == 0) throw new DogeBotException("Oops ! Your list is empty ! Try adding some tasks first c:");

        System.out.println("Stuff to do:");
        int i = 1;
        for (Task task : tasks) {
            if (task == null) break;
            System.out.println(i++ + "." + task.toString());
        }
    }

    public static void mark(int index) throws DogeBotException {
        if (tasks.size() == 0) throw new DogeBotException("Oops ! Try adding some tasks first c:");

        tasks.get(index).markTask(true);
        System.out.println("Good job on completing a task ! You deserve a cookie C:");
        System.out.println("\t" + tasks.get(index).toString());
    }

    public static void unmark (int index) throws DogeBotException {
        if (tasks.size() == 0) throw new DogeBotException("Oops ! Try adding some tasks first c:");

        tasks.get(index).markTask(false);
        System.out.println("Oh nyo, did someone make a mistake ?");
        System.out.println("\t" + tasks.get(index).toString());
    }

    public static void updateTasksCounter() {
        System.out.println("You now have " + tasks.size() + " task(s) in your list");
    }

    public static void todo(String words) throws DogeBotException {
        if (words.isBlank()) throw new DogeBotException("Oops ! The description of a todo cannot be empty :(");

        tasks.add(new ToDos(words));
        // 'tasks.get(tasks.size() - 1)' gets the recent most added task
        System.out.println("\t" + tasks.get(tasks.size() - 1).toString());
        updateTasksCounter();
    }

    public static void deadline(String words) throws DogeBotException {
        if (words.isBlank()) throw new DogeBotException("Oops ! The description of a deadline cannot be empty :(");

        int split = words.indexOf("/");
        // substring w/o the spaces
        String taskDescription = words.substring(0, split - 1);
        String taskDeadline = words.substring(split + 4, words.length());

        tasks.add(new Deadline(taskDescription, taskDeadline));
        // 'tasks.get(tasks.size() - 1)' gets the recent most added task
        System.out.println("\t" + tasks.get(tasks.size() - 1).toString());
        updateTasksCounter();
    }

    public static void event(String words) throws DogeBotException {
        if (words.isBlank()) throw new DogeBotException("Oops ! The description of an event cannot be empty :(");

        // substring w/o the spaces
        int startSplit = words.indexOf("/");
        String taskDesription = words.substring(0, startSplit - 1);
        int endSplit = words.indexOf("/", startSplit + 1); // find "/" after startSplit index
        String start = words.substring(startSplit + 6, endSplit - 1);
        String end = words.substring(endSplit + 4, words.length());

        tasks.add(new Event(taskDesription, start, end));
        // 'tasks.get(tasks.size() - 1)' gets the recent most added task
        System.out.println("\t" + tasks.get(tasks.size() - 1).toString());
        updateTasksCounter();
    }

    public static void delete(int index) throws DogeBotException {
        if (tasks.size() == 0) throw new DogeBotException("Oops ! There's no tasks in your list to delete :O");

        Task curr = tasks.get(index);
        System.out.println("Got it~ This task has been removed:");
        System.out.println("\t" + curr.toString());
        tasks.remove(index);
        updateTasksCounter();
    }
}
