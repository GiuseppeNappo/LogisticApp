package View.HomeView;

import javax.swing.*;
import java.awt.*;

/**
 * Tutte le View Hanno la stessa struttura
 * Un costruttore dove all'interno vengono
 * chimati 4  o piu metodi
 */

public class HomeView extends JFrame {


    private static HomeView homeView = null;
    JPanel menu = new JPanel();
    JPanel home = new JPanel();
    JButton privateAreaButton = new JButton("AREA RISERVATA");
    JButton trackPackButton = new JButton("RINTRACCIA PACCO");
    JPanel privateAreaPanel = new PrivateAreaPanel();
    JPanel trackPackPanel = new TrackPackPanel();


    private HomeView() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionsListener();

    }

    /**
     * setLayoutManager()
     * Settiamo il layout di ogni componente
     */

    private void setLayoutManager() {
        //SET LAYOUT FRAME
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        //SET LAYOUT HOMEVIEW
        home.setLayout(new GridLayout(1, 2));

        //SET LAYOUT MENU
        menu.setLayout(new GridLayout(2, 1));

        //SET COLOR RIGHTPANELS
        privateAreaButton.setBackground(Color.lightGray);
        trackPackButton.setBackground(Color.lightGray);

    }

    /**
     * setLocationAndSize()
     * Settiamo il posizionamento di ogni elemento nella view
     */
    private void setLocationAndSize() {
        this.setSize(800, 600);
    }
    /**
     * addComponentsToContainer();
     * Aggiungiamo i componenti ai relativi container
     */
    private void addComponentsToContainer() {

        //ADD COMPONENT TO MENU
        menu.add(privateAreaButton);
        menu.add(trackPackButton);

        //ADD COMPONENT TO FRAME
        this.setTitle("Logistica Campania");
        this.setVisible(true);
        this.add(home);

        //ADD COMPONENT TO HOMEVIEW
        home.add(menu);
        home.add(privateAreaPanel);

    }

    /**
     * addActionsListener()
     * dove saranno inseriti tutti gli actions listener dei component
     */
    private void addActionsListener() {

        privateAreaButton.addActionListener(actionEvent -> {
            home.remove(home.getComponent(1));
            home.add(privateAreaPanel);
            home.repaint();
            home.revalidate();
        });

        trackPackButton.addActionListener(actionEvent -> {
            home.remove(home.getComponent(1));
            home.add(trackPackPanel);
            home.repaint();
            home.revalidate();

        });
    }

    public JFrame getHome() {
        return this;
    }

    /**
     * GetHomeView e' un singelton in modo da ricevere sempre la stessa instanza
     * se quest'ultima è settata
     */
    public static HomeView getHomeView() {
        if (homeView == null)
            homeView = new HomeView();
        return homeView;
    }
}
