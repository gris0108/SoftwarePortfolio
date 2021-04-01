package Model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/** This class stores all of the Parts and Products in Inventory.*/
public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static ObservableList<Product> searchProducts = FXCollections.observableArrayList();
    private static ObservableList<Part> searchParts = FXCollections.observableArrayList();

    /** Adds part to allParts
     @param newPart the part to add
     */
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }

    /** Adds product to allProducts
     @param newProduct the product to add
     */
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    /** Searches allParts by ID
     @param partId the part id to search
     @return the part with the associated id
     */
    public Part lookupPart(int partId){
        for(Part p : allParts){
            if(p.getId() == partId)
                return p;
        }
        return null;
    }

    /** Searches allProducts by ID
        @param productId the product id to search
        @return the product with the associated id
     */
    public Product lookupProduct(int productId){
        for(Product product : getAllProducts()){
            if(product.getId() == productId)
                return product;
        }
        return null;
    }

    /** Searches allParts by Name
     @param partName the part name to search
     @return the part with the associated name
     */
    public Part lookupPart(String partName){
        for(Part p : allParts){
            if(p.getName() == partName)
                return p;
        }
        return null;
    }

    /** Searches allProducts by Name
     @param productName the part name to search
     @return the product with the associated name
     */
    public Product lookupProduct(String productName){
        for(Product product : getAllProducts()){
            if(product.getName() == productName)
                return product;
        }
        return null;
    }

    /** Updates part at specified index
     @param index the index in allParts
     @param selectedPart the part to update
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index,selectedPart);

    }

    /** Updates product at specified index
     @param index the index in allProducts
     @param newProduct the product to update
     */
    public static void updateProduct(int index, Product newProduct){
        allProducts.set(index,newProduct);

    }

    /**
     *
     * @param selectedPart the selected part to delete
     * @return true if the method was successful
     */
    public static boolean deletePart(Part selectedPart){
        allParts.remove(selectedPart);
        for(Product pr : allProducts) {
            for (Part pt : pr.getAllAssociatedParts()){
                if(selectedPart == pt)
                    pr.deleteAssociatedPart(selectedPart);
            }

        }
        return true;
    }

    /**
     *
     * @param selectedProduct the selected product to delete
     * @return true if the method was successful
     */
    public static boolean deleteProduct(Product selectedProduct){
        allProducts.remove(selectedProduct);
        return true;
    }

    /**
     *
     * @return the observable list of allParts
     */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    /**
     *
     * @return the observable list of allProducts
     */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }

    /**
     *
     * @return the observable list of products searched
     */
    public static ObservableList<Product> getSearchProducts() {
        return searchProducts;
    }

    /**
     *
     * @return the observable list of parts searched
     */
    public static ObservableList<Part> getSearchParts() {
        return searchParts;
    }




}
