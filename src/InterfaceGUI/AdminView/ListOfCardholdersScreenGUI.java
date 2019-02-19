package InterfaceGUI.AdminView;

import InterfaceGUI.BothView.*;
import InterfaceGUI.CardsView.*;
import InterfaceGUI.CardHolderView.*;
import InterfaceGUI.CardHolderView.DeleteAccountScreenGUI;
import Controllers.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;

public class ListOfCardholdersScreenGUI extends javax.swing.JFrame {

  private Image backgroundImage = Toolkit.getDefaultToolkit().getImage("banner2.png");
  private javax.swing.JLabel messageLabel;
  private javax.swing.JLabel message2Label;
  private javax.swing.JLabel emailInfoLabel;
  private javax.swing.JLabel dobInfoLabel;
  private javax.swing.JLabel outcomeLabel;
  private javax.swing.JList transitList;
  private javax.swing.JScrollPane scroll;
  private javax.swing.JComboBox cb;
  private javax.swing.JButton selectBtn;
  private javax.swing.JButton goBackBtn;
  private AdminUser controller;

  public ListOfCardholdersScreenGUI(AdminUser c) {

    this.controller = c;
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
    message2Label = new JLabel();
    outcomeLabel = new JLabel();
    emailInfoLabel = new JLabel();
    dobInfoLabel = new JLabel();
    selectBtn = new JButton();
    scroll = new JScrollPane();
    goBackBtn = new JButton();

    setLocation(200, 200);
    setPreferredSize(new Dimension(1000, 500));
    setResizable(false);
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Transit Cardholders");

    messageLabel.setText("Select a Cardholder");
    messageLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 20));
    message2Label.setText("Select an option");
    message2Label.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 20));
    transitList = new JList(Administrator.getArrayOfTransitUsers());
    scroll.setViewportView(transitList);
    String[] menu = {
            "Select...",
            "Change Password",
            "Change Name",
            "Create New Card",
            "See Recent Trips",
            "Delete Account"
    };
    cb = new JComboBox<>(menu);
    selectBtn.setText("Select");
    goBackBtn.setText("Back");

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    setTitle("List of the Transit Users");

    selectBtn.addActionListener(this::selectButtonActionPerformed);
    transitList.addListSelectionListener(this::usersListValueSelected);
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
                                                                    .addComponent(scroll))
                                                    .addGroup(
                                                            layout
                                                                    .createSequentialGroup()
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(emailInfoLabel))
                                                    .addGroup(
                                                            layout
                                                                    .createSequentialGroup()
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(dobInfoLabel))
                                                    .addGroup(
                                                            layout
                                                                    .createSequentialGroup()
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(message2Label))
                                                    .addGroup(
                                                            layout
                                                                    .createSequentialGroup()
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(cb))
                                                    .addGroup(
                                                            layout
                                                                    .createSequentialGroup()
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(outcomeLabel))
                                                    .addGroup(
                                                            layout
                                                                    .createSequentialGroup()
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(selectBtn)
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(goBackBtn)))
                                    .addContainerGap(100, Short.MAX_VALUE)));

    layout.setVerticalGroup(
            layout
                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addGroup(
                            layout
                                    .createSequentialGroup()
                                    .addContainerGap(80, Short.MAX_VALUE)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(messageLabel))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(scroll))
                                    .addContainerGap(70, Short.MAX_VALUE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(emailInfoLabel))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(dobInfoLabel))
                                    .addContainerGap(70, Short.MAX_VALUE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(message2Label))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(cb))
                                    .addContainerGap(40, Short.MAX_VALUE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(outcomeLabel))
                                    .addContainerGap(40, Short.MAX_VALUE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(selectBtn)
                                                    .addComponent(goBackBtn))
                                    .addContainerGap(150, Short.MAX_VALUE)));
    pack();
  }

  private void usersListValueSelected(ListSelectionEvent evt) {
    if (!transitList.getValueIsAdjusting()) {
      TransitUser tu = Administrator.getTransitUserByName((String) transitList.getSelectedValue());
      if (tu != null) {
        emailInfoLabel.setText("Email: " + tu.getEmail());
        dobInfoLabel.setText("Date of Birth: " + tu.getDateOfBirth());
      }
    }
  }

  private void selectButtonActionPerformed(java.awt.event.ActionEvent evt) {
    if (transitList.getSelectedValue() != null) {
      TransitUser tu = Administrator.getTransitUserByName((String) transitList.getSelectedValue());
      if (cb.getSelectedIndex() == 1) { // Change Password
        new ChangePasswordScreenGUI(tu, controller).setVisible(true);
        this.setVisible(false);
      } else if (cb.getSelectedIndex() == 2) { // Change Name
        new ChangeNameScreenGUI(tu, controller).setVisible(true);
        this.setVisible(false);
      } else if (cb.getSelectedIndex() == 3) { // Create New Card
        new CreateCardScreenGUI(tu, controller).setVisible(true);
        this.setVisible(false);
      } else if (cb.getSelectedIndex() == 4) { // Recent Trips
        new RecentTripsScreenGUI(tu, controller).setVisible(true);
        this.setVisible(false);
      } else if (cb.getSelectedIndex() == 5) { // Delete Account
        new DeleteAccountScreenGUI(tu, controller).setVisible(true);
        this.setVisible(false);
      }

    } else {
      outcomeLabel.setText("A Cardholder must to be selected!");
      outcomeLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 16));
    }
  }

  private void goBackBtnActionPerformed(java.awt.event.ActionEvent evt) {
    new AdminUserScreenGUI(controller).setVisible(true);
    this.setVisible(false);
  }
}
