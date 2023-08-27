
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

    public static void readFile() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        try {
            File eddieTaskList = new File("EddieTaskList.txt");
            Scanner sc = new Scanner(eddieTaskList);
            while (sc.hasNextLine()) {
                String t = sc.nextLine();
                String[] task = new String[5];
                task = t.split(" , ");

                if(task[0].equals("T")) {
                    //System.out.println(task[2]);
                    Todo todo = new Todo(task[2]);
                    if (task[1].equals("x")) {
                        todo.taskIsDone();
                    }

                    tasks.add(todo);
                } if (task[0].equals("D")) {
                    Deadline deadline = new Deadline(task[2], LocalDate.parse(task[3], formatter));
//                    System.out.println(task[1]);
                    if (task[1].equals("x")){
                        deadline.taskIsDone();
                    }
                    tasks.add(deadline);
                } if (task[0].equals("E")) {
                    Event event = new Event(task[2], LocalDate.parse(task[3], formatter), LocalDate.parse(task[4], formatter));

                    if (task[1].equals("x")) {
                        event.taskIsDone();
                    }

                    tasks.add(event);
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public static void add(Task t){
        String taskName = t.getName();
        tasks.add(t);
        System.out.println("Got it. I've added this task:\n "
                + t.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.");
        try {
            writeToFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

    public static void clear() {
        tasks.clear();
        System.out.println("List Cleared!");

        try {
            writeToFile();
        } catch (IOException e) {
            System.out.println("IOException when clearing tasks");
        }
    }

    public static void writeToFile () throws IOException {
        FileWriter eddieTaskList = new FileWriter("EddieTaskList.txt");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);

            if (t.getType() == "T") {
                eddieTaskList.write("T , " + t.getStatus() + " , " + t.getName() + " , \n");
            } else if (t.getType() == "D") {
                eddieTaskList.write("D , " + t.getStatus() + " , " + t.getName() + " , " + t.getDeadline() + " , \n");
            } else if (t.getType() == "E") {
                eddieTaskList.write("E , " + t.getStatus() + " , " + t.getName() + " , " + t.getStartDate() + " , "
                + t.getEndDate() + " , \n");
            }
        }
        eddieTaskList.close();
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

        try {
            writeToFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
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
                try {
                    writeToFile();
                } catch (IOException e) {
                    System.out.println("Cannot write to file (mark)");
                }
            } else if (command.equals("unmark")) {
                int taskNum = sc.nextInt();
                Task task = tasks.get(taskNum - 1);
                task.taskNotDone();

                try {
                    writeToFile();
                } catch (IOException e) {
                    System.out.println("Cannot write to file (unmark)");
                }
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

        tasks.clear();
        readFile();


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
