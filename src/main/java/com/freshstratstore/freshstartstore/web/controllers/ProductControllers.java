package com.freshstratstore.freshstartstore.web.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freshstratstore.freshstartstore.doa.entities.Products;
import com.freshstratstore.freshstartstore.web.models.request.ProductsForm;

@RestController
@RequestMapping("/products")
public class ProductControllers {

    private static List<Products> products = new ArrayList<Products>();
    private static Long idCount = 0L;
    static {
        products.add(new Products(++idCount, "SS-S9", "Samsung Galaxy S9", "hello from description", 500D, 50,
                "samsung-s9.png"));
        products.add(new Products(++idCount, "NK-5P", "Nokia 5.1 Plus", "hello from description", 60D, 60, null));
        products.add(new Products(++idCount, "IP-7", "iPhone 7", "hello from description", 600D, 30, "iphone-7.png"));
    }

    @GetMapping()
    public ResponseEntity<Object> getAllProduct() {
        if (this.products.isEmpty())
            return new ResponseEntity<>("list product is empty", HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(this.products, HttpStatus.OK);
    }

    @GetMapping("/sorted")
    public ResponseEntity<Object> getProductSortedByPriceAsc() {

        if (this.products.isEmpty())
            return new ResponseEntity<>("list product is empty", HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(this.sortProductByPrice(), HttpStatus.OK);
    }

    @GetMapping("/sortedDesc")
    public ResponseEntity<Object> getProductSortedByPriceDesc() {

        if (this.products.isEmpty())
            return new ResponseEntity<>("list product is empty", HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(this.sortProductByPriceDesc(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteProductById(@PathVariable("id") Long id) {
        Products productFound = null;
        try {
            productFound = this.findProductById(id);
            this.products.remove(productFound);
            return new ResponseEntity("Product is deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Product not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<Object> addProduct(@RequestBody() ProductsForm productForm) {
        this.products.add(new Products(++idCount, productForm.getCode(), productForm.getName(),
                productForm.getDescription(), productForm.getPrice(),
                productForm.getQuantity(), productForm.getImage()));
        return new ResponseEntity<>("Product is created !", HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateProduct(@RequestBody ProductsForm productForm, @PathVariable("id") Long id) {
        Products productFound = null;
        try {
            productFound = this.findProductById(id);
            productFound.setCode(productForm.getCode());
            productFound.setName(productForm.getName());
            productFound.setPrice(productForm.getPrice());
            productFound.setQuantity(productForm.getQuantity());
            productFound.setImage(productForm.getImage());
            return new ResponseEntity<>(productFound, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Product not found", HttpStatus.NOT_FOUND);
        }
    }

    private Products findProductById(Long id) {

        return this.products.stream().filter(p -> p.getId() == id).findFirst().get();
    }

    private Products findProductByCode(String code) {
        return this.products.stream().filter(p -> p.getCode().equals(code)).findFirst().get();
    }

    private ArrayList<Products> sortProductByPrice() {
        ArrayList<Products> sortedListProduct = new ArrayList<>(this.products);
        Collections.sort(sortedListProduct, (p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
        return sortedListProduct;
    }

    private ArrayList<Products> sortProductByPriceDesc() {
        ArrayList<Products> sortedListProduct = new ArrayList<>(this.products);
        Collections.sort(sortedListProduct, (p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
        return sortedListProduct;
    }
}
