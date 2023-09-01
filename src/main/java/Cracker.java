import Exceptions.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Cracker {

    private TodoList list = null;
    private Reply reply = new Reply();
    private Storage storage;
    enum Type {
        MARK,
        TASK,
        DELETE
    }

    public void startService(){
        boolean talking = true;
        FileWriter writer = null;
        storage = new Storage("./data/list.txt");
        list = storage.load();


        Scanner sc = new Scanner(System.in);
        reply.echo("What can I do for you?");
        ArrayList<Object> inLine = new ArrayList<>();
        while(talking){
            Type t = null;

            String input = sc.nextLine();



            try {


                if (input.startsWith("mark")) {
                    t = Type.MARK;
                    int index = Integer.parseInt(input.replace("mark", "").trim()) - 1;
                    list.markDone(index);
                    inLine.add(list.getTaskString(index));
                } else if (input.startsWith("unmark")) {
                    t = Type.MARK;
                    int index = Integer.parseInt(input.replace("unmark", "").trim()) - 1;
                    list.markUndone(index);
                    inLine.add(list.getTaskString(index));
                } else if (input.startsWith("deadline")) {
                    t = Type.TASK;
                    list.store(new Deadline(input.replace("deadline", "").trim()));
                    inLine.add(list.getTaskString(list.size() - 1));

                } else if (input.startsWith("event")) {
                    t = Type.TASK;
                    list.store(new Event(input.replace("event", "").trim()));
                    inLine.add(list.getTaskString(list.size() - 1));
                } else if (input.startsWith("todo")) {
                    t = Type.TASK;
                    list.store(new Todo(input.replace("todo", "").trim()));
                    inLine.add(list.getTaskString(list.size() - 1));
                } else if (input.startsWith("delete")) {
                    t = Type.DELETE;
                    int index = Integer.parseInt(input.replace("delete", "").trim()) - 1;
                    inLine.add(list.getTaskString(index));
                    list.deleteTask(index);
                }else {

                    switch (input) {
                        case "bye":
                            sc.close();
                            talking = false;
                            try{
                                storage.save(list);
                            } catch (IOException e){
                                System.out.println("Something wrong happened when saving your tasks");
                            }
                            break;
                        case "list":
                            reply.iterate(list);
                            break;

                        default:
                            try {
                                throw new UnknownCommandException();
                            } catch (UnknownCommandException e) {
                                reply.echo(e.toString());
                            }

                    }
                }
                if(t == Type.MARK){
                    reply.add("Operation Successful: This is the current state of your task:");
                    for(int i = 0; i < inLine.size();i++){
                        reply.add(inLine.get(i).toString());
                    }
                    reply.echo();

                } else if(t == Type.TASK){
                    reply.add("Got it. I've added this task:");
                    reply.add(inLine.get(0).toString());
                    reply.add("Now you have " + list.size() + " task(s) in the list.");
                    reply.echo();

                } else if(t == Type.DELETE){
                    reply.add("Got it. I've removed this task:");
                    reply.add(inLine.get(0).toString());
                    reply.add("Now you have " + list.size() + " task(s) in the list.");
                    reply.echo();

                }
                inLine.removeAll(inLine);
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
