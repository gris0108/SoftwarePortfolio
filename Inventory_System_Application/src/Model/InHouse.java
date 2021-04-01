package Model;
/** This class inherits from Part and defines a Inhouse Part. */
public class InHouse extends Part {
    private int machineId;

    /**
     * Constructor for InHouse. Passes parameters to superclass and sets machineId.
     * @param id id to set
     * @param name name to set
     * @param price price to set
     * @param stock stock to set
     * @param min min to set
     * @param max max to set
     * @param machineId machine id to set
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id,name,price,stock,min,max);
        this.machineId = machineId;
    }
    /** Getter for machineId
     @return the machineId
     */
    public int getMachineId() {
        return machineId;
    }

    /** Setter for machineId
     @param machineId the machineId to set
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
