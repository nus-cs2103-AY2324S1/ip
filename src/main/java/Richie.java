

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Path;
import java.io.BufferedReader;
public class Richie {
    static private String CHATBOT_NAME = "Richie";
    static private ArrayList<Task> itemArray = new ArrayList<>();
    static private String dataFilePathname = "src/data.txt";

    private static void loadTasks() {
        try {
            File textFile = new File(dataFilePathname);
            if (textFile.createNewFile()) {
                System.out.println("File created: " + textFile.getName());
            } else {
                System.out.println("Data file already exists");
            }
            BufferedReader bufferedReader = new BufferedReader(new FileReader(dataFilePathname));
            String taskString;
            while ((taskString = bufferedReader.readLine()) != null) {
                Task task= deconstructStringIntoTask(taskString);
                itemArray.add(task);
            }

        } catch (FileNotFoundException e) {
             System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (RichieException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Task deconstructStringIntoTask(String line) throws RichieException {
        String[] array = line.split("/", 2);
        if (array[0].equals("T")) {
            String[] arrayT = line.split("/", 3);
            Todo task = new Todo(arrayT[2]);
            if (arrayT[1].equals("1")) {
                task.markAsDone();
            }
            return task;
        } else if (array[0].equals("D")) {
            String[] arrayD = line.split("/", 4);
            Deadline task = new Deadline(arrayD[2], arrayD[3]);
            if (arrayD[1].equals("1")) {
                task.markAsDone();
            }
            return task;
        } else if (array[0].equals("E")) {
            String[] arrayE = line.split("/", 5);
            Event task = new Event(arrayE[2], arrayE[3], arrayE[4]);
            if (arrayE[1].equals("1")) {
                task.markAsDone();
            }
            return task;
        } else {
            throw new RichieException("Invalid input in data file!");
        }
    }

    /**
     * Adds a task into the task array
     *
     * @Param item The task that should be added to the task array
     */
    public static void addItem(Task item) {
        itemArray.add(item);
    }

    /**
     * Returns a formatted string that lists out all the tasks that are in the task array
     * @return A string that lists out all the tasks that are in the task array
     */
    public static String listItems() {
        int length = itemArray.size();
        String result = "";
        for (int i = 0; i < length; i++) {
            result += (i + 1) + "." + itemArray.get(i).toString() + "\n";
        }
        return result;
    }

    private static void saveCurrentTasks() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dataFilePathname));

            for (Task task : itemArray) {
                String doneNum = task.getIsDone() ? "1" : "0";
                if (task instanceof Todo) {
//                    System.out.println("success");
                    bufferedWriter.append("T/" + doneNum + "/" + task.getDescription());
                    bufferedWriter.append("\n");
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    bufferedWriter.append("D/" + doneNum + "/" + deadline.getDescription() + "/" + deadline.getBy());
                    bufferedWriter.append("\n");
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    bufferedWriter.append("E/" + doneNum + "/" + event.getDescription() + "/" + event.getFrom() + "/" + event.getTo());
                    bufferedWriter.append("\n");
                }
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Early version of code which handles all parsing on user inputs, storage of tasks and user interface.
     * @param args not used in this application
     */
    public static void main(String[] args) {
        System.out.println("Hello! I'm " + CHATBOT_NAME + "\nWhat can I do for you?");
        Richie.loadTasks();
        Scanner input = new Scanner(System.in);
        String message = input.nextLine();
        while (!message.equals("bye")) {
            String[] stringArray = message.split(" ", 2);
            if (message.equals("list")) {
                System.out.println(listItems());
                message = input.nextLine();

            } else if (stringArray[0].equals("mark")) {
                try {
                    int taskIndex = Integer.parseInt(stringArray[1]);
                    Task task = itemArray.get(taskIndex - 1);
                    task.markAsDone();
                    saveCurrentTasks();
                    System.out.println("Nice! I've marked this task as done:\n " + task);
                    message = input.nextLine();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Incomplete input, please specify which task to mark");
                    message = input.nextLine();
                }

            } else if (stringArray[0].equals("delete")) {
                try {
                    int taskIndex = Integer.parseInt(stringArray[1]);
                    System.out.println(taskIndex);
                    Task task = itemArray.get(taskIndex - 1);
                    itemArray.remove(taskIndex - 1);
                    saveCurrentTasks();
                    System.out.println("Noted. I've removed this task:\n" + task + "\nNow you have " + itemArray.size() + " tasks in the list.");
                    message = input.nextLine();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Incomplete input, please specify which task to delete");
                    message = input.nextLine();
                }
            } else if (stringArray[0].equals("deadline")) {
                try {
                    String[] stringArray2 = stringArray[1].split(" /by ", 2);
                    Deadline deadline = new Deadline(stringArray2[0], stringArray2[1]);
                    addItem(deadline);
                    saveCurrentTasks();
                    System.out.println("Got it. I've added this task:\n" + deadline.toString()
                            + "\nNow you have " + itemArray.size() + " tasks in the list.");
                    message = input.nextLine();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("OOPS!! Either the description or the deadline is empty!");
                    message = input.nextLine();
                }

            } else if (stringArray[0].equals("todo")) {
                try {
                    Todo todo = new Todo(stringArray[1]);
                    addItem(todo);
                    saveCurrentTasks();
                    System.out.println("Got it. I've added this task:\n" + todo.toString()
                            + "\nNow you have " + itemArray.size() + " tasks in the list.");
                    message = input.nextLine();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("OOPS!! The description of a todo cannot be empty.");
                    message = input.nextLine();
                }

            } else if (stringArray[0].equals("event")) {
                try {
                    String[] stringArray2 = stringArray[1].split(" /from ", 2);
                    String[] stringArray3 = stringArray2[1].split(" /to ", 2);
                    Event event = new Event(stringArray2[0], stringArray3[0], stringArray3[1]);
                    addItem(event);
                    saveCurrentTasks();
                    System.out.println("Got it. I've added this task:\n" + event.toString()
                            + "\nNow you have " + itemArray.size() + " tasks in the list.");
                    message = input.nextLine();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("OOPS!! The description of a event or the duration of the event is incomplete");
                    message = input.nextLine();
                }

            } else {
                try {
                    throw new RichieException();
                } catch (RichieException e) {
                    System.out.println(e.getMessage());
                    message = input.nextLine();
                }
            }
        }
        saveCurrentTasks();
        System.out.println("Bye. Hope to see you again soon!");

    }
}
