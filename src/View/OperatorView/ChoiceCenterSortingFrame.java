package View.OperatorView;

import ContextApplication.ContextApplicationOperator;
import Entity.CenterSorting.CenterSorting;
import Entity.CenterSorting.CenterSortingDaoImplement;
import Service.CenterSortingService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *  Estende JFrame in questa classe avremo la possibiltò di scegliere il nostro centro
 *  di smistamento
 */

public class ChoiceCenterSortingFrame extends JFrame {


    private JScrollPane scrollPane;
    private JList list;
    private ArrayList<CenterSorting> tmpCenters;
    private ArrayList<CenterSorting> centers;

    public ChoiceCenterSortingFrame() {
        setVariables();
        setLayoutManager();
        addComponentsToContainer();
        addActionsListener();
    }

    private void setLayoutManager() {
        setVisible(true);
        this.setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout());
        setSize(new Dimension(400, 200));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void setVariables() {
        scrollPane = new JScrollPane();
        list = new JList();
        tmpCenters = getData();
        centers = new ArrayList<CenterSorting>();

    }

    private void addComponentsToContainer() {
        this.add(scrollPane);
        ArrayList<String> choices = new ArrayList<String>();

        for (CenterSorting center : tmpCenters) {
            if(center.getCompany().getVatNumber().equals(ContextApplicationOperator.getInstance().getWorker().getCompany().getVatNumber())){
                    choices.add(center.getCity() + " , " + center.getAddress());
                    centers.add(center);
            }
        }
        list.setListData(choices.toArray());
        scrollPane.setViewportView(list);
    }

    private void addActionsListener() {
        JFrame self = this;
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                list = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    // Double-click detected
                    ContextApplicationOperator.getInstance().setCenterSorting(centers.get(list.locationToIndex(evt.getPoint())));
                    OperatorView.getOperatorView();
                    self.dispose();
                }
            }
        });
    }


    private ArrayList<CenterSorting> getData() {
        CenterSortingService centerSortingService = new CenterSortingService(new CenterSortingDaoImplement());
        return centerSortingService.getAll();
    }
}
