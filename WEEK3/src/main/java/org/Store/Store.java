package org.Store;

import org.Store.PurchaseOrder;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Store {
    private Queue<PurchaseOrder> purchaseOrders;

    public Store() {
        this.purchaseOrders = new LinkedList<>();
    }

    public void addPurchaseOrder(PurchaseOrder purchaseOrder) {
        purchaseOrders.add(purchaseOrder);
    }

    public PurchaseOrder processNextPurchaseOrder() {
        return purchaseOrders.poll();
    }
}
