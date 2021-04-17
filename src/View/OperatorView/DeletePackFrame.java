package View.OperatorView;

import ContextApplication.ContextApplicationOperator;
import Entity.Pack.Pack;
import Entity.Pack.PackDaoImplement;
import Service.ManagePackInCenterService;
import javax.swing.*;
import java.util.ArrayList;

/**
 * Questa classe si occupera di prende l'inupt dell'utente e chiamare dei servizi per l'elimincazione del pacco dal centro di smistamento
 */
public class DeletePackFrame {
    private JFrame frame;
    private JTextField textFieldIdPack;
    private JLabel lblIdPack;
    private JButton btnRemove;
    private ManagePackInCenterService managePackInCenterService;
    private ArrayList<Pack> packs;
    private ManagePackCenterPanel managePackCenterPanel;

    public DeletePackFrame(ManagePackCenterPanel managePackCenterPanel) {
        setVariables();
        setLayoutManager();
        addComponentsToContainer();
        addActionsListener();
        setLocationAndSize();
        this.managePackCenterPanel = managePackCenterPanel;

    }

    private void setVariables() {
        frame = new JFrame();
        lblIdPack = new JLabel("ID COLLO");
        textFieldIdPack = new JTextField();
        btnRemove = new JButton("RIMUOVI");
        managePackInCenterService = new ManagePackInCenterService(new PackDaoImplement());
        packs = managePackInCenterService.getPacksByCenterId(ContextApplicationOperator.getInstance().getCenterSorting().getId());
    }

    private void setLocationAndSize() {
        textFieldIdPack.setBounds(100, 28, 150, 20);
        lblIdPack.setBounds(30, 30, 150, 14);
        btnRemove.setBounds(80, 60, 89, 23);
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
                managePackCenterPanel.removeRow(textFieldIdPack.getText());
                frame.dispose();
            }catch (Exception exception){
                JOptionPane.showMessageDialog(null, "VALORE NULLO");
            }
        });
    }

}
