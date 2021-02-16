package pl.radomczak.repository;

import pl.radomczak.model.Item;
import pl.radomczak.model.ItemPosition;

import java.util.HashSet;
import java.util.Set;

public class PositionRepository {
    private final HashSet<ItemPosition> itemPositions;

    public PositionRepository(HashSet<ItemPosition> itemPositions) {
        this.itemPositions = itemPositions;
    }

    private void addItemPosition(ItemPosition itemPosition)
    {
        itemPositions.add(itemPosition);
    }

    public void calculateItemPositions(Set<Item> items)
    {

    }
}
