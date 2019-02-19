package InterfaceGUI.CardHolderView;

import Controllers.TransitUser;
import javax.swing.*;
import java.awt.*;
import Controllers.AdminUser;
import InterfaceGUI.AdminView.ListOfCardholdersScreenGUI;

public class RecentTripsScreenGUI extends javax.swing.JFrame {

  private Image backgroundImage = Toolkit.getDefaultToolkit().getImage("banner2.png");

  private javax.swing.JLabel messageLabel;
  private javax.swing.JButton goBackBtn;
  private javax.swing.JTable tripsTable;
  private TransitUser user;
  private AdminUser controller;

  public RecentTripsScreenGUI(TransitUser u, AdminUser c) {

    this.setContentPane(
            new JPanel() {
              @Override
              public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, null);
              }
        });
    controller = c;
    user = u;
    initComponents();
  }

  private void initComponents() {

    messageLabel = new JLabel();
    goBackBtn = new JButton();

    setLocation(200, 200);
    setPreferredSize(new Dimension(1000, 500));
    setResizable(false);
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Recent Trips");

    String rows[][] = user.getThreeRecentTrips();
    String headers[] = {"Date/Time", "Start Location", "End Location", "Cost"};

    tripsTable = new JTable(rows, headers);
    tripsTable.setRowHeight(50);
    tripsTable.getColumnModel().getColumn(0).setPreferredWidth(200);
    tripsTable.getColumnModel().getColumn(1).setPreferredWidth(150);
    tripsTable.getColumnModel().getColumn(2).setPreferredWidth(150);
    tripsTable.getColumnModel().getColumn(3).setPreferredWidth(80);
    JScrollPane scrollPane = new JScrollPane(tripsTable);
    tripsTable.setGridColor(Color.gray.brighter());
    Dimension d = new Dimension(580, 200);
    tripsTable.setPreferredScrollableViewportSize(d);

    messageLabel.setText("Please see below to view your card transaction activity");
    messageLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 20));
    goBackBtn.setText("Back");

    goBackBtn.addActionListener(this::goBackBtnActionPerformed);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);

    layout.setHorizontalGroup(
            layout
                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addGroup(
                            layout
                                    .createSequentialGroup()
                                    .addContainerGap(100, Short.MAX_VALUE)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addGroup(layout.createSequentialGroup().addComponent(messageLabel))
                                                    .addGroup(
                                                            layout
                                                                    .createSequentialGroup()
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(scrollPane))
                                                    .addGroup(
                                                            layout
                                                                    .createSequentialGroup()
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(goBackBtn)))
                                    .addContainerGap(100, Short.MAX_VALUE)));

    layout.setVerticalGroup(
            layout
                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addGroup(
                            layout
                                    .createSequentialGroup()
                                    .addContainerGap(50, Short.MAX_VALUE)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(messageLabel))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(scrollPane))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(goBackBtn))
                                    .addContainerGap(100, Short.MAX_VALUE)));
    pack();
  }

  private void goBackBtnActionPerformed(java.awt.event.ActionEvent evt) {
    if (controller != null) new ListOfCardholdersScreenGUI(controller).setVisible(true);
    else new CardholderScreenGUI(user).setVisible(true);
    this.setVisible(false);
  }
}
