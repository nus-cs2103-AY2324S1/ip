import java.io.IOException;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) throws IOException {
        System.out.println("Hey summoner! I'm Three Wolves B.\n" + "What do you want me to do?\n");
        IBot myBot = new IBot();
        boolean state = true;
        Scanner sc = new Scanner(System.in);
        while (state) {
            String smd = sc.nextLine();
            state = myBot.parse(smd);
        }
    }
}
