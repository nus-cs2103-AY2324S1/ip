import java.util.Scanner;
public class Gman {
    public static String userInput;
    public static Task taskList[] = new Task[100];
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Hello! I'm Gman! \nWhat can I do for you?");
        String exitWord = "bye";
        userInput = myScanner.nextLine();

        int counter = 0;
        while (!userInput.equals(exitWord) && counter < 100) {
            if (userInput.equals("list") && counter != 0) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < counter; i++) {
                    (taskList[i]).display();
                }
            } else if (userInput.contains("unmark") && counter != 0) {
                String words[] = userInput.split(" ");
                int number = Integer.valueOf(words[1]) - 1;
                taskList[number].unmark();
            } else if (userInput.contains("mark") && counter != 0) {
                String words[] = userInput.split(" ");
                int number = Integer.valueOf(words[1]) - 1;
                taskList[number].mark();
            }else {
                taskList[counter] = new Task(userInput, counter + 1);
                System.out.println("    added: " + userInput);
                counter++;
            }
            userInput = myScanner.nextLine();
        }
        System.out.println("    Bye. Hope to see you again soon!");
    }
}