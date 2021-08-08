/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuancq.dto;

/**
 *
 * @author ADmin
 */
public class ProductError {

    private String productIDErr;
    private String productNameErr;
    private String price;
    private String quantityErr;

    public ProductError() {
    }

    public ProductError(String productIDErr, String productNameErr, String price, String quantityErr) {
        this.productIDErr = productIDErr;
        this.productNameErr = productNameErr;
        this.price = price;
        this.quantityErr = quantityErr;
    }

    public String getProductIDErr() {
        return productIDErr;
    }

    public void setProductIDErr(String productIDErr) {
        this.productIDErr = productIDErr;
    }

    public String getProductNameErr() {
        return productNameErr;
    }

    public void setProductNameErr(String productNameErr) {
        this.productNameErr = productNameErr;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantityErr() {
        return quantityErr;
    }

    public void setQuantityErr(String quantityErr) {
        this.quantityErr = quantityErr;
    }

}
