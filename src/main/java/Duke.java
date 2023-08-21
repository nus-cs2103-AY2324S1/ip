import java.util.Scanner;

public class Duke {
    private static String greetingString =  "Hello! I'm ChampionSOS\nWhat can I do for you?";
    private static String exitString =  "Bye. Hope to see you again soon!";
    private static DukeList listOfTexts = new DukeList();
    public static void main(String[] args) {
        System.out.println(Duke.greetingString);
        Scanner myObj = new Scanner(System.in);
        String userInput = myObj.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                Duke.listOfTexts.printList();
            } else {
                listOfTexts.addItem(userInput);
            }
            userInput = myObj.nextLine();
        }
        System.out.println(Duke.exitString);
    }
}
