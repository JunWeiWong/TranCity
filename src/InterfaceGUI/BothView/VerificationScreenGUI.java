package InterfaceGUI.BothView;

import InterfaceGUI.AdminView.AdminUserScreenGUI;
import InterfaceGUI.CardHolderView.CardholderScreenGUI;
import InterfaceGUI.WelcomeScreenGUI;
import Controllers.*;
import javax.swing.*;
import java.awt.*;

public class VerificationScreenGUI extends javax.swing.JFrame {

  private Image backgroundImage = Toolkit.getDefaultToolkit().getImage("banner2.png");

  private javax.swing.JTextField emailTextField;
  private javax.swing.JPasswordField passwordPasswordField;
  private javax.swing.JLabel errorLabel;
  private javax.swing.JLabel emailLabel;
  private javax.swing.JLabel passwordLabel;
  private javax.swing.JLabel requestLabel;
  private javax.swing.JButton verifyBtn;
  private javax.swing.JButton goBackBtn;
  private String user;

  public VerificationScreenGUI(String user) {

    this.setContentPane(
            new JPanel() {
              @Override
              public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, null);
              }
        });
    this.user = user;
    initComponents();
  }

  private void initComponents() {
    emailTextField = new JTextField();
    errorLabel = new JLabel();
    passwordPasswordField = new JPasswordField();
    passwordLabel = new JLabel();
    emailLabel = new JLabel();
    requestLabel = new JLabel();
    verifyBtn = new JButton();
    goBackBtn = new JButton();

    setLocation(200, 200);
    setPreferredSize(new Dimension(1000, 500));
    setResizable(false);

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle(this.user + " Verification");

    requestLabel.setText("Email and Password please?");
    requestLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 20));
    emailLabel.setText("Email: ");
    passwordLabel.setText("Password: ");
    verifyBtn.setText("log In");
    goBackBtn.setText("Back");

    verifyBtn.addActionListener(this::verifyButtonActionPerformed);
    goBackBtn.addActionListener(this::goBackBtnActionPerformed);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);

    layout.setHorizontalGroup(
            layout
                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addGroup(
                            layout
                                    .createSequentialGroup()
                                    .addContainerGap(112, Short.MAX_VALUE)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addGroup(layout.createSequentialGroup().addComponent(requestLabel))
                                                    .addGroup(
                                                            layout
                                                                    .createSequentialGroup()
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(emailLabel)
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(emailTextField))
                                                    .addGroup(
                                                            layout
                                                                    .createSequentialGroup()
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(passwordLabel)
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(passwordPasswordField))
                                                    .addGroup(layout.createSequentialGroup().addComponent(errorLabel))
                                                    .addGroup(
                                                            layout
                                                                    .createSequentialGroup()
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(verifyBtn)
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(goBackBtn)))
                                    .addContainerGap(112, Short.MAX_VALUE)));

    layout.setVerticalGroup(
            layout
                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addGroup(
                            layout
                                    .createSequentialGroup()
                                    .addContainerGap(130, Short.MAX_VALUE)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(requestLabel))
                                    .addContainerGap(50, Short.MAX_VALUE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(emailLabel)
                                                    .addComponent(emailTextField))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(passwordLabel)
                                                    .addComponent(passwordPasswordField))
                                    .addContainerGap(40, Short.MAX_VALUE)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(errorLabel))
                                    .addContainerGap(40, Short.MAX_VALUE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(verifyBtn)
                                                    .addComponent(goBackBtn))
                                    .addContainerGap(90, Short.MAX_VALUE)));
    pack();
  }

  private void verifyButtonActionPerformed(java.awt.event.ActionEvent evt) {

    if (user.equals("Admin")) {
      AdminUser ua = Administrator.getAdminUserByEmail(emailTextField.getText());
      if (ua != null && ua.verifyUser(emailTextField.getText(), passwordPasswordField.getText())) {
        this.setVisible(false);
        new AdminUserScreenGUI(ua).setVisible(true);
      } else {
        errorLabel.setText("Invalid User or wrong password");
        errorLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 16));
      }
    } else if (user.equals("Cardholder")) {
      TransitUser tu = Administrator.getTransitUserByEmail(emailTextField.getText());
      if (tu != null && tu.verifyUser(emailTextField.getText(), passwordPasswordField.getText())) {
        this.setVisible(false);
        new CardholderScreenGUI(tu).setVisible(true);
      } else {
        errorLabel.setText("Invalid User or wrong password");
        errorLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 16));
      }
    }
  }

  private void goBackBtnActionPerformed(java.awt.event.ActionEvent evt) {
    new WelcomeScreenGUI().setVisible(true);
    this.setVisible(false);
  }
}
