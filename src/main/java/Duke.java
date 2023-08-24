import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<String> tasks = new ArrayList<>();
        String line = "____________________________________________________________\n";
        System.out.println(line + " Hello! I'm Alcazar\n" +
                " What can I do for you?\n" +
                line);
        String prompt = Duke.inputText();
        while (!prompt.equals("bye")) {
            if(prompt.equals("list")) {
                System.out.println(line + getTasks(tasks) + line);
            } else {
                tasks.add(prompt);
                System.out.println(line + "added: " + prompt + "\n" + line);
            }
            prompt = Duke.inputText();
        }
        System.out.println(line +
                " Bye. Hope to see you again soon!\n" +
                line);
    }
    public static String getTasks(ArrayList<? extends String> tasks) {
        String listedTasks = "";
        for(int i = 0; i < tasks.size(); i++) {
            listedTasks += (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return listedTasks;
    }
    public static String inputText() {
        Scanner sc =  new Scanner(System.in);
        String inp = sc.nextLine();
        return inp;
    }
}
