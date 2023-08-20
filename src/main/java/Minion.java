import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
                List<String> lst = new ArrayList<>();
                lst.add("Here are the tasks in your list:");
                for(int i = 0; i < taskCount; i++) {
                    lst.add((i + 1) + "." + tasks[i].toString());
                }
                prettyPrint(lst);
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
                if(command.startsWith("todo")) {
                    task = new ToDo(command.split(" ",2)[1]);
                }
                else if(command.startsWith("deadline")){
                    String[] strs = command.split(" ", 2)[1].split(" /by ");
                    String description = strs[0];
                    String by = strs[1];
                    task = new Deadline(description, by);
                }
                else if(command.startsWith("event")){
                    String[] strs = command.split(" ", 2)[1].split(" /from ");
                    String description = strs[0];
                    String[] strs2 = strs[1].split(" /to ");
                    String from = strs2[0];
                    String to = strs2[1];
                    task = new Event(description, from, to);
                } else {
                    task = new Task(command);
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
