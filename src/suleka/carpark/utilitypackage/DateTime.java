package suleka.carpark.utilitypackage;
/**
 * Created by sulek on 18/09/2017.
 */
public class DateTime {
    private static int day;
    private static int month;
    private static int year;
    private static int hour;
    private static int min;

    private DateTime(){}//prevents objects being created from this class

    //getters
    public static int getDay() {
        return day;
    }

    public static int getMonth() {
        return month;
    }

    public static int getYear() {
        return year;
    }

    public static int getHour() {
        return hour;
    }

    public static int getMin() {
        return min;
    }

    //this method validates and sets the date elements
    public static String validateDate(String sentDate){

        int dates[] = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};//array containing max number of dates of each month
        boolean isDateSet=false;
        String dateElements[]= sentDate.split("/");//splitting string
        //converting string elements in string array to character arrays
        char yearArr[] = dateElements[2].toCharArray();
        char dayArr[] = dateElements[0].toCharArray();
        char monthArr[] = dateElements[1].toCharArray();
        //converting string element arrays to integers
        int sentMonth=Integer.parseInt(dateElements[1]);
        int sentYear=Integer.parseInt(dateElements[2]);
        int sentDay=Integer.parseInt(dateElements[0]);

        //validation process
        if(dateElements.length == 3 && yearArr.length == 4 && dayArr.length == 2 && monthArr.length == 2) {
            if (sentMonth > 0 && sentMonth < 13) {
                if (sentDay > 0) {
                    if (sentMonth == 2) {
                        //validating leaap years
                        if ((((sentYear % 4 == 0) && (sentYear % 100 != 0)) || (sentYear % 400 == 0)) && sentDay == 29) {
                            isDateSet=true;
                            day=sentDay;
                            month=sentMonth;
                            year=sentYear;
                        }
                        //validating non leaap years
                        else if (!(((sentYear % 4 == 0) && (sentYear % 100 != 0)) || (sentYear % 400 == 0)) && sentDay == 28) {
                            isDateSet=true;
                            day=sentDay;
                            month=sentMonth;
                            year=sentYear;
                        }
                        else{
                            System.out.println("Invalid Date");
                        }
                    }
                    else{
                        for (int i = 0; i < 12; i++) {
                            if ((i + 1) == 2) {
                                continue;//skipping 2nd moth as it was validated before
                            } else if ((i + 1) == sentMonth) {
                                if (sentDay <= dates[i]) {
                                    isDateSet=true;
                                    day=sentDay;
                                    month=sentMonth;
                                    year=sentYear;
                                }
                                else{
                                    System.out.println("Invalid Date");
                                }
                            }
                        }
                    }
                }
                else{
                    System.out.println("Invalid Date");
                }
            }
            else{
                System.out.println("Invalid Date");
            }
        }
        else{
            System.out.println("Invalid Date");
        }
        //if date elemets were set will return the string that was sent to the function
        if(isDateSet){
            return sentDate;
        }
        //if date elements were not set will return null
        else{
            return null;
        }
    }

    //this function validates and sets the time elemets
    public static String validateTime(String sentTime){
        boolean isSetTime=false;
        String timeElements[]= sentTime.split(":");//splitting time
        //converting string array elements to integer
        int sentHour=Integer.parseInt(timeElements[0]);
        int sentMin=Integer.parseInt(timeElements[1]);

        //validating
        if(sentHour<=24){
            if(sentMin <=60){
                hour=sentHour;
                min=sentMin;
                isSetTime=true;
            }
            else{
                System.out.println("Invalid Time");
            }
        }
        else{
            System.out.println("Invalid Time");
        }
        //will return the sent sting id time elements were set
        if(isSetTime){
            return sentTime;
        }
        //will return null if time elements were not set
        else{
            return  null;
        }

    }


}
