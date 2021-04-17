import Entity.Vehicle.Vehicle;
import Entity.Vehicle.VehicleDao;
import View.HomeView.HomeView;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(HomeView::getHomeView);
    }
}
