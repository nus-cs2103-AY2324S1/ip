import java.util.Scanner;

public class Blip {
    public static void main(String[] args) {
        // Intro message by Blip.
        String intro = "Hello! I'm Blip\n"
                + "What can I do for you?";

        // Outro message by Blip.
        String outro = "Bye. Hope to see you again soon!";

        // Constant end trigger word to end the chat with outro.
        String END_TRIGGER = "bye";

        // Constant list trigger word to display back stored text.
        String LIST_TRIGGER = "list";

        // Fixed-size array of tasks to do.
        String[] tasks = new String[100];
        int index = 0;

        // Messages
        System.out.println(intro);

        String userInput;
        Scanner scanIn = new Scanner(System.in);
        userInput = scanIn.nextLine();


        while (!userInput.equals(END_TRIGGER)) {
            if (!userInput.equals(LIST_TRIGGER)) {
                tasks[index] = userInput;
                index++;
                System.out.println("added: " + userInput);
                userInput = scanIn.nextLine();
            } else {
                for (int i = 0; i < index; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                userInput = scanIn.nextLine();
            }

        }

        System.out.println(outro);

    }
}
