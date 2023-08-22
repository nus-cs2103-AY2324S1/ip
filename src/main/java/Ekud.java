import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Ekud {
    String horizontalLine = "##***~-~-~-~-~-~--~-~-~-~-~-~***##";
    String intro = "Hello there! I'm Ekud.\nWhat can I do for you? :O";
    String outro = "Goodbye, have a nice day! :p";
    List<String> userTextList = new ArrayList<>();
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
    public void addToList(String text) {
        userTextList.add(text);
        this.echo("added: " + text);
    }
    public void showList() {
        System.out.println(horizontalLine);
        int len = userTextList.size();
        for (int i = 0; i < len; i++) {
            System.out.println(String.format("%d. %s", i + 1, userTextList.get(i)));
        }
        System.out.println(horizontalLine);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ekud chatbot = new Ekud();
        chatbot.intro();
        String userCommand = scanner.nextLine();
        while (!userCommand.equals("end")) {
            if (userCommand.equals("list")) {
                chatbot.showList();
            } else {
                chatbot.addToList(userCommand);
            }
            userCommand = scanner.nextLine();
        }
        chatbot.outro();
    }
}
