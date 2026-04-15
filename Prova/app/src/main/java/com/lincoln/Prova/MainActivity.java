package com.lincoln.Prova;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity principal responsável pelo cadastro de produtos.
 * Contém formulário com validações e navegação para a tela de listagem.
 */
public class MainActivity extends AppCompatActivity {
    // Declaração dos campos de entrada de texto e do DAO para persistência
    private EditText editTextName, editTextCode, editTextPrice, editTextQuantity;
    private ProductDao productDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Define o layout XML da tela de cadastro
        setContentView(R.layout.activity_main);

        // Mapeamento dos componentes da interface com os IDs do XML
        editTextName = findViewById(R.id.editTextName);
        editTextCode = findViewById(R.id.editTextCode);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextQuantity = findViewById(R.id.editTextQuantity);
        Button buttonSave = findViewById(R.id.buttonSave);
        Button buttonReport = findViewById(R.id.buttonReport);

        // Inicializa o acesso ao banco de dados Room
        productDao = ProductDatabase.getInstance(this).productDao();

        // Configura a ação de clique do botão "Cadastrar Produto"
        buttonSave.setOnClickListener(v -> saveProduct());

        // Configura a ação de clique do botão para ver a listagem
        buttonReport.setOnClickListener(v -> {
            // Cria uma Intent para abrir a Activity de listagem
            startActivity(new Intent(MainActivity.this, ProductListingActivity.class));
        });
    }

    // Valida os campos do formulário e salva o produto no banco de dados
    private void saveProduct() {
        // Captura e remove espaços extras dos textos inseridos
        String name = editTextName.getText().toString().trim();
        String code = editTextCode.getText().toString().trim();
        String priceStr = editTextPrice.getText().toString().trim();
        String quantityStr = editTextQuantity.getText().toString().trim();

        // Validação: Nenhum campo pode estar em branco
        if (name.isEmpty() || code.isEmpty() || priceStr.isEmpty() || quantityStr.isEmpty()) {
            Toast.makeText(this, "Nenhum campo pode ser deixado em branco", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validação do campo Preço
        double price;
        try {
            price = Double.parseDouble(priceStr);
            // Deve ser um valor positivo
            if (price <= 0) {
                Toast.makeText(this, "O preço deve ser um número positivo", Toast.LENGTH_SHORT).show();
                return;
            }
            // Deve ter no máximo duas casas decimais
            if (priceStr.contains(".") && priceStr.substring(priceStr.indexOf(".") + 1).length() > 2) {
                Toast.makeText(this, "O preço deve ter no máximo duas casas decimais", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Preço inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validação do campo Quantidade
        int quantity;
        try {
            quantity = Integer.parseInt(quantityStr);
            // Deve ser um número inteiro positivo
            if (quantity <= 0) {
                Toast.makeText(this, "A quantidade deve ser um número inteiro positivo", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Quantidade inválida", Toast.LENGTH_SHORT).show();
            return;
        }

        // Cria o objeto de produto e realiza a inserção no banco de dados
        Product product = new Product(name, code, price, quantity);
        productDao.insert(product);

        // Feedback visual para o usuário
        Toast.makeText(this, "Produto cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
        // Limpa os campos para um novo cadastro
        clearFields();
    }

    // Limpa todos os campos de entrada do formulário.

    private void clearFields() {
        editTextName.setText("");
        editTextCode.setText("");
        editTextPrice.setText("");
        editTextQuantity.setText("");
    }
}
