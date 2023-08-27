import java.util.Scanner;
import static java.lang.Integer.parseInt;
import java.time.LocalDate;

public class TehO {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public TehO(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.loadTasks());
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.generateHelloMessage();
        storage.loadTasks();
        while (true) {
            String userCommand = sc.nextLine();
            if (userCommand.equals("bye")) {
                ui.generateGoodbyeMessage();
                storage.saveTasks(taskList);
                break;
            } else if (userCommand.equals("list")) {
                ui.generateList(taskList);
            } else if (userCommand.startsWith("mark")) {
                markTask(userCommand);
            } else if (userCommand.startsWith("unmark")) {
                unmarkTask(userCommand);
            } else if (userCommand.startsWith("todo")) {
                addToDo(userCommand);
            } else if (userCommand.startsWith("deadline")) {
                addDeadline(userCommand);
            } else if (userCommand.startsWith("event")) {
                addEvent(userCommand);
            } else if (userCommand.startsWith("delete")) {
                delete(userCommand);
            } else {
                try {
                    throw new InvalidCommandException();
                } catch (InvalidCommandException e) {
                    System.out.println(e.toString());
                }
            }
        }
        sc.close();
    }


    public static void main(String[] args) {
        new TehO("/Users/loomeilinzann/ip/text-ui-test/data/teho.txt").run();
    }


    public void markTask(String userCommand) {
        //note that split returns a String[]
        //parseInt returns the integer value which is represented by the argument
        int taskNumber = parseInt(userCommand.split(" ")[1]) - 1; //counting from 0
        Task task = this.taskList.getTask(taskNumber);
        task.markAsDone(taskNumber);
        ui.generateMarkTaskMessage(task);
    }
    public void unmarkTask(String userCommand) {
        int taskNumber = parseInt(userCommand.split(" ")[1]) - 1; //counting from 0
        Task task = this.taskList.getTask(taskNumber);
        task.markAsNotDone(taskNumber);
        ui.generateUnmarkTaskMessage(task);
    }

    public void addToDo(String userCommand) {
        try {
            if (userCommand.length() < 5) {
                throw new EmptyToDoDescriptionException();
            }
            String command = userCommand.substring(5); //"todo " 5 index
            Task task = new ToDo(command);
            this.taskList.add(task);
            ui.generateAddToDoMessage(task, taskList);
        } catch (EmptyToDoDescriptionException e) {
            System.out.println(e.toString());
        }
    }

    public  void addDeadline(String userCommand) {
        try {
            if (userCommand.length() < 9) {
                throw new EmptyDeadlineDescriptionException();
            }
            String commandWithDate = userCommand.substring(9); //"deadline " 9 index
            String cDeadline = commandWithDate.split(" /by ")[0]; //just description
            LocalDate byDate = LocalDate.parse(commandWithDate.split(" /by ")[1]); //just byDate
            Task task = new Deadline(cDeadline, byDate);
            this.taskList.add(task);
            ui.generateAddDeadlineMessage(task, taskList);
        } catch (EmptyDeadlineDescriptionException e) {
            System.out.println(e.toString());
        }
    }

    public void addEvent(String userCommand) {
        try {
            if (userCommand.length() < 6) {
                throw new EmptyEventDescriptionException();
            }
            String commandWithDate = userCommand.substring(6); //"todo " 6 index
            String cEvent = commandWithDate.split(" /from ")[0]; //just description
            String dates = commandWithDate.split(" /from ")[1]; //bothDates
            LocalDate fromDate = LocalDate.parse(dates.split(" /to ")[0]); //just fromDate
            LocalDate toDate = LocalDate.parse(dates.split(" /to ")[1]); //just toDate
            Task task = new Event(cEvent, fromDate, toDate);
            this.taskList.add(task);
            ui.generateAddEventMessage(task, taskList);
        } catch (EmptyEventDescriptionException e) {
            System.out.println(e.toString());
        }
    }


    public  void delete(String userCommand) {
        int taskNumber = parseInt(userCommand.split(" ")[1]) - 1; //counting from 0
        Task task = this.taskList.getTask(taskNumber);
        this.taskList.remove(taskNumber);
        ui.generateDeleteMessage(task, taskList);
    }
}

