import exceptions.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>(0);
    }

    public TaskList(String content) {
        this.tasks = new ArrayList<>(0);

        addTextToTask(content);
    }

    public void addTextToTask(String content) {
        String[] lines = content.split("\n");

        for (String line: lines) {
            String[] parts = line.split(" ", 3);
            if (parts[0].equals("T")) {

                ToDo task = new ToDo(parts[2]);
                this.tasks.add(task);

            } else if (parts[0].equals("D")) {

                String[] arr = parts[2].split("/");
                Deadline task = new Deadline(arr[0], arr[1]);
                this.tasks.add(task);

            } else {

                String[] arr = parts[2].split("/");
                Event task = new Event(arr[0], arr[1], arr[2]);
                this.tasks.add(task);

            }

        }


    }



    public void inputHandler(String input) {
        try {
            if (input.equals("list")) {
                listTasks();
            } else if (input.startsWith("mark")) {
                markTaskDone(input);
            } else if (input.startsWith("unmark")) {
                unmarkTaskDone(input);
            } else if (input.startsWith("delete")) {
                deleteTask(input);
            } else {
                addTask(input);
            }
        } catch (DukeException e) {
            System.out.println(e);
            System.out.println("Please try again .₊̣̇.ಇ/ᐠˬ ͜   ˬ ᐟ\\∫.₊̣̇.");
        }

    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list:\n");

        if (tasks.size() == 0) {
            System.out.println("There's nothing in your list /ᐠ｡ꞈ｡ᐟ\\");
        }

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i) + "\n");
        }
    }



    public void markTaskDone(String input) throws DukeException {

        // retrieve the index input
        int index = input.indexOf(" ");
        String numStr = input.substring(index + 1);
        int number = Integer.parseInt(numStr);

        if (number < 0 || number > tasks.size()) {
            throw new DukeException("No such task exists (index out of bound error)");
        }

        tasks.get(number - 1).setIsDone();

        System.out.println("Yay! You have completed this task:\n" +
                tasks.get(number - 1) + "\n");
    }

    public void unmarkTaskDone(String input) throws DukeException {
        // retrieve the index input
        int index = input.indexOf(" ");
        String numStr = input.substring(index + 1);
        int number = Integer.parseInt(numStr);

        if (number < 0 || number > tasks.size()) {
            throw new DukeException("No such task exists (index out of bound error)");
        }

        tasks.get(number - 1).setIsNotDone();

        System.out.println("Ok... Guess you're not actually done with this:\n" +
                tasks.get(number - 1) + "\n");
    }

    public void deleteTask(String input) throws DukeException {

        int index = input.indexOf(" ");
        String numStr = input.substring(index + 1);
        int number = Integer.parseInt(numStr);

        if (number < 0 || number > tasks.size()) {
            throw new DukeException("No such task exists (index out of bound error)");
        }

        Task removedTask = tasks.get(number - 1);
        tasks.remove(number - 1);

        System.out.println("banished this task to the shadow realm:\n" + removedTask);
    }

    public void addTask(String input) throws DukeException {
        String[] parts = input.split(" ", 2);

        if (parts[0].equals("todo")) {

            ToDo todo = new ToDo(parts[1]);
            this.tasks.add(todo);

            System.out.println("added new task:\n" + todo);
            System.out.println("you now have " + tasks.size() + " tasks in your list." + "\n");

        } else if (parts[0].equals("deadline")) {

            String[] arr = parts[1].split("/");
            Deadline deadline = new Deadline(arr[0], arr[1]);
            this.tasks.add(deadline);

            System.out.println("added new task:\n" + deadline);
            System.out.println("you now have " + tasks.size() + " tasks in your list." + "\n");

        } else if (parts[0].equals("event")) {

            String[] arr = parts[1].split("/");
            Event event = new Event(arr[0], arr[1], arr[2]);
            this.tasks.add(event);

            System.out.println("added new task:\n" + event);
            System.out.println("you now have " + tasks.size() + " tasks in your list." + "\n");

        } else {
            throw new DukeException("Not a valid task!");
        }
    }
}
