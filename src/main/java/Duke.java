import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./tasks.txt").run();
    }

    static List<Task> taskList = new ArrayList<>();
    static String divider = "    ____________________________________________________________\n";

    static String logo_bird = "    (• >       (• >       (• >       (• >       (• >       (• >\n"
            +  "    /))        /))        /))        /))        /))        /))\n"
            +  "     ``         ``         ``         ``         ``         ``\n";

    static String importantNotes = "    IMPORTANT NOTES:\n"
            + "        Todo: todo <task>\n"
            + "        Deadline: deadline <deadline> /by <duedate>\n"
            + "        Event: event <event> /from <start> /to <end>\n"
            + "        Datetime format: \"dd/MM/yyyy HH:mm\"\n"
            + divider;

    static String greet = "    Hello! I'm Birdy\n"
            + "    chirp chirp! How can I help?\n"
            + divider;

    static String parting = divider + "    chirp! See you around!\n"
            + divider;
    public static void echo() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else {
                System.out.println(divider + "    " + input + "\n" + divider);
            }
        }
    }

    public static void printAddedTask() {
        System.out.println(divider + "    chirp! I've added this task:\n"
                + String.format("    %s", taskList.get(taskList.size() - 1).toString()) + "\n"
                + String.format("    Now you have %d tasks in the list\n", taskList.size())
                + divider);
    }

    public static void rewriteTaskListFile(String filepath, List<Task> filelist) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        filelist.forEach(task -> {
            try {
                fw.write(task.getTaskFileString() + System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        fw.close();
    }

    public static void writeTaskListFile(String filepath, String task) throws IOException {
        FileWriter fw = new FileWriter(filepath, true);
        fw.write(task + System.lineSeparator());
        fw.close();
    }

    public static void setTaskList(String filepath) throws Exception {
        Scanner sc = new Scanner(System.in);
        boolean inLoop = true;

        while (inLoop) {
            try {
                String[] input = sc.nextLine().split(" ", 2);
                String firstWord = input[0];
                String taskDetails = input.length < 2 ? "" : input[1];

                if (firstWord.equals("bye")) {
                    System.out.println(parting);
                    break;
                }

                if (firstWord.equals("list")) {
                    System.out.println(divider);
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println("    " + (i + 1) + ". " + taskList.get(i).toString());
                    }
                    System.out.println(divider);
                    continue;
                }

                switch (firstWord) {
                case "todo":
                    if (taskDetails.equals("")) throw new EmptyTodoException(
                            divider + "    TWEET!!! The description of a todo cannot be empty.\n" + divider
                    );
                    Todo todo = new Todo(taskDetails, false);
                    taskList.add(todo);
                    writeTaskListFile(filepath, todo.getTaskFileString());
                    printAddedTask();
                    break;
                case "deadline":
                    System.out.println(taskDetails);
                    if (taskDetails.equals("")) throw new EmptyDeadlineException(
                            divider + "    TWEET!!! The description of a deadline cannot be empty.\n" + divider
                    );
                    String[] deadlineDetails = taskDetails.split(" /by ");
                    Deadline deadline = new Deadline(deadlineDetails[0], deadlineDetails[1], false);
                    taskList.add(deadline);
                    writeTaskListFile(filepath, deadline.getTaskFileString());
                    printAddedTask();
                    break;
                case "event":
                    if (taskDetails.equals("")) throw new EmptyEventException(
                            divider + "    TWEET!!! The description of an event cannot be empty.\n" + divider
                    );
                    String[] eventDetails = taskDetails.split(" /from ");
                    String[] startEndDetails = eventDetails[1].split(" /to ");
                    Event event = new Event(eventDetails[0], startEndDetails[0], startEndDetails[1], false);
                    taskList.add(event);
                    writeTaskListFile(filepath, event.getTaskFileString());
                    printAddedTask();
                    break;
                case "delete":
                    int deleteIndex = Integer.parseInt(taskDetails) - 1;
                    if (deleteIndex < 0 || deleteIndex >= taskList.size()) throw new InvalidTaskNumberException();
                    taskList.remove(deleteIndex);System.out.print(divider + "    chirp! chirp! Task right out the window!\n" + divider);
                    rewriteTaskListFile(filepath, taskList);
                    break;
                case "mark":
                    int markIndex = Integer.parseInt(taskDetails) - 1;
                    if (markIndex < 0 || markIndex >= taskList.size()) throw new InvalidTaskNumberException();
                    System.out.print(divider);
                    taskList.get(markIndex).markTask();
                    System.out.println(divider);
                    rewriteTaskListFile(filepath, taskList);
                    break;
                case "unmark":
                    int unmarkIndex = Integer.parseInt(taskDetails) - 1;
                    if (unmarkIndex < 0 || unmarkIndex >= taskList.size()) throw new InvalidTaskNumberException();
                    System.out.print(divider);
                    taskList.get(unmarkIndex).unmarkTask();
                    System.out.println(divider);
                    rewriteTaskListFile(filepath, taskList);
                    break;
                case "start":
                    int startIndex = Integer.parseInt(taskDetails) - 1;
                    if (startIndex < 0 || startIndex >= taskList.size()) throw new InvalidTaskNumberException();
                    System.out.print(divider);
                    taskList.get(startIndex).printStart();
                    System.out.println(divider);
                    break;
                case "end":
                    int endIndex = Integer.parseInt(taskDetails) - 1;
                    if (endIndex < 0 || endIndex >= taskList.size()) throw new InvalidTaskNumberException();
                    System.out.print(divider);
                    taskList.get(endIndex).printEnd();
                    System.out.println(divider);
                    break;
                case "due":
                    int dueIndex = Integer.parseInt(taskDetails) - 1;
                    if (dueIndex < 0 || dueIndex >= taskList.size()) throw new InvalidTaskNumberException();
                    System.out.print(divider);
                    taskList.get(dueIndex).printDueDate();
                    System.out.println(divider);
                    break;
                default:
                    throw new EmptyEventException(
                            divider + "    TWEET!!! I'm sorry, but I don't know how to bark\n" + divider
                    );
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }
//    public static void main(String[] args){
//        System.out.println("    chirp chirp!\n" + logo_bird + importantNotes + greet);
//        String filepath = "./tasklistfile.txt";
//        try {
//            taskList = readTaskListFile(filepath);
//            setTaskList(filepath);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }


}
