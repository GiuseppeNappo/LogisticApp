package View.HomeView;

import View.InfoPackView.InfoPackView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

/**
 *  Questa classe ci da modo di accedere semplicemente al
 *  frame di ricerca di un collo
 */
public class TrackPackPanel extends JPanel {

    JLabel searchTrackLabel = new JLabel("ID SPEDIZIONE");
    JTextField searchTrackField = new JTextField();
    JButton submitButton = new JButton("SUBMIT");
    InfoPackView infopackview = new InfoPackView();

    TrackPackPanel() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionsListeners();
    }

    private void setLayoutManager() {
        this.setLayout(null);
    }

    private void setLocationAndSize() {
        searchTrackLabel.setBounds(50, 150, 100, 30);
        searchTrackField.setBounds(150, 150, 150, 30);
        submitButton.setBounds(100, 200, 150, 30);
    }

    private void addComponentsToContainer() {
        add(searchTrackField);
        add(searchTrackLabel);
        add(submitButton);
    }

    private void addActionsListeners() {
        submitButton.addActionListener(actionEvent -> {
            new InfoPackView(searchTrackField.getText());
        });
    }
}
