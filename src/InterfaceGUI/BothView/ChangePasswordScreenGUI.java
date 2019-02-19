package InterfaceGUI.BothView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import Controllers.AdminUser;
import Controllers.TransitUser;
import Controllers.User;
import Controllers.Writer;
import InterfaceGUI.AdminView.AdminUserScreenGUI;
import InterfaceGUI.AdminView.ListOfAdminUsersScreenGUI;
import InterfaceGUI.AdminView.ListOfCardholdersScreenGUI;
import InterfaceGUI.CardHolderView.CardholderScreenGUI;

public class ChangePasswordScreenGUI extends javax.swing.JFrame {

    private Image backgroundImage = Toolkit.getDefaultToolkit().getImage("banner2.png");

    private javax.swing.JLabel messageLabel;
    private javax.swing.JLabel outcomeLabel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel repasswordLabel;
    private javax.swing.JPasswordField passwordPasswordField;
    private javax.swing.JPasswordField repasswordPasswordField;
    private javax.swing.JButton changeBtn;
    private javax.swing.JButton goBackBtn;
    private User user;
    private AdminUser controller;

    public ChangePasswordScreenGUI(User u, AdminUser c) {

        this.setContentPane(
                new JPanel() {
                    @Override
                    public void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawImage(backgroundImage, 0, 0, null);
                    }
        });
        this.controller = c;
        initComponents(u);
    }

    private void initComponents(User u) {

        messageLabel = new JLabel();
        outcomeLabel = new JLabel();
        passwordLabel = new JLabel();
        repasswordLabel = new JLabel();
        passwordPasswordField = new JPasswordField(25);
        repasswordPasswordField = new JPasswordField(25);
        changeBtn = new JButton();
        goBackBtn = new JButton();
        this.user = u;

        setLocation(200, 200);
        setPreferredSize(new Dimension(1000, 500));
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Change Password on User: " + user.getName());

        messageLabel.setText("Please enter your new password");
        messageLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 22));
        passwordLabel.setText("Password:");
        repasswordLabel.setText("Re-enter password:");
        changeBtn.setText("Change");
        goBackBtn.setText("Back");

        changeBtn.addActionListener(this::changeButtonActionPerformed);
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
                                                                        .addComponent(changeBtn)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(goBackBtn)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
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
                                                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                        .addComponent(changeBtn)
                                                        .addComponent(goBackBtn))
                                        .addContainerGap(200, Short.MAX_VALUE)));
        pack();
    }

    private void changeButtonActionPerformed(java.awt.event.ActionEvent evt) {

        try {
            if (passwordPasswordField.getText().equals(repasswordPasswordField.getText())) {
                user.setPassword(passwordPasswordField.getText());
                if (user instanceof TransitUser) {
                    Writer.writeAction(
                            "ChangePassword,Cardholder,"
                                    + user.getEmail()
                                    + ","
                                    + passwordPasswordField.getText());
                } else {
                    Writer.writeAction(
                            "ChangePassword,Admin," + user.getEmail() + "," + passwordPasswordField.getText());
                }
                outcomeLabel.setText("Changes Saved!");
                outcomeLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 16));

            } else {
                outcomeLabel.setText("The Passwords don't Match!");
                outcomeLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 16));
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void goBackBtnActionPerformed(java.awt.event.ActionEvent evt) {
        if (user instanceof AdminUser && controller != null && user != controller)
            new ListOfAdminUsersScreenGUI(controller).setVisible(true);
        else if (user instanceof AdminUser && user == controller)
            new AdminUserScreenGUI((AdminUser) user).setVisible(true);
        else if (user instanceof TransitUser && controller != null)
            new ListOfCardholdersScreenGUI(controller).setVisible(true);
        else if (user instanceof TransitUser)
            new CardholderScreenGUI((TransitUser) user).setVisible(true);
        this.setVisible(false);
    }
}
