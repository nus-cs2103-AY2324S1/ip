
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;




public class Duke {
    static ArrayList<Task> tasks = new ArrayList<>();

    public static void exit() {
        Ui.exit();
        System.exit(0);
    }
//    public static void echo(String input) {
//        System.out.println(input);
//    }



    public static void add(Task t){
        String taskName = t.getName();
        TaskList.add(t);
        Ui.addTask(t.toString(), TaskList.size());

        Storage.write();


    }

    public static void clear() {
        TaskList.clear();
        Ui.clear();

        Storage.write();
    }



    public static void list() {
        int listSize = TaskList.size();
        for (int i = 0; i < listSize ; i++) {
            int num = i + 1;
            Task taskToList = TaskList.get(i);
            Ui.listTask(num, taskToList.toString());
        }
    }

    public static void delete(int num) {
        Task t = TaskList.get(num - 1);
        TaskList.delete(num - 1);

        Ui.removeTask(t.toString(), TaskList.size());
        Storage.write();

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
    public static void main(String[] args) {

        TaskList.clear();
        Storage.readFile();

        Ui.welcome();

        try {
            Duke.execute();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }




    }
}
