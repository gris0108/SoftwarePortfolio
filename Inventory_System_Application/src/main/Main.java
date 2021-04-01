package main;

import Model.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** This class creates the Inventory application.
 <p><b>
 A great feature to add to this program would be the ability to associate images and weights to each part and product.
 This would make it great to identify parts and products uniquely and just make the interface nicer.
 The weight will be very helpful for when needing to ship parts.
 </b></p>
 */

public class Main extends Application {

    @Override
    /** This method shows the Main Screen and loads all Inventory Data.
       @param primaryStage the main stage
      */
    public void start(Stage primaryStage) throws Exception{
        Inventory inv = new Inventory();
        addInventoryData(inv);

        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/main_screen.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1098, 493));
        primaryStage.show();
    }

    /** This method creates all Parts and Products for Inventory and adds them to Inventory.
      @param inv the inventory to add data to
     */
    void addInventoryData(Inventory inv){

        Part i1 = new InHouse(1, "Part 1A", 9.00, 25, 2, 100,1001);
        Part i2 = new InHouse(2, "Part 1B", 9.00, 25, 2, 100,1001);
        Part i3 = new InHouse(3, "Part 3", 49.00, 50, 5, 250,1003);
        Part i4 = new InHouse(4, "Part 4A", 5.00, 99, 20, 300,1004);
        Part i5 = new InHouse(5, "Part 4B", 2.00, 100, 20, 400,1004);

        inv.addPart(i1);
        inv.addPart(i2);
        inv.addPart(i3);
        inv.addPart(i4);
        inv.addPart(i5);


        Part o1 = new Outsourced(6, "Part 2A", 10.00, 200, 2, 300,"Mystery Manufacturing Co.");
        Part o2 = new Outsourced(7, "Part 2B", 30.00, 450, 2, 600,"Mystery Manufacturing Co.");
        Part o3 = new Outsourced(8, "Part 5A", 20.00, 600, 10, 664,"Mysterium Co.");
        Part o4 = new Outsourced(9, "Part 5B", 12.00, 400, 20, 665,"Mysterium Co.");
        Part o5 = new Outsourced(10, "Part 5C", 21.00, 599, 10, 667,"Mysterium Co.");

        inv.addPart(o1);
        inv.addPart(o2);
        inv.addPart(o3);
        inv.addPart(o4);
        inv.addPart(o5);

        Product product = new Product(1001,"Product 1", 150.95, 1, 0,1);

        Product product2 = new Product(1002,"Producto 2", 75.95, 2, 0,2);

        Product product3 = new Product(1003,"Produit 3", 99.95, 3, 0,3);

        Product product4 = new Product(1004, "Produto 4", 120.95, 4, 0,4);

        Product product5 = new Product(1005,"Productum 5", 135.95, 5, 0,5);

        inv.addProduct(product);
        inv.addProduct(product2);
        inv.addProduct(product3);
        inv.addProduct(product4);
        inv.addProduct(product5);

        product.addAssociatedPart(i1);
        product.addAssociatedPart(i2);
        product2.addAssociatedPart(o1);
        product2.addAssociatedPart(o2);
        product3.addAssociatedPart(i3);
        product4.addAssociatedPart(i4);
        product4.addAssociatedPart(i5);
        product5.addAssociatedPart(o3);
        product5.addAssociatedPart(o4);
        product5.addAssociatedPart(o5);



    }

    /** This is the main method. It is executed as soon as you run you java application.
     @param args args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
