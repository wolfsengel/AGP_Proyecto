package agp.sanclemen.agp_proyecto.view;

import agp.sanclemen.agp_proyecto.DAO.*;
import agp.sanclemen.agp_proyecto.model.*;
import agp.sanclemen.agp_proyecto.factory.EntityManagerFactorySingleton;
import jakarta.persistence.EntityManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableView<Product> productsTable;
    @FXML
    private TableColumn<Product,Long> id;
    @FXML
    private TableColumn<Product,String> name;
    @FXML
    private TableColumn<Product,String> description;
    @FXML
    private TableColumn<Product,Integer> price;
    @FXML
    private TableColumn<Product,Integer> stock;
    @FXML
    private TableColumn<Product,String> category;

    @FXML
    public void initialize() {
        // Client Loader
        loadDate();
        loadProducts();
        loadCarts();
        // Manager Loader
        loadManagerProducts();
    }

    private void loadManagerProducts() {
        // Obtener la lista de productos desde el DAO
        List<Product> products = productDAO.getAll();
        // Crear una lista observable de productos
        ObservableList<Product> productObservableList = FXCollections.observableArrayList();
        for (Product product : products) {
            productObservableList.add(product);
        }
        // Configurar la lista observable en el TableView
        productsTable.setItems(productObservableList);
        // Configurar las columnas del TableView
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
    }

    private void loadDate() {
        // Obtener la fecha actual
        // Configurar la fecha actual en el Label
        dateLabel.setText(""+java.time.LocalDate.now());
    }

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

}
