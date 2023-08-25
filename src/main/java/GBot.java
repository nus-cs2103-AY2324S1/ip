import exceptions.*;

import java.util.ArrayList;
import java.util.Scanner;

public class GBot {
    private static ArrayList<Task> list = new ArrayList<>();
    private static final String LINE = "____________________________________________________________";
    enum Keyword {
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE

    }
    private static void print(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }
    private static void listTasks() {
        if (list.isEmpty()) {
            print("No tasks available.");
        } else {
            System.out.println(LINE);
            System.out.println("Here are the tasks in your list:");
            int len = list.size();
            for (int i = 0; i < len; i++) {
                System.out.println((i + 1) + ". " + list.get(i).toString());
            }
            System.out.println(LINE);
        }
    }

    private static void markTask(String message) throws TaskException {
        if (message.length() <= 5) {
            throw new TaskException();
        }

        int taskNum = Integer.parseInt(message.split(" ")[1]);
        if (taskNum > list.size()) {
            throw new TaskException();
        }

        Task curr = list.get(taskNum - 1);
        curr.markAsDone();
        System.out.println(LINE);
        System.out.println("Nice, I've marked this task as done:");
        System.out.println(curr);
        System.out.println(LINE);
    }

    private static void unmarkTask(String message) throws TaskException {
        if (message.length() <= 5) {
            throw new TaskException();
        }

        int taskNum = Integer.parseInt(message.split(" ")[1]);
        if (taskNum > list.size()) {
            throw new TaskException();
        }

        Task curr = list.get(taskNum - 1);
        curr.markAsUndone();
        System.out.println(LINE);
        System.out.println("Nice, I've marked this task as not done yet:");
        System.out.println(curr);
        System.out.println(LINE);
    }

    private static void addTodo(String str) throws TodoException {
        if (str.isBlank()) {
            throw new TodoException();
        }

        Todo newTodo = new Todo(str);
        list.add(newTodo);
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTodo);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    private static void addDeadline(String str) throws DeadlineException {
        if (str.isBlank()) {
            throw new DeadlineException();
        }
        String desc = str.split(" /by ")[0];
        String by = str.split(" /by ")[1];
        Deadline newDeadline = new Deadline(desc, by);
        list.add(newDeadline);
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(newDeadline);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    private static void addEvent(String str) throws EventException {
        if (str.isBlank()) {
            throw new EventException();
        }
        String desc = str.split(" /from ")[0];
        String from = str.split(" /from ")[1].split(" /to ")[0];
        String to = str.split(" /from ")[1].split(" /to ")[1];
        Event newEvent = new Event(desc, from, to);
        list.add(newEvent);
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(newEvent);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    private static void deleteTask(String str) throws TaskException {
        if (str.length() <= 7) {
            throw new TaskException();
        }

        int taskNum = Integer.parseInt(str.split(" ")[1]);
        if (taskNum > list.size()) {
            throw new TaskException();
        }

        Task toDelete = list.get(taskNum - 1);
        list.remove(taskNum - 1);
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println(toDelete);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println(LINE);
        System.out.println("Hello I'm GBot!");
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        while (true) {
            String message = scanner.nextLine();
            if (message.equals("bye")) {
                System.out.println(LINE);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(LINE);
                return;
            } else if (message.equals("list")) {
                listTasks();
                continue;
            }

            Keyword prefix = Keyword.valueOf(message.split(" ")[0].toUpperCase());
            String str = message.substring(prefix.toString().length() + 1);
            switch (prefix) {
                case MARK:
                    markTask(message);
                    break;
                case UNMARK:
                    unmarkTask(message);
                    break;
                case TODO:
                    addTodo(str);
                    break;
                case DEADLINE:
                    addDeadline(str);
                    break;
                case EVENT:
                    addEvent(str);
                    break;
                case DELETE:
                    deleteTask(message);
                    break;
                default:
                    throw new GBotException();
            }
        }
    }
}

