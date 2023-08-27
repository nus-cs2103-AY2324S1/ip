import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    // Constructor for Duke
    public Duke() {
        this.list = new ArrayList<>();
    }

    // Constants
    private static final String NAME = "Beep Boop";
    private static final String LINE = "─".repeat(100);
    private static final String PATH = "./data/duke.txt";

    // Fields
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Task> list;

    public void printMessage(String message) {
        System.out.printf("\t%s\n", message);
        System.out.println(LINE);
    }

    public void greet() {
        System.out.println(LINE);
        System.out.printf("\tHello! I'm %s!\n\n", NAME);

        try {
            loadList();
        } catch (FileNotFoundException e) {
            System.out.println("\tBoop Beep, you do not have any previous task.");
        } catch (DukeException e) {
            printMessage(e.getMessage());
        }

        printMessage("\n\tHow can I help you?\n");
    }

    public void exit() {
        String exiting = "Bye Bye. Hope to see you again soon! Beep Boop!\n";
        printMessage(exiting);
    }

    public void addToList(Task task) {
        list.add(task);
        System.out.println("\tGot it. I've added this task:");
        System.out.printf("\t\t%s\n\tNow you have %d tasks in the list.\n", task.toString(), list.size());
        System.out.println(LINE);
    }

    private void deleteFromList(int index) {
        Task task = list.get(index);
        list.remove(index);
        System.out.println("\tNoted. I've removed this task:");
        System.out.printf("\t\t%s\n\tNow you have %d tasks in the list.\n", task.toString(), list.size());
        System.out.println(LINE);
    }

    public void printList() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i =0; i < list.size(); i++) {
            System.out.printf("\t\t%d. %s\n", i + 1, list.get(i));
        }
        System.out.println(LINE);
    }

    private void validateToDo(String description) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException("Boop Beep OOPS!!! The description of a todo cannot be empty.");
        }
    }

    private void validateDeadline(String[] deadlineString) throws DukeException {
        if (deadlineString.length != 2 || deadlineString[0].isBlank() || deadlineString[1].isBlank()) {
            throw new DukeException("Boop Beep OOPS!!! Please make sure that"
                    + " the description and date of the deadline is not empty.");
        }
    }

    private void validateEvent(String[] eventString) throws DukeException {
        if (eventString.length != 3 || eventString[0].isBlank() || eventString[1].isBlank() || eventString[2].isBlank()) {
            throw new DukeException("Boop Beep OOPS!!! Please make sure that"
                    + " the description and dates of the event is not empty.");
        }
    }

    private void validateMarkOrUnmarkorDelete(String number) throws DukeException {
        if (number.isBlank()) {
            throw new DukeException("Boop Beep OOPS!!! Please make sure that the index of the task is not empty.");
        } else {
            try {
                int numberInt = Integer.parseInt(number);
                if (numberInt <= 0 || numberInt > list.size()) {
                    throw new DukeException("Boop Beep OOPS!!! Please make sure that"
                            + " the index of the task is within range.");
                }
            } catch (NumberFormatException e) {
                throw new DukeException("Boop Beep OOPS!!! Please make sure that the index of the task is an integer.");
            }
        }
    }

    private void loadList() throws FileNotFoundException, DukeException {
        File f = new File(PATH);
        Scanner s = new Scanner(f);

        System.out.println("\tHere are previous tasks in your list:");
        int i = 0;
        while (s.hasNext()) {
            String line = s.nextLine();
            String taskType = line.substring(0, 3);
            String markDone = line.substring(4, 7);

            if (taskType.equals("[T]")) {
                String taskDescription = line.substring(8);

                ToDo todo = new ToDo(taskDescription);
                list.add(todo);
                if (markDone.equals("[X]")) {
                    list.get(i).markDone();
                }
            } else if (taskType.equals("[D]")) {
                String[] deadlineString = line.substring(8).split(":", 2);
                String description = deadlineString[0];
                String taskDescription = description.substring(0, description.length() - 3).trim();
                String date = deadlineString[1];
                String taskDate = date.substring(0, date.length()).trim();

                Deadline deadline = new Deadline(taskDescription, taskDate);
                list.add(deadline);
                if (markDone.equals("[X]")) {
                    list.get(i).markDone();
                }
            } else if (taskType.equals("[E]")) {
                String[] eventString = line.substring(8).split(":", 3);
                String description = eventString[0];
                String taskDescription = description.substring(0, description.length() - 5).trim();
                String startDate = eventString[1];
                String taskStartDate = startDate.substring(0, startDate.length() - 2).trim();
                String endDate = eventString[2];
                String taskEndDate = endDate.substring(0, endDate.length() - 1).trim();

                Event event = new Event(taskDescription, taskStartDate, taskEndDate);
                list.add(event);
                if (markDone.equals("[X]")) {
                    list.get(i).markDone();
                }
            } else {
                throw new DukeException("Boop Beep OOPS!!! It seems like the data file is corrupted :(");
            }

            System.out.printf("\t\t%d. %s\n", i + 1, line);
            i++;
        }
    }

    private void saveList(String filePath) throws IOException {
        File f = new File(filePath);
        if (!f.exists()) {
            try {
                f.getParentFile().mkdirs();
                f.createNewFile();
            } catch (IOException e) {
                System.out.println("Boop Beep OOPS, " + e.getMessage());
            }
        }

        FileWriter fw = new FileWriter(filePath);
        String stringList = "";
        for (int i = 0; i < list.size(); i++) {
            stringList = stringList + list.get(i) + System.lineSeparator();
        }
        fw.write(stringList);
        fw.close();
    }

    public void runDuke() {
        greet();

        boolean isDone = false;
        while (!isDone) {
            String input = sc.nextLine();

            try {
                if (input.equals("bye")) {
                    isDone = true;
                } else if (input.equals("list")) {
                    printList();
                } else if (input.startsWith("mark")) {
                    String number = input.replaceFirst("mark", "").trim();
                    validateMarkOrUnmarkorDelete(number);

                    int index = Integer.parseInt(number) - 1;
                    list.get(index).markDone();
                    String markDoneMessage = String.format("\t%s", list.get(index).toString());

                    System.out.println("\tBeep Boop Beep! I've marked this task as done:");
                    printMessage(markDoneMessage);
                } else if (input.startsWith("unmark")) {
                    String number = input.replaceFirst("unmark", "").trim();
                    validateMarkOrUnmarkorDelete(number);

                    int index = Integer.parseInt(number) - 1;
                    list.get(index).markNotDone();
                    String markNotDoneMessage = String.format("\t%s", list.get(index).toString());

                    System.out.println("\tBeep Boop Boop! I've marked this task as not done yet:");
                    printMessage(markNotDoneMessage);
                } else if (input.startsWith("todo")) {
                    String description = input.replaceFirst("todo", "").trim();
                    validateToDo(description);

                    ToDo todo = new ToDo(description);
                    addToList(todo);
                } else if (input.startsWith("deadline")) {
                    String[] deadlineString = input.replaceFirst("deadline", "")
                            .split("/", 2);
                    validateDeadline(deadlineString);

                    String description = deadlineString[0].trim();
                    String deadlineDate = deadlineString[1].replaceFirst("by", "").trim();

                    Deadline deadline = new Deadline(description, deadlineDate);
                    addToList(deadline);
                } else if (input.startsWith("event")) {
                    String[] eventString = input.replaceFirst("event", "").split("/", 3);
                    validateEvent(eventString);

                    String description = eventString[0].trim();
                    String start = eventString[1].replaceFirst("from", "").trim();
                    String end = eventString[2].replaceFirst("to", "").trim();

                    Event event = new Event(description, start, end);
                    addToList(event);
                } else if (input.startsWith("delete")) {
                    String number = input.replaceFirst("delete", "").trim();
                    validateMarkOrUnmarkorDelete(number);

                    int index = Integer.parseInt(number) - 1;
                    deleteFromList(index);
                } else {
                    throw new DukeException("Boop Beep OOPS!!! I'm sorry, but I don't know what that means :(");
                }

                saveList(PATH);
            } catch (DukeException e) {
                printMessage(e.getMessage());
            } catch (IOException e) {
                printMessage("Boop Beep OOPS, " + e.getMessage());
            }
        }

        exit();
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.runDuke();
    }
}
