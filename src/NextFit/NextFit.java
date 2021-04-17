package NextFit;


import Entity.Pack.Pack;
import Entity.Vehicle.Vehicle;

import java.util.ArrayList;
import java.util.Arrays;





public class NextFit {

    public static ArrayList<Vehicle>  nextFit(ArrayList<Vehicle> vehicles, int m, ArrayList<Pack> packs, int n) {
      
        // pack
        int[] allocation = new int[n];
        int j = 0;
        int out = 0;
        // Initially no vehicle is assigned to any pack
        Arrays.fill(allocation, -1);
        // pick each pack and find suitable vehicles
        // according to its size ad assign to it
        for (int i = 0; i < n; i++) {
            // Do not start from beginning
            while (j < m) {

                if (vehicles.get(j).getAvailableCapacity() >= packs.get(i).getWeight()) {
                    // allocate vehicles j to p[i] pack
                    //allocation[i] = j;
                    // Reduce available memory in this vehicle.
                    vehicles.get(j).decreaseAvailableCapacity(packs.get(i).getWeight());
                    vehicles.get(j).addPackToVehicle(packs.get(i));
                    out = j;
                    break;
                }

                j = (j + 1) % m;
                // mod m will help in traversing the vehicles from
                // starting vehicle after we reach the end.
                if(out == j)
                    break;
            }
        }

        return vehicles;
    }

}

