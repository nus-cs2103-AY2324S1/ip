import java.io.IOException;
import java.util.Scanner;

public class Duke  {
    public static void main(String[] args) throws MYBotExceptions {

        MYBot MYBot = new MYBot("MYBot");
        MYBot.openGreeting();

        Scanner scanner = new Scanner(System.in);

        while(true){
            String input = scanner.nextLine();
            if(input.equals("bye")) {
                MYBot.closeGreeting();
                break;
            } else {
                MYBot.analyseInput(input);
            }
        }
    }
}

