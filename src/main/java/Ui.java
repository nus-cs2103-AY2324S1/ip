public class Ui {
    String line = "~~*~~*~~*~~*~~*~~*~~*~~*~~*~~\n";

    public void chadGreet() {
        System.out.println(line);
        System.out.println("Yo! This is CHADbot\n");
        System.out.println("Need sum help?\n");
        System.out.println(line);
    }
    public void chadBye() {
        System.out.println(line);
        System.out.println("Cya l8r~\n");
        System.out.println(line);
    }

    public void chadOutput(String input) {
        System.out.println(line);
        System.out.println(input + "\n");
        System.out.println(line);
    }

    public void chadAddListOutput(String input){
        System.out.println(line);
        System.out.println(input + " has been added to yo list!\n");
        System.out.println(line);
    }

    public void chadExceptionOutput(String input){
        System.out.println(line);
        System.out.println(input + "\n");
        System.out.println(line);
    }

    public void chadRemoveOutput(String input, int size){
        System.out.println("Okay! I have removed this task :\n" + input);

        if (size == 0){
            System.out.println("Your list is currently empty! Good job :)");
        } else {
            System.out.println("Your list is currently " + size + " long... Get back to work!");
        }
    }

    public void chadMarkTaskOutput(String task, String mark){
        System.out.println(line);
        System.out.println("Good job! Task fulfilled!");
        System.out.println(task + " [" + mark + "]\n");
        System.out.println(line);
    }

    public void chadUnmarkTaskOutput(String task, String mark){
        System.out.println(line);
        System.out.println("Boooo! Task is not done!");
        System.out.println(task + " [" + mark + "]\n");
        System.out.println(line);
    }

}
