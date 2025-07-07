package lk.jiat.ee.core.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "Product.FindByName" ,query = "select p from Product p where p.name=:name"),
        @NamedQuery(name = "Product.FindByCategory" ,query = "select p from Product p where p.category=:category"),
        @NamedQuery(name = "Product.FindAll" ,query = "select p from Product p"),
})
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Double quantity;
    private String category;

    public Product() {
    }

    public Product(String name, String description, Double price, Double quantity, String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


}
