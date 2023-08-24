package org.Store;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.List;

public class ItemQuantitySales {
    private Queue<PurchaseOrder> purchaseOrders;

    public ItemQuantitySales() {
        this.purchaseOrders = new PriorityQueue<>(Comparator.comparingInt(purchaseOrder ->
                purchaseOrder.getItems().size()));
    }

    public void addPurchaseOrder(PurchaseOrder purchaseOrder) {
        purchaseOrders.add(purchaseOrder);
    }

    public void addPurchaseOrders(List<PurchaseOrder> purchaseOrderList) {
        for (PurchaseOrder purchaseOrder : purchaseOrderList) {
            purchaseOrders.add(purchaseOrder);
        }
    }

    public PurchaseOrder processNextPurchaseOrder() {
        return purchaseOrders.poll();
    }

    public boolean isEmpty() {
        return purchaseOrders.isEmpty();
    }

    public int getNumPurchaseOrders() {
        return purchaseOrders.size();
    }
}
