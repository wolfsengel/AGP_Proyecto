package agp.sanclemen.agp_proyecto.view;

import agp.sanclemen.agp_proyecto.DAO.*;
import agp.sanclemen.agp_proyecto.DTO.ProductDTO;
import agp.sanclemen.agp_proyecto.model.*;
import agp.sanclemen.agp_proyecto.factory.EntityManagerFactorySingleton;
import jakarta.persistence.EntityManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class AppController {

    // DAO's & EntityManager
    EntityManager entityManager = EntityManagerFactorySingleton.getEntityManagerFactory().createEntityManager();
    private final ProductDAO productDAO = new ProductDAO(entityManager);
    private final CategoryDAO categoryDAO = new CategoryDAO(entityManager);
    private final CustomerDAO customerDAO = new CustomerDAO(entityManager);
    private final CartDAO cartDAO = new CartDAO(entityManager);
    private final CartItemDAO cartItemDAO = new CartItemDAO(entityManager);

    // FXML stuff
    @FXML
    private ListView<String> productsList;
    @FXML
    private ListView<String> cartList;
    @FXML
    private ComboBox<String> cartsComboBox;
    @FXML
    private Label dateLabel;
    // Table Stuff
    @FXML
    private TableView<ProductDTO> productsTable;
    @FXML
    private TableColumn<ProductDTO,Long> id;
    @FXML
    private TableColumn<ProductDTO,String> name;
    @FXML
    private TableColumn<ProductDTO,String> description;
    @FXML
    private TableColumn<ProductDTO,Integer> price;
    @FXML
    private TableColumn<ProductDTO,Integer> stock;
    @FXML
    private TableColumn<ProductDTO,String> category;
    @FXML
    private Label totalProducts;

    private final ObservableList<ProductDTO> productObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Client Loader
        loadDate();
        loadProducts();
        loadCarts();
        // Manager Loader
        loadManagerProducts();
    }

    // Carga la tabla y la suma de productos
    private void loadManagerProducts() {
        // Obtener la lista de productos desde el DAO
        List<ProductDTO> products = productDAO.getAllProducts();
        // Creandoo una lista observable de productos
        productObservableList.addAll(products);
        // Configurando la lista observable en el TableView
        productsTable.setItems(productObservableList);
        // Configuran2 las columnas del TableView
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        stock.setCellValueFactory(new PropertyValueFactory<>("stockQty"));
        category.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        // Configurando el tooltip de la tabla
        productsTable.setRowFactory(tv -> {
            TableRow<ProductDTO> row = new TableRow<>();
            Tooltip tooltip = new Tooltip();
            row.setOnMouseEntered(event -> {
                ProductDTO product = row.getItem();
                if (product != null) {
                    tooltip.setText(product.getCategoryDescription());
                    row.setTooltip(tooltip);
                }
            });
            return row;
        });
        totalProducts.setText(products.size()+"");
    }

    // Carga la fecha actual
    private void loadDate() {
        // Obtener la fecha actual
        // Configurar la fecha actual en el Label
        dateLabel.setText(""+java.time.LocalDate.now());
    }

    // Carga los carritos en el ComboBox
    private void loadCarts() {
        // Obtener la lista de carritos desde el DAO
        List<Cart> carts = cartDAO.getAll();
        // Crear una lista observable de nombres de carritos
        ObservableList<String> cartNames = FXCollections.observableArrayList();
        for (Cart cart : carts) {
            cartNames.add(cart.getName());
        }
        // Configurar la lista observable en el ComboBox
        cartsComboBox.setItems(cartNames);
    }

    // Carga los productos en el ListView del cliente
    private void loadProducts() {
        // Obtener la lista de productos desde el DAO
        List<Product> products = productDAO.getAll();
        // Crear una lista observable de nombres de productos
        ObservableList<String> productNames = FXCollections.observableArrayList();
        for (Product product : products) {
            productNames.add(product.getName());
        }
        // Configurar la lista observable en el ListView
        productsList.setItems(productNames);
    }

    // Agrega un producto a la tabla
    @FXML
    public void addNewProduct(){
        // Alert window with the form
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Add Product");
        alert.setHeaderText("Add a new product");
        alert.setContentText("Please enter the product details");
        // Form
        TextField name = new TextField();
        name.setPromptText("Name");
        TextField description = new TextField();
        description.setPromptText("Description");
        TextField price = new TextField();
        price.setPromptText("Price");
        TextField stock = new TextField();
        stock.setPromptText("Stock");
        ComboBox<String> category = new ComboBox<>();
        category.setPromptText("Category");
        List<Category> categories = categoryDAO.getAll();
        for (Category c : categories) {
            category.getItems().add(c.getName());
        }
        // Adding the form to the alert
        alert.getDialogPane().setContent(new VBox(8, name, description, price, stock, category));
        // Show the alert and wait for the user to close it
        alert.showAndWait();
        // If the user clicked OK
        if (alert.getResult() == ButtonType.OK) {
            // Create a new product
            Product product = new Product();
            product.setId(null);
            product.setName(name.getText());
            product.setDescription(description.getText());
            product.setPrice(Double.parseDouble(price.getText()));
            product.setStockQty(Integer.parseInt(stock.getText()));
            product.setCategory(categories.get(category.getSelectionModel().getSelectedIndex()));
            product.setLastUpdated(new java.sql.Date(System.currentTimeMillis()));
            // Check if the user filled all the fields
            if (name.getText() == null || description.getText() == null || price.getText() == null || stock.getText() == null || category.getSelectionModel().getSelectedItem() == null){
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error");
                error.setHeaderText("Error adding the product");
                error.setContentText("Please fill all the fields");
                error.showAndWait();
            } else {
                // Save the product in the database
                productDAO.save(product);
                // Reload the products
                loadManagerProducts();
            }
        }
    }

}
