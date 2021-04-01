package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/** This class defines a Product and contains Associated Parts. */
public class Product {
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * The constructor for Product with associatedParts passed as a parameter.
     * @param id the id to set
     * @param name the name to set
     * @param price the price to set
     * @param stock the stock to set
     * @param min the min to set
     * @param max the max to set
     * @param ap associated parts to set
     */
    public Product(int id, String name, double price, int stock, int min, int max, ObservableList ap) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.associatedParts = ap;
    }

    /**
     * The constructor for Product without any associatedParts passed in.
     * @param id the id to set
     * @param name the name to set
     * @param price the price to set
     * @param stock the stock to set
     * @param min the min to set
     * @param max the max to set
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     *
     * @return the product id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return the product name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return the product price
     */
    public double getPrice() {
        return price;
    }

    /**
     *
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     *
     * @return the product stock
     */
    public int getStock() {
        return stock;
    }

    /**
     *
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     *
     * @return the product mininum
     */
    public int getMin() {
        return min;
    }

    /**
     *
     * @param min the minimum to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     *
     * @return the product maximum
     */
    public int getMax() {
        return max;
    }

    /**
     *
     * @param max the maximum to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Associates part with this product
     * @param part the part to be associated with this Product
     */
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }

    /**
     * Deletes the associatedPart from observable list
     * @param selectedAssociatePart the part selected
     * @return true if method was successful
     */
    public boolean deleteAssociatedPart(Part selectedAssociatePart){
        associatedParts.remove(selectedAssociatePart);
        return true;
    }

    /**
     * Returns all Associated Parts to this product
     * @return the observable list of associatedParts
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}
