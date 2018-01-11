package com.irfanstore.productPrice.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="ProductPrice")
public class ProductPrice implements Serializable {

    public ProductPrice() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(float buyPrice) {
        this.buyPrice = buyPrice;
    }

    public float getMargin() {
        return margin;
    }

    public void setMargin(float margin) {
        this.margin = margin;
    }

    public float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(float sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="product_code")
    private String code;

    @Column(name="buy_price")
    private float buyPrice;

    @Column(name="margin")
    private float margin;

    @Column(name="sell_price")
    private float sellPrice;

    @Column(name="currency")
    private String currency;


    @Override
    public String toString() {
        return "ProductPrice {" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", buyPrice='" + buyPrice + '\'' +
                ", margin='" + margin + '\'' +
                ", sellPrice='" + sellPrice + '\'' +
                ", currency=" + currency +
                '}';
    }
}
