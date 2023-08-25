import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The Parser class is used to run the infinite loop and
 * take in inputs by the user
 *
 * @author Zi Xiang
 * @version CS2103 AY23/24 Sem 1
 */
public class Parser {
    // Initialisation of objects and variables
    String entered;
    Scanner s;
    UI ui;
    TaskList storage;
    List<String> commands;

    /**
     * Constructor for Parser
     */
    public Parser(ArrayList arrayList) {
        this.entered = "";
        this.s = new Scanner(System.in);
        this.ui = new UI();
        this.storage = new TaskList(arrayList);
        this.commands = Arrays.asList(new String[]{"todo", "deadline", "event", "mark", "unmark", "delete"});
    }
    /**
     * Method that runs the parser
     */
    public void run(){
        while (true) {
            try {
                entered = s.nextLine();
                if (entered.equals("bye")) {
                    break;
                } else if (entered.equals("list")) {
                    storage.display();
                } else {
                    // Checks for commands that require parameters
                    String[] temp = entered.split(" ", 2);
                    String command = temp[0];
                    if (this.commands.contains(command)) {
                        if (temp.length == 1 || temp[1].length() == 0) {
                            if (command.equals("mark") || command.equals("unmark") || command.equals("delete")){
                                throw new MissingIndexException("");
                            } else {
                                throw new EmptyDescriptionException("");
                            }
                        } else {
                            if (command.equals("mark")) {
                                int index = Integer.parseInt(temp[1]);
                                this.storage.mark(index);
                            } else if (command.equals("unmark")) {
                                int index = Integer.parseInt(temp[1]);
                                this.storage.unmark(index);
                            } else if (command.equals("todo")) {
                                String todo = temp[1];
                                storage.add(new Todo(todo));
                            } else if (command.equals("deadline")) {
                                String[] items = temp[1].split(" /");
                                if (items.length == 1) {
                                    throw new EmptyDescriptionException("");
                                } else if (!items[1].startsWith("by ")){
                                    throw new UnknownCommandException("");
                                } else {
                                    if (items[1].length() == 3) {
                                        throw new EmptyDescriptionException("");
                                    } else {
                                        storage.add(new Deadline(items[0], items[1].substring(3)));
                                    }
                                }
                            } else if (command.equals("event")) {
                                String[] items = temp[1].split(" /");
                                if (items.length == 3){
                                    // Todo: More Error Catching to be done here
                                    if (items[1].startsWith("from ") && items[2].startsWith("to ")) {
                                        storage.add(new Event(items[0], items[1].substring(5), items[2].substring(3)));
                                    } else {
                                        throw new UnknownCommandException("");
                                    }
                                } else {
                                    throw new UnknownCommandException("");
                                }
                            } else if (command.equals("delete")){
                                int index = Integer.parseInt(temp[1]);
                                this.storage.delete(index);
                            }
                        }
                    } else {
                        throw new UnknownCommandException("");
                    }
                }
            } catch (NumberFormatException e) {
                ui.line();
                System.out.println("Please give a proper index for marking/unmarking");
                ui.line();
            } catch (NullPointerException e) {
                ui.line();
                System.out.println("You gave an index with no object!");
                ui.line();
            } catch (UnknownCommandException e) {
                ui.line();
                System.out.println("Sorry but I do not understand that command");
                ui.line();
            } catch (EmptyDescriptionException e) {
                ui.line();
                System.out.println("The task description cannot be empty!");
                ui.line();
            } catch (MissingIndexException e) {
                ui.line();
                System.out.println("There is a missing parameter");
                ui.line();
            } catch (IndexOutOfBoundsException e) {
                ui.line();
                System.out.println("Object does not exist");
                ui.line();
            }
        }
    }
}
