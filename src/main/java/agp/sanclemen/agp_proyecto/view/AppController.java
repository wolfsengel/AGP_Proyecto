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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class AppController {

    // DAO's & EntityManager
    EntityManager entityManager = EntityManagerFactorySingleton.getEntityManagerFactory().createEntityManager();
    private final DAO_DTO<Product, ProductDTO> productDAO = new ProductDAO(entityManager);
    private final DAO<Category> categoryDAO = new CategoryDAO(entityManager);
    private final DAO<Customer> customerDAO = new CustomerDAO(entityManager);
    private final DAO<CartItem> cartItemDAO = new CartItemDAO(entityManager);
    private final DAO<ProductPhoto> productPhotoDAO = new ProductPhotoDAO(entityManager);

    // FXML stuff
    @FXML
    private ListView<String> productsList;
    List<Customer> customers;
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
        loadClients();
        // Manager Loader
        loadManagerProducts();
    }

    // Carga la tabla y la suma de productos
    private void loadManagerProducts() {
        // Obtener la lista de productos desde el DAO
        List<ProductDTO> products = productDAO.getAllDTO();
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
    private void loadClients() {
        // Obtener la lista de clientes desde el DAO
        customers = customerDAO.getAll();
        // Crear una lista observable de nombres de clientes
        ObservableList<String> customerNames = FXCollections.observableArrayList();
        for (Customer customer : customers) {
            customerNames.add(customer.getName());
        }
        // Configurar la lista observable en el ComboBox
        cartsComboBox.setItems(customerNames);
    }

    // Carga los productos comprados por el cliente seleccionado en el ComboBox
    public void loadProducts() {
        // print the index of the selected item
        System.out.println(cartsComboBox.getSelectionModel().getSelectedIndex());
        //print the max index of the list
        System.out.println(cartsComboBox.getItems().size());
        // Get frfon the ComboBox the selected client
        Customer customer = customers.get(cartsComboBox.getSelectionModel().getSelectedIndex());
        // Obtener la lista de productos desde el DAO
        List<ProductDTO> products = productDAO.getProductsByClient(customer.getId());
        // Crear una lista observable de nombres de productos
        ObservableList<String> productNames = FXCollections.observableArrayList();
        for (ProductDTO product : products) {
            productNames.add(product.getName() + " - " + product.getPrice() + " - " + product.getLastUpdated());
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
        ImageView imageView = new ImageView();
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        Button button = new Button("Add Image");
        button.setOnAction(event -> {
            File file = new FileChooser().showOpenDialog(null);
            if (file != null) {
                try {
                    byte[] photoBytes = Files.readAllBytes(file.toPath());
                    // Convertir el byte array a una imagen
                    ByteArrayInputStream inputStream = new ByteArrayInputStream(photoBytes);
                    Image image = new Image(inputStream);
                    // Configurar la imagen en el ImageView
                    imageView.setImage(image);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        // Adding the form to the alert
        alert.getDialogPane().setContent(new VBox(8, name, description, price, stock, category, imageView, button));
        // Show the alert and wait for the user to close it
        alert.showAndWait();
        // If the user clicked OK
        if (alert.getResult() == ButtonType.OK) {
            // Check if the user filled all the fields
            if (name.getText() == null || description.getText() == null || price.getText() == null ||
                    stock.getText() == null || category.getSelectionModel().getSelectedItem() == null) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error");
                error.setHeaderText("Error adding the product");
                error.setContentText("Please fill all the fields");
                error.showAndWait();
            } else {
                // Create a new product
                Product product = new Product();
                setFields(name, description, price, stock, category, categories, product);

                // Save the product in the database
                productDAO.save(product);

                // Create a new product photo
                ProductPhoto productPhoto = new ProductPhoto();
                productPhoto.setProduct(product);
                Image image = imageView.getImage();
                if (image != null) {
                    try {
                        byte[] photoBytess = Files.readAllBytes(new File(image.getUrl()).toPath());
                        productPhoto.setPhotoData(photoBytess);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    productPhoto.setPhotoData(new byte[0]);
                }
                // Empty the table
                productObservableList.clear();

                // Reload the products
                loadManagerProducts();

                // Empty the list in the client view
                productsList.getItems().clear();
            }
        }
    }
    @FXML
    public void editProduct(){
        // Get the selected product
        ProductDTO product = productsTable.getSelectionModel().getSelectedItem();
        // If the user selected a product
        if (product != null) {
            // Alert window with the form
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Edit Product");
            alert.setHeaderText("Edit the product: "+product.getName());
            alert.setContentText("Please enter the new product details");
            // Form
            TextField name = new TextField();
            name.setPromptText("Name");
            name.setText(product.getName());
            TextField description = new TextField();
            description.setPromptText("Description");
            description.setText(product.getDescription());
            TextField price = new TextField();
            price.setPromptText("Price");
            price.setText(product.getPrice()+"");
            TextField stock = new TextField();
            stock.setPromptText("Stock");
            stock.setText(product.getStockQty()+"");
            ComboBox<String> category = new ComboBox<>();
            category.setPromptText("Category");
            List<Category> categories = categoryDAO.getAll();
            for (Category c : categories) {
                category.getItems().add(c.getName());
            }
            category.getSelectionModel().select(product.getCategoryName());
            ImageView imageView = new ImageView();
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            // Image from the byte array of the product photo
            ProductPhoto prodphot;
            if (productPhotoDAO.get(product.getId()) != null) {
                ProductPhoto productPhoto = productPhotoDAO.get(product.getId());
                // Convertir el byte array a una imagen
                ByteArrayInputStream inputStream = new ByteArrayInputStream(productPhoto.getPhotoData());
                Image image = new Image(inputStream);
                // Configurar la imagen en el ImageView
                imageView.setImage(image);
                prodphot = productPhotoDAO.get(product.getId());
            } else {
                ProductPhoto productPhoto = new ProductPhoto();
                productPhoto.setProduct(productDAO.get(product.getId()));
                productPhoto.setPhotoData(new byte[0]);
                productPhotoDAO.save(productPhoto);
                prodphot = productPhotoDAO.get(product.getId());
            }
            Button button = new Button("Change Image");
            button.setOnAction(event -> {
                File file = new FileChooser().showOpenDialog(null);
                if (file != null) {
                    try {
                        byte[] photoBytes = Files.readAllBytes(file.toPath());
                        prodphot.setPhotoData(photoBytes);
                        productPhotoDAO.update(prodphot);
                        // Convertir el byte array a una imagen
                        ByteArrayInputStream inputStream = new ByteArrayInputStream(prodphot.getPhotoData());
                        Image image = new Image(inputStream);
                        // Configurar la imagen en el ImageView
                        imageView.setImage(image);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            // Adding the form to the alert
            alert.getDialogPane().setContent(new VBox(8, name, description, price, stock, category, imageView, button));
            // Show the alert and wait for the user to close it
            alert.showAndWait();
            // If the user clicked OK
            if (alert.getResult() == ButtonType.OK) {
                // Create a new product
                Product productToEdit = productDAO.get(product.getId());
                setFields(name, description, price, stock, category, categories, productToEdit);
                // Check if the user filled all the fields
                if (name.getText() == null || description.getText() == null || price.getText() == null || stock.getText() == null || category.getSelectionModel().getSelectedItem() == null){
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText("Error editing the product");
                    error.setContentText("Please fill all the fields");
                    error.showAndWait();
                } else {
                    // Save the product in the database
                    productDAO.update(productToEdit);
                    // Empty the table
                    productObservableList.clear();
                    // Reload the products
                    loadManagerProducts();
                    // Empty the list in the client view
                    productsList.getItems().clear();
                }
            }
        }else{
            // alert the user that the product was not selected
            Alert error = new Alert(Alert.AlertType.WARNING);
            error.setTitle("Error");
            error.setHeaderText("Please, select a product to edit");
            error.showAndWait();
        }
    }

    private void setFields(TextField name, TextField description, TextField price, TextField stock, ComboBox<String> category, List<Category> categories, Product productToEdit) {
        productToEdit.setName(name.getText());
        productToEdit.setDescription(description.getText());
        productToEdit.setPrice(Double.parseDouble(price.getText()));
        productToEdit.setStockQty(Integer.parseInt(stock.getText()));
        productToEdit.setCategory(categories.get(category.getSelectionModel().getSelectedIndex()));
        productToEdit.setLastUpdated(new java.sql.Date(System.currentTimeMillis()));
    }

    // Elimina un producto de la tabla
    @FXML
    public void deleteProduct(){
        // Get the selected product
        ProductDTO product = productsTable.getSelectionModel().getSelectedItem();
        // If the user selected a product
        if (product != null) {
            Product productToDelete = productDAO.get(product.getId());
            // Create a confirmation alert
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Delete Product");
            alert.setHeaderText("Delete the product: "+product.getName()+"?");
            alert.setContentText("Are you sure you want to delete the product?");
            // Show the alert and wait for the user to close it
            alert.showAndWait();
            // If the user clicked OK
            if (alert.getResult() == ButtonType.OK) {
                // Delete the product from the database
                productDAO.delete(productToDelete);
                // Empty the table
                productObservableList.clear();
                // Reload the products
                loadManagerProducts();
            }
        } else {
            // alert the user that the product was not selected
            Alert error = new Alert(Alert.AlertType.WARNING);
            error.setTitle("Error");
            error.setHeaderText("Please, select a product to delete");
            error.showAndWait();
        }
    }

    @FXML
    public void addProductToClientsHistory(){
        // Get the selected Client
        Customer customer = customers.get(cartsComboBox.getSelectionModel().getSelectedIndex());
        // Dialog alert about the product to add
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Add Product");
        alert.setHeaderText("Add a product to the client: "+customer.getName());
        alert.setContentText("Please enter the product details");
        // Form
        ComboBox<String> products = new ComboBox<>();
        products.setPromptText("Product");
        List<Product> productsToChoose = productDAO.getAll();
        for (Product p : productsToChoose) {
            products.getItems().add(p.getName() + " - " + p.getPrice());
        }
        TextField quantity = new TextField();
        quantity.setPromptText("Quantity");
        // Adding the form to the alert
        alert.getDialogPane().setContent(new VBox(8, products, quantity));
        // Show the alert and wait for the user to close it
        alert.showAndWait();
        // If the user clicked OK
        if (alert.getResult() == ButtonType.OK) {
            if (quantity.getText() == null || products.getSelectionModel().getSelectedItem() == null){
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error");
                error.setHeaderText("Error adding the product");
                error.setContentText("Please fill all the fields");
                error.showAndWait();
            } else {
                // Create a new cart item key
                CartItemKey cartItemKey = new CartItemKey();
                cartItemKey.setCustomerId(customer.getId());
                cartItemKey.setProductId(productsToChoose.get(products.getSelectionModel().getSelectedIndex()).getId());
                // Create a new cart item
                CartItem cartItem = new CartItem();
                cartItem.setId(cartItemKey);
                cartItem.setItemQty(Integer.parseInt(quantity.getText()));
                cartItem.setLastUpdated(new java.sql.Date(System.currentTimeMillis()));
                cartItem.setCustomer(customer);
                cartItem.setProduct(productsToChoose.get(products.getSelectionModel().getSelectedIndex()));
                // Save the product in the database
                cartItemDAO.save(cartItem);
                // Empty the list in the client view
                productsList.getItems().clear();
                // Load the products in the client view
                loadProducts();
            }
        }
    }

    @FXML
    public void watchPhotoFromProduct(){
        // Get the selected product
        ProductDTO product = productsTable.getSelectionModel().getSelectedItem();
        // If the user selected a product
        if (product != null) {
            // Get the product photo
            ProductPhoto productPhoto = productPhotoDAO.get(product.getId());
            // If the product has a photo
            if (productPhoto != null) {
                // Convertir el byte array a una imagen
                ByteArrayInputStream inputStream = new ByteArrayInputStream(productPhoto.getPhotoData());
                Image image = new Image(inputStream);
                // Create a new alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Product Photo");
                alert.setHeaderText("Product: "+product.getName());
                alert.setContentText("The photo of the product");
                // Create an ImageView
                ImageView imageView = new ImageView();
                imageView.setImage(image);
                imageView.setFitHeight(300);
                imageView.setFitWidth(300);
                // Adding the ImageView to the alert
                alert.getDialogPane().setContent(new VBox(8, imageView));
                // Show the alert and wait for the user to close it
                alert.showAndWait();
            } else {
                // Create a new alert
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Product Photo");
                alert.setHeaderText("Product: "+product.getName());
                alert.setContentText("The product has no photo");
                // Show the alert and wait for the user to close it
                alert.showAndWait();
            }
        } else {
            // alert the user that the product was not selected
            Alert error = new Alert(Alert.AlertType.WARNING);
            error.setTitle("Error");
            error.setHeaderText("Please, select a product to watch the photo");
            error.showAndWait();
        }
    }
}