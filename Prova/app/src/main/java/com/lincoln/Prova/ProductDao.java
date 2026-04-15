package com.lincoln.Prova;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

/**
 * Interface Data Access Object (DAO) para a entidade Product.
 * Define as operações de banco de dados, como inserção e consulta.
 */
@Dao
public interface ProductDao {
    /**
     * Insere um novo produto na tabela "products".
     * @param product Objeto contendo os dados do produto a ser salvo
     */
    @Insert
    void insert(Product product);

    /**
     * Recupera todos os produtos cadastrados no banco de dados.
     * @return Lista contendo todos os objetos Product encontrados
     */
    @Query("SELECT * FROM products")
    List<Product> getAllProducts();
}
