import errors.TaskErrors;
import errors.IncorrectMarkParametersException;
import tasks.*;
import ui.Ui;

import java.util.Scanner;

public class Dot {
    private static final TaskList dotTaskList = TaskList.newTaskList(100);

    public static void main(String[] args) {
        Ui.welcome();
        Scanner sc = new Scanner(System.in);

        boolean isOngoing = true;
        while (isOngoing) {
            String input = sc.nextLine();
            switch (input) {
                case "bye":
                    isOngoing = false;
                    break;
                case "list":
                    dotTaskList.list();
                    break;
                default:
                    // TODO: abstract out validation
                    if (input.startsWith("mark")) {
                        String[] substrings = input.split(" ");
                        if (substrings.length == 2) {
                            try {
                                int position = Integer.parseInt(substrings[1]);
                                dotTaskList.markTask(position - 1, true);
                            } catch (NumberFormatException e) {
                                TaskErrors.ERR_USING_MARK.printErrorMessage(e);
                            }
                        } else {
                            TaskErrors.ERR_USING_MARK.printErrorMessage(
                                    new IncorrectMarkParametersException("incorrect number of parameters"));
                        }
                        break;
                    }
                    dotTaskList.addItem(input);
                    break;
            }
        }
        Ui.goodbye();
    }
}
