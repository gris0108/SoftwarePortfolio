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

/** This class defines all action handlers and stores data used in the Modify Part Screen. */

public class ModifyPartController {
    Stage stage;
    Parent scene;
    private static Part part;
    private int index;

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
    /** The handler for the cancel button in the UI*/
    void cancelHandler(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/main_screen.fxml"));
        stage.setScene(new Scene(scene,1098,493));
        stage.show();
    }

    @FXML
    /** The handler for the inHouse radio button in the UI*/
    void inHouseHandler(ActionEvent event) {
        lblmachineid.setText("Machine ID");
        outsourcedRadio.setSelected(false);
    }

    @FXML
    /** The handler for the outsourced radio button in the UI*/
    void outsourcedHandler(ActionEvent event) {
        lblmachineid.setText("Company Name");
        inHouseRadio.setSelected(false);
    }

    @FXML
    /** The handler for the save button in the UI*/
    void saveHandler(ActionEvent event) throws IOException {
        try {
            int index = Inventory.getAllParts().indexOf(part);
            int id = Integer.parseInt(partID.getText());
            String name = partName.getText();
            int inv = Integer.parseInt(partInv.getText());
            double price = Double.parseDouble(partPrice.getText());
            int max = Integer.parseInt(partMax.getText());
            int min = Integer.parseInt(partMin.getText());
            boolean error = false;

            if(min > max) {
                error = true;
                errorDialogBox(2);
            }
            if (inv < min || inv > max) {
                error = true;
                errorDialogBox(3);
            }

            if(price < 0){
                error = true;
                errorDialogBox(4);
            }

            /**
            <p><b>
             When trying to save a part with a logic error, such as the min being greater than the max, the part would still update part and switch scenes after exiting dialog box.
             To prevent this from happening for logic errors I created a boolean called error and set error to true if one of the logic errors was true for the part data to be saved.
             The below code will only run if there are no logic errors after performing the three logic error validations above.
             </b></p>
            */
            if(!error) {
                if (inHouseRadio.isSelected()) {
                    int machineId = Integer.parseInt(partMachineID.getText());
                    Part newPart = new InHouse(id, name, price, inv, min, max, machineId);
                    Inventory.updatePart(index, newPart);
                } else if (outsourcedRadio.isSelected()) {
                    String companyName = partMachineID.getText();
                    Part newPart = new Outsourced(id, name, price, inv, min, max, companyName);
                    Inventory.updatePart(index, newPart);
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
     * Passes in a part parameter from the Main Screen and sets the values for the text fields
     * @param pt the part to modify
     */
    public void setPart(Part pt){
        part = pt;
        partID.setText(Integer.toString(part.getId()));
        partName.setText(part.getName());
        partInv.setText(Integer.toString(part.getStock()));
        partPrice.setText(Double.toString(part.getPrice()));
        partMax.setText(Integer.toString(part.getMax()));
        partMin.setText(Integer.toString(part.getMin()));
        if (part instanceof InHouse) {
            partMachineID.setText(Integer.toString(((InHouse) part).getMachineId()));
            inHouseRadio.setSelected(true);
        } else if (part instanceof Outsourced){
            partMachineID.setText(((Outsourced) part).getCompanyName());
            lblmachineid.setText("Company Name");
            outsourcedRadio.setSelected(true);
        }
    }

    /**
     * Displays a error dialog box based on the code passed through
     * @param errorCode the errorCode to display
     */
    private void errorDialogBox(int errorCode){
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

        assert partID != null : "fx:id=\"partID\" was not injected: check your FXML file 'modify_part.fxml'.";
        assert partName != null : "fx:id=\"partName\" was not injected: check your FXML file 'modify_part.fxml'.";
        assert partInv != null : "fx:id=\"partInv\" was not injected: check your FXML file 'modify_part.fxml'.";
        assert partPrice != null : "fx:id=\"partPrice\" was not injected: check your FXML file 'modify_part.fxml'.";
        assert partMax != null : "fx:id=\"partMax\" was not injected: check your FXML file 'modify_part.fxml'.";
        assert partMachineID != null : "fx:id=\"partMachineID\" was not injected: check your FXML file 'modify_part.fxml'.";
        assert partMin != null : "fx:id=\"partMin\" was not injected: check your FXML file 'modify_part.fxml'.";

    }
}
