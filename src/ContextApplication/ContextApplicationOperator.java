package ContextApplication;

import Entity.CenterSorting.CenterSorting;
import Entity.Worker.Worker;

/**
 * Il conesto semoplicemente e' una classe singelton usata per salvare dei dati che si saranno utili nel tempo per recuperare altre informazioni
 */
public class ContextApplicationOperator {
    private Worker worker;
    private CenterSorting centerSorting;
    private static ContextApplicationOperator context;

    private ContextApplicationOperator() {

    }

    public static ContextApplicationOperator getInstance() {
        if (context == null)
            context = new ContextApplicationOperator();
        return context;
    }

    public CenterSorting getCenterSorting() {
        return centerSorting;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setCenterSorting(CenterSorting centerSorting) {
        this.centerSorting = centerSorting;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }


}
