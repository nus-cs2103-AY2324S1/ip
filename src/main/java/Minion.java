import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class Minion {
    public static String line = "\t____________________________________________________________\n";
    public static String[] tasks = new String[100];
    public static void main(String[] args) {
        sayHi();
        Scanner sc = new Scanner(System.in);
        int taskCount= 0;
        while(true) {
            String command = sc.nextLine();
            if(command.equals("list")){
                List<String> lst = new ArrayList<>();
                for(int i = 0; i < taskCount; i++) {
                    lst.add((i + 1) + ". " + tasks[i]);
                }
                prettyPrint(lst);
                continue;
            }
            if(command.equals("bye")) {
                sayBye();
                break;
            }
            tasks[taskCount++] = command;
            prettyPrint(String.format("added %s", command));
        }
        sc.close();
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
