package org.Store;

import java.util.ArrayList;
import java.util.List;

public class PurchaseOrder {
    private Customer customer;
    private List<Item> items;

    public PurchaseOrder(Customer customer) {
        this.customer = customer;
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public Customer getCustomer() {
        return customer;
    }
}
