import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import Exceptions.InitializationException;
import Exceptions.RunException;
import Exceptions.NoCommandException;
import Exceptions.InvalidCommandException;

import Tasks.Task;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Todo;
import Tasks.Task.TaskType;

public class Jeoe {
    private static Scanner scanner;
    private static List<Task> storage;
    private static String filePath = System.getProperty("user.dir") + "/storage/taskListData.txt"; // dir is till ip

    private static Task StringToTask(String taskInStringForm) {
        String[] taskData = taskInStringForm.split(" \\| "); // assuming the description and all that doesnt have |

        for (String s : taskData) {
            System.out.println(s);
        }

        // splits into type, mark or not, description, from, to
        switch (taskData[0]) {
            case "T":
                Task todo = new Todo(taskData[2]);
                if (taskData[1] == "1") {
                    todo.mark();
                }
                return todo;
            case "D":
                Task deadline = new Deadline(taskData[2], taskData[3]);
                if (taskData[1] == "1") {
                    deadline.mark();
                }
                return deadline;
            case "E":
                Task event = new Event(taskData[2], taskData[3], taskData[4]);
                if (taskData[1] == "1") {
                    event.mark();
                }
                return event;
            default:
                return null;
        }
    }

    private static String taskToString(Task t) {
        // splits into type, mark or not, description, from, to
        String deLim = " | ";
        switch (t.taskType()) {
            case TODO:
                String todo = "T" + deLim;
                todo += t.isDone() ? ("1" + deLim) : ("0" + deLim);
                todo += t.getDescription();
                return todo;
            case DEADLINE:
                String deadline = "D" + deLim;
                deadline += t.isDone() ? ("1" + deLim) : ("0" + deLim);
                deadline += t.getDescription() + deLim;
                deadline += t.getEndDateTimeString();
                return deadline;
            case EVENT:
                String event = "E" + deLim;
                event += t.isDone() ? ("1" + deLim) : ("0" + deLim);
                event += t.getDescription() + deLim;
                event += t.getEndDateTimeString();
                return event;
            default:
                return null;
        }
    }

    private static void save() { // for saving into the storage, just rewrite the entire file
        // to overwrite the file
        try {
            // concatenate all the tasks in string form
            System.out.println("saving...");
            String listOfTasksString = "";
            for(Task t : storage) {
                String tString = taskToString(t);
                listOfTasksString += (tString + "\n");
            }

            // write to the file
            FileWriter fw = new FileWriter(filePath);
            fw.write(listOfTasksString);
            fw.close();
            System.out.println("successfully saved");
        } catch (IOException e) {
            System.out.println("failed to save");
            throw new RuntimeException(e);
        }

    }

    private static void initialize() throws InitializationException { // create the initialization exception
        try {
            scanner = new Scanner(System.in); // makes it such that the scanner takes in inputs from the console
            storage = new ArrayList<>(); // everytime you initialize, you start with a new storage
            // add items into the storage when loading the app
            // find the file
            System.out.println("finding the file...");
            File taskListData = new File(filePath);
            new FileReader(filePath);
            System.out.println("file found?");
            System.out.println(taskListData);
            // if file doesnt exist, // taskListData.length() == 0; // is used to check length of file
            if (!taskListData.exists()) {
                System.out.println("task list data doesnt exist");
                taskListData.getParentFile().mkdirs();
                taskListData.createNewFile();
            }

            Scanner fileSc = new Scanner(taskListData);
            while (fileSc.hasNextLine()) { // if no task, storage is empty
                Task task = StringToTask(fileSc.nextLine());
                if (task != null) {
                    storage.add(task);
                }
            }

            String openingStr = "____________________________________________________________\n" +
                    " Hello! I'm JEOE\n" +
                    " What can I do for you?\n" +
                    " type :\n" +
                    " list => to list out items in storage\n" +
                    " _Anything else_ => store in storage\n" +
                    "____________________________________________________________\n";
            System.out.println(openingStr);
        } catch (Exception e) {
            System.out.println("smt went wrong");
            // could be scanner fail OR cannot find such file (assumes if file is found, its always correct format)
            // if cannot find file, create new file
            // if scanner fail, throw scanner fail exception
//            throw new InitializationException("initiazlization exception");
        }
    }

    public static void run(){
        boolean isRunning = true;
        while (isRunning) {
            try {
                String input = scanner.nextLine();

                String reply = "____________________________________________________________\n";
                String command = input.split(" ")[0]; // even if its empty string, the 0 index will still be ""
                switch (command) {
                case "bye":
                    isRunning = false;
                    reply += " Bye. Hope to see you again soon!\n";
                    break;
                case "list":
                    reply += "Here are the tasks in your list:\n";

                    for (int i = 0; i < storage.size(); i++) {
                        String num = String.valueOf(i + 1) + ". ";
                        Task task = storage.get(i);
                        reply += num + task + "\n";
                    }
                    break;
                case "todo":
                    // create the actual task
                    String todoDescription = input.replaceFirst("todo ", "");
                    Todo todo = new Todo(todoDescription);
                    // add to the storage
                    storage.add(todo);
                    Jeoe.save();
                    // add to the reply
                    reply += todo.replyString(storage.size());
                    break;
                case "deadline":
                    String[] deadlineArr = input.replaceFirst("deadline ", "").split(" /by ");
                    String deadlineDescription = deadlineArr[0];
                    String by = deadlineArr[1];
                    Deadline deadline = new Deadline(deadlineDescription, by);
                    // add to storage
                    storage.add(deadline);
                    Jeoe.save();
                    //add to the reply
                    reply += deadline.replyString(storage.size());
                    break;
                case "event":
                    String[] eventArr = input.replaceFirst("event ", "").split(" /from "); // eventArr have description
                    String eventDescription = eventArr[0];
                    String[] eventArr2 = eventArr[1].split(" /to "); // eventArr2 have the from & to
                    String from = eventArr2[0];
                    String to = eventArr2[1];
                    Event event = new Event(eventDescription, from, to);
                    // add to storage
                    storage.add(event);
                    Jeoe.save();
                    // add to the reply
                    reply += event.replyString(storage.size());
                    break;
                case "mark":
                    int idxMark = Integer.parseInt(input.split(" ")[1]) - 1;
                    // mark the task
                    storage.get(idxMark).mark();
                    Jeoe.save();
                    // format the reply
                    reply += ("Nice! I've marked this task as done:\n" +
                            storage.get(idxMark).toString() + "\n");
                    break;
                case "unmark":
                    int idxUnmark = Integer.parseInt(input.split(" ")[1]) - 1;
                    // unmark the task
                    storage.get(idxUnmark).unmark();
                    Jeoe.save();
                    // format the reply
                    reply += ("OK, I've marked this task as not done yet:\n" +
                            storage.get(idxUnmark).toString() + "\n");
                    break;
                case "delete":
                    int idxDel = Integer.parseInt(input.split(" ")[1]) - 1;
                    // delete from the array
                    Task t = storage.remove(idxDel);
                    Jeoe.save();
                    // add to the reply
                    reply += ("Noted. I've removed this task:\n" +
                            t.toString() + "\n" +
                            "Now you have " + storage.size() + " tasks in the list.\n");
                    break;
                case "":
                    throw new NoCommandException(command);
                default:
                    // throw exception when it doesnt have a command word
                    throw new InvalidCommandException(command);
                }
                reply += "____________________________________________________________\n";
                System.out.println(reply);
            } catch (InvalidCommandException e) {
                // print exception, they will handle their formatting themselves
                System.out.println(e.getMessage());
            } catch (NoCommandException e) { // can think of if tried 3 empty commands, terminate program
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        try {
            Jeoe.initialize();
            Jeoe.run();
            scanner.close();
        } catch (InitializationException e) {
            // exception to do with initialization => scanner fails (cannot be file issue)
        }
    }
}