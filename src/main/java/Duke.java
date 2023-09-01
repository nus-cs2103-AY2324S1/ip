import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    static final String FILEPATH = "./data/duke.txt";
    static Storage storage = new Storage(FILEPATH);
    static boolean allowNext = true;
    static boolean willAddStr = true;
    static ArrayList<Task> storedTasks = new ArrayList<>();

    public static void handleExit(String s) {
        String exit = "Bye. See you soon! :)\n";

        if (s.equals("bye")) {
            System.out.println(exit);
            allowNext = false;
        }
    }

    public static void handleList(String s) {
        if (s.equals("list")) {
            willAddStr = false;
            int len = storedTasks.size();
            if (len > 0) {
                System.out.println("Your added tasks:");

                for (int i = 1; i < len + 1; i++) {
                    System.out.println(i + ". " + storedTasks.get(i - 1));
                }
            } else {
                System.out.println("No tasks found!");
            }
        }
    }

    public static void handleMarking(String s) throws Exception {
        if (s.startsWith("mark ")) {
            int index;
            int len = storedTasks.size();
            String indexStr = s.substring(5);

            try {
                index = Integer.parseInt(indexStr);
            } catch (NumberFormatException nfe) {
                return;
            }

            if (index > 0 && index < len + 1) {
                willAddStr = false;
                storedTasks.get(index - 1).markAsDone();
                System.out.println("The following task has been marked as done!");
                System.out.println(storedTasks.get(index - 1));
            } else {
                throw new InvalidTaskNumberException(indexStr);
            }

        } else if (s.startsWith("unmark ")) {
            int index;
            int len = storedTasks.size();
            String indexStr = s.substring(7);

            try {
                index = Integer.parseInt(indexStr);
            } catch (NumberFormatException nfe) {
                return;
            }

            if (index > 0 && index < len + 1) {
                willAddStr = false;
                storedTasks.get(index - 1).markAsUndone();
                System.out.println("The following task has been marked as undone!");
                System.out.println(storedTasks.get(index - 1));
            } else {
                throw new InvalidTaskNumberException(indexStr);
            }
        }
    }

    public static void handleTaskAdd(String s) throws Exception {
        if (s.startsWith("todo ")) {
            addTodo(s);
        } else if (s.startsWith("deadline ")) {
            addDeadline(s);
        } else if (s.startsWith("event ")) {
            addEvent(s);
        } else if (s.equals("todo") || s.equals("event")) {
            throw new InvalidTaskException("Description", s);
        } else if (s.equals("deadline")) {
            throw new InvalidTaskException("Description", "task with " + s);
        } else {
            throw new InvalidCommandException(s);
        }
    }

    public static void addTodo(String s) throws InvalidTaskException {
        String todoName = s.substring(5);

        if (todoName.length() < 1) {
            throw new InvalidTaskException("Description", "todo");
        }

        Todo todo = new Todo(todoName);
        System.out.println("New task added: " + todo);
        addTaskSuccess(todo);
    }

    public static void addDeadline(String s) throws IndexOutOfBoundsException {
        String[] strArr = s.split(" /by ");

        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            String dlName = strArr[0].substring(9);
            LocalDateTime dlTime = LocalDateTime.parse(strArr[1], dateTimeFormatter);

            Deadline dl = new Deadline(dlName, dlTime);
            System.out.println("New task with deadline added: " + dl);
            addTaskSuccess(dl);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Enter a description or due date with /by");
        } catch (DateTimeParseException e) {
            System.out.println("Error: Enter valid date and time in the format DD/MM/YYYY HH:MM");
        }
    }

    public static void addEvent(String s) throws IndexOutOfBoundsException, DateTimeParseException {
        String[] strArr = s.split(" /from ");

        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            String eName = strArr[0].substring(6);

            String[] dueDateArr = strArr[1].split(" /to ");
            LocalDateTime eFrom = LocalDateTime.parse(dueDateArr[0], dateTimeFormatter);
            LocalDateTime eTo = LocalDateTime.parse(dueDateArr[1], dateTimeFormatter);

            Event e = new Event(eName, eFrom, eTo);
            System.out.println("New task added: " + e);
            addTaskSuccess(e);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Enter description, start date with /from or end date with /to");
        } catch (DateTimeParseException e) {
            System.out.println("Error: Enter valid date and time in the format DD/MM/YYYY HH:MM");
        }
    }

    public static void addTaskSuccess(Task t) {
        storedTasks.add(t);

        int len = storedTasks.size();
        System.out.println("You now have " + len + " task(s) in your list.");
    }

    public static void handleDelete(String s) throws Exception {
        if (s.startsWith("delete ")) {
            int index;
            int len = storedTasks.size();
            String indexStr = s.substring(7);

            try {
                index = Integer.parseInt(indexStr);
            } catch (NumberFormatException nfe) {
                return;
            }

            if (index > 0 && index < len + 1) {
                willAddStr = false;
                System.out.println("Task successfully deleted: " + storedTasks.get(index - 1));
                deleteTaskSuccess(index);
            } else {
                throw new InvalidTaskNumberException(indexStr);
            }
        }
    }

    public static void deleteTaskSuccess(int index) {
        storedTasks.remove(index - 1);

        int len = storedTasks.size();
        System.out.println("You now have " + len + " task(s) in your list.");
    }


    public static void main(String[] args) throws IOException {
        String greet = "Hi! I'm Uke\n" + "What can I do for you?\n";
        System.out.println(greet);

        storage.createDataFile();
        storedTasks = storage.loadTaskList();

        Scanner input = new Scanner(System.in);

        while (allowNext) {
            String str = input.nextLine();
            try {
                handleExit(str);
                handleList(str);
                handleMarking(str);
                handleDelete(str);
                storage.update(storedTasks);

                if (allowNext && willAddStr) {
                    handleTaskAdd(str);
                    storage.update(storedTasks);
                } else if (!willAddStr) {
                    willAddStr = true;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
