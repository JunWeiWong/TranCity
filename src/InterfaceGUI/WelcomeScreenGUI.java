package InterfaceGUI;

import InterfaceGUI.BothView.CreateCardholderScreenGUI;
import InterfaceGUI.BothView.VerificationScreenGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class WelcomeScreenGUI extends javax.swing.JFrame {

  private Image backgroundImage = Toolkit.getDefaultToolkit().getImage("banner2.png");
  private javax.swing.JLabel messageLabel;
  private javax.swing.JButton adminUserBtn;
  private javax.swing.JButton createUserBtn;
  private javax.swing.JButton alreadyUserBtn;
  private javax.swing.JButton exitBtn;

  public WelcomeScreenGUI() {

    this.setContentPane(
            new JPanel() {
              @Override
              public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, null);
              }
        });
    initComponents();
    this.setVisible(false);
    this.setVisible(true);
  }

  private void initComponents() {

    messageLabel = new JLabel();
    adminUserBtn = new JButton();
    createUserBtn = new JButton();
    alreadyUserBtn = new JButton();
    exitBtn = new JButton();

    setLocation(200, 200);
    setPreferredSize(new Dimension(1000, 500));
    setResizable(false);
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Transit System");

    messageLabel.setText("Welcome to TranCity");
    messageLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 35));
    adminUserBtn.setText("Admin User");
    createUserBtn.setText("Create Cardholder");
    alreadyUserBtn.setText("Already Cardholder");
    exitBtn.setText("Exit");

    adminUserBtn.addActionListener(this::adminUserButtonActionPerformed);
    createUserBtn.addActionListener(this::createUserButtonActionPerformed);
    alreadyUserBtn.addActionListener(this::alreadyUserButtonActionPerformed);
    exitBtn.addActionListener(this::exitButtonActionPerformed);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);

    layout.setHorizontalGroup(
            layout
                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addGroup(
                            layout
                                    .createSequentialGroup()
                                    .addContainerGap(350, Short.MAX_VALUE)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addGroup(layout.createSequentialGroup().addComponent(messageLabel))
                                                    .addGroup(
                                                            layout
                                                                    .createSequentialGroup()
                                                                    .addComponent(adminUserBtn)
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(alreadyUserBtn)
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(createUserBtn)
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(exitBtn)))
                                    .addContainerGap(350, Short.MAX_VALUE)));

    layout.setVerticalGroup(
            layout
                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addGroup(
                            layout
                                    .createSequentialGroup()
                                    .addContainerGap(200, Short.MAX_VALUE)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(messageLabel))
                                    .addContainerGap(150, Short.MAX_VALUE)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(adminUserBtn)
                                                    .addComponent(alreadyUserBtn)
                                                    .addComponent(createUserBtn)
                                                    .addComponent(exitBtn))
                                    .addContainerGap(250, Short.MAX_VALUE)));
    pack();
    this.setVisible(true);
  }

  private void adminUserButtonActionPerformed(java.awt.event.ActionEvent evt) {
    new VerificationScreenGUI("Admin").setVisible(true);
    this.setVisible(false);
  }

  private void createUserButtonActionPerformed(java.awt.event.ActionEvent evt) {
    new CreateCardholderScreenGUI("Cardholder").setVisible(true);
    this.setVisible(false);
  }

  private void alreadyUserButtonActionPerformed(java.awt.event.ActionEvent evt) {
    new VerificationScreenGUI("Cardholder").setVisible(true);
    this.setVisible(false);
  }

  private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {
    System.exit(0);
  }
}
