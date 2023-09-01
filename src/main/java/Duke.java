import java.io.IOException;
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
            String todoName = s.substring(5);

            if (todoName.length() < 1) {
                throw new InvalidTaskException("Description", "todo");
            }

            Todo todo = new Todo(todoName);
            System.out.println("New task added: " + todo);
            addTaskSuccess(todo);
        } else if (s.startsWith("deadline ")) {
            int dlIndex = s.indexOf("/by ");

            if (dlIndex == -1 ) {
                throw new InvalidTaskException("Deadline", "task with deadline");
            }

            if (dlIndex <= 9) {
                throw new InvalidTaskException("Description", "task with deadline");
            }

            String dlName = s.substring(9, dlIndex - 1);
            String dlTime = s.substring(dlIndex + 4);

            if (dlTime.length() < 1) {
                throw new InvalidTaskException("Deadline", "task with deadline");
            }

            Deadline dl = new Deadline(dlName, dlTime);
            System.out.println("New task with deadline added: " + dl);
            addTaskSuccess(dl);
        } else if (s.startsWith("event ")) {
            int fromIndex = s.indexOf("/from ");
            int toIndex = s.indexOf("/to ");

            if (fromIndex == -1 || toIndex == -1 || toIndex <= fromIndex + 6) {
                throw new InvalidTaskException("Timing", "event");
            }

            if (fromIndex <= 6) {
                throw new InvalidTaskException("Description", "event");
            }

            String eName = s.substring(6, fromIndex - 1);
            String eFrom = s.substring(fromIndex + 6, toIndex - 1);
            String eTo = s.substring(toIndex + 4);

            if (eTo.length() < 1) {
                throw new InvalidTaskException("Timing", "event");
            }

            Event e = new Event(eName, eFrom, eTo);
            System.out.println("New task added: " + e);
            addTaskSuccess(e);
        } else if (s.equals("todo") || s.equals("event")) {
            throw new InvalidTaskException("Description", s);
        } else if (s.equals("deadline")) {
            throw new InvalidTaskException("Description", "task with " + s);
        } else {
            throw new InvalidCommandException(s);
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
