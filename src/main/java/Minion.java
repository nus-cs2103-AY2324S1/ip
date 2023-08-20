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
    public static void main(String[] args) {
        sayHi();
        Scanner sc = new Scanner(System.in);
        int taskCount= 0;
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
            } else if (command.startsWith("mark")){
                Task currTask = getTask(command);
                currTask.markDone();
                List<String> lst = new ArrayList<>();
                lst.add("Nice! I've marked this task as done:");
                lst.add("\t" + currTask.toString());
                prettyPrint(lst);
            } else if (command.startsWith("unmark")){
                Task currTask = getTask(command);
                currTask.markUndone();
                List<String> lst = new ArrayList<>();
                lst.add("OK, I've marked this task as not done yet:");
                lst.add("\t" + currTask.toString());
                prettyPrint(lst);
            } else {
                tasks[taskCount++] = new Task(command);
                prettyPrint(String.format("added %s", command));
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
