import java.util.Scanner;  // Import the Scanner class

public class Duke {
    public static void main(String[] args) {
        String line = "    ____________________________________________________________\n";
        String logo = "                        .-\"-.\n"
                + "                       /|6 6|\\\n"
                + "       _  ._ _   _    {/(_0_)\\}\n"
                + "      (_) | (/_ (_)    _/ ^ \\_\n"
                + "                      (/ /^\\ \\)-'\n"
                + "                       \"\"' '\"\"\n";
        String greet = line + logo
                + "     Woof! I'm Oreo! How may I help you?\n"
                + line;
        String exit = line + "     I will be sad to see you go! bye!\n" + line;

        String reply = "";
        String[] list = new String[100];
        int numberOfTasks = 0;

        System.out.println(greet);  // print greet message
        while (true) {
            Scanner userReply = new Scanner(System.in);
            reply = userReply.nextLine();

            if (reply.equals("bye")) {
                break;
            } else if (reply.equals("list")) {
                if (numberOfTasks == 0) {
                    System.out.println(line
                            + "     "
                            + "looks empty to me!");
                } else {
                    if (numberOfTasks > 10) {
                        System.out.println(line
                                + "     "
                                + "seems like you have a lot of things to do...\n"
                                + "     "
                                + "remember to play with me after :D");
                    } else {
                        System.out.println(line
                                + "     "
                                + "here are the things you told me to keep track of:");
                    }
                    for (int i = 0; i < numberOfTasks; i++) {
                        System.out.println("     " + (i + 1) + ". "
                                + list[i]);
                    }
                }
                System.out.println(line);
                continue;
            } else {
                list[numberOfTasks] = reply;
                numberOfTasks++;
                String echo = line + "     " + "gotchu! noted down... "
                        + reply + "\n" + line;

                System.out.println(echo);
            }
        }
        System.out.println(exit);
    }
}
