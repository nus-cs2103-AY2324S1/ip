import java.util.Scanner;

public class Ui {
    protected String line = "----------------------------------------------------\n";
    public Ui() {}
    protected Scanner myObj = new Scanner(System.in);

    public void greet() {
        System.out.println(line);
        System.out.println(format_response(
                "Hello I'm Project54\n" +
                        "What can I do for you?"
        ));
    }

    public void bye() {
        System.out.println(format_response(
                "Bye. Hope to see you again soon"
        ));
    }

    public String readCommand() {
        return myObj.nextLine();
    }

    public String format_response(String response) {
        return response + "\n\n" + line;
    }
}
