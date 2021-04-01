package View_Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.FXCollections;
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

/** This class defines all action handlers and stores data used in the Add Product Screen. */

public class AddProductController {

    Stage stage;
    Parent scene;
    private static ObservableList<Part> productAssociatedParts = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField productidfield;

    @FXML
    private TextField productpricefield;

    @FXML
    private TextField productinvfield;

    @FXML
    private TextField productnamefield;

    @FXML
    private TextField productmaxfield;

    @FXML
    private TextField productminfield;

    @FXML
    private TextField partsearch;

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
    private TableView <Part> associatedPartsTableView;

    @FXML
    private TableColumn<Part, Integer> associatedpartid;

    @FXML
    private TableColumn<Part, String> associatedpartname;

    @FXML
    private TableColumn<Part, Integer> associatedpartinventorylevel;

    @FXML
    private TableColumn<Part, Double> associatedpartprice;

    @FXML
    /** The handler for the searchParts text field in the UI. Press enter to search*/
    void searchPartsHandler(KeyEvent event) {
        String search = partsearch.getText().trim();

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
    /** The handler for the add button in the UI*/
    void addHandler(ActionEvent event) {
        Part part = partsTableView.getSelectionModel().getSelectedItem();
        System.out.println(part.toString());
        productAssociatedParts.add(part);
        associatedPartsTableView.setItems(productAssociatedParts);
    }

    @FXML
    /** The handler for the remove associated part button in the UI*/
    void removeAssociatedPartHandler(ActionEvent event) {
        Part part = associatedPartsTableView.getSelectionModel().getSelectedItem();
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you wish to remove this part?");
        Optional<ButtonType> result = confirmation.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            productAssociatedParts.remove(part);
            associatedPartsTableView.setItems(productAssociatedParts);
        }
    }


    @FXML
    /** The handler for the cancel button in the UI*/
    void cancelHandler(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/main_screen.fxml"));
        stage.setScene(new Scene(scene,1098,493));
        stage.show();
    }

    @FXML
    /** The handler for the save button in the UI*/
    void saveHandler(ActionEvent event) throws IOException {
        try{
            int id = Inventory.getAllProducts().size()+1001;
            String name = productnamefield.getText();
            int inv = Integer.parseInt(productinvfield.getText());
            double price = Double.parseDouble(productpricefield.getText());
            int max = Integer.parseInt(productmaxfield.getText());
            int min = Integer.parseInt(productminfield.getText());
            boolean error = false;

            if(min > max){
                error = true;
                errorDialogBox(2);
            }

            if (inv < min || inv > max){
                error = true;
                errorDialogBox(3);
            }

            if(price < 0)
            {
                error = true;
                errorDialogBox(4);
            }

            if(!error) {
                ObservableList<Part> partsList = FXCollections.observableArrayList();
                partsList.addAll(associatedPartsTableView.getItems());

                Product product = new Product(id, name, price, inv, min, max, partsList);
                Inventory.addProduct(product);

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View_Controller/main_screen.fxml"));
                stage.setScene(new Scene(scene, 1098, 493));
                stage.show();
            }

        }
        catch (NumberFormatException e){
            errorDialogBox(1);
        }

    }
    /**
     * Displays a error dialog box based on the code passed through
     * @param errorCode the errorCode to display
     */
    private void errorDialogBox(int errorCode) {
        switch (errorCode) {
            case 1:
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Invalid Value");
                alert.setContentText("Please enter valid values in all text fields! ");
                alert.showAndWait();
                break;
            case 2:
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("ERROR");
                alert2.setHeaderText("Logic Error");
                alert2.setContentText("Minimum for product cannot be less than maximum");
                alert2.showAndWait();
                break;
            case 3:
                Alert alert3 = new Alert(Alert.AlertType.ERROR);
                alert3.setTitle("ERROR");
                alert3.setHeaderText("Logic Error");
                alert3.setContentText("Inventory stock cannot be greater than maximum or less than minimum");
                alert3.showAndWait();
                break;
            case 4:
                Alert alert4 = new Alert(Alert.AlertType.ERROR);
                alert4.setTitle("ERROR");
                alert4.setHeaderText("Logic Error");
                alert4.setContentText("Price cannot be less than zero");
                alert4.showAndWait();
                break;
        }
    }

    @FXML
    /** Runs with scene is initialized */
    void initialize() {

        //Table view
        partsTableView.setItems(Inventory.getAllParts());
        partid.setCellValueFactory(new PropertyValueFactory<>("id"));
        partname.setCellValueFactory(new PropertyValueFactory<>("name"));
        partinventorylevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partprice.setCellValueFactory(new PropertyValueFactory<>("price"));


        associatedpartid.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedpartname.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedpartinventorylevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedpartprice.setCellValueFactory(new PropertyValueFactory<>("price"));


        assert productidfield != null : "fx:id=\"productidfield\" was not injected: check your FXML file 'add_product.fxml'.";
        assert productpricefield != null : "fx:id=\"productpricefield\" was not injected: check your FXML file 'add_product.fxml'.";
        assert productinvfield != null : "fx:id=\"productinvfield\" was not injected: check your FXML file 'add_product.fxml'.";
        assert productnamefield != null : "fx:id=\"namefield\" was not injected: check your FXML file 'add_product.fxml'.";
        assert productmaxfield != null : "fx:id=\"productmaxfield\" was not injected: check your FXML file 'add_product.fxml'.";
        assert productminfield != null : "fx:id=\"productminfield\" was not injected: check your FXML file 'add_product.fxml'.";
        assert partsearch != null : "fx:id=\"partsearch\" was not injected: check your FXML file 'add_product.fxml'.";
        assert partid != null : "fx:id=\"partid\" was not injected: check your FXML file 'add_product.fxml'.";
        assert partname != null : "fx:id=\"partname\" was not injected: check your FXML file 'add_product.fxml'.";
        assert partinventorylevel != null : "fx:id=\"partinventorylevel\" was not injected: check your FXML file 'add_product.fxml'.";
        assert partprice != null : "fx:id=\"partprice\" was not injected: check your FXML file 'add_product.fxml'.";
        assert associatedpartid != null : "fx:id=\"associatedpartid\" was not injected: check your FXML file 'add_product.fxml'.";
        assert associatedpartname != null : "fx:id=\"associatedpartname\" was not injected: check your FXML file 'add_product.fxml'.";
        assert associatedpartinventorylevel != null : "fx:id=\"associatedpartinventorylevel\" was not injected: check your FXML file 'add_product.fxml'.";
        assert associatedpartprice != null : "fx:id=\"associatedpartprice\" was not injected: check your FXML file 'add_product.fxml'.";

    }
}
