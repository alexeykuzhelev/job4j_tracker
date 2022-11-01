package ru.job4j.tracker.store;

import java.util.ArrayList;
import java.util.List;

import ru.job4j.tracker.model.Item;

public class MemTracker implements Store {

    private final List<Item> items = new ArrayList<>();

    private int id = 0;

    @Override
    public void init() {
    }

    @Override
    public Item add(Item item) {
        item.setId(id++);
        items.add(item);
        return item;
    }

    @Override
    public List<Item> findAll() {
        return items;
    }

    @Override
    public Item findById(int id) {
        int index = indexOf(id);
        return index != -1 ? items.get(index) : null;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        for (Item item : items) {
            if (key.equals(item.getName())) {
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public boolean replace(int id, Item item) {
        int index = indexOf(id);
        if (index == -1) {
            return false;
        }
        item.setId(id);
        items.set(index, item);
        return true;
    }

    @Override
    public boolean delete(int id) {
        int index = indexOf(id);
        if (index == -1) {
            return false;
        }
        items.remove(index);
        return true;
    }

    private int indexOf(int id) {
        int index = -1;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == id) {
                index = i;
                break;
            }
        }
        return index;
    }

}
