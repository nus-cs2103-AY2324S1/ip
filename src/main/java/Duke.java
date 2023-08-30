import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;


/**
 * Ding Chatbot
 */
public class Duke {

    private ArrayList<Task> tasks;

    public Duke() {
        this.tasks = Duke.setupTasks();
    }
    /**
     * Main function for Ding Chatbot
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Duke ding = new Duke();
        System.out.println(
                "____________________________________________________________\n" +
                "Hello! I'm Ding!\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!str.equals("bye")) {
            try {
                String command_word = str.split(" ")[0];
                Command command = Command.valueOf(command_word.toUpperCase());
                switch (command) {
                case LIST:
                    ding.printList();
                    break;
                case MARK:
                    Duke.markItem(str, ding.tasks);
                    break;
                case UNMARK:
                    Duke.unmarkItem(str, ding.tasks);
                    break;
                case TODO:
                    Duke.addTodo(str, ding.tasks);
                    break;
                case DEADLINE:
                    Duke.addDeadline(str, ding.tasks);
                    break;
                case EVENT:
                    Duke.addEvent(str, ding.tasks);
                    break;
                case DELETE:
                    Duke.deleteTask(str, ding.tasks);
                    break;
                }
            } catch (IllegalArgumentException e) {
                handleIllegalArgumentException();
            } catch (InvalidDescriptionException e) {
                handleInvalidDescriptionException();
            } catch (InvalidTaskIndexException e) {
                handleInvalidTaskIndexException(ding);
            } catch (MissingTaskIndexException e) {
                handleMissingTaskIndexException();
            } catch (InvalidDateTimeException e) {
                handleInvalidDateTimeException();
            } finally {
                System.out.println("\n____________________________________________________________\n");
                Duke.updateTasks(ding.tasks);
                str = sc.nextLine();
            }
        }
        System.out.println(
                "____________________________________________________________\n" +
                "Ding: Bye. Hopefully I get to see you again soon!\n" +
                "____________________________________________________________");

    }


    private void printList() {
        System.out.println("Ding: These are your tasks... If I remember correctly:");
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, this.tasks.get(i)));
        }
    }

    private static void markItem(String str, ArrayList<Task> tasks) throws InvalidTaskIndexException, MissingTaskIndexException {
        if (str.split(" ").length == 2) {
            int taskIndex = Integer.parseInt(str.split(" ")[1]) - 1;
            if (taskIndex + 1 > tasks.size() || taskIndex < 0) {
                System.out.println("Ding: I can't find that task :( I may have lost it...");
                throw new InvalidTaskIndexException("Invalid Task Index.");
            }
            tasks.get(taskIndex).markAsDone();
            System.out.println(String.format("Ding: Okay, I marked this task as done, but I have no idea what that means:\n %s", tasks.get(taskIndex)));
        } else {
            System.out.println("Ding: Which task do you want to mark as done?");
            throw new MissingTaskIndexException("Task Index Missing.");
        }
    }

    private static void unmarkItem(String str, ArrayList<Task> tasks) throws InvalidTaskIndexException, MissingTaskIndexException {
        if (str.split(" ").length == 2) {
            int taskIndex = Integer.parseInt(str.split(" ")[1]) - 1;
            if (taskIndex + 1 > tasks.size() || taskIndex < 0) {
                System.out.println("Ding: I can't find that task :( I may have lost it...");
                throw new InvalidTaskIndexException("Invalid Task Index.");
            }
            tasks.get(taskIndex).markAsUndone();
            System.out.println(String.format("Ding: Okay, I marked this task as undone, but I have no idea what that means:\n %s", tasks.get(taskIndex)));
        } else {
            System.out.println("Ding: Which task do you want to mark as undone?");
            throw new MissingTaskIndexException("Task Index Missing.");
        }
    }

    private static void addTodo(String str, ArrayList<Task> tasks) throws InvalidDescriptionException {
        if (str.split(" ").length > 1) {
            ToDo todo = new ToDo(str.split(" ")[1]);
            tasks.add(todo);
            System.out.println(String.format("Ding: What does '%s' mean? I'll just add it anyway.\n You have like %d tasks now", str, tasks.size()));
        } else {
            System.out.println("Ding: I seriously have no idea what I need to do here");
            throw new InvalidDescriptionException("Invalid description.");
        }
    }

    private static void addDeadline(String str, ArrayList<Task> tasks) throws InvalidDescriptionException, InvalidDateTimeException {
        if (str.split(" ").length > 3) {
            String fullTaskDescription = str.split(" ", 2)[1];
            String description = fullTaskDescription.split(" /by ")[0];
            String by = fullTaskDescription.split(" /by ")[1];
            String[] datetime = by.split(" ");
            try {
                LocalDate date = LocalDate.parse(datetime[0]);
                LocalTime time = LocalTime.parse(datetime[1]);
                Deadline deadline = new Deadline(description, date, time);
                tasks.add(deadline);
                System.out.println(String.format("Ding: What does '%s' mean? I'll just add it anyway.\n You have like %d tasks now", str, tasks.size()));
            } catch (DateTimeParseException e) {
                throw new InvalidDateTimeException("Invalid Datetime.");
            }
        } else {
            System.out.println("Ding: I seriously have no idea what I need to do here");
            throw new InvalidDescriptionException("Invalid description.");
        }
    }

    private static void addEvent(String str, ArrayList<Task> tasks) throws InvalidDescriptionException, InvalidDateTimeException {
        if (str.split(" ").length > 4) {
            String fullTaskDescription = str.split(" ", 2)[1];
            String description = fullTaskDescription.split(" /from ")[0];
            String from = String.join("", fullTaskDescription.split(" /from ")[1]).split(" /to ")[0];
            String to = fullTaskDescription.split(" /to ")[1];
            try {
                String[] fromDatetime = from.split(" ");
                String[] toDatetime = to.split(" ");
                LocalDate fromDate = LocalDate.parse(fromDatetime[0]);
                LocalTime fromTime = LocalTime.parse(fromDatetime[1]);
                LocalDate toDate = LocalDate.parse(toDatetime[0]);
                LocalTime toTime = LocalTime.parse(toDatetime[1]);
                Event event = new Event(description, fromDate, fromTime, toDate, toTime);
                tasks.add(event);
                System.out.println(String.format("Ding: What does '%s' mean? I'll just add it anyway.\n You have like %d tasks now", str, tasks.size()));
            } catch (DateTimeParseException e) {
                throw new InvalidDateTimeException("Invalid Datetime");
            }

        } else {
            System.out.println("Ding: I seriously have no idea what I need to do here");
            throw new InvalidDescriptionException("Invalid description.");
        }
    }

    private static void deleteTask(String str, ArrayList<Task> tasks) throws InvalidTaskIndexException, MissingTaskIndexException {
        if (str.split(" ").length == 2) {
            int taskIndex = Integer.parseInt(str.split(" ")[1]) - 1;
            if (taskIndex + 1 > tasks.size() || taskIndex < 0) {
                System.out.println("Ding: I can't find that task :( I may have lost it...");
                throw new InvalidTaskIndexException("Invalid Task Index.");
            }
            Task toRemove = tasks.get(taskIndex);
            tasks.remove(taskIndex);
            System.out.println(String.format("Ding: Okay, I've forgotten this task, so don't expect me to remember it:\n %s", toRemove));
            System.out.println(String.format("Ding: Right so now you have like %d tasks left", tasks.size()));
        } else {
            System.out.println("Ding: Which task do you want delete?");
            throw new MissingTaskIndexException("Task Index Missing.");
        }
    }

    private static void handleIllegalArgumentException() {
        System.out.println("Ding: I seriously have no idea what I need to do here");
        System.out.println("Ding: No way you forgot to even input a proper command...");
        System.out.println("Ding: Available commands are 'todo', 'deadline', 'event', 'list', 'mark', 'unmark', 'delete', 'bye'");
    }

    private static void handleInvalidDescriptionException() {
        System.out.println("Ding: I may be forgetful but you're so bad you even forgot the task description...");
        System.out.println("Ding: For ToDos, input 'todo (task)'");
        System.out.println("Ding: For Deadlines, input 'deadline (task) /by (date or time)");
        System.out.println("Ding: For Events, input 'event (task) /from (date or time) /to (date or time)");
    }

    private static void handleInvalidTaskIndexException(Duke ding) {
        System.out.println("Ding: Oh wait it's not lost, the task number you gave just doesn't exist in your list...");
        if (ding.tasks.size() > 0) {
            System.out.println(String.format("Ding: Please input a task number from 1 to %d", ding.tasks.size()));
        } else {
            System.out.println("Ding: You have nothing in your task list... What are you even trying to get me to do?");
        }
    }

    private static void handleMissingTaskIndexException() {
        System.out.println("Ding: I don't quite understand what you want to do...");
        System.out.println("Ding: Please input '(command) (task number)'...");
    }

    private static void handleInvalidDateTimeException() {
        System.out.println("Ding: I already told you... please enter the timestamps in this format 'YYYY-MM-DD HH:mm'");
    }


    private static void updateTasks(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter("./data/ding.txt");
            for (Task task : tasks) {
                writer.write(task + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
            System.out.println(e);
        }
    }
    private static ArrayList<Task> setupTasks() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            File taskFile = new File("./data/ding.txt");
            if (taskFile.createNewFile()) {
                //file created
            } else {
                taskList = getTasks(taskFile);
            }
        } catch (IOException e) {
            System.out.println("Error occurred");
            e.printStackTrace();
        } finally {
            return taskList;
        }
    }

    private static ArrayList<Task> getTasks(File file) {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                char taskType = str.charAt(1);
                boolean isMarked = str.charAt(4) == 'X';
                String description = str.split("] ")[1];
                switch (taskType) {
                case 'T':
                    ToDo taskT = new ToDo(description);
                    if (isMarked) {
                        taskT.markAsDone();
                    }
                    taskList.add(taskT);
                    break;
                case 'D':
                    description = description.split(" \\(by: ")[0];
                    String by = str.split(" \\(by: ")[1];
                    LocalDate date = LocalDate.parse(by.split(" ")[0]);
                    LocalTime time = LocalTime.parse(by.split(" ")[1].replaceAll(".$", ""));
                    Deadline taskD = new Deadline(description, date, time);
                    if (isMarked) {
                        taskD.markAsDone();
                    }
                    taskList.add(taskD);
                    break;
                case 'E':
                    description = description.split(" \\(from: ")[0];
                    String from = String.join("", str.split("\\(from: ")[1]).split(" to: ")[0];
                    String to = str.split(" to: ")[1];
                    LocalDate fromDate = LocalDate.parse(from.split(" ")[0]);
                    LocalTime fromTime = LocalTime.parse(from.split(" ")[1]);
                    LocalDate toDate = LocalDate.parse(to.split(" ")[0]);
                    LocalTime toTime = LocalTime.parse(to.split(" ")[1].replaceAll(".$", ""));
                    Event taskE = new Event(description, fromDate, fromTime, toDate, toTime);
                    if (isMarked) {
                        taskE.markAsDone();
                    }
                    taskList.add(taskE);
                    break;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            return taskList;
        }
    }

}
