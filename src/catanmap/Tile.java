package catanmap;


//  A tile should have a position, a resource type, and a number
// Each tile has 6 points, each can have a settlement or city
public class Tile {
    private String resourceType;
    private int position;
    private int number;


    public Tile(String resourceType, int position, int number) {
        this.resourceType = resourceType;
        this.position = position;
        this.number = number;
    }

    public String getResourceType() {
        return resourceType;
    }

    public int getPosition() {
        return position;
    }

    public int getNumber() {
        return number;
    }
}
