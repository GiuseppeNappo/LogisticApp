package View.InfoPackView;

import Entity.CenterSorting.CenterSorting;
import Entity.CenterSorting.CenterSortingDaoImplement;
import Entity.Company.CompanyDaoImplement;
import Entity.Pack.Pack;
import Entity.Pack.PackDaoImplement;
import Entity.Pack.PackState;
import Entity.Track.Track;
import Entity.Track.TrackDaoImplement;
import Entity.Vehicle.Vehicle;
import Entity.Vehicle.VehicleDaoImplement;
import Service.InfoTrackService;

import javax.swing.*;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public class InfoPackView extends JFrame {

    private JFrame frame;
    private JLabel nameCompany;
    private JLabel idVehicle;
    private JLabel stateLabel;
    private JLabel lastPlaceLabel;
    private JLabel nameAndSurnameSenderLabel;
    private JLabel nameAndSurnameRecipientLabel;
    private String idPack;
    private InfoTrackService infoTrackService;

    public InfoPackView(String idPack) {
        this.idPack = idPack;
        setVariables();
        setLayoutManager();
        addComponentsToContainer();
        addActionsListener();
        setLocationAndSize();

    }

    public InfoPackView() {
		// TODO Auto-generated constructor stub
	}

	private void setVariables() {
        frame = new JFrame();
        stateLabel = new JLabel("STATO: ");
        nameCompany = new JLabel("NOME AZIENDA: ");
        idVehicle = new JLabel("ID VEICOLO:  ");
        lastPlaceLabel = new JLabel("PRESSO:  ");
        nameAndSurnameSenderLabel = new JLabel("DATI MITTENTE:  ");
        nameAndSurnameRecipientLabel = new JLabel("DATI DESTINATARIO:  ");
        infoTrackService = new InfoTrackService(new TrackDaoImplement(), new VehicleDaoImplement(), new PackDaoImplement(), new CenterSortingDaoImplement());

    }

    private void setLocationAndSize() {
        stateLabel.setBounds(40, 20, 300, 100);
        nameCompany.setBounds(40, 50, 300, 100);
        idVehicle.setBounds(40, 80, 300, 100);
        lastPlaceLabel.setBounds(40, 110, 300, 100);
        nameAndSurnameSenderLabel.setBounds(40, 140, 300, 100);
        nameAndSurnameRecipientLabel.setBounds(40, 170, 300, 100);
    }

    private void addComponentsToContainer() {
        frame.add(stateLabel);
        frame.add(nameCompany);
        frame.add(idVehicle);
        frame.add(lastPlaceLabel);
        frame.add(nameAndSurnameSenderLabel);
        frame.add(nameAndSurnameRecipientLabel);
    }

    private void setLayoutManager() {
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        frame.setBounds(100, 100, 450, 500);
        frame.setResizable(false);
    }


    private void addActionsListener() {
        try {

            Pack pack = infoTrackService.getPack(idPack);
            Vehicle vehicle1 = infoTrackService.getVehicle(pack.getId());

            stateLabel.setText(stateLabel.getText() + pack.getState());

            if (pack.getState() == PackState.PACK_INSERTED && vehicle1 == null) {
                CenterSorting centerSorting = infoTrackService.getCenterSorting(pack.getId());
                idVehicle.setText(idVehicle.getText() + "NESSUN VEICOLO");
                nameCompany.setText(nameCompany.getText() + centerSorting.getCompany().getName());
                lastPlaceLabel.setText(lastPlaceLabel.getText() + centerSorting.getCity() + " " + centerSorting.getAddress());
            } else if (pack.getState() == PackState.PACK_DELIVERING || pack.getState() == PackState.PACK_DELIVERED || pack.getState() == PackState.PACK_INSERTED) {
                Track track = infoTrackService.getTrackByPackId(pack.getId());
                Vehicle vehicle = track.getVehicle();
                nameCompany.setText(nameCompany.getText() + vehicle.getCompany().getName());
                idVehicle.setText(idVehicle.getText() + vehicle.getId());
                track = infoTrackService.getLastStop(Integer.parseInt(vehicle.getId()));
                lastPlaceLabel.setText(lastPlaceLabel.getText() + track.getCenterSorting().getCity() + " " + track.getCenterSorting().getAddress());
            }

            nameAndSurnameSenderLabel.setText(nameAndSurnameSenderLabel.getText() + pack.getSender().getName() + " " + pack.getSender().getSurname() + " " + pack.getSender().getCity() + " " + pack.getSender().getAddress());
            nameAndSurnameRecipientLabel.setText(nameAndSurnameRecipientLabel.getText() + pack.getRecipient().getName() + " " + pack.getRecipient().getSurname() + " " + pack.getRecipient().getCity() + " " + pack.getRecipient().getAddress());
        }catch (NullPointerException nullPointerException){
            JOptionPane.showMessageDialog(null , "IL COLLO NON ESISTE");
        }
    }
}
