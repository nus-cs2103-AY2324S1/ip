import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        String line = "____________________________________________________________\n";
        System.out.println(line + " Hello! I'm Alcazar\n" +
                " What can I do for you?\n" +
                line);
        String prompt = Duke.inputText();
        while (!prompt.equals("bye")) {
            if(prompt.equals("list")) {
                System.out.println(line + "Here are the tasks in your list:\n"
                        + getTasks(tasks) + line);
            } else if (prompt.contains("mark") || prompt.contains("unmark")) {
                    if(prompt.contains("unmark")) {
                        int index = Integer.parseInt(prompt.charAt(prompt.length() - 1) + "");
                        tasks.get(index - 1).unmarkTask();
                        System.out.println(line +
                                "OK, I've marked this task as not done yet:\n" +
                                tasks.get(index - 1).toString());
                    } else {
                        int index = Integer.parseInt(prompt.charAt(prompt.length() - 1) + "");
                        tasks.get(index - 1).markTask();
                        System.out.println(line +
                                "Nice! I've marked this task as done:\n" +
                                tasks.get(index - 1).toString());
                    }
            } else {
                tasks.add(new Task(prompt));
                System.out.println(line + "added: " + prompt + "\n" + line);
            }
            prompt = Duke.inputText();
        }
        System.out.println(line +
                " Bye. Hope to see you again soon!\n" +
                line);
    }
    public static String getTasks(ArrayList<? extends Task> tasks) {
        String listedTasks = "";
        for(int i = 0; i < tasks.size(); i++) {
            listedTasks += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return listedTasks;
    }
    public static String inputText() {
        Scanner sc =  new Scanner(System.in);
        String inp = sc.nextLine();
        return inp;
    }
}
