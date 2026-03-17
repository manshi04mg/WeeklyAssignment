import java.util.*;

public class w1w2 {

    static class ParkingSpot{

        String license;
        long entryTime;
        boolean occupied;

        ParkingSpot(){

            license = null;
            occupied = false;
        }
    }

    static int SIZE = 500;

    static ParkingSpot table[] =
            new ParkingSpot[SIZE];

    static int totalProbes = 0;
    static int vehicles = 0;

    // Initialize parking
    static{

        for(int i=0;i<SIZE;i++)
            table[i] = new ParkingSpot();
    }

    // Hash function
    static int hash(String license){

        return Math.abs(
                license.hashCode()) % SIZE;
    }

    // Park vehicle
    static void parkVehicle(String license){

        int index = hash(license);

        int probes = 0;

        while(table[index].occupied){

            index = (index+1)%SIZE;
            probes++;
        }

        table[index].license = license;

        table[index].entryTime =
                System.currentTimeMillis();

        table[index].occupied = true;

        vehicles++;
        totalProbes += probes;

        System.out.println(
                "Assigned spot #"+index+
                        " ("+probes+" probes)");
    }

    // Exit vehicle
    static void exitVehicle(String license){

        int index = hash(license);

        while(table[index].occupied){

            if(table[index].license.equals(license)){

                long duration =
                        (System.currentTimeMillis()
                                -table[index].entryTime)/1000;

                table[index].occupied=false;

                vehicles--;

                double fee =
                        duration*0.01;

                System.out.println(
                        "Spot #"+index+
                                " freed Duration: "+
                                duration+" sec Fee: $"+
                                fee);

                return;
            }

            index=(index+1)%SIZE;
        }

        System.out.println("Vehicle not found");
    }

    // Statistics
    static void getStatistics(){

        double occupancy =
                ((double)vehicles/SIZE)*100;

        double avgProbes =
                (vehicles==0)?0:
                        (double)totalProbes/vehicles;

        System.out.println(
                "Occupancy: "+occupancy+"%");

        System.out.println(
                "Avg Probes: "+avgProbes);
    }

    public static void main(String[] args) {

        parkVehicle("ABC1234");

        parkVehicle("ABC1235");

        parkVehicle("XYZ9999");

        exitVehicle("ABC1234");

        getStatistics();
    }
}