import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public class Parser {
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
                Task task = TaskList.get(taskNum - 1);
                task.taskIsDone();
                Storage.write();
            } else if (command.equals("unmark")) {
                int taskNum = sc.nextInt();
                Task task = TaskList.get(taskNum - 1);
                task.taskNotDone();

                Storage.write();
            } else if (command.equals("delete")) {
                int index = sc.nextInt();
                Duke.delete(index - 1);
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
                    LocalDate d = LocalDate.parse(date);
                    Task taskToAdd = new Deadline(taskName, d);
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

                    LocalDate from = LocalDate.parse(fromDate);
                    LocalDate to = LocalDate.parse(toDate);
                    Task taskToAdd = new Event(taskName, from, to);
                    Duke.add(taskToAdd);
                } else {
                    throw new EmptyDescriptionException();
                }


            } else if (command.equals("clear")){
                Duke.clear();
            } else {
                throw new NoSuchCommandException();
            }
        }
    }

    public static void parse() {
        try {
            Parser.execute();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
