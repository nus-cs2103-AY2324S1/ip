import org.w3c.dom.Text;

import java.util.Scanner;  // Import the Scanner class

public class Duke {
    public static void main(String[] args) {
        String line = "    ____________________________________________________________\n";
        String logo = "                  .-\"-.\n"
                + "                 /|6 6|\\\n"
                + " _  ._ _   _    {/(_0_)\\}\n"
                + "(_) | (/_ (_)    _/ ^ \\_\n"
                + "                (/ /^\\ \\)-'\n"
                + "                 \"\"' '\"\"\n";
        String greet = logo + "Woof! I'm Oreo! How may I help you?\n";
        String exit = "I will be sad to see you go! bye!\n";

        String reply = "";
        String[] list = new String[100];
        int numberOfTasks = 0;

        System.out.println(TextFormat.botReply(greet));  // print greet message
        while (true) {
            Scanner userReply = new Scanner(System.in);
            reply = userReply.nextLine();

            if (reply.equals("bye")) {
                break;
            } else if (reply.equals("list")) {
                if (numberOfTasks == 0) {
                    System.out.println(TextFormat.botReply("looks empty to me!"));
                } else {
                    StringBuilder taskList = new StringBuilder();
                    if (numberOfTasks > 10) {
                        taskList.append("seems like you have a lot of things to do...\n")
                                .append("remember to play with me after :D\n");
                    } else {
                        taskList.append("here are the things you told me to keep track of:\n");
                    }
                    for (int i = 0; i < numberOfTasks; i++) {
                        taskList.append(i + 1).append(". ").append(list[i] + "\n");
                    }
                    System.out.println(TextFormat.botReply(taskList.toString()));
                }
            } else {
                list[numberOfTasks] = reply;
                numberOfTasks++;
                String echo = "gotchu! noted down... " + reply;

                System.out.println(TextFormat.botReply(echo));
            }
        }
        System.out.println(TextFormat.botReply(exit));
    }
}
