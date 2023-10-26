package place;

public class FoodPlace extends Place {

    private String name;
    private String desc;

    public FoodPlace(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String editDesc(String newDesc) {
        desc = newDesc;
        return "This food place exists! Description has been updated for you.";
    }

    @Override
    public String toString() {
        return this.desc;
    }
}
