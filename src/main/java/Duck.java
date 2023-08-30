import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Duck {
    public static void main(String[] args) {
        
        TaskList list = new TaskList();

        // Create data directory if it doesnt exist
        File dataDir = new File("data");
        if (dataDir.mkdir()) {
            // Directory doesnt exist
            // System.out.println("New data directory is created!");
        } else {
            // Directory already exists
            // System.out.println("Data directory already exists!");
        }

        // Create history file if it doesnt exist
        File historyFile;
        try {
            historyFile = new File("data/duck.txt");
            if (historyFile.createNewFile()) {
                // File doesnt exist
                // System.out.println("new file is created!");
            } else {
                // File already exists
                // System.out.println("file already exists!");
                int taskCount = 0;
                Scanner fileScanner = new Scanner(historyFile);
                while (fileScanner.hasNextLine()) {
                    list.addTask(Task.parse(fileScanner.nextLine()));
                    taskCount++;
                }
                System.out.println("Loaded " + taskCount + " tasks from history.");
                fileScanner.close();
            }
        } catch (IOException e) {
            System.out.println("Error with task storage file.");
        }

        Scanner in = new Scanner(System.in);
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

    public void addTask (Task task) {
        list.add(task);
        listSize++;
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

        try {
            FileWriter writer = new FileWriter("data/duck.txt", true);
            writer.write(newTask.stringify() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error - unable to access history file.");
        }

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

        ArrayList<String> history = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(new File("data/duck.txt"));
            while (fileScanner.hasNextLine()) {
                if (history.size() == index) {
                    history.add(currTask.stringify());
                    fileScanner.nextLine();
                    continue;
                }
                history.add(fileScanner.nextLine());
            }

            FileWriter writer = new FileWriter("data/duck.txt");
            while (history.size() > 0) {
                writer.write(history.remove(0) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error - unable to access history file");
        }

        System.out.println("Nice! I've marked this task as done:");
        System.out.println(currTask);
    }

    public void unmark(int index) throws IndexOutOfBoundsException{
        Task currTask = list.get(index);
        currTask.unmark();

        ArrayList<String> history = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(new File("data/duck.txt"));
            while (fileScanner.hasNextLine()) {
                if (history.size() == index) {
                    history.add(currTask.stringify());
                    fileScanner.nextLine();
                    continue;
                }
                history.add(fileScanner.nextLine());
            }

            FileWriter writer = new FileWriter("data/duck.txt");
            while (history.size() > 0) {
                writer.write(history.remove(0) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error - unable to access history file");
        }

        System.out.println("OK, I've unmarked this task:");
        System.out.println(currTask);
    }

    public void delete(int index) {
        Task currTask = list.get(index);
        list.remove(index);
        listSize--;

        ArrayList<String> history = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(new File("data/duck.txt"));
            int lineCounter = 0;
            while (fileScanner.hasNextLine()) {
                if (lineCounter == index) {
                    System.out.println("removed: " + fileScanner.nextLine());
                    lineCounter++;
                    continue;
                }
                history.add(fileScanner.nextLine());
            }

            FileWriter writer = new FileWriter("data/duck.txt");
            while (history.size() > 0) {
                writer.write(history.remove(0) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error - unable to access history file");
        }

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

    public Task(String name, boolean isDone, TaskType type, String info) {
        this.name = name;
        this.isDone = isDone;
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

    public String stringify() {
        String typeChar = this.type.toString().substring(0,1);
        String done = String.valueOf(this.isDone ? '1' : '0');
        String nameLength = "/" + String.valueOf(this.name.length()) + "/";
        String infoLength = "/" + String.valueOf(this.info.length()) + "/";
        // System.out.println(typeChar + done + nameLength + this.name + infoLength + this.info);
        return typeChar + done + nameLength + this.name + infoLength + this.info;
    }

    public static Task parse(String taskString) {
        char typeChar = taskString.charAt(0);
        char doneChar = taskString.charAt(1);

        int index_1 = 2; // index of first "/"
        int index_2 = taskString.indexOf("/", index_1 + 1); // index of second "/"
        int nameLength = Integer.parseInt(taskString.substring(index_1 + 1, index_2));

        int index_3 = index_2 + nameLength + 1; // index of third "/"
        int index_4 = taskString.indexOf("/", index_3 + 1); // index of fourth "/"
        int infoLength = Integer.parseInt(taskString.substring(index_3 + 1, index_4));

        String nameString = taskString.substring(index_2 + 1, index_3); 
        String infoString = taskString.substring(index_4 + 1, index_4 + infoLength + 1);

        boolean done = doneChar == '1' ? true : false;
        String name = nameString;
        String info = infoString;

        Task task;
        if (typeChar == 'T') {
            task = new TodoTask(name, done, info);
        } else if (typeChar == 'D') {
            task = new DeadlineTask(name, done, info);
        } else if (typeChar == 'E') {
            task = new EventTask(name, done, info);
        } else {
            throw new IllegalArgumentException(); // Replace with custom error later
        }
        return task;
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
                false,
                TaskType.Todo, 
                "");
    }

    public TodoTask(String name, boolean isDone, String info) {
        super(name, isDone, TaskType.Todo, "");
    }
}

class DeadlineTask extends Task {
    public DeadlineTask(String input) throws StringIndexOutOfBoundsException {
        super(input.trim().substring(9, input.indexOf("/by") - 1), 
                false,
                TaskType.Deadline, 
                " (by: " + input.substring(input.indexOf("/by") + 4) + ")");
    }

    public DeadlineTask(String name, boolean isDone, String info) {
        super(name,  isDone, TaskType.Deadline, info);
    }
}

class EventTask extends Task {
    public EventTask(String input) throws StringIndexOutOfBoundsException {
        super(input.trim().substring(6, input.indexOf("/from") - 1), 
                false,
                TaskType.Event, 
                " (from: " + input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1) + 
                " to: " + input.substring(input.indexOf("/to") + 4) + ")");
    }

    public EventTask(String name, boolean isDone, String info) {
        super(name,  isDone, TaskType.Event, info);
    }
}

enum TaskType {
    Todo,
    Deadline,
    Event,
}