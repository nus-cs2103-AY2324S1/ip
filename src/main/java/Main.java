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
                String[] parts = userInput.split(" ");
                keyWord = parts[0];
                details = "";

                for (int i = 1; i < parts.length; i++) {
                    details += parts[i];
                    if (i + 1 < parts.length) details += " ";
                }
            }
        }
    }
}
