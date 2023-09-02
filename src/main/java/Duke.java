import java.time.DateTimeException;
import java.util.Scanner;
import java.io.IOException;


public class Duke {

    private static TaskList tasks;
    private static Ui ui = new Ui();

    public static void main(String[] args) {
        Storage storage;
        boolean isRunning = true;
        tasks = new TaskList();
        storage = new Storage("data/duke.txt", tasks);
        Duke buddy = new Duke();

       try {
           storage.saveTasks();
       }
       catch (Exception e) {
            e.printStackTrace();
        }


        Scanner scanner = new Scanner(System.in);
        ui.printWelcomeMessage();
        int count = 0;

        while (isRunning) {
            String input = scanner.nextLine();


            if (input.equals("bye")) {
                ui.printGoodByeMessage();
                break;
            }

            //marking task
            else if (input.startsWith("mark ")) {
                try {
                    int taskIndex = Integer.parseInt(input.substring(5));
                    tasks.markTaskAsDone(taskIndex - 1);
                    ui.printMarkTasksAsDone(taskIndex, tasks);
                    storage.writeTasksToFile(tasks);
                }
                catch (IOException i) {
                    System.out.println(i);
                }

            }

            //unmarking task
            else if (input.startsWith("unmark ")) {
                try {
                    int taskIndex = Integer.parseInt(input.substring(7));
                    tasks.markTaskAsNotDone(taskIndex - 1);
                    ui.printMarkTasksAsNotDone(taskIndex, tasks);
                    storage.writeTasksToFile(tasks);
                }
                catch (IOException i) {
                    System.out.println(i);
                }

            }

            //displaying list
            else if (input.strip().equals("list")) {
                try {
                    ui.printListMessage(tasks);
                }
                catch (DukeException e) {
                    e.printMessage();
                }
            }

            // deleting task from list
            else if (input.startsWith("delete ")) {
                try {
                    int pos = Integer.parseInt(input.substring(7).trim());
                    if (pos > tasks.getSize() || pos == 0) {
                        throw new DukeException("\tThis number is out of bounds! ");
                    }
                    Task element = tasks.getTask(pos - 1);
                    tasks.deleteTask(pos - 1);
                    ui.printDeleteTasks(pos, tasks, element);
                    storage.writeTasksToFile(tasks);
                }
                catch (DukeException e) {
                    e.printMessage();
                }
                catch (IOException i) {
                    System.out.println(i);
                }
            }

            // adding task to list
            else {
                try {
                    addTaskToList(input);
                    storage.writeTasksToFile(tasks);
                }
                catch (DukeException e){
                    e.printMessage();
                }
                catch (IOException i) {
                    System.out.println(i);
                }
            }
        }

    }

    public static void addTaskToList(String input) throws DukeException {
        if (input.startsWith("todo")) {
            if (input.trim().length() <= 4 ) {
                throw new DukeException("\t Sorry! The description of a todo cannot be empty :(");
            }
            Task task = new ToDo(input.substring(5));
            tasks.addTask(task);
            ui.printAddTaskToList(tasks, task);
        }

        else if (input.startsWith("deadline")) {
            try {
                if (input.trim().length() <= 8) {
                    throw new DukeException("\t Sorry! The description of a deadline cannot be empty :(");
                }
                if (!input.contains("/by")) {
                    throw new DukeException("\t Hey bud! Please include when the deadline is! " +
                            "\n\t For example you can type: deadline read /by 2023-09-01 1700");
                }
                int index = input.lastIndexOf("/by");
                Task task = new Deadline(input.substring(9, index - 1), input.substring(index + 4));
                tasks.addTask(task);
                ui.printAddTaskToList(tasks, task);

            }
            catch(DateTimeException d) {
                System.out.println("Please put a valid deadline in YYYY-MM-DD HHMM form." +
                        "\n\tFor example, 2003-19-08 1855");
            }

        }

        else if (input.startsWith("event")) {
            try {
                if (input.trim().length() <= 5) {
                    throw new DukeException("\t Sorry! The description of an event cannot be empty :(");
                }
                if (!input.contains("/from")) {
                    throw new DukeException("\t Hey bud! Please include when the event is!" +
                            "\n\t For example you can type: event hangout /from 2023-09-01 1700 /to 2023-09-01 2000");
                }
                if (!input.contains("/to")) {
                    throw new DukeException("\t Hey bud! Please include when the end date of the event is!" +
                            "\n\t For example you can type: event hangout /from 2023-09-01 1700 /to 2023-09-01 2000");
                }
                int indexFrom = input.lastIndexOf("/from");
                int indexTo = input.lastIndexOf("/to");
                Task task = new Event(input.substring(6, indexFrom - 1),
                        input.substring(indexFrom + 6, indexTo - 1), input.substring(indexTo + 4));
                tasks.addTask(task);
                ui.printAddTaskToList(tasks, task);

            }
            catch(DateTimeException d) {
                System.out.println("Please put valid start and end dates in YYYY-MM-DD HHMM form." +
                        "For example, 2003-19-08 1855");
            }
        }

        else {
            throw new DukeException("\tHey bud! Sorry I don't quite know what you mean :-(");
        }

    }

}