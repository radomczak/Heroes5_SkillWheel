package pl.radomczak.repository;

import pl.radomczak.model.Item;
import pl.radomczak.model.ItemPosition;

import java.util.Collection;
import java.util.Set;

public class PositionRepository {
    private final Collection<ItemPosition> itemPositions;

    public PositionRepository(Collection<ItemPosition> itemPositions) {
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
