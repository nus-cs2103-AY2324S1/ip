import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

public class Ruiz {
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * This method prints out the list of tasks currently
     */
    public void getTasks() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i + 1) + ". " + this.tasks.get(i).toString());
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * This method marks the given task in the input.
     *
     * @param input The string that consists of the keyWord "mark" and task index being input by the user.
     * @throws BotException if the input is not a valid one.
     */
    public void markTask(String input) throws BotException {
        if (input.split(" ").length < 2) {
            throw new BotException("OOPS!!! The index of a task cannot be empty.");
        }
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (NumberFormatException e) {
            throw new BotException("OOPS!!! The index of a task has to be an integer.");
        }
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        if (taskIndex >= 0 && this.tasks.size() > taskIndex) {
            Task task = this.tasks.get(taskIndex);
            task.mark();
            System.out.println("____________________________________________________________\n" +
                    "Nice! I've marked this task as done\n" +
                    task +
                    "\n" +
                    "____________________________________________________________");
        } else {
            throw new BotException("This task does not exist!");
        }
    }

    /**
     * This method unmarks the specified task in the string input.
     *
     * @param input The string that consists of the keyWord "unmark" and task index being input by the user.
     * @throws BotException if the input is not a valid one.
     */
    public void unmarkTask(String input) throws BotException {
        if (input.split(" ").length < 2) {
            throw new BotException("OOPS!!! The index of a task cannot be empty.");
        }
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (NumberFormatException e) {
            throw new BotException("OOPS!!! The index of a task has to be an integer.");
        }
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        if (taskIndex >= 0 && this.tasks.size() > taskIndex) {
            Task task = this.tasks.get(taskIndex);
            task.unmark();
            System.out.println("____________________________________________________________\n" +
                    "OK, I've marked this task as not done yet\n" +
                    task +
                    "\n" +
                    "____________________________________________________________\n");
        } else {
            throw new BotException("This task does not exist!");
        }
    }

    /**
     * This method deletes the specified task from its index from the list of tasks.
     *
     * @param input The string that contains the keyWord "delete" and the index of the task.
     * @throws BotException if the input of the user is not a valid one.
     */
    public void deleteTask(String input) throws BotException {
        if (input.split(" ").length < 2) {
            throw new BotException("OOPS!!! The index of a task cannot be empty.");
        }
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (NumberFormatException e) {
            throw new BotException("OOPS!!! The index of a task has to be an integer.");
        }
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        if (taskIndex >= 0 && this.tasks.size() > taskIndex) {
            Task task = this.tasks.get(taskIndex);
            System.out.println("____________________________________________________________\n" +
                    "Noted. I've removed this task:\n" + task);
            tasks.remove(taskIndex);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.\n" +
                    "____________________________________________________________");
        } else {
            throw new BotException("This task does not exist!");
        }
    }

    /**
     * This method creates a todo and adds it to the list of tasks.
     *
     * @param input contains the keyWord "todo" and the description of the todo.
     * @throws BotException if the input is not in the format of a valid todo.
     */
    public void addTodo(String input) throws BotException {
        if (input.split(" ", 2).length <= 1) {
            throw new BotException("OOPS!!! The description of a todo cannot be empty.");
        }
        String content = input.split(" ", 2)[1];
        Task temp = new ToDos(content);
        this.tasks.add(temp);
        System.out.println("____________________________________________________________\n" +
                "Got it. I've added this task:\n" +
                temp +
                "\nNow you have " + tasks.size() + " tasks in the list.\n" +
                "____________________________________________________________");
    }

    /**
     * This method creates a deadline and adds it to the list of tasks.
     *
     * @param input contains the keyWord "deadline" and the description of the deadline
     *              with the time it needs to be completed by.
     * @throws BotException if the input is not in the valid format of a deadline.
     */
    public void addDeadline(String input) throws BotException, IOException {
        if (input.split("/by", 2).length <= 1) {
            throw new BotException("OOPS!!! The description the deadline is incomplete");
        }
        String content = input.split(" ", 2)[1];
        String description = content.split(" /by ")[0];
        String by = content.split("/by ")[1];
        Task temp = new Deadlines(description, by);
        this.tasks.add(temp);
        System.out.println("____________________________________________________________\n" +
                "Got it. I've added this task:\n" +
                temp +
                "\nNow you have " + tasks.size() + " tasks in the list.\n" +
                "____________________________________________________________"
        );
    }

    /**
     * This method creates an event and adds it to the list of tasks.
     *
     * @param input contains the keyWord "event" and the description of the event
     *              with the time it takes place from and ends by.
     * @throws BotException if the input is not in the form a valid event.
     */
    public void addEvent(String input) throws BotException, IOException {
        if (input.split("/").length <= 2) {
            throw new BotException("OOPS!!! The description the event is incomplete.");
        }
        String content = input.split(" ", 2)[1];
        String description = content.split(" /from")[0];
        String from = content.split(" /from ")[1].split(" /")[0];
        String to = content.split(" /from ")[1].split("/to ")[1];
        Task temp = new Events(description, from, to);
        this.tasks.add(temp);
        System.out.println("____________________________________________________________\n" +
                "Got it. I've added this task:\n" +
                temp +
                "\nNow you have " + tasks.size() + " tasks in the list.\n" +
                "____________________________________________________________");
    }

    /**
     * This method prints the greeting message of the bot.
     */
    public void greet() {
        String greet = "____________________________________________________________\n" +
                " Hello! I'm Ruiz\n" +
                " What can I do for you?\n" +
                "____________________________________________________________";
        System.out.println(greet);
    }

    /**
     * This method prints the goodbye message of the bot.
     */
    public void bye() {
        String bye = "____________________________________________________________\n" +
                "Bye! Comeback soon!\n" +
                "____________________________________________________________";
        System.out.println(bye);
    }

    public static void main(String[] args) {
        Scanner inputObj = new Scanner(System.in);
        Ruiz bot = new Ruiz();
        Storage fe = new Storage();
        try {
            Ruiz.tasks = fe.loadTasks();
        } catch (FileNotFoundException e) {
            System.out.println("____________________________________________________________\n" +
                    "File cannot be loaded\n" +
                    "____________________________________________________________\n");
        }
        bot.greet();
        while (true) {
            try {
                String input = inputObj.nextLine();
                String keyWord = input.split(" ")[0];
                switch (keyWord) {
                case "bye":
                    bot.bye();
                    return;
                case "list":
                    bot.getTasks();
                    break;
                case "mark":
                    bot.markTask(input);
                    break;
                case "unmark":
                    bot.unmarkTask(input);
                    break;
                case "delete":
                    bot.deleteTask(input);
                    break;
                case "deadline":
                    bot.addDeadline(input);
                    break;
                case "todo":
                    bot.addTodo(input);
                    break;
                case "event":
                    bot.addEvent(input);
                    break;
                default:
                    throw new BotException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                fe.saveTasks(Ruiz.tasks);
            } catch (BotException e) {
                System.out.println(e);
            } catch (IOException e) {
                System.out.println("____________________________________________________________\n" +
                        "Unable to save task" +
                        "____________________________________________________________");
            } catch (DateTimeParseException e) {
                System.out.println("____________________________________________________________\n" +
                        "Please input your date and time in the correct format: yyyy-MM-dd HHmm\n" +
                        "____________________________________________________________");
            }
        }
    }
}
