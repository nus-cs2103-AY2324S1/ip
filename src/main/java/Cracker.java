import java.util.Scanner;

public class Cracker {

    private TodoList list = new TodoList();
    private Reply reply = new Reply();

    public void startService(){
        boolean talking = true;

        reply.echo("What can I do for you?");

        while(talking){
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();

            if(input.startsWith("mark")){
                int index = Integer.parseInt(input.replace("mark","").trim()) - 1;
                list.markDone(index);
                reply.echo("Nice! I've marked this task as done: \n  " + list.getTask(index));
            } else if(input.startsWith("unmark")){
                int index = Integer.parseInt(input.replace("unmark","").trim()) - 1;
                list.markunDone(index);
                reply.echo("Ok, I've marked this task as not done yet: \n  " + list.getTask(index));
            }
            else {

                switch (input){
                    case "bye":
                        sc.close();
                        talking = false;
                        break;
                    case "list":
                        reply.iterate(list);
                        break;

                    default:
                        list.store(input);
                        reply.echo("added: " + input);

                }
            }



        }
        reply.echo("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Cracker bot = new Cracker();
        bot.startService();
    }

}
