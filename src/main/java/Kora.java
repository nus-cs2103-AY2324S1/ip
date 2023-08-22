import java.util.Scanner;
import task.TaskList;
public class Kora {

    private TaskList taskList;
    public Kora() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello, I am your chatbot Kora!\nHow can I help you today?");
        System.out.println("------------------------------");

        taskList = new TaskList();
        boolean isExit = false;
        while (!isExit) {
            Scanner scanner = new Scanner(System.in);
            //System.out.println("------------------------------");
            System.out.println(getResponse(scanner.nextLine()));
            System.out.println("------------------------------");
        }
    }

    public String getResponse(String userInput) {
        String output = "------------------------------" + "\n";
        try {
            if (userInput.equals("bye")) {
                output = output + "Byebye. See you again!";
            } else if (userInput.equals("list")) {
                output = output + taskList.toString();
            } else {
                taskList.addTask(userInput);
                output = output + "added: " + userInput;
            }
        } catch (Exception e) {
            output = output + e.getMessage();
        }
        return output;
    }
    public static void main(String[] args) {
        Kora kora = new Kora();
    }
}
