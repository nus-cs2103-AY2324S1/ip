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
        //possible exceptions: nothing is given after keyword,
        //list when there is nothing
        //marked unmarked something that is already marked unmarked
        //
        while (!userInput.equals(exitWord) && counter < 100) {
            if (userInput.equals("list") && counter != 0) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < counter; i++) {
                    (taskList[i]).displayTask();
                }
            } else if (userInput.contains("unmark") && counter != 0) {
                String words[] = userInput.split(" ");
                int number = Integer.valueOf(words[1]) - 1;
                taskList[number].unmark();
            } else if (userInput.contains("mark") && counter != 0) {
                String words[] = userInput.split(" ");
                int number = Integer.valueOf(words[1]) - 1;
                taskList[number].mark();
            } else if (userInput.contains("todo")) {
                //String words[] = userInput.split(" ");

                    taskList[counter] = new Todo(userInput.substring(4), counter + 1);
                    taskList[counter].addedTask();
                    counter++;

            } else if (userInput.contains("deadline")) {
                String words[] = userInput.substring(8).split("/");
                taskList[counter] = new Deadline(words[0], counter + 1, words[1].substring(3));
                taskList[counter].addedTask();
                counter++;
            } else if (userInput.contains("event")) {
                String words[] = userInput.substring(5).split("/");
                taskList[counter] = new Event(words[0], counter + 1,
                        words[1].substring(5), words[2].substring(3));
                taskList[counter].addedTask();
                counter++;
            }
            else {
                taskList[counter] = new GenericTask( " " + userInput, counter + 1);
                taskList[counter].addedTask();
                counter++;
            }
            userInput = myScanner.nextLine();
        }
        System.out.println("    Bye. Hope to see you again soon!");
    }
}