import java.util.Scanner;
public class Blip {
    public static void main(String[] args) {
        // Separator line.
        String separator = "\n____________________________________________________________________________\n";

        // Intro message by Blip.
        String intro = separator
                + "Hello! I'm Blip\n"
                + "What can I do for you?"
                + separator;

        // Outro message by Blip.
        String outro = separator
                + "Bye. Hope to see you again soon!"
                + separator;

        // Constant end trigger word to end the chat with outro.
        String END_TRIGGER = "bye";

        // Messages
        System.out.println(intro);

        String userInput;
        Scanner scanIn = new Scanner(System.in);
        userInput = scanIn.nextLine();


        while (!userInput.equals(END_TRIGGER)) {
            System.out.println(separator + userInput + separator);
            userInput = scanIn.nextLine();
        }

        System.out.println(outro);


    }
}
