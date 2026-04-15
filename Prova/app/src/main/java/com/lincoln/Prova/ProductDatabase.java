package com.lincoln.Prova;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Classe que representa o Banco de Dados Room para Produtos.
 * Utiliza o padrão Singleton para garantir que apenas uma instância do banco seja criada.
 */
@Database(entities = {Product.class}, version = 1)
public abstract class ProductDatabase extends RoomDatabase {
    private static ProductDatabase instance;

    /**
     * Retorna o DAO de produtos para interagir com o banco de dados.
     * @return ProductDao implementado automaticamente pelo Room
     */
    public abstract ProductDao productDao();

    /**
     * Obtém a instância única do banco de dados (Singleton).
     * @param context Contexto da aplicação
     * @return Instância única de ProductDatabase
     */
    public static synchronized ProductDatabase getInstance(Context context) {
        if (instance == null) {
            // Cria a instância do banco usando o builder do Room
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            ProductDatabase.class, "product_database")
                    .fallbackToDestructiveMigration() // Recria o banco se houver mudança de versão sem migração
                    .allowMainThreadQueries() // Permite consultas na main thread (usado para simplicidade nesta prova)
                    .build();
        }
        return instance;
    }
}
