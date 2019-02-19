package InterfaceGUI.AdminView;

import Controllers.*;

import java.awt.*;
import InterfaceGUI.BothView.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;

public class ListOfAdminUsersScreenGUI extends javax.swing.JFrame {

    private Image backgroundImage = Toolkit.getDefaultToolkit().getImage("banner2.png");
    private javax.swing.JLabel messageLabel;
    private javax.swing.JLabel emailInfoLabel;
    private javax.swing.JLabel accessInfoLabel;
    private javax.swing.JLabel outcomeLabel;
    private javax.swing.JList adminList;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JLabel message2Label;
    private javax.swing.JComboBox cb;
    private javax.swing.JButton selectBtn;
    private javax.swing.JButton goBackBtn;
    private AdminUser controller;

    public ListOfAdminUsersScreenGUI(AdminUser u) {

        this.setContentPane(
                new JPanel() {
                    @Override
                    public void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawImage(backgroundImage, 0, 0, null);
                    }
        });
        controller = u;
        initComponents();
    }

    private void initComponents() {

        messageLabel = new JLabel();
        outcomeLabel = new JLabel();
        emailInfoLabel = new JLabel();
        accessInfoLabel = new JLabel();
        message2Label = new JLabel();
        selectBtn = new JButton();
        scroll = new JScrollPane();
        goBackBtn = new JButton();

        setLocation(200, 200);
        setPreferredSize(new Dimension(1000, 500));
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("List of the Admin Users");

        messageLabel.setText("Select an Admin User");
        messageLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 20));
        message2Label.setText("Select an option");
        message2Label.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 20));
        adminList = new JList(Administrator.getArrayOfAdminUsers());
        scroll.setViewportView(adminList);
        String[] menu = {
                "Select...", "Change Name", "Change Password", "Change Access", "Change Email"
        };
        cb = new JComboBox<>(menu);
        selectBtn.setText("Select");
        goBackBtn.setText("Back");

        selectBtn.addActionListener(this::selectButtonActionPerformed);
        adminList.addListSelectionListener(this::jListValueSelected);
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
                                                                        .addComponent(accessInfoLabel))
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
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                        .addComponent(emailInfoLabel))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                        .addComponent(accessInfoLabel))
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

    private void jListValueSelected(ListSelectionEvent evt) {
        if (!adminList.getValueIsAdjusting()) {
            AdminUser au = Administrator.getAdminUserByName((String) adminList.getSelectedValue());
            emailInfoLabel.setText("Email: " + au.getEmail());
            accessInfoLabel.setText("Access: " + au.getAccessLevel());
        }
    }

    private void selectButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (adminList.getSelectedValue() != null) {
            AdminUser au = Administrator.getAdminUserByName((String) adminList.getSelectedValue());
            if (cb.getSelectedIndex() == 1) { // Change Name
                new ChangeNameScreenGUI(au, controller).setVisible(true);
                this.setVisible(false);
            } else if (cb.getSelectedIndex() == 2) { // Change Password
                new ChangePasswordScreenGUI(au, controller).setVisible(true);
                this.setVisible(false);
            } else if (cb.getSelectedIndex() == 3) { // Change Access
                new ChangeAccessScreenGUI(au, controller).setVisible(true);
                this.setVisible(false);
            } else if (cb.getSelectedIndex() == 4) { // Change Email
                new ChangeEmailScreenGUI(au, controller).setVisible(true);
                this.setVisible(false);
            }
        } else {
            outcomeLabel.setText("An admin must to be selected!");
            outcomeLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 16));
        }
    }

    private void goBackBtnActionPerformed(java.awt.event.ActionEvent evt) {
        new AdminUserScreenGUI(controller).setVisible(true);
        this.setVisible(false);
    }
}
