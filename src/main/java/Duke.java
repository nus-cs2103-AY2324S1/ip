import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    private static final String hardDiskPath = "./data/duke.txt";
    private static File hardDisk;
    public static final String horizontalLine = "    ____________________________________________________________";
    public static ArrayList<Task> taskArray = new ArrayList<>();
    public static int numTask = 0;
    enum TASK { MARK, UNMARK, DELETE, TODO, EVENT, DEADLINE, BYE, LIST, INVALID}
    public static void greet() {
        System.out.println(horizontalLine + "\n"
                + "     Hello! I'm POPOOH\n"
                + "     What can I do for you?\n"
                + horizontalLine);
    }
    public static void exit() {
        System.out.println(horizontalLine + "\n"
                           + "     Bye. Hope to see you again soon!\n"
                           + horizontalLine);
    }
    public static void unmark(String i) {
        int taskId = Integer.parseInt(i.substring(7)) - 1;
        taskArray.get(taskId).markAsUndone();
        // update the duke.txt
        try {
            saveTask();
        } catch (IOException e) {
            System.out.println("      Uhm.. something is not working right..");
        }
    }
    public static void mark(String i) {
        int taskId = Integer.parseInt(i.substring(5)) - 1;
        taskArray.get(taskId).markAsDone();
        // update the duke.txt
        try {
            saveTask();
        } catch (IOException e) {
            System.out.println("      Uhm.. something is not working right..");
        }
    }
    public static void deleteTask(String i) {
        int deleteTask = Integer.parseInt(i.substring(7)) - 1;
        Task removed = taskArray.get(deleteTask);
        taskArray.remove(deleteTask);
        numTask--;
        System.out.println("     Noted. I've removed this task:\n"
                           + "     " + removed.printDesc() + "\n"
                           + "     Now you have " + numTask +" tasks in the list.");
    }
    public static void listTask(String i) {
        System.out.println("     Here are the tasks in your list:\n");
        for (int a = 0; a < numTask; a++) {
            System.out.println("     " + (a + 1) + ". " + taskArray.get(a).printDesc());
        }
    }
    public static void todoTask(String i) {
        String[] taskDetails = i.split(" ", 2);
        try {
            taskArray.add(new Todo(taskDetails[1]));
            taskArray.get(numTask).printMessage(numTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(horizontalLine + "\n"
                               + "     OOPS!!! The description of todo cannot be empty :(.\n"
                               + horizontalLine);
            numTask--;
        }
        numTask++;
    }
    public static void deadlineTask(String i) {
        String[] taskDetails = i.split(" ", 2);
        try {
            String[] deadlineDetails = taskDetails[1].split(" /by ", 2);
            taskArray.add(new Deadline(deadlineDetails[0], deadlineDetails[1]));
            taskArray.get(numTask).printMessage(numTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(horizontalLine + "\n"
                               + "     OOPS!!! The description of deadline is incomplete.\n"
                               + horizontalLine);
            numTask--;
        }
        numTask++;
    }
    public static void eventTask(String i) {
        String[] taskDetails = i.split(" ", 2);
        try {
            String[] eventDetails = taskDetails[1].split(" /", 3);
            taskArray.add(new Event(eventDetails[0], eventDetails[1].substring(5),
                    eventDetails[2].substring(3)));
            taskArray.get(numTask).printMessage(numTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(horizontalLine + "\n"
                               + "     OOPS!!! The description of event is incomplete :(.\n"
                               + horizontalLine);
            numTask--;
        }
        numTask++;
    }
    public static TASK commandCheck(String command) {
        switch(command) {
            case "bye":
                return TASK.BYE;
            case "unmark":
                return TASK.UNMARK;
            case "mark":
                return TASK.MARK;
            case "delete":
                return TASK.DELETE;
            case "list":
                return TASK.LIST;
            case "todo":
                return TASK.TODO;
            case "deadline":
                return TASK.DEADLINE;
            case "event":
                return TASK.EVENT;
            default:
                return TASK.INVALID;
        }
    }
    public static void printCommand(TASK command, String info) throws DukeException, IOException {
        switch(command) {
            case BYE:
                exit();
                break;
            case UNMARK:
                System.out.println(horizontalLine);
                unmark(info);
                System.out.println(horizontalLine);
                break;
            case MARK:
                System.out.println(horizontalLine);
                mark(info);
                System.out.println(horizontalLine);
                break;
            case DELETE:
                System.out.println(horizontalLine);
                deleteTask(info);
                System.out.println(horizontalLine);
                break;
            case LIST:
                System.out.println(horizontalLine);
                listTask(info);
                System.out.println(horizontalLine);
                break;
            case TODO:
                todoTask(info);
                break;
            case EVENT:
                eventTask(info);
                break;
            case DEADLINE:
                deadlineTask(info);
                break;
            default:
                throw new DukeException("     OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
    private static void checkHardDisk() {
        File dataDirectory = new File("./data");
        if (!dataDirectory.exists()) {
            System.out.println("     OOPS! The data directory doesn't exist. I'll create one for you!");
            dataDirectory.mkdir();
        }
        hardDisk = new File(hardDiskPath);
        if (!hardDisk.exists()) {
            System.out.println("     OOPS! The hard disk doesn't exist. I'll create one for you!");
            try {
                hardDisk.createNewFile();
                hardDisk.setReadable(true);
                hardDisk.setWritable(true);
            } catch (IOException e) {
                System.out.println("     Something went wrong, we couldn't create duke.txt");
            }
        }
    }
    // retrieves past tasks
    private static void loadTask() {
        try {
            Scanner fileScanner = new Scanner(hardDisk);
            while (fileScanner.hasNext()) {
                String task = fileScanner.nextLine();
                String[] taskDetails = task.split("~",5);
                String taskType = taskDetails[0];
                String taskStatus = taskDetails[1];
                String taskDescription = taskDetails[2];
                switch (taskType) {
                    case "T":
                        Todo addTodo = new Todo(taskDescription);
                        if (Objects.equals(taskStatus, "done")) {
                            addTodo.updateAsDone();
                        }
                        taskArray.add(addTodo);
                        numTask++;
                        break;
                    case "D":
                        Deadline addDeadline = new Deadline(taskDescription, taskDetails[3]);
                        if (Objects.equals(taskStatus, "Y")) {
                            addDeadline.markAsDone();
                        }
                        taskArray.add(addDeadline);
                        numTask++;
                        break;
                    case "E":
                        String[] timeDetails = taskDetails[3].split(" - ", 2);
                        Event addEvent = new Event(taskDescription, timeDetails[0], timeDetails[1]);
                        if (Objects.equals(taskStatus, "Y")) {
                            addEvent.markAsDone();
                        }
                        taskArray.add(addEvent);
                        numTask++;
                        break;
                    default:
                        throw new DukeException("     invalid task in the hard disk");
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("     There is no saved duke.txt");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
    // saves new tasks
    private static void saveTask() throws IOException {
        FileWriter fw = new FileWriter(hardDiskPath);
        for (Task task : taskArray) {
            String writeTask = task.getDescription() + "\n";
            fw.write(writeTask);
        }
        fw.close();
    }
    public static void main(String[] args) {
        checkHardDisk();
        loadTask();
        greet();
        File f = new File("/Desktop/CS2103T/ip/hardDrive.txt");
        Scanner input = new Scanner(System.in);

        while (input.hasNext()) {
            String command = input.nextLine();
            String[] findCommand = command.split(" ", 2);
            TASK order = commandCheck(findCommand[0]);
            try {
                printCommand(order, command);
                saveTask();
            } catch (DukeException message) {
                System.out.println(horizontalLine + "\n" + message.getMessage() + "\n" + horizontalLine);
            } catch (IOException e) {
                System.out.println("     Oh no, seems like something is not working.. We can't save your data.");
            }
        }
    }
}
