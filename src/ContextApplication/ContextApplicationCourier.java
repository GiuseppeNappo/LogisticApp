package ContextApplication;

import Entity.Worker.Worker;

/**
 * Il conesto semoplicemente e' una classe singelton usata per salvare dei dati che si saranno utili nel tempo per recuperare altre informazioni
 */
public class ContextApplicationCourier   {

    private Worker worker;
    private String vehicleId;
    private static ContextApplicationCourier context;


    private ContextApplicationCourier() {

    }

    public static ContextApplicationCourier getInstance() {
        if (context == null)
            context = new ContextApplicationCourier();
        return context;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }


}
