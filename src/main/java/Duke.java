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

        System.out.println(greet);  // print greet message
        while (true) {
            Scanner userReply = new Scanner(System.in);
            reply = userReply.nextLine();
            if (reply.equals("bye")) break;
            String echo = line + "     " + reply + "\n" + line;

            System.out.println(echo);
        }
        System.out.println(exit);
    }
}
