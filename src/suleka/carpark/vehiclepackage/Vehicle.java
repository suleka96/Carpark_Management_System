package suleka.carpark.vehiclepackage;
import suleka.carpark.utilitypackage.DateTime;

/**
 * Created by sulek on 18/09/2017.
 */
public abstract class Vehicle {
    private String vehicleType;
    private String plateId;
    private String brand;
    private int arrivalDay;
    private int arrivalMonth;
    private int arrivalYear;
    private int arrivalHour;
    private int arrivalMin;
    private int departureHour;
    private int departureMin;
    private int departureDay;
    private int departureMonth;
    private int departureYear;

    public Vehicle(String vehicleType,String plateId,String brand){
        this.vehicleType=vehicleType;
        this.plateId=plateId;
        this.brand=brand;
        this.arrivalDay = DateTime.getDay();
        this.arrivalMonth = DateTime.getMonth();
        this.arrivalYear = DateTime.getYear();
        this.arrivalHour = DateTime.getHour();
        this.arrivalMin = DateTime.getMin();
    }

    //getters and setters
    public int getArrivalDay() {
        return arrivalDay;
    }

    public int getArrivalMonth() {
        return arrivalMonth;
    }

    public int getArrivalYear() {
        return arrivalYear;
    }

    public int getArrivalHour() {
        return arrivalHour;
    }

    public int getArrivalMin() {
        return arrivalMin;
    }

    public int getDepartureHour() {
        return departureHour;
    }

    public int getDepartureMin() {
        return departureMin;
    }

    public void setDepartureHour() {
        this.departureHour = DateTime.getHour();
    }

    public void setDepartureMin() {
        this.departureMin = DateTime.getMin();
    }

    public int getDepartureDay() {
        return departureDay;
    }

    public void setDepartureDay() {
        this.departureDay = DateTime.getDay();
    }

    public int getDepartureMonth() {
        return departureMonth;
    }

    public void setDepartureMonth() {
        this.departureMonth = DateTime.getMonth();
    }

    public int getDepartureYear() {
        return departureYear;
    }

    public void setDepartureYear() {
        this.departureYear = DateTime.getYear();
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getPlateId() {
        return plateId;
    }

    public String getBrand() {
        return brand;
    }

    //this method automatically eecutes when printing an object
    @Override
    public String toString() {
        return ("Vehicle Type: "+this.vehicleType +"\n"+"PlateId: "+ this.plateId+"\n"+"Arrival Time: "+this.arrivalHour+":"+this.arrivalMin+"\n");
    }




}
