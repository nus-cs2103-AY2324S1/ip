import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Ace chatBot = new Ace();
        Scanner scanner = new Scanner(System.in);
        String keyWord = "start";
        String details = "";

        while (true) {
            if (keyWord.equals("bye")) {
                System.out.println(chatBot.sendMessage("bye", details));
                scanner.close();
                break;
            } else {
                System.out.println(chatBot.sendMessage(keyWord, details));
                String userInput = scanner.nextLine();
                String[] parts = userInput.split(" ", 2);
                keyWord = parts[0];
                if (parts.length == 2) {
                    details = parts[1];
                }
            }
        }
    }
}
