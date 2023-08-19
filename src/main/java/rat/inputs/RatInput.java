package rat.inputs;
import rat.storage.*;
import java.util.Scanner;
import rat.inputs.throwables.RatExitThrowable;
public class RatInput {

    public RatInput() { };

    public static void handleInput(Scanner sc, RatStorage ratStorage) throws RatExitThrowable {
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] inputArr = input.split(" ");
            if (inputArr[0].equals("mark") || inputArr[0].equals("unmark")) {
                switch (inputArr[0]) {
                    case "mark":
                        ratStorage.markItemDone(Integer.parseInt(inputArr[1]));
                        break;
                    case "unmark":
                        ratStorage.unmarkItemDone(Integer.parseInt(inputArr[1]));
                        break;
                }
            } else {
                switch (input) {
                    case "bye":
                        throw new RatExitThrowable();
                    case "list":
                        ratStorage.listItems();
                        break;
                    default:
                        ratStorage.addItem(new Task(input));
                        break;
                }
            }
        }
    }
}
