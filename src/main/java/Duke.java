import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

  public static void main(String[] args) {

    String greeting = "Hello! I'm KimochiUsagi (きもち　うさぎ)!\n";
    String info = "Ask the bunny a question!\n";
    String goodbye = "Bye. See you again! (またね)";

    ArrayList<Task> taskList = new ArrayList<>();
    System.out.println(greeting);
    System.out.println(info);

    Scanner scanner = new Scanner(System.in);

    label:
    while (true) {
      String inputString = scanner.nextLine();
      String[] inputTokens = inputString.split(" ");

      // there is no input
      if (inputTokens.length == 0) {
        break;
      }

      String commandString = inputTokens[0];

      switch (commandString) {
        case "bye":
          break label;
        case "list":

          for (int i = 0; i < taskList.size(); i++) {

            String index = Integer.toString(i + 1);
            Task selectectedTask = taskList.get(i);
            System.out.println(index + "\t" + "[" + selectectedTask.getDoneIcon() + "] "
                + selectectedTask.description);
          }
          break;
        case "mark": {
          int index = Integer.parseInt(inputTokens[1]);
          index--;
          System.out.println("Marked selected task as done");
          // set current task as done
          Task selectedTask = taskList.get(index);
          selectedTask.setDone();
          System.out.println("[" + selectedTask.getDoneIcon() + "] " + selectedTask.description);

          break;
        }
        case "unmark": {

          int index = Integer.parseInt(inputTokens[1]);
          index--;
          System.out.println("Marked selected task as un-done desu");
          // set current task as un-done
          Task selectedTask = taskList.get(index);
          selectedTask.setUnDone();
          System.out.println("[" + selectedTask.getDoneIcon() + "] " + selectedTask.description);

          break;
        }
        case "todo": {
          int commandLength = commandString.length()+1;
          String taskName = inputString.substring(commandLength);
          System.out.println(taskName);
          break;
        }
        default:
          taskList.add(new Task(inputString));
          System.out.println("added:\t" + inputString);
          break;
      }
    }

    System.out.println(goodbye);

  }
}
