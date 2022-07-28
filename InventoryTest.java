public class InventoryTest{
    public static void main(String[] args){
        Inventory inventory = new Inventory();
        inventory.addItem("Monitor", 3);
        inventory.addItem("Monitor", 2);
        inventory.addItem("Keyboard", 2);
        inventory.addItem("HardDisk", 12);
        inventory.addItem("Mouse", 4);
        System.out.print("takeItem: ");
        System.out.println(inventory.takeItem("Monitor",8));
        System.out.print("checkInventory: ");
        System.out.println(inventory.checkInventory("Monitor"));
//  TODO       System.out.print("getThreshold: ");
//  TODO      System.out.println(inventory.getThreshold(5));
    }
}