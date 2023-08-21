import java.util.*;

/**
 * Represents the Minion chatbot.
 */
public class Minion {
    private static List<Task> tasks = new ArrayList<>();

    private Ui ui;

    public static void main(String[] args) {
        new Minion().run(args);
    }

    /**
     * Driver function.
     * @param args arguments passed in to stdin.
     */
    private void run(String[] args){
        ui = new Ui();
        ui.sayHi();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            if (command.trim().equals("bye")) {
                ui.sayBye();
                break;
            }
            Task task = null;
            try {
                task = parseCommand(command);
            } catch (IllegalValueException | ParserException e) {
                ui.print(e.getMessage());
            }
            if (task == null) {
                continue;
            }
            tasks.add(task);
            ui.print(Arrays.asList(
                            "Got it. I've added this task:",
                            "\t" + task.toString(),
                            "Now you have " + tasks.size() +  " tasks in the list."
                    )
            );
        }
        sc.close();
    }

    /**
     * Lists current tasks.
     */
    private void listTasks() {
        List<String> lst = new ArrayList<>();
        lst.add("Here are the tasks in your list:");
        for(int i = 0; i < tasks.size(); i++) {
            lst.add((i + 1) + "." + tasks.get(i).toString());
        }
        ui.print(lst);
    }

    /**
     * Returns a task from the parsed command, throws an exception if there is no such task or invalid parameters.
     * @param command Command to parse.
     * @return the task parsed from the command if no exception is thrown.
     * @throws ParserException()
     */
    private Task parseCommand(String command) throws ParserException, IllegalValueException {
        command = command.trim();
        if (command.isEmpty()){
            throw new ParserException("☹ OOPS!!! I'm sorry, please input a legit command. :-(");
        }
        String[] arr = command.split(" ", 2);
        String firstWord = arr[0];
        if (firstWord.equals("list")) {
            listTasks();
            return null;
        }
        if (firstWord.equals("mark")){
            int taskIdx = Integer.valueOf(arr[1]) - 1;
            Task currTask = null;
            try {
                currTask = getTask(taskIdx);
            } catch (IllegalValueException e) {
                throw e;
            }
            currTask.markDone();
            ui.print(Arrays.asList(
                    "Nice! I've marked this task as done:",
                    "\t" + currTask.toString()
            ));
            return null;
        }
        if (firstWord.equals("unmark")){
            int taskIdx = Integer.valueOf(arr[1]) - 1;
            Task currTask = null;
            try {
                currTask = getTask(taskIdx);
            } catch (IllegalValueException e) {
                throw e;
            }
            currTask.markUndone();
            ui.print(Arrays.asList(
                    "OK, I've marked this task as not done yet:",
                    "\t" + currTask.toString()
            ));
            return null;
        }
        if (firstWord.equals("delete")) {
            int taskIdx = Integer.valueOf(arr[1]) - 1;
            Task currTask = null;
            try {
                currTask = getTask(taskIdx);
            } catch (IllegalValueException e) {
                throw e;
            }
            tasks.remove(taskIdx);
            ui.print(Arrays.asList(
                    "OK, I've removed this task",
                    "\t" + currTask.toString()
            ));
            return null;
        }
        if (firstWord.equals("todo")) {
            if(arr.length < 2 || arr[1].isEmpty()) {
                throw new ParserException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            return new ToDo(arr[1]);
        }
        //assumes there's only one /by in the command
        if (firstWord.equals("deadline")){
            // nothing after deadline
            if (arr.length < 2) {
                throw new ParserException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            // something after deadline but it's just empty space(s)
            // empty -> no description; non-empty -> still need to check if description is missing.
            if (arr[1].isEmpty()) {
                throw new ParserException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            String[] strs = arr[1].split("/by");
            String description = null;
            switch (strs.length){
                // nothing to left and right
                case 0:
                    throw new ParserException("☹ OOPS!!! The description of a deadline cannot be empty.");
                //something to left, nothing to the right
                case 1:
                    description = strs[0].trim();
                    if (description.isEmpty()) {
                        throw new ParserException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    throw new ParserException("☹ OOPS!!! The date of a deadline cannot be empty.");
                case 2:
                    description = strs[0].trim();
                    String by = strs[1].trim();
                    if (description.isEmpty()) {
                        throw new ParserException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    if (by.isEmpty()) {
                        throw new ParserException("☹ OOPS!!! The date of a deadline cannot be empty.");
                    }
                    return new Deadline(description, by);
            }
        }
        //assumes we only have one '/from' and one '/to'
        if (firstWord.equals("event")){
            if (arr.length < 2) {
                throw new ParserException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            if (arr[1].isEmpty()) {
                throw new ParserException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            String[] strs = arr[1].split("/from");

            String description = null;

            switch (strs.length) {
                // nothing to left and right
                case 0:
                    throw new ParserException("☹ OOPS!!! The description of an event cannot be empty.");
                case 1:
                    description = strs[0].trim();
                    if (description.isEmpty()) {
                        throw new ParserException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    throw new ParserException("☹ OOPS!!! The from date of an event cannot be empty.");
                case 2:
                    description = strs[0].trim();
                    String dates = strs[1].trim();
                    if (description.isEmpty()) {
                        throw new ParserException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    if (dates.isEmpty()) {
                        throw new ParserException("☹ OOPS!!! The from date of an event cannot be empty.");
                    }
                    strs = strs[1].split("/to");
            }

            String from = null;
            String to = null;
            switch (strs.length) {
                // nothing to left and right
                case 0:
                    throw new ParserException("☹ OOPS!!! The from date of an event cannot be empty.");
                case 1:
                    from = strs[0].trim();
                    if (from.isEmpty()) {
                        throw new ParserException("☹ OOPS!!! The from date of an event cannot be empty.");
                    }
                    throw new ParserException("☹ OOPS!!! The to date of an event cannot be empty.");
                case 2:
                    from = strs[0].trim();
                    to = strs[1].trim();
                    if (from.isEmpty()) {
                        throw new ParserException("☹ OOPS!!! The from date of an event cannot be empty.");
                    }
                    if (to.isEmpty()) {
                        throw new ParserException("☹ OOPS!!! The to date of an event cannot be empty.");
                    }
                    return new Event(description, from, to);
            }
        }
        throw new ParserException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Retrieves a Task object based on the taskIdx.
     * @param taskIdx index of task in the list.
     * @return the Task corresponding to the index.
     */
    private static Task getTask(int taskIdx) throws IllegalValueException {
        if (taskIdx < 0 || taskIdx >= tasks.size()) {
            throw new IllegalValueException("☹ OOPS!!! Please enter a valid task number.");
        }
        return tasks.get(taskIdx);
    }
}
