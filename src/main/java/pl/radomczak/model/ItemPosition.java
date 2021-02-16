package pl.radomczak.model;

import java.util.Objects;

public class ItemPosition {
    private Item item;
    private double x;
    private double y;

    public ItemPosition(Item item, double x, double y) {
        this.item = item;
        this.x = x;
        this.y = y;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPosition that = (ItemPosition) o;
        return Double.compare(that.x, x) == 0 && Double.compare(that.y, y) == 0 && item.equals(that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, x, y);
    }
}



