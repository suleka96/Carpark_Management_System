package suleka.carpark.utilitypackage;

import suleka.carpark.vehiclepackage.Vehicle;

/**
 * Created by sulek on 18/09/2017.
 */
public class VehicleUtility {

    private VehicleUtility(){}//prevents objects being created from this class

    //this method validated the vehicle type
    public static String validateVehicle(String vehicle){
        vehicle=vehicle.toLowerCase();
        if(vehicle.equals("car") || vehicle.equals("van") || vehicle.equals("motorbike")){
            return vehicle;//returns the sent string if validation was successful
        }
        else{
            System.out.println("Invalid Vehicle Type");
            return null;//returns 0 if validation failed
        }
    }
    //this method validates the number of doors of a car
    public static  int noOfDoors(int doors){
        if(doors>0 && (doors==2 || doors==4)){
            return doors;//returns the sent integer if validation was successful
        }
        else{
            System.out.println("Invalid number of doors");
            return 0;//returns 0 if validation failed
        }
    }

    //this method moves the elemets of the parkingSlots array so that there will be no spaces in the middle
    public static void moveVehicles(int i, Vehicle parkingSlots[] ){
        while(i<(20-1)){
            parkingSlots[i]=parkingSlots[i+1];
            i++;
        }
        parkingSlots[i]=null;//assigning the last slot of the array as null after moving all the oter elements one slot forward
    }
}
