import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String DEFAULT_FILEPATH = "./data/duke.txt";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage(DEFAULT_FILEPATH);
        try {
            ArrayList<Task> taskList = storage.createList();
            helloGreeting();
            while (true) {
                String command = sc.nextLine();
                try {
                    if (command.equals("bye")) {
                        byeGreeting();
                        break;
                    } else if (command.equals("list")) {
                        printList(taskList);
                    } else if (command.startsWith("mark")) {
                        int index = Integer.parseInt(command.split(" ")[1]);
                        mark(taskList, index);
                        storage.writeFile(taskList);
                    } else if (command.startsWith("unmark")) {
                        int index = Integer.parseInt(command.split(" ")[1]);
                        unmark(taskList, index);
                        storage.writeFile(taskList);
                    } else if (command.startsWith("delete")) {
                        int index = Integer.parseInt(command.split(" ")[1]);
                        delete(taskList, index);
                        storage.writeFile(taskList);
                    } else if (command.startsWith("deadline")) {
                        String[] temp = command.replace("deadline ", "").split("/by");
                        Deadline deadline = new Deadline(temp[0].strip(), temp[1].strip());
                        addTask(deadline, taskList);
                        storage.appendFile(deadline);
                    } else if (command.startsWith("event")) {
                        String[] tempDesc = command.replace("event ", "").split("/from");
                        String[] tempFromTo = tempDesc[1].strip().split("/to");
                        Event event = new Event(tempDesc[0].strip(), tempFromTo[0].strip(), tempFromTo[1].strip());
                        addTask(event, taskList);
                        storage.appendFile(event);
                    } else if (command.startsWith("todo")) {
                        if (command.trim().equals("todo")) {
                            throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        ToDo toDo = new ToDo(command.replace("todo ", ""));
                        addTask(toDo, taskList);
                        storage.appendFile(toDo);
                    } else {
                        throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } catch (DukeException e) {
                    System.out.print(output(e.toString()));
                }
            }
        } catch (IOException e) {
            System.out.print(output(e + "\n"));
        }
    }

    public static String output(String msg) {
        String line = "----------------------------(≧▽≦)----------------------------";
        return String.format("%s\n%s%s\n",
                line, msg, line);
    }

    public static void helloGreeting() {
        System.out.print(output("Hello! I'm ForsakenX\nWhat can I do for you?\n"));
    }

    public static void byeGreeting() {
        System.out.print(output("Bye. Hope to see you again soon!\n"));
    }

    public static void printList(ArrayList<Task> taskList) {
        StringBuilder msg = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 1; i <= taskList.size(); i++) {
            msg.append(String.format("%d.%s\n", i, taskList.get(i - 1)));
        }
        System.out.print(output(msg.toString()));
    }

    public static void addTask(Task task, ArrayList<Task> taskList) {
        taskList.add(task);
        System.out.print(
                output(String.format("Got it. I've added this task:" +
                        "\n\t%s\nNow you have %d tasks in the list.\n", task, taskList.size())));
    }

    public static void mark(ArrayList<Task> taskList, int index) throws DukeException {
        if (index > taskList.size() || index <= 0) {
            throw new DukeException(String.format(" ☹ OOPS!!! Task %d does not exist.", index));
        }
        Task task = taskList.get(index - 1);
        task.markIsDone();
        taskList.set(index - 1, task);
        System.out.print(output(
                String.format("Nice! I've marked this task as done:\n\t%s\n", task)));
    }

    public static void unmark(ArrayList<Task> taskList, int index) throws DukeException {
        if (index > taskList.size() || index <= 0) {
            throw new DukeException(String.format(" ☹ OOPS!!! Task %d does not exist.", index));
        }
        Task task = taskList.get(index - 1);
        task.markNotDone();
        taskList.set(index - 1, task);
        System.out.print(output(
                String.format("OK, I've marked this task as not done yet:\n\t%s\n", task)));
    }

    public static void delete(ArrayList<Task> taskList, int index) throws DukeException {
        if (index > taskList.size() || index <= 0) {
            throw new DukeException(String.format(" ☹ OOPS!!! Task %d does not exist.", index));
        }
        Task removedTask = taskList.remove(index - 1);
        System.out.print(output(String.format("Noted. I've removed this task:" +
                "\n\t%s\nNow you have %d tasks in the list.\n", removedTask, taskList.size())));
    }
}
