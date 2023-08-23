
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
        String[] store = new String[100];
        Boolean[] done = new Boolean[100];
        for (int i = 0; i < 100; i++) {
            done[i] = false;
        }
        Integer index = 0;

        System.out.println(hello);
        
        while (true) {
            String input = System.console().readLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println(line);

            if (input.startsWith("mark")) {
                Integer unchecked = Integer.valueOf(input.replace("mark ", ""));
                System.out.println("    yay, one task down: ");
                done[unchecked] = true;
                System.out.println("    [X] " + store[unchecked]);
                System.out.println(line);
                continue;
            } 
            
            if (input.startsWith("unmark")){
                Integer checked = Integer.valueOf(input.replace("unmark ", ""));
                System.out.println("    ok, this task is not done yet: ");
                done[checked] = false;
                System.out.println("    [ ] " + store[checked]);
                System.out.println(line);
                continue;
            }

            if (input.equals("list")) {
                System.out.println("    you need to get these done today:");
                for (int i = 0; i < index; i++) {
                    
                    String checkbox = done[i] ? "[X] " : "[ ] ";
                    System.out.println("    " + (i + 1) + ". " + checkbox + store[i]);

                }
                System.out.println(line);
                continue;
            }
                
            store[index] = input;
            index ++;
            
            System.out.println("    " 
                        + "added: "
                        + input);
                
            System.out.println(line);
        }

        System.out.println(bye);
        
     
    }
}
