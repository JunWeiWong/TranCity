package InterfaceGUI.BothView;

import javax.swing.*;
import Controllers.*;
import InterfaceGUI.AdminView.AdminUserScreenGUI;
import InterfaceGUI.AdminView.ListOfAdminUsersScreenGUI;
import InterfaceGUI.AdminView.ListOfCardholdersScreenGUI;
import InterfaceGUI.CardHolderView.CardholderScreenGUI;

import java.awt.*;
import java.io.IOException;

public class ChangeNameScreenGUI extends javax.swing.JFrame {

    private Image backgroundImage = Toolkit.getDefaultToolkit().getImage("banner2.png");

    private javax.swing.JLabel messageLabel;
    private javax.swing.JLabel outcomeLabel;
    private javax.swing.JLabel newNameLabel;
    private javax.swing.JTextField newNameTextField;
    private javax.swing.JButton changeBtn;
    private javax.swing.JButton goBackBtn;
    private User user;
    private AdminUser controller;

    public ChangeNameScreenGUI(User u, AdminUser c) {

        this.setContentPane(
                new JPanel() {
                    @Override
                    public void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawImage(backgroundImage, 0, 0, null);
                    }
        });
        this.user = u;
        this.controller = c;
        initComponents();
    }

    private void initComponents() {

        messageLabel = new JLabel();
        outcomeLabel = new JLabel();
        newNameLabel = new JLabel();
        newNameTextField = new JTextField(25);
        changeBtn = new JButton();
        goBackBtn = new JButton();

        setLocation(200, 200);
        setPreferredSize(new Dimension(1000, 500));
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Change Name on " + user.getName());

        messageLabel.setText("Please enter new name");
        messageLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 20));
        newNameLabel.setText("New Name: ");
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
                                                                        .addComponent(newNameLabel)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(newNameTextField))
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
                                                        .addComponent(newNameLabel)
                                                        .addComponent(newNameTextField))
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
            user.setName(newNameTextField.getText());
            if (user instanceof TransitUser) {
                Writer.writeAction(
                        "ChangeName,Cardholder," + user.getEmail() + "," + newNameTextField.getText());
            } else {
                Writer.writeAction(
                        "ChangeName,Admin," + user.getEmail() + "," + newNameTextField.getText());
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
