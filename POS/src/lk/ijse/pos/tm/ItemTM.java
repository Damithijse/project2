package lk.ijse.pos.tm;

import javafx.scene.control.Button;

public class ItemTM {
    private String code;
    private String description;
    private double uprice;
    private int qtyOnHand;
    private Button button;

    public ItemTM(String code, String description, double uprice, int qtyOnHand, Button button) {
        this.code = code;
        this.description = description;
        this.uprice = uprice;
        this.qtyOnHand = qtyOnHand;
        this.button = button;
    }

    public ItemTM(String code) {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUprice() {
        return uprice;
    }

    public void setUprice(double uprice) {
        this.uprice = uprice;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    @Override
    public String toString() {
        return "ItemTM{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", uprice=" + uprice +
                ", qtyOnHand=" + qtyOnHand +
                ", button=" + button +
                '}';
    }
}
