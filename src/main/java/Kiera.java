
public class Kiera {
    public static void main(String[] args) {
        String name = "Kiera";
        String line = "    -------------------------------------";
        String hello = line
                + "\n"
                + "    " 
                + "hi, it's kiera.\n" 
                + "    " 
                + "what do you want?\n"
                + line;
        String bye =  line
                + "\n"
                + "    " 
                + "muaks! <3\n"
                + line;
        Task[] store = new Task[100];
        Integer index = 0;

        System.out.println(hello);
        
        while (true) {
            String input = System.console().readLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println(line);

            if (input.startsWith("mark")) {
                Integer unchecked = Integer.valueOf(input.replace("mark ", "")) - 1;
                Task t = store[unchecked];

                t.markAsDone();

                String status = t.getStatusIcon();
                String desc = t.getDescription();

                System.out.println("    yay, one task down: \n"
                            + "    "
                            + "["
                            + status
                            + "] "
                            + desc
                            + "\n"
                            + line);
                continue;
            } 
            
            if (input.startsWith("unmark")){
                Integer checked = Integer.valueOf(input.replace("mark ", "")) - 1;
                Task t = store[checked];

                t.markAsUndone();

                String status = t.getStatusIcon();
                String desc = t.getDescription();

                System.out.println("    ok, this task is not done yet: \n"
                            + "    "
                            + "["
                            + status
                            + "] "
                            + desc
                            + "\n"
                            + line);
                continue;
            }

            if (input.equals("list")) {
                System.out.println("    you need to get these done today:");
                for (int i = 0; i < index; i++) {
                    Task t = store[i];
                    String status = t.getStatusIcon();
                    String desc = t.getDescription();

                    System.out.println("    " + (i + 1) + ". ["  +  status + "] " + desc);

                }
                System.out.println(line);
                continue;
            }
                
            Task t = new Task(input);
            store[index] = t;
            index ++;
            
            System.out.println("    " 
                        + "added: "
                        + t.getDescription());
                
            System.out.println(line);
        }

        System.out.println(bye);
        
     
    }
}
