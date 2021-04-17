package Service;

import Entity.Pack.Pack;
import Entity.Vehicle.Vehicle;
import NextFit.NextFit;

import java.util.ArrayList;

public class NextFitService {
/**
 *  Questo  classe offre un servizio  alle classi , che vogliono utilizzare l'algoritmo di nextFit , Questa classe prende in input nel costruttore un ArrayList di veicoli
 *  e un array list di colli andando elabora il next fit e successivamente ritorna l'arraylist dei veicoli allocati dai colli
 */

    public ArrayList<Vehicle> getService(ArrayList<Vehicle> vehicles , ArrayList<Pack> packs){

        return NextFit.nextFit(vehicles , vehicles.size() , packs , packs.size());
    }
}
