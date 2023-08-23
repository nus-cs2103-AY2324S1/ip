
public class Kiera {
    public static void main(String[] args) {
        String name = "Kiera";
        String line = "    -------------------------------------";
        String hello = line
                + "\n"
                + "    " 
                + "hi.\n" 
                + "    " 
                + "what you want?\n"
                + line;
        String bye =  line
                + "\n"
                + "    " 
                + "muaks! <3\n"
                + line;
        String[] store = new String[100];
        Integer index = 0;

        System.out.println(hello);
        
        while (true) {
            String input = System.console().readLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println(line);
            if (input.equals("list")) {
                for (int i = 0; i < index; i++) {
                    System.out.println("    "
                                + (i + 1)
                                + ". "
                                + store[i]);
                }
                
            } else {
                store[index] = input;
                index ++;
            
                System.out.println("    " 
                            + "added: "
                            + input);
                
            }
            System.out.println(line);
        }
        System.out.println(bye);
        
        
    }
}
