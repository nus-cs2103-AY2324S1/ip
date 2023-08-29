import java.util.ArrayList;
import java.util.Scanner;
public class Duck {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        TaskList list = new TaskList();
        
        Duck.greet();
        String input = in.nextLine();
        while (!input.equals("bye")) {

            if (input.equals("list")) {
                line();
                list.listTasks();
                line();
                input = in.nextLine();
                continue;
            }

            if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.substring(5));
                line();
                try{
                    list.mark(index - 1);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! There is no task " + index + " in your list.");
                    line();
                    input = in.nextLine();
                    continue;
                } catch (IllegalArgumentException e) {
                    System.out.println("☹ OOPS!!! Task " + index + " is already marked.");
                    line();
                    input = in.nextLine();
                    continue;
                }
                line();
                input = in.nextLine();
                continue;
            }

            if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.substring(7));
                line();
                
                try {
                    list.unmark(index - 1);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! There is no task " + index + " in your list.");
                    line();
                    input = in.nextLine();
                    continue;
                } catch (IllegalArgumentException e) {
                    System.out.println("☹ OOPS!!! Task " + index + " is already unmarked.");
                    line();
                    input = in.nextLine();
                    continue;
                }
                line();
                input = in.nextLine();
                continue;
            }

            if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                Task newTask;
                try {
                    newTask = list.addTask(input);
                } catch (StringIndexOutOfBoundsException e ) {
                    line();
                    System.out.println("☹ OOPS!!! The description of a " + input + " cannot be empty.");
                    line();
                    input = in.nextLine();
                    continue;
                } 
                line();
                System.out.println("Got it. I've added this task:");
                System.out.println(newTask);
                System.out.println("Now you have " + list.getListSize() + " tasks in the list.");
                line();
                input = in.nextLine();
                continue;
            }

            if (input.startsWith("delete")) {
                int index = Integer.parseInt(input.substring(7));
                line();
                list.delete(index - 1);
                line();
                input = in.nextLine();
                continue;
            }
            line();
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            line();
            input = in.nextLine();
        }
        Duck.bye();
        in.close();
    }

    private static void line() {
        System.out.println("____________________________________________________________");
    }

    private static void greet() {
        String greeting = "Hello! I'm Duck\n" + "What can I do for you?";

        line();
        System.out.println(greeting);
        line();
    }

    private static void bye() {
        String bye = "Bye. Hope to see you again soon!";

        line();
        System.out.println(bye);
        line();
    }
}

class TaskList {
    private ArrayList<Task> list;
    private int listSize;

    public TaskList() {
        this.list = new ArrayList<Task>();
        this.listSize = 0;
    }

    public int getListSize() {
        return this.listSize;
    }

    public Task addTask(String input) throws IllegalArgumentException, StringIndexOutOfBoundsException {
        Task newTask;
        if (input.startsWith("todo")) {
            newTask = new TodoTask(input);
        } else if (input.startsWith("deadline")) {
            newTask = new DeadlineTask(input);
        } else if (input.startsWith("event")) {
            newTask = new EventTask(input);
        } else {
            throw new IllegalArgumentException();
        }

        list.add(newTask);
        listSize++;
        return newTask;
    }

    public void listTasks() {
        if (listSize == 0) {
            System.out.println("There are no tasks in your list.");
            return;
        }

        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= listSize; i++) {
            System.out.println("" + i + ". " + list.get(i - 1));
        }
    }

    public void mark(int index) throws IndexOutOfBoundsException {
        Task currTask = list.get(index);
        currTask.mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(currTask);
    }

    public void unmark(int index) throws IndexOutOfBoundsException{
        Task currTask = list.get(index);
        currTask.unmark();
        System.out.println("OK, I've unmarked this task:");
        System.out.println(currTask);
    }

    public void delete(int index) {
        Task currTask = list.get(index);
        list.remove(index);
        listSize--;

        System.out.println("Noted. I've removed this task:");
        System.out.println(currTask);
        System.out.println("Now you have " + listSize + " tasks in the list.");
    }
}

abstract class Task {
    private String name;
    private boolean isDone;
    private TaskType type;
    private String info;

    public Task(String name, TaskType type, String info) {
        this.name = name;
        this.isDone = false;
        this.type = type;
        this.info = info;
    }

    public void mark() throws IllegalArgumentException {
        if (this.isDone == true) {
            throw new IllegalArgumentException();
        } else {
            this.isDone = true;
        }
    }

    public void unmark() throws IllegalArgumentException {
        if (this.isDone == false) {
            throw new IllegalArgumentException();
        } else {
            this.isDone = false;
        }
    }

    @Override
    public String toString() {
        char typeChar = this.type.toString().charAt(0);
        char done = this.isDone ? 'X' : ' ';
        String str = "[" + typeChar + "][" + done + "] " + name + info;
        return str;
    }
}

class TodoTask extends Task {
    public TodoTask(String input) throws StringIndexOutOfBoundsException{
        super(input.trim().substring(5), 
                TaskType.Todo, 
                "");
    }
}

class DeadlineTask extends Task {
    public DeadlineTask(String input) throws StringIndexOutOfBoundsException {
        super(input.trim().substring(9, input.indexOf("/by") - 1), 
                TaskType.Deadline, 
                " (by: " + input.substring(input.indexOf("/by") + 4) + ")");
    }
}

class EventTask extends Task {
    public EventTask(String input) throws StringIndexOutOfBoundsException {
        super(input.trim().substring(6, input.indexOf("/from") - 1), 
                TaskType.Event, 
                " (from: " + input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1) + 
                " to: " + input.substring(input.indexOf("/to") + 4) + ")");
    }
}

enum TaskType {
    Todo,
    Deadline,
    Event,
}