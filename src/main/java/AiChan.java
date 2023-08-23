import java.util.ArrayList;
import java.util.Scanner;
public class AiChan {
    public static void main(String[] args) {
        String line = "_______________________________________________________________________\n";
        String greet = "Hiya! I'm Ai-chan~ ✧◝(⁰▿⁰)◜✧\n" +
                "Hey there, dear viewer, what's on your mind?\n" +
                "Is there anything I can do to sprinkle some magic into your day?\n";
        String bye = "Ta-da! It's time to go~ Keep smiling till we reunite!\n";

        Scanner scn = new Scanner(System.in);
        ArrayList<Task> arrTask = new ArrayList<>();

        System.out.println(line + greet + line);

        while (true) {
            String command = scn.nextLine();
            if (command.equals("bye")) {
                System.out.println(line + bye + line);
                break;
            } else if (command.equals("list")){
                System.out.print(line + "Here are the tasks in your list: \n");
                for (Task t : arrTask) {
                    System.out.println(t.toStringId());
                }
                System.out.println(line);
            } else if (command.startsWith("mark")){
                int taskId = Integer.parseInt(command.substring(5));
                Task t = arrTask.get(taskId - 1);
                t.mark();
                System.out.println(line + "Nice! I've marked this task as done: \n" + t.toString() + "\n" + line);
            } else if (command.startsWith("unmark")){
                int taskId = Integer.parseInt(command.substring(7));
                Task t = arrTask.get(taskId - 1);
                t.unmark();
                System.out.println(line + "OK, I've marked this task as not done yet: \n" + t.toString() + "\n" + line);
            } else {
                arrTask.add(new Task(command));
                System.out.println(line + "added: " + command + "\n" + line);
            }
        }
    }
}
