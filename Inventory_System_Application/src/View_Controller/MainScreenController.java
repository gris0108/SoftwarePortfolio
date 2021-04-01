package View_Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import Model.Inventory;
import Model.Part;
import Model.Product;
import com.sun.javafx.image.BytePixelSetter;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

/** This class defines all action handlers and stores data used in Main Screen. */

public class MainScreenController {

        Stage stage;
        Parent scene;


        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private TextField searchpartsfield;

        @FXML
        private TableView<Part> partsTableView;

        @FXML
        private TableColumn<Part, Integer> partid;

        @FXML
        private TableColumn<Part, String> partname;

        @FXML
        private TableColumn<Part, Integer> partinventorylevel;

        @FXML
        private TableColumn<Part, Double> partprice;

        @FXML
        private TextField searchproductsfield;

        @FXML
        private TableView<Product> productsTableView;

        @FXML
        private TableColumn<Product, Integer> productid;

        @FXML
        private TableColumn<Product, String> productname;

        @FXML
        private TableColumn<Product, Integer> productinventorylevel;

        @FXML
        private TableColumn<Product, Double> productprice;

        // Searches when Enter is pressed
        @FXML
        /** The handler for the searchParts text field in the UI. Press enter to search*/
        void searchPartsHandler(KeyEvent event) {
                String search = searchpartsfield.getText().trim();

                if(!search.isEmpty()) {

                        Inventory.getSearchParts().clear();
                        for (Part p : Inventory.getAllParts()) {
                                if (p.getName().contains(search) || Integer.toString(p.getId()).contains(search))
                                        Inventory.getSearchParts().add(p);
                        }
                        partsTableView.setItems(Inventory.getSearchParts());
                        partsTableView.refresh();
                }
                else if(search.isEmpty()) {
                        partsTableView.setItems(Inventory.getAllParts());
                        partsTableView.refresh();
                }
        }

        @FXML
        /** The handler for the searchProducts text field in the UI. Press enter to search*/
        void searchProductsHandler(KeyEvent event) {
                String search = searchproductsfield.getText().trim();

                if(!search.isEmpty()) {

                        Inventory.getSearchProducts().clear();
                        for (Product p : Inventory.getAllProducts()) {
                                if (p.getName().contains(search) || Integer.toString(p.getId()).contains(search))
                                        Inventory.getSearchProducts().add(p);
                        }
                        productsTableView.setItems(Inventory.getSearchProducts());
                        productsTableView.refresh();
                }
                else if(search.isEmpty()) {
                        productsTableView.setItems(Inventory.getAllProducts());
                        productsTableView.refresh();
                }
        }

        @FXML
        /** The handler for the add part button in the UI*/
        void addPartHandler(ActionEvent event) throws IOException {
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View_Controller/add_part.fxml"));
                stage.setScene(new Scene(scene,600,400));
                stage.show();
        }

        @FXML
        /** The handler for the add product button in the UI*/
        void addProductHandler(ActionEvent event) throws IOException {
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View_Controller/add_product.fxml"));
                stage.setScene(new Scene(scene,1039,637));
                stage.show();
        }

        @FXML
        /** The handler for the delete part button in the UI*/
        void deletePartHandler(ActionEvent event) {
                try{
                        Part part = partsTableView.getSelectionModel().getSelectedItem();
                        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you wish to delete this part?");
                        Optional<ButtonType> result = confirmation.showAndWait();
                        if(result.isPresent() && result.get() == ButtonType.OK) {
                                Inventory.deletePart(part);
                        }
                }
                catch (NullPointerException e){
                        dialogBox(2);
                }
        }

        @FXML
        /** The handler for the delete product button in the UI*/
        void deleteProductHandler(ActionEvent event) {
                try {
                        Product product = productsTableView.getSelectionModel().getSelectedItem();

                        if(!product.getAllAssociatedParts().isEmpty()) {
                                dialogBox(1);
                        }

                        else {
                                Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you wish to delete this product?");
                                Optional<ButtonType> result = confirmation.showAndWait();
                                if(result.isPresent() && result.get() == ButtonType.OK) {
                                        Inventory.deleteProduct(product);
                                }
                        }

                }
                catch (NullPointerException e){
                        dialogBox(2);
                }


        }

        @FXML
        /** The handler for the modify part button in the UI.*/
        void modifyPartHandler(ActionEvent event) throws IOException {
                //Passing Selection to ModifyPart Screen
                try{
                        Part part = partsTableView.getSelectionModel().getSelectedItem();
                        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                        FXMLLoader partloader = new FXMLLoader(getClass().getResource("/View_Controller/modify_part.fxml"));
                        Parent root = partloader.load();
                        ModifyPartController partcontroller = partloader.getController();
                        partcontroller.setPart(part);
                        Scene partscene = new Scene(root);
                        stage.setScene(partscene);
                        stage.show();

                }
                catch (NullPointerException e){
                        dialogBox(2);
                }
        }

        @FXML
        /** The handler for the modify product button in the UI.*/
        void modifyProductHandler(ActionEvent event) throws IOException {

                //Passing Selection to ModifyProduct Screen
                try {
                        Product product = productsTableView.getSelectionModel().getSelectedItem();
                        FXMLLoader productloader = new FXMLLoader(getClass().getResource("/View_Controller/modify_product.fxml"));
                        Parent root = productloader.load();
                        ModifyProductController productcontroller = productloader.getController();
                        productcontroller.setProduct(product);
                        Scene productscene = new Scene(root);
                        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                        stage.setScene(productscene);
                        stage.show();

                }
                catch (NullPointerException e){
                        dialogBox(2);
                }

        }

        @FXML
        /** The handler for the exit button in the UI.*/
        void exitApplicationHandler(ActionEvent event){
                System.exit(0);
        }
        /**
         * Displays a error dialog box based on the code passed through
         * @param code the errorCode to display
         */
        private void dialogBox(int code){
                switch (code){
                        case 1:
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("ERROR");
                                alert.setHeaderText("Cannot Delete Product!");
                                alert.setContentText("Selected product has associated parts, please remove associated parts to continue");
                                alert.showAndWait();
                                break;
                        case 2:
                                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                                alert2.setTitle("ERROR");
                                alert2.setHeaderText("Invalid Selection!");
                                alert2.setContentText("You must select an item");
                                alert2.showAndWait();
                                break;
                }
        }



        @FXML
        /** Runs with scene is initialized */
        void initialize() {

                partsTableView.setItems(Inventory.getAllParts());
                partid.setCellValueFactory(new PropertyValueFactory<>("id"));
                partname.setCellValueFactory(new PropertyValueFactory<>("name"));
                partinventorylevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
                partprice.setCellValueFactory(new PropertyValueFactory<>("price"));

                productsTableView.setItems(Inventory.getAllProducts());
                productid.setCellValueFactory(new PropertyValueFactory<>("id"));
                productname.setCellValueFactory(new PropertyValueFactory<>("name"));
                productinventorylevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
                productprice.setCellValueFactory(new PropertyValueFactory<>("price"));

                assert searchpartsfield != null : "fx:id=\"searchPartsField\" was not injected: check your FXML file 'main_screen.fxml'.";
                assert partsTableView != null : "fx:id=\"table\" was not injected: check your FXML file 'main_screen.fxml'.";
                assert partid != null : "fx:id=\"partid\" was not injected: check your FXML file 'main_screen.fxml'.";
                assert partname != null : "fx:id=\"partname\" was not injected: check your FXML file 'main_screen.fxml'.";
                assert partinventorylevel != null : "fx:id=\"partinventorylevel\" was not injected: check your FXML file 'main_screen.fxml'.";
                assert partprice != null : "fx:id=\"partprice\" was not injected: check your FXML file 'main_screen.fxml'.";
                assert searchproductsfield != null : "fx:id=\"searchProductsField\" was not injected: check your FXML file 'main_screen.fxml'.";
                assert productsTableView != null : "fx:id=\"table1\" was not injected: check your FXML file 'main_screen.fxml'.";
                assert productid != null : "fx:id=\"productid\" was not injected: check your FXML file 'main_screen.fxml'.";
                assert productname != null : "fx:id=\"productname\" was not injected: check your FXML file 'main_screen.fxml'.";
                assert productinventorylevel != null : "fx:id=\"productinventorylevel\" was not injected: check your FXML file 'main_screen.fxml'.";
                assert productprice != null : "fx:id=\"productprice\" was not injected: check your FXML file 'main_screen.fxml'.";

        }
}
