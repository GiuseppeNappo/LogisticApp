package View.OperatorView;

import javax.swing.*;
import java.awt.*;

/**
 * Questa classe rappresentera la schermata principale dell'utente
 * dove potremo vedere le funzionalità a cui ha accesso l'operatore
 */
public class OperatorView extends JFrame {
    private static OperatorView instance = null;
    JPanel menu = new JPanel();
    JPanel home = new JPanel();
    JButton insertPackButton = new JButton("INSERIMENTO COLLO");
    JButton loadVehicleButton = new JButton("CARICAMENTO VEICOLI");
    JPanel insertPackPanel = ManagePackCenterPanel.getInstance();
    JPanel loadVehiclePanel = new LoadVehiclePanel();


    private OperatorView() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionsListener();
    }

    private void setLayoutManager() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);


        home.setLayout(new GridLayout(1, 2));


        menu.setLayout(new GridLayout(2, 1));

        insertPackButton.setBackground(Color.lightGray);
        loadVehicleButton.setBackground(Color.lightGray);

    }

    private void setLocationAndSize() {
        this.setSize(1020, 600);
    }

    private void addComponentsToContainer() {

        //ADD COMPONENT TO MENU
        menu.add(insertPackButton);
        menu.add(loadVehicleButton);

        //ADD COMPONENT TO FRAME
        this.setTitle("Logistica Campania");
        this.setVisible(true);
        this.add(home);

        //ADD COMPONENT TO HOMEVIEW
        home.add(menu);
        home.add(insertPackPanel);

    }

    private void addActionsListener() {

        insertPackButton.addActionListener(actionEvent -> {
            home.remove(home.getComponent(1));
            home.add(insertPackPanel);
            home.repaint();
            home.revalidate();
        });

        loadVehicleButton.addActionListener(actionEvent -> {
            home.remove(home.getComponent(1));
            home.add(loadVehiclePanel);
            home.repaint();
            home.revalidate();
        });
    }


    public JFrame getHome() {
        return this;
    }

    public static OperatorView getOperatorView() {
        if (instance == null)
            instance = new OperatorView();
        return instance;
    }
}
