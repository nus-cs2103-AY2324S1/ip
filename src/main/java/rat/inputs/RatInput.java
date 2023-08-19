package rat.inputs;

import java.util.Arrays;
import rat.storage.*;
import java.util.Scanner;
import rat.throwables.RatExitThrowable;
import rat.print.RatPrinter;

public class RatInput {

    Scanner sc;
    RatStorage ratStorage;

    public RatInput(Scanner sc, RatStorage ratStorage) {
        this.sc = sc;
        this.ratStorage = ratStorage;
    };

    public void handleInput() throws RatExitThrowable {
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] inputArr = input.split(" ");
            String command = inputArr[0];
            switch (command) {
                case "bye":
                    throw new RatExitThrowable();
                case "list":
                    RatPrinter.printWithLines(this.ratStorage.toString());
                    break;
                case "mark":
                    this.handleMark(inputArr);
                    break;
                case "unmark":
                    this.handleUnmark(inputArr);
                    break;
                case "todo":
                    this.ratStorage.addToDo(input.substring(5));
                    break;
                case "deadline":
                    this.handleDeadline(input.substring(9));
                    break;
                case "event":
                    this.handleEvent(input.substring(6));
                    break;
                default:
                    RatPrinter.printWithLines("invalid command");
            }
        }
    }

    public void handleMark(String[] inputs) {
        int index = Integer.parseInt(inputs[1]);
        this.ratStorage.markItemDone(index);
    }

    public void handleUnmark(String[] inputs) {
        int index = Integer.parseInt(inputs[1]);
        this.ratStorage.unmarkItemDone(index);
    }

    public void handleDeadline(String params) {
        String[] paramsArr = params.split(" /by ");
        String name = paramsArr[0];
        String deadline = paramsArr[1];
        this.ratStorage.addDeadline(deadline, name);
    }

    public void handleEvent(String params) {
        String eventName = params.split(" /from ")[0];
        String[] time = params.split(" /from ")[1].split(" /to ");
        System.out.println(Arrays.toString(time));
        String startTime = time[0];
        String endTime = time[1];
        this.ratStorage.addEvent(startTime, endTime, eventName);
    }


}
