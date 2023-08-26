import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        String name = "Johnnythesnake";
        System.out.println("Hello I'm " + name + "\n" + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a command: ");
        ArrayList<String> commandList = new ArrayList<>();
        while (true) {
            System.out.print("Enter a command: ");
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                Exit exit = new Exit();
                System.out.println(exit.exitMessage());
                break;
            } else if (command.equalsIgnoreCase("list")){
                if (!commandList.isEmpty()) {
                    for (int i = 1; i <= commandList.size(); i++) {
                        System.out.println(i + " ." + commandList.get(i-1));
                    }
                } else {
                    System.out.println("");
                }
            } else {
                System.out.println("added: " + command);
                commandList.add(command);

            }

        }
    }
}
