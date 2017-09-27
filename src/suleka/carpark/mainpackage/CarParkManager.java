package suleka.carpark.mainpackage;

import suleka.carpark.vehiclepackage.Vehicle;

/**
 * Created by sulek on 18/09/2017.
 */
public interface CarParkManager {
    //methods to be overridden

    public int addVehicle();

    public Vehicle deleteVehicles();

    public void getPercentage();

    public void getstatistics();

    public void getVehicleList();

    public void printParkedVehicles();

    public void calculateCharge();

}
