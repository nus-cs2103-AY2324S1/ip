import java.util.function.Consumer;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
public class CommandInvoker {
    /**
     * List of tasks
     */
    private static List<Task> taskList = new ArrayList<>();
    /**
     * Lookup Table that relates a keyword to a corresponding method.
     */
    private static Dictionary<String, Consumer<Parser>> COMMAND_LIST = new Hashtable<String, Consumer<Parser>>() {{
        put("bye", CommandInvoker::bye);
        put("list", CommandInvoker::list);
        put("mark", CommandInvoker::mark);
        put("unmark", CommandInvoker::unmark);
        put("todo", CommandInvoker::todo);
        put("deadline", CommandInvoker::deadline);
        put("event", CommandInvoker::event);
    }};
    /**
     * Helper function used to obtain the rest of a sentence sans keyword.
     * @param sentence String to be trimmed.
     * @return Same string without keyword, empty string if there is no other characters
     * besides keyword
     */
    private static String removeFirstWord(String sentence) {
        String[] words = sentence.split(" ", 2);
        return words.length > 1 ? words[1] : "";
    }
    /**
     * Used to handle a given user input and call the corresponding method.
     * @param inputString User's input.
     */
    public static void handle(String inputString) {
        Parser input = new Parser(removeFirstWord(inputString));
        String keyword = inputString.split(" ")[0];
        Consumer<Parser> calledConsumer = COMMAND_LIST.get(keyword);
        if (calledConsumer != null) {
            calledConsumer.accept(input);
        } else {
            unknownCommand(inputString);
        }
    }
    /**
     * Terminates chatbot
     * @param input Unused
     */
    private static void bye(Parser input) {
        Rock.terminate();
    }
    /**
     * List all tasks in the task list
     * @param input Unused
     */
    private static void list(Parser input) {
        int counter = 1;
        Rock.say("Task List: ");
        for (Task task:taskList) {
            Rock.say(Integer.toString(counter) + ". " + task.toString());
            counter++;
        }
        Rock.say(Rock.LINE_BREAK);
    }
    /**
     * Handler for unknown commands given by user.
     * @param input User's input
     */
    private static void unknownCommand(String input) {
        Rock.say("Unknown command!");
        Rock.say(Rock.LINE_BREAK);
    }
    /**
     * Marks a task as completed
     * @param inputString Contains index of task to be
     * marked. 1-indexed.
     */
    private static void mark(Parser input) {
        String inputString = input.getDefaultString();
        try {
            int taskIdx = Integer.parseInt(inputString);
            if (taskIdx < 1 || taskIdx > taskList.size()) {
                Rock.say("Invalid index given!");
            } else if (taskList.get(taskIdx - 1).isCompleted()){
                Rock.say("Task already marked!");
            } else {
                taskList.get(taskIdx - 1).mark();
                Rock.say("Task marked successfully:");
                Rock.say(taskList.get(taskIdx - 1).toString());
            }
        } catch (NumberFormatException e) {
            Rock.say("Invalid index given!");
        }
    }
    /**
     * Unmarks a task as completed
     * @param input Contains index of task to be
     * unmarked. 1-indexed.
     */
    private static void unmark(Parser input) {
        String inputString = input.getDefaultString();
        try {
            int taskIdx = Integer.parseInt(inputString);
            if (taskIdx < 1 || taskIdx > taskList.size()) {
                Rock.say("Invalid index given!");
            } else if (!taskList.get(taskIdx - 1).isCompleted()){
                Rock.say("Task already unmarked!");
            } else {
                taskList.get(taskIdx - 1).unmark();
                Rock.say("Task unmarked successfully:");
                Rock.say(taskList.get(taskIdx - 1).toString());
            }
        } catch (NumberFormatException e) {
            Rock.say("Invalid index given!");
        }
    }
    private static void todo(Parser input) {
        taskList.add(new TaskTodo(input));
    }
    private static void deadline(Parser input) {
        taskList.add(new TaskDeadline(input));
    }
    private static void event(Parser input) {
        taskList.add(new TaskEvent(input));
    }
}
