import java.util.Scanner;
public class Ekud {
    String horizontalLine = "##***~-~-~-~-~-~--~-~-~-~-~-~***##";
    String intro = "Hello there! I'm Ekud.\nWhat can I do for you? :O";
    String outro = "Goodbye, have a nice day! :p";
    public void intro() {
        this.echo(this.intro);
    }
    public void outro() {
        this.echo(this.outro);
    }
    public void echo(String message) {
        System.out.println(String.format("%s\n%s\n%s",
                this.horizontalLine,
                message,
                this.horizontalLine));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ekud chatbot = new Ekud();
        chatbot.intro();
        String userCommand = scanner.next();
        while (!userCommand.equals("end")) {
            chatbot.echo(userCommand);
            userCommand = scanner.next();
        }
        chatbot.outro();
    }
}
