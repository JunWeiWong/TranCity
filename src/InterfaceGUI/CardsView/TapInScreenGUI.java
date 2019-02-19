package InterfaceGUI.CardsView;

import Controllers.*;
import InterfaceGUI.BothView.ListOfTransitCardsScreenGUI;
import Model.BusSystem;
import Model.SubwaySystem;
import Model.TimeArray;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Date;

public class TapInScreenGUI extends javax.swing.JFrame {

    private Image backgroundImage = Toolkit.getDefaultToolkit().getImage("banner2.png");

    private javax.swing.JLabel messageLabel;
    private javax.swing.JLabel outcomeLabel;
    private javax.swing.JLabel systemLabel;
    private javax.swing.JLabel pointLabel;
    private javax.swing.JLabel hrLabel;
    private javax.swing.JLabel minLabel;
    private javax.swing.JLabel secLabel;
    private javax.swing.JComboBox hoursBox;
    private javax.swing.JComboBox minutesBox;
    private javax.swing.JComboBox secondsBox;
    private javax.swing.JTextField systemTextField;
    private javax.swing.JTextField pointTextField;
    private javax.swing.JButton tapInBtn;
    private javax.swing.JButton goBackBtn;
    private String trans;
    private TransitCard card;
    private TransitUser user;

    public TapInScreenGUI(String transport, TransitCard c, TransitUser u) {

        user = u;
        card = c;
        trans = transport;
        this.setContentPane(
                new JPanel() {
                    @Override
                    public void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawImage(backgroundImage, 0, 0, null);
                    }
        });
        initComponents();
    }

    private void initComponents() {

        messageLabel = new JLabel();
        outcomeLabel = new JLabel();
        systemLabel = new JLabel();
        pointLabel = new JLabel();
        hrLabel = new JLabel();
        minLabel = new JLabel();
        secLabel = new JLabel();
        systemTextField = new JTextField(20);
        pointTextField = new JTextField();
        tapInBtn = new JButton();
        goBackBtn = new JButton();

        setLocation(200, 200);
        setPreferredSize(new Dimension(1000, 500));
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tap In " + trans + " on card " + card.getCardNumber());

        messageLabel.setText("Welcome on board, Please TAP IN");
        messageLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 20));
        if (trans.equals("Subway")) {
            systemLabel.setText("Subway Line:");
            pointLabel.setText("Station Name:");

        } else {
            systemLabel.setText("Bus Route:");
            pointLabel.setText("Stop Number:");
        }
        systemLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 16));
        pointLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 16));

        hoursBox = new JComboBox<>(TimeArray.hours());
        minutesBox = new JComboBox<>(TimeArray.minsec());
        secondsBox = new JComboBox<>(TimeArray.minsec());
        hrLabel.setText("hour: ");
        hrLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 16));
        minLabel.setText("min:");
        minLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 16));
        secLabel.setText("sec:");
        secLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 16));

        tapInBtn.setText("TAP IN");
        goBackBtn.setText("Back");

        tapInBtn.addActionListener(this::tapInButtonActionPerformed);
        goBackBtn.addActionListener(this::goBackBtnActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout
                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(
                                layout
                                        .createSequentialGroup()
                                        .addContainerGap(300, Short.MAX_VALUE)
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                        .addGroup(layout.createSequentialGroup().addComponent(messageLabel))
                                                        .addGroup(
                                                                layout
                                                                        .createSequentialGroup()
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(systemLabel)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(systemTextField))
                                                        .addGroup(
                                                                layout
                                                                        .createSequentialGroup()
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(pointLabel)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(pointTextField))
                                                        .addGroup(
                                                                layout
                                                                        .createSequentialGroup()
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(hrLabel)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(hoursBox)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(minLabel)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(minutesBox)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(secLabel)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(secondsBox))
                                                        .addGroup(
                                                                layout
                                                                        .createSequentialGroup()
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(outcomeLabel))
                                                        .addGroup(
                                                                layout
                                                                        .createSequentialGroup()
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(tapInBtn)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(goBackBtn)))
                                        .addContainerGap(300, Short.MAX_VALUE)));

        layout.setVerticalGroup(
                layout
                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(
                                layout
                                        .createSequentialGroup()
                                        .addContainerGap(150, Short.MAX_VALUE)
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                        .addComponent(messageLabel))
                                        .addContainerGap(50, Short.MAX_VALUE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                        .addComponent(systemLabel)
                                                        .addComponent(systemTextField))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                        .addComponent(pointLabel)
                                                        .addComponent(pointTextField))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                        .addComponent(hrLabel)
                                                        .addComponent(hoursBox)
                                                        .addComponent(minLabel)
                                                        .addComponent(minutesBox)
                                                        .addComponent(secLabel)
                                                        .addComponent(secondsBox))
                                        .addContainerGap(50, Short.MAX_VALUE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                        .addComponent(outcomeLabel))
                                        .addContainerGap(50, Short.MAX_VALUE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(tapInBtn)
                                                        .addComponent(goBackBtn))
                                        .addContainerGap(200, Short.MAX_VALUE)));
        pack();
    }

    private void tapInButtonActionPerformed(java.awt.event.ActionEvent evt) {
        outcomeLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 16));
        try {
            String time =
                    hoursBox.getSelectedItem()
                            + ":"
                            + minutesBox.getSelectedItem()
                            + ":"
                            + secondsBox.getSelectedItem();
            String date =
                    Administrator.getListOfDays().get(Administrator.getListOfDays().size() - 1).getDate();
            Date tapInTime = new Date(date + " " + time);
            long eastTime = tapInTime.getTime();

            if (trans.equals("Subway")
                    && SubwaySystem.getStationByName(systemTextField.getText(), pointTextField.getText())
                    != null) {

                if (card.getBalance() >= 0 && card.getStatus().equals("Active")) {
                    card.tapInSubway(pointTextField.getText(), eastTime, systemTextField.getText());
                    Writer.writeAction(
                            "EnterSubway,"
                                    + card.getCardNumber()
                                    + ","
                                    + pointTextField.getText()
                                    + ","
                                    + systemTextField.getText()
                                    + ","
                                    + eastTime);
                    new TapOutScreenGUI("Subway", card, user).setVisible(true);
                    this.setVisible(false);
                } else {
                    outcomeLabel.setText("Not enough balance or your card is inactive");
                }
            } else if (trans.equals("Bus")
                    && BusSystem.getStopByNumber(
                    systemTextField.getText(), Integer.valueOf(pointTextField.getText()))
                    != null) {

                if (card.getBalance() >= 0 && card.getStatus().equals("Active")) {
                    double cost = card.getBalance();
                    card.tapInBus(
                            Integer.valueOf(pointTextField.getText()), eastTime, systemTextField.getText());
                    cost = cost - card.getBalance();
                    Administrator.getListOfDays()
                            .get(Administrator.getListOfDays().size() - 1)
                            .incrementFare(cost);
                    Writer.writeAction(
                            "EnterBus,"
                                    + card.getCardNumber()
                                    + ","
                                    + pointTextField.getText()
                                    + ","
                                    + systemTextField.getText()
                                    + ","
                                    + eastTime);
                    new TapOutScreenGUI("Bus", card, user).setVisible(true);
                    this.setVisible(false);
                } else {
                    outcomeLabel.setText("Not enough balance or your card is inactive");
                }
            } else if (SubwaySystem.getStationByName(systemTextField.getText(), pointTextField.getText())
                    == null
                    || BusSystem.getStopByNumber(
                    systemTextField.getText(), Integer.valueOf(pointTextField.getText()))
                    == null) {
                outcomeLabel.setText("The Line/Route name or station/stop is incorrect, check the spell");
            }

        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void goBackBtnActionPerformed(java.awt.event.ActionEvent evt) {
        new ListOfTransitCardsScreenGUI(user).setVisible(true);
        this.setVisible(false);
    }
}
