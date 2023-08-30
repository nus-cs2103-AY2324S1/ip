public class Task {
    private String text;
    private String type = "";
    private boolean checked;
    public final static String horiLine = "____________________________________________________________";


    public Task(String text){
        this.text = text;
        this.checked = false;
    }
    public Task(String text,boolean checked){
        this.text = text;
        this.checked = checked;
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
    public String getType(){
        String result = "[" + type +"]";
        return result;
    }

    public String getTypeCheckedText(){
        String result = getType() + getChecked() + " " + getText();
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
    public void setType(String text){
        type = text;
    }

    public String getParsed(){
        String result = this.type + ";" + this.text + ";" + this.checked;
        return result;
    }



}
