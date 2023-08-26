import java.util.Scanner;
public class Duke {

    private static Task[] currList = new Task[100]; // array to store users' input
    private static int counter = 0; // current input

    public static void main(String[] args) {
        // Duke customisation
        String name = "misty";
        String logo = "|\\---/|\n" +
                "| o_o |\n" +
                " \\___/";
        String separator = "------------------------------------------------";

        // Welcome user
        System.out.println(">  Hello from " + name + "\n" + logo);
        System.out.println(">  What can misty help you with?");
        System.out.println(separator);


        Scanner sc = new Scanner(System.in);
        while (true) {
            String currInput = sc.nextLine();
            if (currInput.equals("bye")) {
                System.out.println(">  ok thanks bye");
                break;
            } else if (currInput.equals("list")) {
                if (counter == 0) {
                    System.out.println(">  You have no tasks :)");
                } else {
                    System.out.println(">  Your tasks: ");
                    for (int i = 0; i < counter; i++) {

                        System.out.println((i + 1) + ") " + currList[i]);
                    }
                }

            } else if (currInput.startsWith("mark")) {
                int index = Integer.parseInt(currInput.split(" ")[1]) - 1;
                if (index >= 0 && index < counter) {
                    currList[index].setCompleted();
                    System.out.println(">  ok, you have completed Task " + index + 1);
                    System.out.println(currList[index]);
                } else {
                    System.out.println(">  Task " + (index + 1) + " was not found :(");
                }
            } else if (currInput.startsWith("unmark")) {
                int index = Integer.parseInt(currInput.split(" ")[1])-1;
                if (index >= 0 && index < counter) {
                currList[index].setNotCompleted();
                System.out.println(">  ok, you haven't completed Task " + index + 1);
                System.out.println(currList[index]);
                } else {
                    System.out.println(">  Task " + (index + 1) + " was not found :(");
                }
            } else {
                if (currInput.isEmpty()) {
                    System.out.println(">  empty tasks are not allowed :/");
                } else {
                    currList[counter] = new Task(currInput);
                    counter++;
                    System.out.println(">  added: " + currInput);
                }
            }
            System.out.println(separator);
        }
        sc.close();
    }
}
