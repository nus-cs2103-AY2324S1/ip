import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        IBot myBot = new IBot();
        boolean state = true;
        Scanner sc = new Scanner(System.in);
        while (state) {
            String smd = sc.nextLine();
            state = myBot.parse(smd);
        }
    }
}
