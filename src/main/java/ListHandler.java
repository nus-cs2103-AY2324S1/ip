
public class ListHandler implements ICommandHandler{

    private String[] list;
    private int count;

    public ListHandler(){
        this.list = new String[100];
        this.count = 0;
    }

    @Override
    public void execute(String command) {
        if (!command.equals("list")) {
            if(this.count >= 100){
                Main.getInstance().say("list is full");
            }
            this.list[count] = command;
            count++;
            Main.getInstance().say("added: " + command);
        }
        else{
            int number = 0;
            for(int i = 0;i < this.count; i++){
                Main.getInstance().say((i + 1) + ". " + this.list[i], i == 0, i == count - 1);
            }
        }
    }
}
