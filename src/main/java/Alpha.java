import com.alpha.exceptions.InvalidTaskException;
import com.alpha.exceptions.InvalidTaskException.InvalidCommandException;
import com.alpha.tasks.TaskList;
import com.alpha.utils.Parser;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Alpha {

  public static void main(String[] args) {

    System.out.println("______________________________");
    System.out.println("Hello! I'm Alpha.");
    System.out.println("What can I do for you?");
    System.out.println("______________________________");

    Scanner sc = new Scanner(System.in);
    TaskList taskList = new TaskList();

    while (true) {
      String userInput = sc.nextLine();
      String[] tokens;

      try {
        tokens = Parser.getTokens(userInput);
      } catch (InvalidCommandException e) {
        System.out.println(e.getMessage());
        continue;
      }

      System.out.println("______________________________");

      switch (tokens[0]) {
        case "list":
          taskList.printTasks();
          break;
        case "mark":
          try {
            taskList.markTask(Integer.parseInt(tokens[1]));
          } catch (NumberFormatException | InvalidTaskException e) {
            System.out.println(e.getMessage());
          }
          break;
        case "unmark":
          try {
            taskList.unmarkTask(Integer.parseInt(tokens[1]));
          } catch (NumberFormatException | InvalidTaskException e) {
            System.out.println(e.getMessage());
          }
          break;
        case "delete":
          try {
            taskList.deleteTask(Integer.parseInt(tokens[1]));
          } catch (NumberFormatException | InvalidTaskException e) {
            System.out.println(e.getMessage());
          }
          break;
        case "bye":
          taskList.saveData();
          System.out.println("Bye. Hope to see you again soon!");
          System.out.println("______________________________");
          return;
        default:
          try {
            taskList.addTask(userInput);
          } catch (InvalidTaskException | DateTimeParseException e) {
            System.out.println(e.getMessage());
          }

      }
      System.out.println("______________________________");
    }
  }
}
