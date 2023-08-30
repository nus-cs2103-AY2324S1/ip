public class Parser {

    public Parser() {
    }

    public static boolean parse(String userInput, TaskList tasks, Ui ui) throws DukeException {
        String[] parsedCommand = userInput.split(" ", 2);
        String command = parsedCommand[0];
        String task = "";
        if (parsedCommand.length > 1) {
            task = parsedCommand[1];
        }
        if (command.equals("bye")) {
            return false;
        } else if (command.equals("list")) {
            tasks.printList(ui);
        } else if (command.equals("mark")) {
            tasks.toggleDone(task, "mark", ui);
        } else if (command.equals("unmark")) {
            tasks.toggleDone(task, "unmark", ui);
        } else if (command.equals("delete")) {
            tasks.removeItem(task, ui);
        } else if (command.equals("todo")) {
            tasks.addItem(new ToDos(task, "0"), ui);
        } else if (command.equals("deadline")) {
            parsedCommand = task.split("/");
            if (parsedCommand.length == 1) {
                throw new DukeException("☹ OOPS!!! There are missing deadline details.");
            } else {
                tasks.addItem(new Deadlines(parsedCommand[0], parsedCommand[1],"0"), ui);
            }
        } else if (command.equals("event")) {
            parsedCommand = task.split("/");
            if (parsedCommand.length <= 2) {
                throw new DukeException("☹ OOPS!!! There are missing event details.");
            } else {
                tasks.addItem(new Events(parsedCommand[0], parsedCommand[1], parsedCommand[2], "0"), ui);
            }
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return true;
    }

//        if (!userInput.equals("bye")) {
//            if (userInput.equals("list")) {
//                Duke.listOfTexts.printList();
//            } else if (userInput.startsWith("mark")) {
//
//                try {
//                    Duke.listOfTexts.toggleDone(Integer.parseInt(userInput.substring(5)), "mark");
//                } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
//                    System.out.println("☹ OOPS!!! Please indicate a task to mark.");
//                } catch (NullPointerException | IndexOutOfBoundsException e) {
//                    System.out.println("☹ OOPS!!! Please indicate an appropriate index.");
//                }
//
//            } else if (userInput.startsWith("unmark")) {
//
//                try {
//                    Duke.listOfTexts.toggleDone(Integer.parseInt(userInput.substring(7)), "unmark");
//                } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
//                    System.out.println("☹ OOPS!!! Please indicate a task to unmark.");
//                } catch (NullPointerException | IndexOutOfBoundsException e) {
//                    System.out.println("☹ OOPS!!! Please indicate an appropriate index.");
//                }
//
//            } else if (userInput.startsWith("todo")) {
//
//                try {
//                    Duke.listOfTexts.addItem(new ToDos(userInput.substring(5)));
//                } catch (Exception e) {
//                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
//                }
//
//            } else if (userInput.startsWith("deadline")) {
//
//                try {
//                    // Split input into an array containing the task description, and 'by' date
//                    String[] inputs = userInput.split("/");
//                    Duke.listOfTexts.addItem(new Deadlines(inputs[0].substring(9), inputs[1]));
//                } catch (DukeException e) {
//                    System.out.println(e.getMessage());
//                } catch (Exception e) {
//                    System.out.println("☹ OOPS!!! There are missing details for the deadline.");
//                }
//
//            } else if (userInput.startsWith("event")) {
//
//                try {
//                    // Split input into an array containing the task description, 'from' and 'to' date
//                    String[] inputs = userInput.split("/");
//                    Duke.listOfTexts.addItem(new Events(inputs[0].substring(6), inputs[1], inputs[2]));
//                } catch (DukeException e) {
//                    System.out.println(e.getMessage());
//                } catch (Exception e) {
//                    System.out.println("☹ OOPS!!! There are missing details for the event.");
//                }
//
//            } else if (userInput.startsWith("delete")) {
//
//                try {
//                    Duke.listOfTexts.removeItem(Integer.parseInt(userInput.substring(7)));
//                } catch (IndexOutOfBoundsException | NumberFormatException e) {
//                    System.out.println("☹ OOPS!!! Please indicate a task to delete.");
//                } catch (NullPointerException e) {
//                    System.out.println("☹ OOPS!!! Please indicate an appropriate index.");
//                }
//
//            } else {
//
//                try {
//                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
//                } catch(DukeException e) {
//                    System.out.println(e.getMessage());
//                }
//
//            }
//    }
}
