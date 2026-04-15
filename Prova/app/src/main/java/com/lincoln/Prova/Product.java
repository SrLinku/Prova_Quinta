package com.lincoln.Prova;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entidade que representa a tabela de produtos no banco de dados Room.
 * Cada instância desta classe corresponde a uma linha na tabela "products".
 */
@Entity(tableName = "products")
public class Product {
    // Chave primária autoincrementada para identificação única do produto
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;      // Nome do produto
    private String code;      // Código alfanumérico do produto
    private double price;     // Preço em reais
    private int quantity;    // Quantidade em estoque

    /**
     * Construtor da classe Product.
     * @param name Nome do produto
     * @param code Código alfanumérico
     * @param price Preço positivo
     * @param quantity Quantidade inteira positiva
     */
    public Product(String name, String code, double price, int quantity) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters e Setters necessários para o Room e para o uso nas Activities

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
