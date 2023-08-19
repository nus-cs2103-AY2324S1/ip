import java.util.Scanner;

public class Duke {

    public static Scanner scannerObj = new Scanner(System.in);
    public static String userInput;

    public static void main(String[] args) {
        PrintIntro();
        PromptInput();

        do {
            EchoString(userInput);
            PromptInput();
        } while (!userInput.equals("bye"));

        PrintEnd();
    }
    private static void PrintIntro() {
        PrintLine();
        System.out.println("Hello! I'm Roe!\n" + "What can I do for you?\n");
        PrintLine();
    }

    private static void PrintEnd() {
        PrintLine();
        System.out.println("Bye. Hope to see you again soon!\n");
        PrintLine();
    }

    private static void EchoString(String input) {
        PrintLine();
        System.out.println(input + "\n");
        PrintLine();
    }

    private static String PromptInput() {
        System.out.println();
        userInput = scannerObj.nextLine();
        System.out.println();
        return userInput;
    }

    private static void PrintLine() {
        int charCount = 50;
        for (int i = 0; i < charCount; i++) {
            System.out.print("─");
        }
        System.out.print("\n");
    }
}
