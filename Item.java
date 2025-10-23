public class Item {
    private String type, name;
    private int quantity;

    public Item(String theName, String theType) {
        this.name = theName;
        this.type = theType;
        this.quantity = 1;
    }

    public void display() {
        System.out.println("Name: " + name + " | Type: " + type + " | Amount: " + quantity);
    }
}