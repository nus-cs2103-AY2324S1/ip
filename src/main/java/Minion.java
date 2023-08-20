import java.util.*;

/**
 * Represents the Minion chatbot.
 */
public class Minion {
    public static String line = "\t____________________________________________________________\n";
    public static Task[] tasks = new Task[100];
    public static int taskCount = 0;
    public static void main(String[] args) {
        sayHi();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                sayBye();
                break;
            } else if (command.equals("list")){
                listTasks();
            } else if (command.startsWith("mark")) {
                Task currTask = getTask(command);
                currTask.markDone();
                prettyPrint(Arrays.asList(
                        "Nice! I've marked this task as done:",
                        "\t" + currTask.toString()
                ));
            } else if (command.startsWith("unmark")){
                Task currTask = getTask(command);
                currTask.markUndone();
                prettyPrint(Arrays.asList(
                            "OK, I've marked this task as not done yet:",
                            "\t" + currTask.toString()
                ));
            } else {
                Task task = null;
                try {
                    task = parseCommand(command);
                } catch (MinionException e) {
                    prettyPrint(e.getMessage());
                }
                if (task == null) {
                    continue;
                }
                tasks[taskCount++] = task;
                prettyPrint(Arrays.asList(
                        "Got it. I've added this task:",
                        "\t" + task.toString(),
                        "Now you have " + taskCount +  " tasks in the list."
                    )
                );
            }
        }
        sc.close();
    }

    /**
     * Lists current tasks.
     */
    public static void listTasks() {
        List<String> lst = new ArrayList<>();
        lst.add("Here are the tasks in your list:");
        for(int i = 0; i < taskCount; i++) {
            lst.add((i + 1) + "." + tasks[i].toString());
        }
        prettyPrint(lst);
    }

    /**
     * Returns a task from the parsed command, throws an exception if there is no such task or invalid parameters.
     * @param command Command to parse.
     * @return the task parsed from the command if no exception is thrown.
     * @throws MinionException
     */
    public static Task parseCommand(String command) throws MinionException {
        command = command.stripLeading();
        if (command.isEmpty()){
            throw new MinionException("☹ OOPS!!! I'm sorry, please input a legit command. :-(");
        }
        String[] arr = command.split(" ", 2);
        String firstWord = arr[0];
        if (firstWord.equals("todo")) {
            if(arr.length < 2 || arr[1].trim().isEmpty()) {
                throw new MinionException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            return new ToDo(arr[1]);
        }
        //assumes there's only one /by in the command
        if (firstWord.equals("deadline")){
            // nothing after deadline
            if (arr.length < 2) {
                throw new MinionException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            // something after deadline but it's just empty space(s)
            // empty -> no description; non-empty -> still need to check if description is missing.
            if (arr[1].trim().isEmpty()) {
                throw new MinionException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            String[] strs = arr[1].split("/by");
            String description = null;
            switch (strs.length){
                // nothing to left and right
                case 0:
                    throw new MinionException("☹ OOPS!!! The description of a deadline cannot be empty.");
                //something to left, nothing to the right
                case 1:
                    description = strs[0].trim();
                    if (description.isEmpty()) {
                        throw new MinionException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    throw new MinionException("☹ OOPS!!! The date of a deadline cannot be empty.");
                case 2:
                    description = strs[0].trim();
                    String by = strs[1].trim();
                    if (description.isEmpty()) {
                        throw new MinionException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    if (by.isEmpty()) {
                        throw new MinionException("☹ OOPS!!! The date of a deadline cannot be empty.");
                    }
                    return new Deadline(description, by);
            }
        }
        //assumes we only have one '/from' and one '/to'
        if (firstWord.equals("event")){
            if(arr.length < 2) {
                throw new MinionException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            if (arr[1].trim().isEmpty()) {
                throw new MinionException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            String[] strs = arr[1].split("/from");

            String description = null;

            switch (strs.length) {
                // nothing to left and right
                case 0:
                    throw new MinionException("☹ OOPS!!! The description of an event cannot be empty.");
                    //something to left, nothing to the right
                case 1:
                    description = strs[0].trim();
                    if (description.isEmpty()) {
                        throw new MinionException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    throw new MinionException("☹ OOPS!!! The from and to dates of an event cannot be empty.");
                case 2:
                    description = strs[0].trim();
                    String dates = strs[1].trim();
                    if (description.isEmpty()) {
                        throw new MinionException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    if (dates.isEmpty()) {
                        throw new MinionException("☹ OOPS!!! The from and to dates of a deadline cannot be empty.");
                    }
                    strs = strs[1].split("/to");
            }

            String from = null;
            String to = null;
            switch (strs.length) {
                // nothing to left and right
                case 0:
                    throw new MinionException("☹ OOPS!!! The from date of an event cannot be empty.");
                case 1:
                    from = strs[0].trim();
                    if (from.isEmpty()) {
                        throw new MinionException("☹ OOPS!!! The from date of an event cannot be empty.");
                    }
                    throw new MinionException("☹ OOPS!!! The to date of an event cannot be empty.");
                case 2:
                    from = strs[0].trim();
                    to = strs[1].trim();
                    if (from.isEmpty()) {
                        throw new MinionException("☹ OOPS!!! The from date of an event cannot be empty.");
                    }
                    if (to.isEmpty()) {
                        throw new MinionException("☹ OOPS!!! The to date of an event cannot be empty.");
                    }
                    return new Event(description, from, to);
            }
        }
        throw new MinionException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Retrieves a Task object based on the user's command.
     * @param command Command given by the user.
     * @return the Task corresponding to the index requested by the user.
     */
    public static Task getTask(String command) {
        int taskIdx = Integer.valueOf(command.split(" ")[1]);
        return tasks[taskIdx - 1];
    }

    /**
     * Function to say hi to the user.
     */
    public static void sayHi(){
        prettyPrint(Arrays.asList("Hello! I'm Minion!", "What can I do for you?"));
    }

    /**
     * Function to say bye to the user.
     */
    public static void sayBye(){
        prettyPrint("Bye. Hope to see you again soon!");
    }

    /**
     * Pretty prints a single string.
     * @param s String to be printed.
     */
    public static void prettyPrint(String s){
        System.out.println(line + String.format("\t%s\n", s) + line);
    }

    /**
     * Pretty prints a list of strings.
     * @param lst List of strings to be printed.
     */
    public static void prettyPrint(List<String> lst){
        String text = line;
        for(String s: lst) {
            text += String.format("\t%s\n", s);
        }
        text += line;
        System.out.println(text);
    }
}
