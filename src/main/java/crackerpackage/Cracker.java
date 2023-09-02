package crackerpackage;

import Exceptions.*;
import UIComponents.Parser;
import UIComponents.Reply;
import crackerpackage.tasks.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A chatbot that manages tasks.
 *
 * @author Anton Tan Hong Zhi
 */
public class Cracker {

    private TodoList list = null;
    private Reply reply = new Reply();
    private Storage storage;
    public enum Type {
        MARK,
        UNMARK,
        TASK,
        DELETE,
        UNKNOWN,
        LIST,
        QUIT
    }

    /**
     * Starts up the bot.
     */
    public void startService(){
        boolean talking = true;
        storage = new Storage("./data/list.txt");
        list = storage.load();
        Scanner sc = new Scanner(System.in);
        reply.echo("What can I do for you?");
        while(talking){
            Type t = null;

            String input = sc.nextLine();



            try {
                Type command = Parser.parseCommand(input);

                switch(command){
                    case MARK:
                        list.markDone(Parser.parseIndex(input));
                        reply.modifyTaskReply(list.getTask(Parser.parseIndex(input)));
                        break;
                    case UNMARK:
                        list.markUndone(Parser.parseIndex(input));
                        reply.modifyTaskReply(list.getTask(Parser.parseIndex(input)));
                        break;
                    case DELETE:
                        list.deleteTask(Parser.parseIndex(input));
                        reply.deleteTaskReply(list.getTask(Parser.parseIndex(input)),list.size());
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
                    case QUIT:
                        sc.close();
                        talking = false;
                        try{
                            storage.save(list);
                        } catch (IOException e){
                            System.out.println("Something wrong happened when saving your tasks");
                        }
                        break;
                    default:
                        break;

                }

            } catch (EmptyDescriptionException e){
                reply.echo(e.toString());
            } catch (IndexOutOfBoundsException e){
                reply.echo("The index you provided does not exist");
            } catch (IllegalFormatException e){
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