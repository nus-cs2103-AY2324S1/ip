import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final static FileManager fm = new FileManager("data/duke.txt");
    private static ArrayList<Task> taskList;

    private static void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    private static String formatTask(Task task) {
        return "Got it. I've added this task:\n  " +
                task + "\nNow you have " +
                taskList.size() + " tasks in the list.";
    }
    private static void addTodo(String description) {
        Task task = new Task(description);
        taskList.add(task);
        System.out.println(formatTask(task));
    }

    private static void addDeadline(String description, LocalDate by) {
        Task task = new Deadline(description, by);
        taskList.add(task);
        System.out.println(formatTask(task));
    }

    private static void addEvent(String description, String from, String to) {
        Task task = new Event(description, from, to);
        taskList.add(task);
        System.out.println(formatTask(task));
    }

    private static void listTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < taskList.size() + 1; i++) {
            System.out.println(i + ". " + taskList.get(i - 1).toString());
        }
    }

    private static void markDone(int taskNumber) throws ChatException {
        try {
            Task task = taskList.get(taskNumber - 1);
            task.setTaskState(true);
            System.out.println("Nice! I've marked this task as done:\n" + task);
        } catch (IndexOutOfBoundsException e) {
            throw new ChatException("☹ OOPS!!! Please specify the correct task number.");
        }
    }

    private static void markUndone(int taskNumber) throws ChatException {
        try {
            Task task = taskList.get(taskNumber - 1);
            task.setTaskState(false);
            System.out.println("OK, I've marked this task as not done yet:\n" + task);
        } catch (IndexOutOfBoundsException e) {
            throw new ChatException("☹ OOPS!!! Please specify the correct task number.");
        }
    }

    private static void deleteTask(int taskNumber) throws ChatException {
        try {
            Task task = taskList.get(taskNumber - 1);
            taskList.remove(taskNumber - 1);
            System.out.println("Noted. I've removed this task:\n" + task +
                    "\nNow you have " + taskList.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new ChatException("☹ OOPS!!! Please specify the correct task number.");
        }
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) throws ChatException{
        taskList = fm.loadFile();
        Scanner sc = new Scanner(System.in);
        greet();
        String userCommand = sc.nextLine();
        while(!userCommand.equals("bye")) {
            if(userCommand.equals("list")) {
                listTask();
            } else {
                try {
                    String[] words = userCommand.split(" ", 2);
                    switch (words[0]) {
                        case "mark": {
                            int taskNumber = Integer.parseInt(words[1]);
                            markDone(taskNumber);
                            break;
                        }
                        case "unmark": {
                            int taskNumber = Integer.parseInt(words[1]);
                            markUndone(taskNumber);
                            break;
                        }
                        case "todo":
                            addTodo(words[1]);
                            break;
                        case "deadline":
                            try{
                                String[] deadlineTask = words[1].split(" /by ");
                                addDeadline(deadlineTask[0], LocalDate.parse(deadlineTask[1]));
                                break;
                            } catch (ArrayIndexOutOfBoundsException e) {
                                throw new ChatException("☹ OOPS!!! Please specify the deadline.");
                            }
                        case "event":
                            try {
                                String[] eventTask = words[1].split(" /from ");
                                String[] duration = eventTask[1].split(" /to ");
                                addEvent(eventTask[0], duration[0], duration[1]);
                                break;
                            } catch (ArrayIndexOutOfBoundsException e) {
                                throw new ChatException("☹ OOPS!!! Please specify the duration.");
                            }
                        case "delete":
                            int taskNumber = Integer.parseInt(words[1]);
                            deleteTask(taskNumber);
                            break;
                        default:
                            throw new ChatException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    switch (userCommand) {
                        case "mark":
                        case "unmark":
                            throw new ChatException("☹ OOPS!!! Please specify the task number.");
                        case "todo":
                            throw new ChatException("☹ OOPS!!! ☹ OOPS!!! The description of a todo cannot be empty.");
                        case "deadline":
                            throw new ChatException("☹ OOPS!!! ☹ OOPS!!! The description of a deadline cannot be empty.");
                        case "event":
                            throw new ChatException("☹ OOPS!!! ☹ OOPS!!! The description of an event cannot be empty.");
                        default:
                            throw new ChatException("☹ OOPS!!! Please be more detailed in your instructions.");
                    }
                }
            }
            userCommand = sc.nextLine();
        }
        fm.saveList(taskList);
        exit();
    }
}
