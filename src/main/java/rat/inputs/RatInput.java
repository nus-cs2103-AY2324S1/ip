package rat.inputs;

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
    }

    public void handleInput() throws RatExitThrowable {
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] inputArr = input.split(" ");
            String command = inputArr[0];
            switch (command) {
                case "bye":
                    throw new RatExitThrowable();
                case "list":
                    this.ratStorage.listItems();
                    break;
                case "mark":
                    this.handleMark(inputArr);
                    break;
                case "unmark":
                    this.handleUnmark(inputArr);
                    break;
                case "todo":
                    this.handleToDo(input);
                    break;
                case "deadline":
                    this.handleDeadline(input);
                    break;
                case "event":
                    this.handleEvent(input);
                    break;
                case "delete":
                    this.handleDelete(inputArr);
                    break;
                default:
                    RatPrinter.printWarning("Sorry, I don't understand what you mean by " + RatPrinter.italicise(input));
            }
        }
    }

    protected void handleMark(String[] inputs) {
        try {
            int index = Integer.parseInt(inputs[1]);
            this.ratStorage.markItemDone(index);
        } catch (IndexOutOfBoundsException e) {
            RatPrinter.printWarning(e.getMessage());
        } catch (NumberFormatException e) {
            RatPrinter.printWarning(" \"mark\" command must be followed by a number");
        }
    }

    protected void handleUnmark(String[] inputs) {
        try {
            int index = Integer.parseInt(inputs[1]);
            this.ratStorage.unmarkItemDone(index);
        } catch (IndexOutOfBoundsException e) {
            RatPrinter.printWarning(e.getMessage());
        } catch (NumberFormatException e) {
            RatPrinter.printWarning(" \"unmark\" command must be followed by a number");
        }
    }

    protected void handleToDo(String params) {
        try {
            params = params.substring(5);
            this.ratStorage.addToDo(params);
        } catch (StringIndexOutOfBoundsException e) {
            RatPrinter.printWarning("To Do name cannot be empty");
        }
    }

    protected void handleDeadline(String params) {
        try {
            params = params.substring(9);
            String[] paramsArr = params.split(" /by ");
            String name = paramsArr[0];
            String deadline = paramsArr[1];
            this.ratStorage.addDeadline(deadline, name);
        } catch (StringIndexOutOfBoundsException e) {
            RatPrinter.printWarning("Deadline name cannot be empty");
        } catch (ArrayIndexOutOfBoundsException e) {
            RatPrinter.printWarning("Invalid deadline format. Please use \"deadline <name> /by <deadline>\"");
        }
    }

    protected void handleEvent(String params) {
        try {
            params = params.substring(6);
            String eventName = params.split(" /from ")[0];
            if (eventName.isEmpty()) {
                RatPrinter.printWarning("Event name cannot be empty");
            }
            String[] time = params.split(" /from ")[1].split(" /to ");
            String startTime = time[0];
            String endTime = time[1];
            this.ratStorage.addEvent(startTime, endTime, eventName);
        } catch (StringIndexOutOfBoundsException e) {
            RatPrinter.printWarning("Event name cannot be empty");
        } catch (ArrayIndexOutOfBoundsException e) {
            RatPrinter.printWarning("Invalid event format. Please use \"event <name> /from <start> /to <end>\"");
        }
    }

    protected void handleDelete(String[] inputs) {
        try {
            int index = Integer.parseInt(inputs[1]);
            this.ratStorage.deleteItem(index);
        } catch (IndexOutOfBoundsException e) {
            RatPrinter.printWarning(e.getMessage());
        } catch (NumberFormatException e) {
            RatPrinter.printWarning(" \"delete\" command must be followed by a number");
        }
    }

}
