import java.util.Scanner;

public class Ui {
    private String logo =  "                  .-\"-.\n"
            + "                 /|6 6|\\\n"
            + " _  ._ _   _    {/(_0_)\\}\n"
            + "(_) | (/_ (_)    _/ ^ \\_\n"
            + "                (/ /^\\ \\)-'\n"
            + "                 \"\"' '\"\"\n";

    private String greet = logo + "Woof! I'm Oreo! How may I help you?\n";

    private String exit = "I will be sad to see you go! bye!\n";

    private String hLine = "    ____________________________________________________________\n";

    private String indent = "     ";

    private Scanner sc = new Scanner(System.in);

    public static String indentLineBy(String message, int indents) {
        StringBuilder indentedLine = new StringBuilder();
        for (int i = 0; i < indents; i++) {
            indentedLine.append(" ");
        }
        return indentedLine.append(message).toString();
    }

    public String indentMessage(String message) {
        String[] lines = message.split("\n");
        StringBuilder indentedMessage = new StringBuilder();
        for (String line : lines) {
            indentedMessage.append(indent).append(line).append("\n");
        }
        return indentedMessage.toString();
    }
    public String botReply(String message) {
        StringBuilder reply = new StringBuilder();
        return reply.append(hLine).append(indentMessage(message))
                .append(hLine).toString();
    }

    public void greet(TaskList tasks) {
        if (tasks.getNumberOfTask() != 0) {
            System.out.println(botReply(greet +
                    "Welcome back! " +
                    tasks.list()));
        } else {
            System.out.println(botReply(greet));
        }
    }

    public void sayBye() {
        System.out.println(botReply(exit));
    }

    public void say(String message) {
        System.out.println(botReply(message));
    }

    public String readCommand() {
        return sc.nextLine();
    }
}
