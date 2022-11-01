package ru.job4j.tracker.store;

import java.util.List;

import org.junit.Test;
import ru.job4j.tracker.model.Item;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class MemTrackerTest {

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Store memTracker = new MemTracker();
        Item item = new Item("test1");
        memTracker.add(item);
        Item result = memTracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenFindAll() {
        Store memTracker = new MemTracker();
        Item item1 = new Item("first");
        Item item2 = new Item("second");
        memTracker.add(item1);
        memTracker.add(item2);
        List<Item> expected = List.of(item1, item2);
        List<Item> result = memTracker.findAll();
        assertThat(result, is(expected));
    }

    @Test
    public void whenFindByName() {
        Store memTracker = new MemTracker();
        Item item1 = new Item("first");
        Item item2 = new Item("second");
        Item item3 = new Item("first");
        memTracker.add(item1);
        memTracker.add(item2);
        memTracker.add(item3);
        List<Item> expected = List.of(item1, item3);
        List<Item> result = memTracker.findByName("first");
        assertThat(result, is(expected));
    }

    @Test
    public void whenFindById() {
        Store memTracker = new MemTracker();
        Item item1 = new Item("first");
        Item item2 = new Item("second");
        Item item3 = new Item("first");
        memTracker.add(item1);
        memTracker.add(item2);
        memTracker.add(item3);
        Item result = memTracker.findById(item2.getId());
        assertThat(result, is(item2));
    }

    @Test
    public void whenFindByIdNotFound() {
        Store memTracker = new MemTracker();
        Item item1 = new Item("first");
        Item item2 = new Item("second");
        Item item3 = new Item("first");
        memTracker.add(item1);
        memTracker.add(item2);
        memTracker.add(item3);
        Item result = memTracker.findById(-1);
        assertThat(result, is(nullValue()));
    }

    @Test
    public void whenReplace() {
        Store memTracker = new MemTracker();
        Item item1 = new Item("first");
        memTracker.add(item1);
        memTracker.replace(item1.getId(), new Item("second"));
        assertThat(memTracker.findById(item1.getId()).getName(), is("second"));
    }

    @Test
    public void whenDelete() {
        Store memTracker = new MemTracker();
        Item item1 = new Item("first");
        memTracker.add(item1);
        memTracker.delete(item1.getId());
        assertThat(memTracker.findById(item1.getId()), is(nullValue()));
    }

}
