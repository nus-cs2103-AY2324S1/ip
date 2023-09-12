package place;

public class ShoppingPlace extends Place {
    private String name;
    private String desc;

    public ShoppingPlace(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String editDesc(String newDesc) {
        this.desc = newDesc;
        return "This shopping place exists! Description has been updated for you.";
    }
}
