package InterfaceGUI.CardHolderView;

import InterfaceGUI.AdminView.*;
import InterfaceGUI.WelcomeScreenGUI;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import Controllers.*;

public class DeleteAccountScreenGUI extends javax.swing.JFrame {

  private Image backgroundImage = Toolkit.getDefaultToolkit().getImage("banner2.png");

  private javax.swing.JLabel messageLabel;
  private javax.swing.JLabel outcomeLabel;
  private javax.swing.JButton deleteBtn;
  private javax.swing.JButton goBackBtn;
  private TransitUser user;
  private AdminUser controller;

  public DeleteAccountScreenGUI(TransitUser u, AdminUser c) {

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
    outcomeLabel = new JLabel();
    goBackBtn = new JButton();
    deleteBtn = new JButton();

    setLocation(200, 200);
    setPreferredSize(new Dimension(1000, 500));
    setResizable(false);
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Delete Account Window");

    messageLabel.setText("Are you sure you want to delete the account?");
    messageLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 22));
    deleteBtn.setText("Delete");
    goBackBtn.setText("Back");

    goBackBtn.addActionListener(this::goBackBtnActionPerformed);
    deleteBtn.addActionListener(this::deleteBtnActionPerformed);

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
                                                    .addGroup(layout.createSequentialGroup().addComponent(outcomeLabel))
                                                    .addGroup(
                                                            layout
                                                                    .createSequentialGroup()
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(deleteBtn)
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(goBackBtn)))
                                    .addContainerGap(100, Short.MAX_VALUE)));

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
                                    .addContainerGap(40, Short.MAX_VALUE)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(outcomeLabel))
                                    .addContainerGap(40, Short.MAX_VALUE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(deleteBtn)
                                                    .addComponent(goBackBtn))
                                    .addContainerGap(200, Short.MAX_VALUE)));
    pack();
  }

  private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {
    try {
      for (TransitCard card : user.getCards()) {
        card.deactivate();
      }
      Administrator.getTransitUsers().remove(user);
      Writer.writeAction("DeleteCardholder," + user.getEmail());

    } catch (IOException e) {
      System.err.println(e);
    }
    outcomeLabel.setText("Account deleted");
    outcomeLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 16));
  }

  private void goBackBtnActionPerformed(java.awt.event.ActionEvent evt) {
    if (controller != null) {
      new ListOfCardholdersScreenGUI(controller).setVisible(true);
    } else if (Administrator.getTransitUsers().contains(user)) {
      new CardholderScreenGUI(user).setVisible(true);
    } else {
      new WelcomeScreenGUI().setVisible(true);
    }
    this.setVisible(false);
  }
}
