package org.Store;

import org.Store.PurchaseOrder;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.List;

public class CustomerFIFOSales {
    private Queue<PurchaseOrder> purchaseOrders;

    public CustomerFIFOSales() {
        this.purchaseOrders = new PriorityQueue<>((po1, po2) -> {
            if (po1.getCustomer().getName().equals(po2.getCustomer().getName())) {
                // If the customers have the same name, maintain FIFO order
                return 0;
            } else {
                // Otherwise, use the default ordering of PurchaseOrder objects
                return po1.hashCode() - po2.hashCode();
            }
        });
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
