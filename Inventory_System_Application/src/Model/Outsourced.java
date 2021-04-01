package Model;
/** This class inherits from Part and defines a Outsourced Part. */
public class Outsourced extends Part{
    private String companyName;

    /**
     * Constructor for Outsourced. Passes parameters to superclass and sets companyName
     * @param id id to set
     * @param name name to set
     * @param price price to set
     * @param stock stock to set
     * @param min min to set
     * @param max max to set
     * @param companyName company name to set
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id,name,price,stock,min,max);
        this.companyName = companyName;
    }

    /**
     *
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     *
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
