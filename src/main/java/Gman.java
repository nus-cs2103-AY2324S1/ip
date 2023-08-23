import java.util.Scanner;
public class Gman {
    public static String userInput;
    public static String inputList[] = new String[100];
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Hello! I'm Gman! \nWhat can I do for you?");
        String exitWord = "bye";
        userInput = myScanner.nextLine();

        int counter = 0;
        while (!userInput.equals(exitWord) && counter < 100) {
            if (userInput.equals("list") && counter != 0) {
                for (int i = 0; i < counter; i++) {
                    System.out.println(inputList[i]);
                }
            } else {
                String toAdd = String.valueOf(counter + 1) + ". " + userInput;
                inputList[counter] = toAdd;
                System.out.println("    added: " + userInput);
                counter++;
            }
            userInput = myScanner.nextLine();
        }
        System.out.println("    Bye. Hope to see you again soon!");
    }
}