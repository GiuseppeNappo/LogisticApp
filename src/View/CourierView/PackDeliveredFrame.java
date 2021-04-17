package View.CourierView;

import ContextApplication.ContextApplicationOperator;
import Entity.Pack.Pack;
import Entity.Pack.PackDaoImplement;
import View.OperatorView.ManagePackCenterPanel;

import javax.swing.*;
import java.util.ArrayList;

public class PackDeliveredFrame {
    private JFrame frame;
    private JTextField textFieldIdPack;
    private JLabel lblIdPack;
    private JButton btnRemove;


    public PackDeliveredFrame() {
        setVariables();
        setLayoutManager();
        addComponentsToContainer();
        addActionsListener();
        setLocationAndSize();

    }

    private void setVariables() {
        frame = new JFrame();
        lblIdPack = new JLabel("ID COLLO");
        textFieldIdPack = new JTextField();
        btnRemove = new JButton("CONSEGNATO");
    }

    private void setLocationAndSize() {
        textFieldIdPack.setBounds(100, 28, 150, 20);
        lblIdPack.setBounds(30, 30, 150, 14);

        btnRemove.setBounds(80, 60, 150, 23);
    }

    private void addComponentsToContainer() {
        frame.getContentPane().add(textFieldIdPack);
        frame.getContentPane().add(lblIdPack);
        frame.getContentPane().add(btnRemove);

    }

    private void setLayoutManager() {
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        frame.setBounds(100, 100, 300, 150);
        frame.setResizable(false);
    }


    private void addActionsListener() {

        btnRemove.addActionListener(actionEvent -> {
            try {

                CourierView.getInstance().removeRow(textFieldIdPack.getText());

                frame.dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "valore nullo");
            }
        });
    }
}
