import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Ace chatBot = new Ace();
        Scanner scanner = new Scanner(System.in);
        String keyWord = "start";

        while (true) {
            if (keyWord.equals("bye")) {
                System.out.println(chatBot.sendMessage("bye"));
                scanner.close();
                break;
            } else {
                System.out.println((chatBot.sendMessage(keyWord)));
                keyWord = scanner.nextLine();
            }
        }
    }
}
