package suleka.carpark.vehiclepackage;

/**
 * Created by sulek on 18/09/2017.
 */
public class Car extends Vehicle {

    private int noOfDoors;
    private String color;

    public Car(String vehicleType,String plateId,String brand,int noOfDoors,String color){
        super(vehicleType,plateId,brand);//calling super class
        this.color=color;
        this.noOfDoors=noOfDoors;
    }

    //getters and setters
    public int getNoOfDoors() {
        return noOfDoors;
    }

    public void setNoOfDoors(int noOfDoors) {
        this.noOfDoors = noOfDoors;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


}
