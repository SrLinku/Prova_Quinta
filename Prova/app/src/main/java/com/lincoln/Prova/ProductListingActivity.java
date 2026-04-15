package com.lincoln.Prova;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import java.util.Locale;

public class ProductListingActivity extends AppCompatActivity {
    private TextView textViewListing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_listing);

        textViewListing = findViewById(R.id.textViewListing);
        Button btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> finish());

        ProductDatabase db = ProductDatabase.getInstance(this);
        ProductDao productDao = db.productDao();

        List<Product> productList = productDao.getAllProducts();
        StringBuilder report = new StringBuilder();

        if (productList.isEmpty()) {
            report.append("Nenhum produto cadastrado.");
        } else {
            for (Product product : productList) {
                report.append("Nome: ").append(product.getName()).append("\n")
                      .append("Código: ").append(product.getCode()).append("\n")
                      .append(String.format(Locale.getDefault(), "Preço: R$ %.2f", product.getPrice())).append("\n")
                      .append("Estoque: ").append(product.getQuantity()).append("\n")
                      .append("-----------------------------\n");
            }
        }

        textViewListing.setText(report.toString());
    }
}
