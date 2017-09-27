package suleka.carpark.vehiclepackage;

/**
 * Created by sulek on 18/09/2017.
 */
public class Van extends Vehicle {

    private double cargoVolume;

    public Van(String vehicleType,String plateId,String brand,double scargoVolume){
        super(vehicleType,plateId,brand);//calling super class
        this.cargoVolume=cargoVolume;
    }
    //getters and setters
    public double getCargoVolume() {
        return cargoVolume;
    }

    public void setCargoVolume(double cargoVolume) {
        this.cargoVolume = cargoVolume;
    }
}
