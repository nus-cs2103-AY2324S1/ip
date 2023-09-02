package crackerpackage;

import java.io.IOException;
import java.util.Scanner;

import crackerpackage.tasks.Task;
import exceptions.EmptyDescriptionException;
import exceptions.IllegalFormatException;
import exceptions.UnknownCommandException;
import uicomponents.Parser;
import uicomponents.Reply;

/**
 * A chatbot that manages tasks.
 *
 * @author Anton Tan Hong Zhi
 */
public class Cracker {

    private TodoList list = null;
    private Reply reply = new Reply();
    private Storage storage;

    /**
     * The types of operations supported by the chatbot.
     */
    public enum Type {
        MARK,
        UNMARK,
        TASK,
        DELETE,
        UNKNOWN,
        LIST,
        QUIT,
        FIND
    }

    /**
     * Starts up the bot.
     */
    public void startService() {
        boolean talking = true;
        storage = new Storage("./data/list.txt");
        list = storage.load();
        Scanner sc = new Scanner(System.in);
        reply.echo("What can I do for you?");
        while (talking) {
            Type t = null;

            String input = sc.nextLine();



            try {
                Type command = Parser.parseCommand(input);

                switch (command) {
                case MARK:
                    list.markDone(Parser.parseIndex(input));
                    reply.modifyTaskReply(list.getTask(Parser.parseIndex(input)));
                    break;
                case UNMARK:
                    list.markUndone(Parser.parseIndex(input));
                    reply.modifyTaskReply(list.getTask(Parser.parseIndex(input)));
                    break;
                case DELETE:
                    reply.deleteTaskReply(list.getTask(Parser.parseIndex(input)), list.size());
                    list.deleteTask(Parser.parseIndex(input));
                    break;
                case TASK:
                    Task newTask = Parser.parseTask(input);
                    list.store(newTask);
                    reply.storeTaskReply(newTask, list.size());
                    break;
                case UNKNOWN:
                    try {
                        throw new UnknownCommandException();
                    } catch (UnknownCommandException e) {
                        reply.echo(e.toString());
                    }
                    break;
                case LIST:
                    reply.iterate(list);
                    break;
                case FIND:
                    reply.findTaskReply(list.filter(Parser.parseKeyword(input)));
                    break;
                case QUIT:
                    sc.close();
                    talking = false;
                    try {
                        storage.save(list);
                    } catch (IOException e) {
                        System.out.println("Something wrong happened when saving your tasks");
                    }
                    break;
                default:
                    break;

                }

            } catch (EmptyDescriptionException e) {
                reply.echo(e.toString());
            } catch (IndexOutOfBoundsException e) {
                reply.echo("The index you provided does not exist");
            } catch (IllegalFormatException e) {
                reply.echo(e.toString());
            }




        }
        reply.echo("Bye. Hope to see you again soon!");


    }

    public static void main(String[] args) {
        Cracker bot = new Cracker();


        bot.startService();


    }

}
