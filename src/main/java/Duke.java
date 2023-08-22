import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static boolean exited = false;
    static ArrayList<Task> tasks = new ArrayList<>();

    public static void exit() {
        String goodbye = "Bye. Hope to see you again soon!";
        System.out.println(goodbye);
        System.exit(0);
    }
//    public static void echo(String input) {
//        System.out.println(input);
//    }

    public static void add(Task t) {
        String taskName = t.getName();
        tasks.add(t);
        System.out.println("Got it. I've added this task:\n "
                + t.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.");


    }

    public static void list() {
        int listSize = tasks.size();
        for (int i = 0; i < listSize ; i++) {
            int num = i + 1;
            Task taskToList = tasks.get(i);
            System.out.println(num + ". " + taskToList.toString());
        }
    }

    public static void delete(int num) {
        Task t = tasks.get(num - 1);
        tasks.remove(num - 1);
        System.out.println("Noted. I've removed this task: \n" +
                t.toString() + "\n" +
                "Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void execute() throws DukeException {
        Scanner sc = new Scanner(System.in);
        while (true) {

            String command = sc.next();


            if (command.equals("bye")) {
                Duke.exit();
            } else if (command.equals("list")) {
                Duke.list();
            } else if (command.equals("mark")) {
                int taskNum = sc.nextInt();
                Task task = tasks.get(taskNum - 1);
                task.taskIsDone();
            } else if (command.equals("unmark")) {
                int taskNum = sc.nextInt();
                Task task = tasks.get(taskNum - 1);

            } else if (command.equals("delete")) {
                int index = sc.nextInt();
                delete(index);
            } else if (command.equals("todo")) {

                    String restOfString = sc.nextLine();
                if (restOfString.length() != 0) {
                    String taskName = restOfString;
                    Task taskToAdd = new Todo(taskName);
                    Duke.add(taskToAdd);

                } else {
                    throw new EmptyDescriptionException();
                }

            } else if (command.equals("deadline")) {
                String restOfString = sc.nextLine();

                if (restOfString.length() != 0) {
                    int slashIndex = restOfString.indexOf("/by");
                    if (slashIndex == -1) {
                        throw new MissingByDateException();
                    }
                    String taskName = restOfString.substring(0, slashIndex - 1);
                    String date = restOfString.substring(slashIndex + 4);
                    Task taskToAdd = new Deadline(taskName, date);
                    Duke.add(taskToAdd);
                } else {
                    throw new EmptyDescriptionException();
                }

            } else if (command.equals("event")) {

                String restOfString = sc.nextLine();
                if (restOfString.length() != 0) {
                    int fromIndex = restOfString.indexOf("/from");
                    int toIndex = restOfString.indexOf("/to");
                    if (fromIndex == -1) {
                        throw new MissingFromDateException();
                    } else if (toIndex == -1) {
                        throw new MissingToDateException();
                    }

                    String taskName = restOfString.substring(0, fromIndex - 1);
                    String fromDate = restOfString.substring(fromIndex + 6, toIndex - 1);

                    String toDate = restOfString.substring(toIndex + 4);
                    Task taskToAdd = new Event(taskName, fromDate, toDate);
                    Duke.add(taskToAdd);
                } else {
                    throw new EmptyDescriptionException();
                }


            } else {
                throw new NoSuchCommandException();
            }
        }
    }
    public static void main(String[] args) {

        tasks.clear();

        String welcome = "Hello! I'm Eddie\n" +
                "What can I do for you?";

        System.out.println(welcome);

        try {
            Duke.execute();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }




    }
}
