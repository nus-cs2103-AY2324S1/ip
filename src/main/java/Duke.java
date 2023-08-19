import java.util.Scanner;
public class Duke {
    String divider = "------------------------------------\n";
    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    String greet = divider +
            "Hello! I'm Khaleelur!\n" +
            "What can I do for you?\n " +
            divider;

    String exit = divider +
            "Bye. Hope to see you again soon!\n" +
            divider;

    public String echo(String userInput) {
        return userInput;
    }
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        Duke duke = new Duke();

        System.out.println(duke.greet);

        String userInput = obj.nextLine();
        while(!userInput.equals("bye")) {
            System.out.println(duke.divider + duke.echo(userInput) + "\n" + duke.divider);
            userInput = obj.nextLine();
        }
        System.out.println(duke.exit);

    }
}
