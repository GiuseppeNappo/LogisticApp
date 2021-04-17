package View.HomeView;

import ContextApplication.ContextApplicationCourier;
import ContextApplication.ContextApplicationOperator;
import Entity.Worker.WorkerDaoImplement;
import Service.AuthType;
import Service.LoginService;
import Service.WorkerService;
import View.CourierView.ChoiceVehicleFrame;
import View.CourierView.CourierView;
import View.OperatorView.ChoiceCenterSortingFrame;

import javax.swing.*;

/**
 * Questo classe estende la classe JPANEL in questa classe andiamo a gestire i
 * login dei lavoratori
 */
public class PrivateAreaPanel extends JPanel {

    JLabel idWorkerLabel;
    JLabel passwordLabel;
    JTextField idWorkerTextField;
    JPasswordField passwordField;
    JButton loginButton;
    JButton resetButton;
    WorkerService workerService;


    PrivateAreaPanel() {
        setVariables();
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionsListener();
    }

    private void setLayoutManager() {
        this.setLayout(null);
    }

    private void setVariables() {
        idWorkerLabel = new JLabel("ID USER");
        passwordLabel = new JLabel("PASSWORD");
        idWorkerTextField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("LOGIN");
        resetButton = new JButton("RESET");
        workerService = new WorkerService(new WorkerDaoImplement());
    }

    private void setLocationAndSize() {
        //LABELS
        idWorkerLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 230, 100, 30);

        //FIELDS
        idWorkerTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 230, 150, 30);

        //BUTTONS
        loginButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);
    }

    private void addComponentsToContainer() {
        add(idWorkerLabel);
        add(passwordLabel);
        add(idWorkerTextField);
        add(passwordField);
        add(loginButton);
        add(resetButton);
    }

    private void addActionsListener() {
        /**
         * In questo evento gestiamo l'autenticazione attraverso dei service delle classi che si occuperanno
         * di fornire appunto dei servizi
         */
        loginButton.addActionListener(actionEvent -> {
            LoginService loginService = new LoginService(new WorkerDaoImplement());
            if (AuthType.COURIER_AUTHENTICATED.equals(loginService.authenticate(idWorkerTextField.getText(), passwordField.getText()))) {
                ContextApplicationCourier.getInstance().setWorker(workerService.get(idWorkerTextField.getText()));
                closeHomeFrame();
                new ChoiceVehicleFrame();
            } else if (AuthType.OPERATOR_AUTHENTICATED.equals(loginService.authenticate(idWorkerTextField.getText(), passwordField.getText()))) {
                ContextApplicationOperator.getInstance().setWorker(workerService.get(idWorkerTextField.getText()));
                closeHomeFrame();
                new ChoiceCenterSortingFrame();
            } else
                JOptionPane.showMessageDialog(null, "PASSWORD O/E ID ERRATO");
        });

        resetButton.addActionListener(actionEvent -> {
            idWorkerTextField.setText(null);
            passwordField.setText(null);
        });
    }

    private void closeHomeFrame() {
        HomeView.getHomeView().getHome().dispose();
        HomeView.getHomeView().getHome().setVisible(false);
    }
}
