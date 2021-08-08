/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuancq.dto;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ADmin
 */
public class CartDTO {
    private Map<String, ProductDTO> cart;

    public CartDTO() {
    }

    public CartDTO(Map<String, ProductDTO> cart) {
        this.cart = cart;
    }

    public Map<String, ProductDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String, ProductDTO> cart) {
        this.cart = cart;
    }
    
    public void add(ProductDTO product) {
        if(this.cart == null) {
            this.cart = new HashMap<>();
        }
        int quantity = 1;
        product.setQuantity(quantity);
        if(this.cart.containsKey(product.getProductID())) {
            int oldQuantity = this.cart.get(product.getProductID()).getQuantity();
            quantity += oldQuantity;
            product.setQuantity(quantity);        
        }
        this.cart.put(product.getProductID(), product);
    }
    
    public void deleteCart(String productID) {
        int oldQuantity = ((ProductDTO) this.cart.get(productID)).getQuantity();
            if(oldQuantity > 1) {
                this.cart.get(productID).setQuantity(oldQuantity - 1);
            }else{
                this.cart.remove(productID);
            }
        
    }
    
    public float totalPro() {
        float total = 0;
        for(ProductDTO dto : this.cart.values()) {
            total += dto.getPrice() * dto.getQuantity();  
        }
        return total;
    }

    public void removeOneCart(ProductDTO dto) {
       if(this.cart == null) {
           return;
       }
       if(this.cart.containsKey(dto.getProductID())){
           int quantityAdd = this.cart.get(dto.getProductID()).getQuantity() - 1;
          if(quantityAdd == 0) {
              this.cart.remove(dto.getProductID());
          }else{
              dto.setQuantity(quantityAdd);
              this.cart.put(dto.getProductID(), dto);
          }
       }
    }
}
