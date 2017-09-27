package suleka.carpark.vehiclepackage;

/**
 * Created by sulek on 18/09/2017.
 */
public class Motorbike extends Vehicle {

    private String sizeEngine;

    public Motorbike(String vehicleType,String plateId,String brand,String sizeEngine){
        super(vehicleType,plateId,brand);//calling super class
        this.sizeEngine=sizeEngine;
    }

    //getters and setters
    public String getSizeEngine() {
        return sizeEngine;
    }

    public void setSizeEngine(String sizeEngine) {
        this.sizeEngine = sizeEngine;
    }
}
