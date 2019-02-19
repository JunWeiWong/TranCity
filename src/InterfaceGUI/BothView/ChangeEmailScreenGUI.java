package InterfaceGUI.BothView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import Controllers.*;
import InterfaceGUI.AdminView.AdminUserScreenGUI;
import InterfaceGUI.AdminView.ListOfAdminUsersScreenGUI;
import InterfaceGUI.AdminView.ListOfCardholdersScreenGUI;
import InterfaceGUI.CardHolderView.CardholderScreenGUI;

public class ChangeEmailScreenGUI extends javax.swing.JFrame {

    Image backgroundImage = Toolkit.getDefaultToolkit().getImage("banner2.png");

    private javax.swing.JLabel messageLabel;
    private javax.swing.JLabel outcomeLabel;
    private javax.swing.JLabel newEmailLabel;
    private javax.swing.JTextField newEmailTextField;
    private javax.swing.JButton changeBtn;
    private javax.swing.JButton goBackBtn;
    private User user;
    private AdminUser controller;

    public ChangeEmailScreenGUI(User u, AdminUser c) {

        this.setContentPane(
                new JPanel() {
                    @Override
                    public void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawImage(backgroundImage, 0, 0, null);
                    }
        });
        user = u;
        controller = c;
        initComponents();
    }

    private void initComponents() {

        messageLabel = new JLabel();
        outcomeLabel = new JLabel();
        newEmailLabel = new JLabel();
        newEmailTextField = new JTextField(25);
        changeBtn = new JButton();
        goBackBtn = new JButton();

        setLocation(200, 200);
        setPreferredSize(new Dimension(1000, 500));
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Change Email on " + user.getName());

        messageLabel.setText("Please enter new email");
        messageLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 20));
        newEmailLabel.setText("New Email: ");
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
                                                                        .addComponent(newEmailLabel)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(newEmailTextField))
                                                        .addGroup(layout.createSequentialGroup().addComponent(outcomeLabel))
                                                        .addGroup(
                                                                layout
                                                                        .createSequentialGroup()
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(changeBtn)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(goBackBtn)))
                                        .addContainerGap(100, Short.MAX_VALUE)));

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
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                        .addComponent(newEmailLabel)
                                                        .addComponent(newEmailTextField))
                                        .addContainerGap(50, Short.MAX_VALUE)
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
            String oldEmail = user.getEmail();
            user.setEmail(newEmailTextField.getText());
            if (user instanceof TransitUser) {
                Writer.writeAction(
                        "ChangeEmail,Cardholder," + oldEmail + "," + newEmailTextField.getText());
            } else {
                Writer.writeAction("ChangeEmail,Admin," + oldEmail + "," + newEmailTextField.getText());
            }
            outcomeLabel.setText("Changes Saved!");
            outcomeLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 16));
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void goBackBtnActionPerformed(java.awt.event.ActionEvent evt) {
        if (user instanceof AdminUser && controller != null)
            new ListOfAdminUsersScreenGUI(controller).setVisible(true);
        else if (user instanceof AdminUser) new AdminUserScreenGUI((AdminUser) user).setVisible(true);
        else if (user instanceof TransitUser && controller != null)
            new ListOfCardholdersScreenGUI(controller).setVisible(true);
        else if (user instanceof TransitUser)
            new CardholderScreenGUI((TransitUser) user).setVisible(true);
        this.setVisible(false);
    }
}
