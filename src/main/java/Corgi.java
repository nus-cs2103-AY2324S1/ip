import java.util.List;
import java.util.Scanner;

import tasks.Task;
import tasks.TaskStatusException;
import tasks.ToDo;
import tasks.Deadline;
import tasks.Event;

import commands.CommandType;
import commands.EmptyDescException;
import commands.InvalidCommandException;
import commands.InvalidDescFormatException;

import parsers.TaskParser;

import storage.Storage;

public class Corgi {
    private List<Task> tasks;
    private Storage<Task> storage;

    public static void main(String[] args) {
        Corgi bot = new Corgi();
        bot.start();
    }

    /**
     * Constructs new Corgi chatbot with an empty task list.
     */
    public Corgi() {
        this.storage = new Storage<>(new TaskParser(), "./data/tasks.txt");
        this.tasks = storage.load();

        if (tasks.size() > 0) {
            System.out.println("Successfully loaded " + tasks.size() + " tasks!");
        }
    }

    /**
     * Starts the chatbot - Corgi.
     */
    public void start() {
        String logo = "  ____ ___  ____   ____ ___\n"
                + " / ___/ _ \\|  _ \\ / ___|_ _|\n"
                + "| |  | | | | |_) | |  _ | |\n"
                + "| |__| |_| |  _ <| |_| || |\n"
                + " \\____\\___/|_| \\_\\\\____|___|\n";
        System.out.println(logo);
        System.out.println("Woof! I'm Corgi!");
        System.out.println("So, what's your wish this time, hooman?\n");

        Scanner sc = new Scanner(System.in);

        while(true) {
            String userInput = sc.nextLine().trim();

            if (userInput.equals("")) {
                continue;
            }

            System.out.println("---------------------------------------------------------------------");

            String[] inputParts = userInput.split(" ", 2);
            String cmdStr = inputParts[0];

            CommandType cmd = null;

            try {
                cmd = CommandType.getCommandType(cmdStr);
            } catch (InvalidCommandException e) {
                this.printException("Can't believe you're asking that! Grrr, what do you want now?");
            }

            boolean breakLoop = false;

            if (cmd != null) {
                breakLoop = this.executeCommand(cmd, inputParts);
            }

            System.out.println("---------------------------------------------------------------------\n");

            if (breakLoop) break;
        }

        sc.close();
    }

    /**
     * Executes a command based on the provided CommandType and input arguments.
     *
     * @param cmd The CommandType representing the command to execute
     * @param inputs The array of input arguments for the command
     * @return True if the command execution should exit the chatbot, false otherwise
     */
    private boolean executeCommand(CommandType cmd, String[] inputs) {

        try {
            switch (cmd) {
                case BYE:
                    if (inputs.length > 1) throw new InvalidCommandException();
                    System.out.println("Fine! Whatever! Just go away then! See if I care! huffs");
                    return true;
                case LIST:
                    if (inputs.length > 1) throw new InvalidCommandException();
                    this.displayTasks();
                    break;
                case MARK:
                    if (inputs.length < 2) throw new EmptyDescException();
                    this.markTaskAsDone(inputs[1]);
                    break;
                case UNMARK:
                    if (inputs.length < 2) throw new EmptyDescException();
                    this.markTaskAsNotDone(inputs[1]);
                    break;
                case TODO:
                    if (inputs.length < 2) throw new EmptyDescException();
                    this.addToDo(inputs[1]);
                    break;
                case DEADLINE:
                    if (inputs.length < 2) throw new EmptyDescException();
                    this.addDeadline(inputs[1]);
                    break;
                case EVENT:
                    if (inputs.length < 2) throw new EmptyDescException();
                    this.addEvent(inputs[1]);
                    break;
                case DELETE:
                    if (inputs.length < 2) throw new EmptyDescException();
                    this.deleteTask(inputs[1]);
                    break;
            }
        } catch (InvalidCommandException e) {
            this.printException("Can't believe you're asking that! Grrr, what do you want now?");
        } catch (EmptyDescException e) {
            this.printException("Seriously? You want me to do something with an empty description?");
        } catch (InvalidDescFormatException e) {
            this.printException("Are you trying to confuse me with this nonsense? Try again hooman!" + "\n" 
                + "Format: < " + cmd.getCommandFormat() + " >");
        } catch (Exception e) {
            this.printException("Oh wonderful, you've broken something. And guess what? I have absolutely no idea what happened either."
                + "\n\n❗An error of type " + e.getClass().getSimpleName() + " occurred: " + e.getMessage());
        }

        return false;
    }

    /**
    * Prints an corgi-themed error message.
    *
    * @param msg The error message to display.
    */
    private void printException(String msg) {
        System.out.println("Woof?! 🤬 \n" + msg);
    }

    /**
     * Mark task as done.
     * 
     * @param indexStr Target task index.
     */
    private void markTaskAsDone(String indexStr) {
        try {
            int index = Integer.parseInt(indexStr) - 1;
            if (index >= 0 && index < this.tasks.size()) {
                Task target = tasks.get(index);
                target.markAsDone();
                this.storage.save(tasks);
                System.out.println("Congratulations, I guess! You finally managed to do something right 🎉:\n" + "\n " + target + "\n");
            } else {
                System.out.println("Arf! Invalid task number? Seriously, can't you count? 💢");
            }
        } catch (NumberFormatException e) {
            this.printException("Arf! You're trying to trick me with words instead of numbers?");
        } catch (TaskStatusException e) {
            this.printException("This task is already marked as done. What are you trying to do? 🤔");
        }
    }

    /**
     * Mark task as not done.
     * 
     * @param indexStr Target task index.
     */
    private void markTaskAsNotDone(String indexStr) {
        try {
            int index = Integer.parseInt(indexStr) - 1;
            if (index >= 0 && index < this.tasks.size()) {
                Task target = tasks.get(index);
                target.markAsNotDone();
                this.storage.save(tasks);
                System.out.println("Oh great, you've undone something 🐕. Just like always:\n" + "\n " + target + "\n");
            } else {
                System.out.println("Arf! Invalid task number? Seriously, can't you count? 💢");
            }
        } catch (NumberFormatException e) {
            this.printException("Arf! You're trying to trick me with words instead of numbers?");
        } catch (TaskStatusException e) {
            this.printException("This task isn't even marked as done yet. What are you trying to do? 🤔");
        }
    }

    /**
     * Display the list of tasks.
     */
    private void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("If you haven't noticed, there's nothing here! No tasks to be found.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i+1) + ") " + tasks.get(i));
            }
        }
    }

    /**
     * Remove task from list.
     * 
     * @param indexStr Target task index.
     */
    private void deleteTask(String indexStr) {
        try {
            int index = Integer.parseInt(indexStr) - 1;
            if (index >= 0 && index < this.tasks.size()) {
                Task target = this.tasks.get(index);
                this.tasks.remove(index);
                this.storage.save(tasks);
                System.out.println("Finally got rid of that task. Took you long enough... uninterested woof\n" 
                    + "\n " + target + "\n\nNow you have " + this.tasks.size() + " tasks in the list.🐾");
            } else {
                System.out.println("Arf! Invalid task number? Seriously, can't you count? 💢");
            }
        } catch (NumberFormatException e) {
            this.printException("Arf! You're trying to trick me with words instead of numbers?");
        } 
    }

     /**
      * Add a new ToDo to the list of tasks based on the provided task type.
      *
      * @param taskInfo Information about the todo
      */
    private void addToDo(String taskInfo) {
        Task newTask = new ToDo(taskInfo);

        this.tasks.add(newTask);

        this.storage.save(tasks);

        System.out.println("Woof, whatever. I've added this ToDo:\n" + 
            "\n " + newTask + "\n\nNow you have " + this.tasks.size() + " tasks in the list.🐾");
    }

    /**
      * Add a new Deadline to the list of tasks based on the provided task type.
      *
      * @param taskInfo Information about the deadline, including description and date/time details
      */
    private void addDeadline(String taskInfo) throws InvalidDescFormatException{
        String[] deadlineInfos = taskInfo.split(" /by ");

        if (deadlineInfos.length < 2) throw new InvalidDescFormatException();

        String deadlineDesc = deadlineInfos[0];
        String by = deadlineInfos[1];

        Task newTask = new Deadline(deadlineDesc, by);

        this.tasks.add(newTask);

        this.storage.save(tasks);

        System.out.println("Woof, whatever. I've added this deadline:\n" + 
            "\n " + newTask + "\n\nNow you have " + this.tasks.size() + " tasks in the list.🐾");
    }

    /**
      * Add a new Event to the list of tasks based on the provided task type.
      *
      * @param taskInfo Information about the event, including description and date/time details
      */
    private void addEvent(String taskInfo) throws InvalidDescFormatException{
        String[] eventInfos = taskInfo.split(" /from ");

        if (eventInfos.length < 2) throw new InvalidDescFormatException();

        String eventDesc = eventInfos[0];
        String[] eventDuration = eventInfos[1].split(" /to ");

        if (eventDuration.length < 2) throw new InvalidDescFormatException();

        String from = eventDuration[0];
        String to = eventDuration[1];

        Task newTask = new Event(eventDesc, from, to);

        this.tasks.add(newTask);

        this.storage.save(tasks);

        System.out.println("Woof, whatever. I've added this event:\n" + 
            "\n " + newTask + "\n\nNow you have " + this.tasks.size() + " tasks in the list.🐾");
    }
}
