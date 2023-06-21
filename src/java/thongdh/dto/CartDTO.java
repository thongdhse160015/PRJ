package thongdh.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author thongdhse160015
 */
public class CartDTO implements Serializable {

    private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }

    public boolean addItemToCart(String sku, int quantity) {
        boolean result = false;
        //1. check data validation 
        if (sku == null) {
            return result;
        }
        if (sku.trim().isEmpty()) {
            return result;
        }
        if (quantity <= 0) {
            return result;
        }
        //2. check existed items
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        //3. Check existed item
        if (this.items.containsKey(sku)) {
            int currentQuantity = this.items.get(sku);
            quantity = quantity + currentQuantity;
        }//end item has existed
        //4. Update items
        this.items.put(sku, quantity);
        result = true;
        return result;
    }

    public boolean removeItemFromCart(String sku, int quantity) {
        boolean result = false;
        //1. check data validation
        if (sku == null) {
            return result;
        }
        if (sku.trim().isEmpty()) {
            return result;
        }
        if (quantity <= 0) {
            return result;
        }
        //2. check existed items
        if (this.items == null) {
            return result;
        }
        //3. check existed item
        if (!this.items.containsKey(sku)) {
            return result;
        }
        //4. decrease quantity
        int currentQuantity = this.items.get(sku);
        if (currentQuantity >= quantity) {
            quantity = currentQuantity - quantity;
        }
        if (quantity == 0) {
            this.items.remove(sku);
            if (this.items.isEmpty()) {
                this.items = null;
            }//Ko bao gio chap nhan mot doi tuong ma` ben trong ko chua gi ca
        } else {
            this.items.put(sku, quantity);
        }
        result = true;

        return result;
    }
}
