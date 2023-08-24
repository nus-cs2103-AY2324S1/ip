public class Task {
    private String text;
    private boolean checked;
    private static String horiLine = "____________________________________________________________";


    public Task(String text){
        this.text = text;
        this.checked = false;
    }

    public String getText(){

        return text;
    }

    public String getChecked(){
        if(checked){
            return "[/]";
        }else{
            return "[]";
        }
    }

    public String getStatusText(){
        String result = getChecked() + " " + getText();
        return result;
    }

    public void mark(){
        checked = true;
        System.out.println(horiLine);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(getStatusText());
        System.out.println(horiLine);
    }
    public void unmark(){
        checked = false;
        System.out.println(horiLine);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(getStatusText());
        System.out.println(horiLine);

    }


}
