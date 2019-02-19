package InterfaceGUI.AdminView;

import Controllers.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

class CreateAdminUserScreenGUI extends javax.swing.JFrame {

  private Image backgroundImage = Toolkit.getDefaultToolkit().getImage("banner2.png");

  private javax.swing.JLabel messageLabel;
  private javax.swing.JLabel outcomeLabel;
  private javax.swing.JLabel nameLabel;
  private javax.swing.JLabel emailLabel;
  private javax.swing.JLabel accessLevelLabel;
  private javax.swing.JLabel passwordLabel;
  private javax.swing.JLabel repasswordLabel;
  private javax.swing.JTextField nameTextField;
  private javax.swing.JTextField emailTextField;
  private javax.swing.JPasswordField passwordPasswordField;
  private javax.swing.JPasswordField repasswordPasswordField;
  private javax.swing.JComboBox cbAccess;
  private javax.swing.JButton createBtn;
  private javax.swing.JButton goBackBtn;
  private AdminUser creator;

  CreateAdminUserScreenGUI(AdminUser u) {

    this.setContentPane(
            new JPanel() {
              @Override
              public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, null);
              }
        });
    creator = u;
    initComponents();
  }

  private void initComponents() {

    messageLabel = new JLabel();
    outcomeLabel = new JLabel();
    nameLabel = new JLabel();
    emailLabel = new JLabel();
    accessLevelLabel = new JLabel();
    passwordLabel = new JLabel();
    repasswordLabel = new JLabel();
    nameTextField = new JTextField(25);
    emailTextField = new JTextField();
    passwordPasswordField = new JPasswordField();
    repasswordPasswordField = new JPasswordField();
    createBtn = new JButton();
    goBackBtn = new JButton();

    setLocation(200, 200);
    setPreferredSize(new Dimension(1000, 500));
    setResizable(false);
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("New Admin User");

    messageLabel.setText("Please enter the information required");
    messageLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 22));
    nameLabel.setText("Name:");
    emailLabel.setText("Email:");
    passwordLabel.setText("Password:");
    repasswordLabel.setText("Re-enter password:");
    accessLevelLabel.setText("Access Level:");
    String[] menu = {"High", "Medium", "Low"};
    cbAccess = new JComboBox<>(menu);

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
                                    .addContainerGap(100, Short.MAX_VALUE)
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
                                                    .addGroup(
                                                            layout
                                                                    .createSequentialGroup()
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(accessLevelLabel)
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(cbAccess))
                                                    .addGroup(layout.createSequentialGroup().addComponent(outcomeLabel))
                                                    .addGroup(
                                                            layout
                                                                    .createSequentialGroup()
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(createBtn)
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(goBackBtn)))
                                    .addContainerGap(100, Short.MAX_VALUE)));

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
                                                    .addComponent(passwordLabel)
                                                    .addComponent(passwordPasswordField))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(repasswordLabel)
                                                    .addComponent(repasswordPasswordField))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(accessLevelLabel)
                                                    .addComponent(cbAccess))
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
                                                    .addComponent(createBtn)
                                                    .addComponent(goBackBtn))
                                    .addContainerGap(130, Short.MAX_VALUE)));
    pack();
  }

  private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {
    try {
      if (passwordPasswordField.getText().equals(repasswordPasswordField.getText())) {
        AdminUser newUser =
                new AdminUser(
                        nameTextField.getText(),
                        emailTextField.getText(),
                        (String) cbAccess.getSelectedItem(),
                        passwordPasswordField.getText());
        Administrator.add(newUser);
        Writer.writeAction(
                "New,Admin,"
                        + newUser.getName()
                        + ","
                        + newUser.getEmail()
                        + ","
                        + newUser.getAccessLevel()
                        + ","
                        + passwordPasswordField.getText());
        outcomeLabel.setText("Admin User Created");
        outcomeLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 16));

      } else {
        outcomeLabel.setText("Passwords don't match");
        outcomeLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 16));
      }

    } catch (IOException e) {
      System.err.println(e);
    }
  }

  private void goBackBtnActionPerformed(java.awt.event.ActionEvent evt) {
    new AdminUserScreenGUI(creator).setVisible(true);
    this.setVisible(false);
  }
}
