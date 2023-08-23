import java.util.Scanner;

public class Cracker {

    private static TodoList list = new TodoList();
    private static Reply reply = new Reply();

    public static void main(String[] args) {
        boolean talking = true;

        reply.echo("What can I do for you?");

        while(talking){
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();

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



        reply.echo("Bye. Hope to see you again soon!");



        
    }

}
