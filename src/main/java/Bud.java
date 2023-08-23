
public class Bud {
    public static void main(String[] args) {
        String name = "Bud";
        String line = "-------------------------------------\n";
        String hello = "    " 
                + line
                + "    " 
                + "Hey! It's your buddy, " + name + "!\n" 
                + "    " 
                + "What's up?\n"
                + "    " 
                + line;
        String bye =  "    "  
                + line
                + "    " 
                + "See ya!\n"
                + "    " 
                + line;
        System.out.println(hello);
        
        while (true) {
            String input = System.console().readLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println("    " 
                        + line
                        + "    " 
                        + input
                        + "\n"
                        + "    " 
                        + line);
        }
        System.out.println(bye);
        
        
    }
}
