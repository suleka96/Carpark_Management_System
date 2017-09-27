package suleka.carpark.mainpackage;

import suleka.carpark.utilitypackage.*;
import suleka.carpark.vehiclepackage.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by sulek on 18/09/2017.
 */
public class WestminsterCarParkManager implements CarParkManager {

    private WestminsterCarParkManager(){}//prevent objects being creted from this class


    public static Scanner scr;
    private static Vehicle parkingSlots[];//array that stores currently parked vehicles
    private static ArrayList<Vehicle> vehicleHistory;//array list that stors the vehicles that leaves the car park
    private static WestminsterCarParkManager carPark;//object to call non static overridden methods


    public static void main(String[] args){

        //initializations
        scr = new Scanner(System.in);
        parkingSlots = new Vehicle[20];
        vehicleHistory=new ArrayList<Vehicle>();
        carPark= new WestminsterCarParkManager();
        String opt;

        do {
            //menu
            System.out.println("-----------Westminster Car Park--------------");
            System.out.println("(A) Add Vehicle to Car Park");
            System.out.println("(D) Delete Vehicle from Car Park");
            System.out.println("(P) Print parked vehicles");
            System.out.println("(S) Print Statistics");
            System.out.println("(L) List Vehicles Parked on a Perticular Day");
            System.out.println("(C) Calculate Charge");
            System.out.println("(E) Exit");
            System.out.println("\n" + "Enter your option" + "\n");
            opt = scr.next().toUpperCase();//converting the input to uppercase

            switch (opt) {
                case "A": {
                    int emptySlots = carPark.addVehicle();
                    if (emptySlots > 0) {
                        System.out.println("Number of Empty Slots: " + emptySlots);
                    } else {
                        System.out.println("No Empty Slots Available");
                    }
                }
                    break;
                case "D":{
                    Vehicle vehicle=carPark.deleteVehicles();
                    System.out.println("Deleted Vehicle Type: "+ vehicle.getVehicleType());
                }
                    break;
                case "P":
                    carPark.printParkedVehicles();
                    break;
                case "S":
                    System.out.println("-------------Statistics------------------");
                    carPark.getPercentage();
                    carPark.getstatistics();
                    break;
                case "L":
                    carPark.getVehicleList();
                    break;
                case "C":
                    carPark.calculateCharge();
                    break;
                case "E":
                    System.out.println("You will Exit the Program");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        } while (!(opt.equals("E")));//will repeat until exit option is entered

    }

    //this method adds vehicles to the car park
    public int addVehicle(){

        int emptySlots=0;
        boolean isFirstTime=true;
        boolean isSuccessful=false;
        int slotIndex=0;

        //searching if there are empty slots available
        for(int i=0;i<20;i++){
            if(parkingSlots[i] == null){
                if(isFirstTime){
                    isFirstTime=false;
                    slotIndex=i;
                }
                emptySlots++;
            }
        }
        try {
            if (emptySlots > 0) {
                System.out.println("Enter Vehicle type");
                String vehicle = VehicleUtility.validateVehicle(scr.next());//validation vehicle type
                if (vehicle != null) {
                    System.out.println("Enter Vehicle plate ID");
                    String vehicleId = scr.next();
                    System.out.println("Enter Vehicle brand");
                    String brand = scr.next();
                    System.out.println("Enter Arrival Day (DD/MM/YYYY)");
                    String day = scr.next();
                    day = DateTime.validateDate(day);//validating date
                    if (day != null) {
                        System.out.println("Enter Arrival Time (hh:mm hrs)");
                        String time = scr.next();
                        time = DateTime.validateTime(time);//validating time
                        if (time != null) {
                            //the relevant type of object will get creted according to the vehicle type selected
                            if (vehicle.equals("car")) {
                                System.out.println("Enter Color of Car");
                                String color = scr.next();
                                System.out.println("Enter Number of Doors in Car");
                                int doors = VehicleUtility.noOfDoors(scr.nextInt());
                                if (doors > 0) {
                                    Car carObj = new Car(vehicle, vehicleId, brand, doors, color);//creating car object and setting values through constructor
                                    parkingSlots[slotIndex] = carObj;//adding car object to the parking lot
                                    isSuccessful = true;
                                    System.out.println("Car added successfully");
                                }
                            } else if (vehicle.equals("van")) {
                                System.out.println("Enter Cargo Volume of Van");
                                double cargoVolume = scr.nextDouble();
                                Van vanObj = new Van(vehicle, vehicleId, brand, cargoVolume);//creating van object and setting values through constructor
                                parkingSlots[slotIndex] = vanObj;//adding van object to the parking lot
                                isSuccessful = true;
                                System.out.println("Van added successfully");
                            } else {
                                System.out.println("Enter Size Engine of Motorbike");
                                String sizeEngine = scr.next();
                                Motorbike motorbikeObj = new Motorbike(vehicle, vehicleId, brand, sizeEngine);//creating motorbike object and setting values through constructor
                                parkingSlots[slotIndex] = motorbikeObj;//adding motorbike object to the parking lot
                                isSuccessful = true;
                                System.out.println("Motorbike added successfully");
                            }
                        }
                    }

                }
            }
        }
        //to catch unplanned exceptopns
        catch(Exception e){
            System.out.println("Exception Encountered!");
        }
        if(isSuccessful){
            emptySlots=emptySlots-1;//if vehicle was added successfully the number of available slots will get reduced
        }
        return (emptySlots);
    }

    //this methods deletes vehicles from the car park
    public Vehicle deleteVehicles(){

        int historyIndex=0;//to keep the current index of the vehicleHistory array
        boolean isfound=false;

        //desplaying plate Ids for the user to choose
        for(Vehicle vehicleObj: parkingSlots){
            if(vehicleObj != null){
                System.out.println(vehicleObj.getPlateId());
            }
        }
        try {
            System.out.println("Enter plate Id of the Vehicle you want to delete");
            String vehicleId = scr.next();

            for (int i = 0; i < 20; i++) {
                if (parkingSlots[i] != null) {
                    //seeing if there is a  match for the entered plate id
                    if (vehicleId.equals(parkingSlots[i].getPlateId())) {
                        isfound = true;
                        System.out.println("Enter Departure Day (DD/MM/YYYY)");
                        String day = scr.next();
                        day = DateTime.validateDate(day);//validating date
                        if (day != null) {
                            //setting date fields
                            parkingSlots[i].setDepartureDay();
                            parkingSlots[i].setDepartureMonth();
                            parkingSlots[i].setDepartureYear();
                            System.out.println("Enter Departure Time (hh:mm hrs)");
                            String time = scr.next();
                            time = DateTime.validateTime(time);//validating time
                            if (time != null) {
                                //setting departure time fiels
                                parkingSlots[i].setDepartureHour();
                                parkingSlots[i].setDepartureMin();
                                vehicleHistory.add(parkingSlots[i]);//adding selected vehicle to vehicleHistory array list
                                historyIndex = vehicleHistory.indexOf(parkingSlots[i]);//assigning the current index the vehicle object was put to
                                parkingSlots[i] = null;//assigning the relevant slot in parkingSlots as null
                                System.out.println("Vehicle Deleted");
                                VehicleUtility.moveVehicles(i, parkingSlots);//readjusting objects in array parkingslots so that no empty spaces ramain in the middle
                            }
                        }
                    }
                }
            }
        }
        //catching unplanned exceptions
        catch(Exception e){
            System.out.println("Exception Encountered!");
        }

        if (!(isfound)) {
            System.out.println("incorrect plate Id");
        }

        return  vehicleHistory.get(historyIndex);//returning object in the index of he vehicalHistory array
    }


    //this method prints the vehicles in the cronological order
    public void printParkedVehicles(){
        for(int i=(parkingSlots.length-1); i>=0; i-- ){
            if(parkingSlots[i] != null) {
                System.out.println(parkingSlots[i]);
            }
        }
    }

    //this method prints the percentages of vehicles
    public void getPercentage(){
        int carCount=0,vanCount=0,motorbikeCount=0,total=0;

        for(Vehicle vehicleObj:parkingSlots ) {
            //counting the total number parked from each kind
            if(vehicleObj != null) {
                if (vehicleObj.getVehicleType().equals("car")) {
                    carCount = carCount + 1;
                } else if (vehicleObj.getVehicleType().equals("van")) {
                    vanCount = vanCount + 1;
                } else {
                    motorbikeCount = motorbikeCount + 1;
                }
            }

        }
        total=carCount+vanCount+motorbikeCount;//gettng the total number of vehicles parked
        //calculating percentages
        double carPercentage=(carCount/(double)total)*100;
        double vanPercentage=(vanCount/(double)total)*100;
        double motorbikePercentage=(motorbikeCount/(double)total)*100;
        System.out.println("Percentage of Cars currently parked: "+ carPercentage+"%");
        System.out.println("Percentage of Vans currently parked: "+vanPercentage +"%");
        System.out.println("Percentage of Motorbikes currently parked: "+ motorbikePercentage+"%");
    }

    //this methos prints last parked vehicle and vehicle that was parked for the longest period of time
    public void getstatistics(){
        int objectCount=0;
        // becouse of VehicleUtility.moveVehicles() method the vehicle that was parked the logest will always be at the 0th index
        if(parkingSlots[0] != null){
            System.out.println("Vehicle that was parked for the longest period: "+"\n"+parkingSlots[0] );
        }
        else{
            System.out.println("No Vehicles in the Car Park");
        }
        // becouse of VehicleUtility.moveVehicles() method the last vehicle parked will always be the last object in the array
        for(Vehicle vehicleObj:parkingSlots){
            if(vehicleObj != null){
                objectCount++;
            }
        }
        System.out.println("The last Vehicle that was Parked: "+"\n"+ parkingSlots[objectCount-1] );
    }

    //this method prints vehicles tat were entered in a perticular day
    public void getVehicleList(){
        boolean isFound=false;
        try {
            System.out.println("Enter the Day ");
            int day = scr.nextInt();
            System.out.println("Enter the Year ");
            int year = scr.nextInt();
            //searching in history list
            for (Vehicle vehicleObj : vehicleHistory) {
                if (vehicleObj != null) {
                    if (vehicleObj.getArrivalDay() == day && vehicleObj.getArrivalYear() == year) {
                        System.out.println(vehicleObj);
                        isFound = true;
                    }
                }
            }
            //searching currently parked vehicles
            for (int i = 0; i < 20; i++) {
                if (parkingSlots[i] != null) {
                    if (parkingSlots[i].getArrivalDay() == day && parkingSlots[i].getArrivalYear() == year) {
                        System.out.println(parkingSlots[i]);
                        isFound = true;
                    }
                }
            }
        }
        //catching unplanned exceptions
        catch(Exception e){
            System.out.println("Exception Encountered!");
        }
        if(!(isFound)){
            System.out.println("\n"+"No Vehicles were parked on this day");
        }

    }

    //this method calculates the charge for parking in the car park
    public void calculateCharge(){
        boolean noVehiclesDeparted=true;
        double total=0.0;
        int differenceDays=0,differenceHours=0;
        for(Vehicle vehicleObj: vehicleHistory){
            if(vehicleObj != null) {
                differenceDays = vehicleObj.getDepartureDay() - vehicleObj.getArrivalDay();
                //seeing if he has exceeded one day
                if (differenceDays > 0) {
                    total = 30 * differenceDays;
                } else {
                    differenceHours = vehicleObj.getDepartureHour() - vehicleObj.getArrivalHour();
                    //seeing if he has exceeded more than 3 hours
                    if (differenceHours > 3) {
                        total = (3 * 3) + 1 * (differenceHours - 3);
                    } else {
                        if(differenceHours == 0){
                            total = 3;//if he left without staying for an hour
                        }
                        total = differenceHours * 3;
                    }

                }
                System.out.println("Final Charge is: " + total+"\n"+"Plate Id: "+vehicleObj.getPlateId());
                noVehiclesDeparted=false;
            }
        }
        if(noVehiclesDeparted){
            System.out.println("No vehicles have left the Car Park");
        }
    }

}
