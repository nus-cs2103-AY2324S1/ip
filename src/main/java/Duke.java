import org.w3c.dom.Text;

import java.util.Scanner;  // Import the Scanner class

public class Duke {
     static String logo = "                  .-\"-.\n"
            + "                 /|6 6|\\\n"
            + " _  ._ _   _    {/(_0_)\\}\n"
            + "(_) | (/_ (_)    _/ ^ \\_\n"
            + "                (/ /^\\ \\)-'\n"
            + "                 \"\"' '\"\"\n";

    static String greet = logo + "Woof! I'm Oreo! How may I help you?\n";
    static String exit = "I will be sad to see you go! bye!\n";
    private Task[] taskList;

    public Duke() {
        this.taskList = new Task[100];
    }

    private void list() {
        if (Task.numberOfTasks == 0) {
            System.out.println(TextFormat.botReply("looks empty to me!"));
        } else {
            StringBuilder displayList = new StringBuilder();
            if (Task.numberOfTasks > 10) {
                displayList.append("seems like you have a lot of things to do...\n")
                        .append("remember to play with me after :D\n");
            } else {
                displayList.append("here are the things you told me to keep track of:\n");
            }
            for (int i = 0; i < Task.numberOfTasks; i++) {
                displayList.append(taskList[i].toString());
            }
            System.out.println(TextFormat.botReply(displayList.toString()));
        }
    }

    public void run() {
        System.out.println(TextFormat.botReply(greet));  // print greet message

        while (true) {
            String command = "";
            int id = 0;
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            Scanner tokeniser = new Scanner(input);
            command = tokeniser.next();

            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                this.list();
            } else if (command.equals("mark")) {
                id = Integer.parseInt(tokeniser.next());
                taskList[id - 1].markDone();
            } else if (command.equals("unmark")) {
                id = Integer.parseInt(tokeniser.next());
                taskList[id - 1].markNotDone();
            } else {
                Task newTask = new Task(Task.numberOfTasks + 1, input);
                Task.addTask(newTask);
                taskList[Task.numberOfTasks - 1] = newTask;
            }
        }
        System.out.println(TextFormat.botReply(exit));
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
