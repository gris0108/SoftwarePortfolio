package View_Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/** This class defines all action handlers and stores data used in the Add Part Screen. */
public class AddPartController {

    Stage stage;
    Parent scene;
    Inventory inv;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField partID;

    @FXML
    private TextField partName;

    @FXML
    private TextField partInv;

    @FXML
    private TextField partPrice;

    @FXML
    private TextField partMax;

    @FXML
    private TextField partMachineID;

    @FXML
    private TextField partMin;

    @FXML
    private RadioButton inHouseRadio;

    @FXML
    private RadioButton outsourcedRadio;

    @FXML
    private Label lblmachineid;


    @FXML
    /** The handler for the cancel button in the UI. */
    void cancelHandler(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/main_screen.fxml"));
        stage.setScene(new Scene(scene,1098,493));
        stage.show();
    }

    @FXML
    /** The handler for the inHouse radio button in the UI*/
    void inHouseHandler(ActionEvent event) {
        outsourcedRadio.setSelected(false);
        lblmachineid.setText("Machine ID");

    }

    @FXML
    /** The handler for the outsourced radio button in the UI*/
    void outsourcedHandler(ActionEvent event) {
        inHouseRadio.setSelected(false);
        lblmachineid.setText("Company Name");
    }

    @FXML
    /** The handler for the save button in the UI*/
    void saveHandler(ActionEvent event) throws IOException {
        try {
            int id = inv.getAllParts().size() + 1;
            String name = partName.getText();
            int inv = Integer.parseInt(partInv.getText());
            double price = Double.parseDouble(partPrice.getText());
            int max = Integer.parseInt(partMax.getText());
            int min = Integer.parseInt(partMin.getText());
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
                if (inHouseRadio.isSelected()) {
                    int machineId = Integer.parseInt(partMachineID.getText());
                    Part newPart = new InHouse(id, name, price, inv, min, max, machineId);

                    for (Product p : Inventory.getAllProducts())
                        if (machineId == p.getId()) {
                            System.out.println("Added " + machineId + " to " + p.getId());
                            p.addAssociatedPart(newPart);
                        }

                    Inventory.addPart(new InHouse(id, name, price, inv, min, max, machineId));
                } else if (outsourcedRadio.isSelected()) {
                    String companyName = partMachineID.getText();
                    Inventory.addPart(new Outsourced(id, name, price, inv, min, max, companyName));
                    System.out.println("Outsourced:" + new Outsourced(id, name, price, inv, min, max, companyName));
                }

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
                alert2.setContentText("Minimum for part cannot be less than maximum");
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
        assert partID != null : "fx:id=\"partID\" was not injected: check your FXML file 'add_part.fxml'.";
        assert partName != null : "fx:id=\"partName\" was not injected: check your FXML file 'add_part.fxml'.";
        assert partInv != null : "fx:id=\"partInv\" was not injected: check your FXML file 'add_part.fxml'.";
        assert partPrice != null : "fx:id=\"partPrice\" was not injected: check your FXML file 'add_part.fxml'.";
        assert partMax != null : "fx:id=\"partMax\" was not injected: check your FXML file 'add_part.fxml'.";
        assert partMachineID != null : "fx:id=\"partMachineID\" was not injected: check your FXML file 'add_part.fxml'.";
        assert partMin != null : "fx:id=\"partMin\" was not injected: check your FXML file 'add_part.fxml'.";
        assert inHouseRadio != null : "fx:id=\"inHouseRadio\" was not injected: check your FXML file 'add_part.fxml'.";
        assert outsourcedRadio != null : "fx:id=\"outsourcedRadio\" was not injected: check your FXML file 'add_part.fxml'.";
        assert lblmachineid != null : "fx:id=\"lblmachineid\" was not injected: check your FXML file 'add_part.fxml'.";

    }
}
