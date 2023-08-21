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
    private static Dictionary<String, Consumer<String>> COMMAND_LIST = new Hashtable<String, Consumer<String>>() {{
        put("bye", CommandInvoker::bye);
        put("list", CommandInvoker::list);
        put("echo", CommandInvoker::echo);
        put("mark", CommandInvoker::mark);
        put("unmark", CommandInvoker::unmark);
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
        String inputWords = removeFirstWord(inputString);
        String keyword = inputString.split(" ")[0];
        Consumer<String> calledConsumer = COMMAND_LIST.get(keyword);
        if (calledConsumer != null) {
            calledConsumer.accept(inputWords);
        } else {
            unknownCommand(inputString);
        }
    }
    /**
     * Terminates chatbot
     * @param inputString Unused
     */
    private static void bye(String inputString) {
        Rock.terminate();
    }
    /**
     * List all tasks in the task list
     * @param inputString
     */
    private static void list(String inputString) {
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
     * @param inputString User's input
     */
    private static void unknownCommand(String inputString) {
        taskList.add(new Task(inputString));
        Rock.say(inputString + " was added to the task list!");
        Rock.say(Rock.LINE_BREAK);
    }
    /**
     * Marks a task as completed
     * @param inputString Contains index of task to be
     * marked. 1-indexed.
     */
    private static void mark(String inputString) {
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
     * @param inputString Contains index of task to be
     * unmarked. 1-indexed.
     */
    private static void unmark(String inputString) {
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
    /**
     * Prints out the user's input. UNUSED
     * @param inputString User's input.
     */
    private static void echo(String inputString) {
        Rock.say(inputString);
        Rock.say(Rock.LINE_BREAK);
    }
}
