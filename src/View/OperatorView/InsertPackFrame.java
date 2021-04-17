package View.OperatorView;

import ContextApplication.ContextApplicationOperator;
import Entity.CenterSorting.CenterSortingDaoImplement;
import Entity.Client.ClientDaoImplement;
import Entity.Pack.Pack;
import Entity.Pack.PackBuilder;
import Entity.Pack.PackDaoImplement;
import Entity.Pack.PackState;
import Service.ManagePackInCenterService;

import javax.swing.*;


class InsertPackFrame {

    private JFrame frame;
    private JTextField textFieldWeight;
    private JTextField textFieldAddressSender;
    private JTextField textFieldTelephoneSender;
    private JTextField textFieldCitySender;
    private JTextField textFieldNameSender;
    private JTextField textFieldSurnameSender;
    private JTextField textFieldAddressRecipient;
    private JTextField textFieldTelephoneRecipient;
    private JTextField textFieldCityRecipient;
    private JTextField textFieldNameRecipient;
    private JTextField textFieldSurnameRecipient;


    private JLabel lblWeight;
    private JLabel lblAddressSender;
    private JLabel lblTelephoneSender;
    private JLabel lblCitySender;
    private JLabel lblNameSender;
    private JLabel lblSurnameSender;
    private JLabel lblAddressRecipient;
    private JLabel lblTelephoneRecipient;
    private JLabel lblCityRecipient;
    private JLabel lblNameRecipient;
    private JLabel lblSurnameRecipient;
    private JButton btnClear;
    private JButton btnSubmit;
    private Thread tmp;
    private ManagePackCenterPanel managePackCenterPanel;
    private String name;


    /**
     * Insert packframe è una view che funge da form per l'inserimento dei dati di un collo
     *
     */
    public InsertPackFrame(ManagePackCenterPanel managePackCenterPanel) {
        setVariables();
        setLayoutManager();
        addComponentsToContainer();
        addActionsListener();
        setLocationAndSize();
        // tmp = myExecutor;
        this.managePackCenterPanel = managePackCenterPanel;
        //initComponents();
    }

    private void setVariables() {
        frame = new JFrame();
        lblWeight = new JLabel("PESO");
        lblAddressSender = new JLabel("INDIRIZZO MITTENTE");
        lblTelephoneSender = new JLabel("TELEFONO MITTENTE");
        lblCitySender = new JLabel("CITTA' MITTENTE");
        lblNameSender = new JLabel("NOME MITTENTE");
        lblSurnameSender = new JLabel("COGNOME MITTENTE");
        lblAddressRecipient = new JLabel("INDIRIZZO DESTINATARIO");
        lblTelephoneRecipient = new JLabel("TELEFONO DESTINATARIO");
        lblCityRecipient = new JLabel("CITTA' DESTINATARIO");
        lblNameRecipient = new JLabel("NOME DESTINATARIO");
        lblSurnameRecipient = new JLabel("COGNOME DESTINATARIO");
        textFieldWeight = new JTextField();
        textFieldAddressSender = new JTextField();
        textFieldTelephoneSender = new JTextField();
        textFieldCitySender = new JTextField();
        textFieldNameSender = new JTextField();
        textFieldSurnameSender = new JTextField();
        textFieldAddressRecipient = new JTextField();
        textFieldTelephoneRecipient = new JTextField();
        textFieldCityRecipient = new JTextField();
        textFieldNameRecipient = new JTextField();
        textFieldSurnameRecipient = new JTextField();
        btnClear = new JButton("RESET");
        btnSubmit = new JButton("AGGIUNGI");

    }

    private void setLocationAndSize() {
        textFieldWeight.setBounds(250, 28, 150, 20);
        lblWeight.setBounds(60, 30, 46, 14);

        textFieldAddressSender.setBounds(250, 58, 150, 20);
        lblAddressSender.setBounds(60, 60, 120, 14);

        textFieldTelephoneSender.setBounds(250, 88, 150, 20);
        lblTelephoneSender.setBounds(60, 90, 120, 14);

        textFieldCitySender.setBounds(250, 118, 150, 20);
        lblCitySender.setBounds(60, 120, 120, 14);

        textFieldNameSender.setBounds(250, 148, 150, 20);
        lblNameSender.setBounds(60, 150, 120, 14);

        textFieldSurnameSender.setBounds(250, 178, 150, 20);
        lblSurnameSender.setBounds(60, 180, 120, 14);

        textFieldAddressRecipient.setBounds(250, 208, 150, 20);
        lblAddressRecipient.setBounds(60, 210, 150, 14);

        textFieldTelephoneRecipient.setBounds(250, 238, 150, 20);
        lblTelephoneRecipient.setBounds(60, 240, 150, 14);

        textFieldCityRecipient.setBounds(250, 268, 150, 20);
        lblCityRecipient.setBounds(60, 270, 150, 14);

        textFieldNameRecipient.setBounds(250, 298, 150, 20);
        lblNameRecipient.setBounds(60, 300, 150, 14);

        textFieldSurnameRecipient.setBounds(250, 328, 150, 20);
        lblSurnameRecipient.setBounds(60, 330, 150, 14);

        btnClear.setBounds(240, 387, 89, 23);
        btnSubmit.setBounds(140, 387, 89, 23);
    }

    private void addComponentsToContainer() {
        frame.getContentPane().add(textFieldTelephoneSender);
        frame.getContentPane().add(textFieldWeight);
        frame.getContentPane().add(lblWeight);
        frame.getContentPane().add(textFieldWeight);
        frame.getContentPane().add(lblAddressSender);
        frame.getContentPane().add(textFieldAddressSender);
        frame.getContentPane().add(lblTelephoneSender);
        frame.getContentPane().add(textFieldCitySender);
        frame.getContentPane().add(lblCitySender);
        frame.getContentPane().add(textFieldNameSender);
        frame.getContentPane().add(lblNameSender);
        frame.getContentPane().add(textFieldSurnameSender);
        frame.getContentPane().add(lblSurnameSender);
        frame.getContentPane().add(lblAddressRecipient);
        frame.getContentPane().add(textFieldAddressRecipient);
        frame.getContentPane().add(lblTelephoneRecipient);
        frame.getContentPane().add(textFieldTelephoneRecipient);
        frame.getContentPane().add(lblCityRecipient);
        frame.getContentPane().add(textFieldCityRecipient);
        frame.getContentPane().add(lblNameRecipient);
        frame.getContentPane().add(textFieldNameRecipient);
        frame.getContentPane().add(lblSurnameRecipient);
        frame.getContentPane().add(textFieldSurnameRecipient);
        frame.getContentPane().add(btnClear);
        frame.getContentPane().add(btnSubmit);
    }

    private void setLayoutManager() {
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        frame.setBounds(100, 100, 450, 500);
        frame.setResizable(false);
    }


    private void addActionsListener() {


        btnSubmit.addActionListener(actionEvent -> {
            ManagePackInCenterService managePackInCenterService = new ManagePackInCenterService(new PackDaoImplement(), new ClientDaoImplement(), new CenterSortingDaoImplement());
            try {
                Valuables(textFieldWeight.getText());
                Valuables(textFieldAddressSender.getText());
                Valuables(textFieldTelephoneSender.getText());
                Valuables(textFieldCitySender.getText());
                Valuables(textFieldNameSender.getText());
                Valuables(textFieldSurnameSender.getText());
                Valuables(textFieldAddressRecipient.getText());
                Valuables(textFieldTelephoneRecipient.getText());
                Valuables(textFieldCityRecipient.getText());
                Valuables(textFieldNameRecipient.getText());
                Valuables(textFieldSurnameRecipient.getText());


                Pack pack = PackBuilder.newBuilder()
                        .weight(Integer.parseInt(textFieldWeight.getText()))
                        .addressSender(textFieldAddressSender.getText())
                        .telephoneSender(textFieldTelephoneSender.getText())
                        .citySender(textFieldCitySender.getText())
                        .nameSender(textFieldNameSender.getText())
                        .surnameSender(textFieldSurnameSender.getText())
                        .addressRecipient(textFieldAddressRecipient.getText())
                        .telephoneRecipient(textFieldTelephoneRecipient.getText())
                        .cityRecipient(textFieldCityRecipient.getText())
                        .nameRecipient(textFieldNameRecipient.getText())
                        .surnameRecipient(textFieldSurnameRecipient.getText())
                        .PackState(PackState.PACK_INSERTED).build();

                managePackInCenterService.insertPackInCenterService(pack, ContextApplicationOperator.getInstance().getCenterSorting());
                managePackCenterPanel.addRow();
                frame.dispose();

            } catch (RuntimeException exc) {
                JOptionPane.showMessageDialog(null, exc.getMessage());
            }
        });

        btnClear.addActionListener(actionEvent -> {
            textFieldWeight.setText(null);
            textFieldAddressSender.setText(null);
            textFieldTelephoneSender.setText(null);
            textFieldCitySender.setText(null);
            textFieldNameSender.setText(null);
            textFieldSurnameSender.setText(null);
            textFieldAddressRecipient.setText(null);
            textFieldTelephoneRecipient.setText(null);
            textFieldCityRecipient.setText(null);
            textFieldNameRecipient.setText(null);
            textFieldSurnameRecipient.setText(null);
        });
    }

    private void Valuables(String name) {

        if (name == null || name.isEmpty()) {
            throw new RuntimeException("Valore non valido o nullo");

        } else
            this.name = name;
    }
}



