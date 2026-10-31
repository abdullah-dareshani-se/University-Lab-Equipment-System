
package universitylab.model;

public class Equipment {

    private String equipmentId;
    private String name;
    private String category;
    private int totalQuantity;
    private int availableQuantity;

    public Equipment(String equipmentId, String name,
                     String category, int totalQuantity) {

        this.equipmentId = equipmentId;
        this.name = name;
        this.category = category;
        this.totalQuantity = totalQuantity;
        this.availableQuantity = totalQuantity;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    @Override
    public String toString() {
        return "Equipment ID: " + equipmentId +
                "\nName: " + name +
                "\nCategory: " + category +
                "\nTotal Quantity: " + totalQuantity +
                "\nAvailable Quantity: " + availableQuantity;
    }
}
