package InterfaceGUI.BothView;

import Controllers.Administrator;
import Controllers.TransitUser;
import Controllers.AdminUser;
import Controllers.Writer;
import InterfaceGUI.AdminView.AdminUserScreenGUI;
import InterfaceGUI.CardHolderView.CardholderScreenGUI;
import InterfaceGUI.WelcomeScreenGUI;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Date;

public class CreateCardholderScreenGUI extends javax.swing.JFrame {

  private Image backgroundImage = Toolkit.getDefaultToolkit().getImage("banner2.png");
  private javax.swing.JLabel messageLabel;
  private javax.swing.JLabel outcomeLabel;
  private javax.swing.JLabel nameLabel;
  private javax.swing.JLabel emailLabel;
  private javax.swing.JLabel dobLabel;
  private javax.swing.JLabel passwordLabel;
  private javax.swing.JLabel repasswordLabel;
  private javax.swing.JTextField dobTextField;
  private javax.swing.JTextField nameTextField;
  private javax.swing.JTextField emailTextField;
  private javax.swing.JPasswordField passwordPasswordField;
  private javax.swing.JPasswordField repasswordPasswordField;
  private javax.swing.JButton createBtn;
  private javax.swing.JButton goBackBtn;
  private Object creator;

  public CreateCardholderScreenGUI(Object c) {

    this.setContentPane(
            new JPanel() {
              @Override
              public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, null);
              }
        });
    creator = c;
    initComponents();
  }

  private void initComponents() {

    messageLabel = new JLabel();
    outcomeLabel = new JLabel();
    nameLabel = new JLabel();
    emailLabel = new JLabel();
    dobLabel = new JLabel();
    passwordLabel = new JLabel();
    repasswordLabel = new JLabel();
    nameTextField = new JTextField(25);
    emailTextField = new JTextField();
    dobTextField = new JTextField();
    passwordPasswordField = new JPasswordField();
    repasswordPasswordField = new JPasswordField();
    createBtn = new JButton();
    goBackBtn = new JButton();

    setLocation(200, 200);
    setPreferredSize(new Dimension(1000, 500));
    setResizable(false);
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("New Transit User");

    messageLabel.setText("Please enter the information required");
    messageLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 22));
    nameLabel.setText("Name:");
    emailLabel.setText("Email:");
    dobLabel.setText("Date of Birth (MM/DD/YYYY): ");
    passwordLabel.setText("Password:");
    repasswordLabel.setText("Re-enter password:");
    createBtn.setText("Create");
    goBackBtn.setText("Back");

    createBtn.addActionListener(this::createButtonActionPerformed);
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
                                                                    .addComponent(nameLabel)
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(nameTextField))
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
                                                                    .addComponent(dobLabel)
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(dobTextField))
                                                    .addGroup(
                                                            layout
                                                                    .createSequentialGroup()
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(passwordLabel)
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(passwordPasswordField))
                                                    .addGroup(
                                                            layout
                                                                    .createSequentialGroup()
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(repasswordLabel)
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(repasswordPasswordField))
                                                    .addGroup(layout.createSequentialGroup().addComponent(outcomeLabel))
                                                    .addGroup(
                                                            layout
                                                                    .createSequentialGroup()
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(createBtn)
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
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(nameLabel)
                                                    .addComponent(nameTextField))
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
                                                    .addComponent(dobLabel)
                                                    .addComponent(dobTextField))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(passwordLabel)
                                                    .addComponent(passwordPasswordField))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(repasswordLabel)
                                                    .addComponent(repasswordPasswordField))
                                    .addContainerGap(80, Short.MAX_VALUE)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(outcomeLabel))
                                    .addContainerGap(80, Short.MAX_VALUE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(createBtn)
                                                    .addComponent(goBackBtn))
                                    .addContainerGap(200, Short.MAX_VALUE)));
    pack();
  }

  private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {

    try {
      if (passwordPasswordField.getText().equals(repasswordPasswordField.getText())) {
        String date =
                Administrator.getListOfDays().get(Administrator.getListOfDays().size() - 1).getDate();
        Date initDate = new Date(date);
        Date dob = new Date(dobTextField.getText());
        TransitUser newU =
                new TransitUser(
                        nameTextField.getText(),
                        emailTextField.getText(),
                        initDate.getTime(),
                        passwordPasswordField.getText(),
                        dob.getTime());
        Administrator.add(newU);
        Writer.writeAction(
                "New,Cardholder,"
                        + newU.getName()
                        + ","
                        + newU.getEmail()
                        + ","
                        + newU.getInitiationDate()
                        + ","
                        + passwordPasswordField.getText()
                        + ","
                        + dob.getTime());
        if (creator instanceof String) {
          this.setVisible(false);
          new CardholderScreenGUI(newU).setVisible(true);

        } else {
          outcomeLabel.setText("Cardholder " + nameTextField.getText() + " has been created");
          outcomeLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 16));
        }
      } else {
        outcomeLabel.setText("Passwords don't match.");
        outcomeLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 16));
      }

    } catch (IOException e) {
      System.err.println(e);
    }
  }

  private void goBackBtnActionPerformed(java.awt.event.ActionEvent evt) {
    if (creator instanceof String) {
      new WelcomeScreenGUI().setVisible(true);
    } else {
      new AdminUserScreenGUI((AdminUser) creator).setVisible(true);
    }
    this.setVisible(false);
  }
}
