package InterfaceGUI.AdminView;

import Controllers.AdminUser;
import Controllers.Administrator;
import InterfaceGUI.*;
import InterfaceGUI.BothView.ChangePasswordScreenGUI;
import InterfaceGUI.BothView.CreateCardholderScreenGUI;
import InterfaceGUI.BothView.ListOfTransitCardsScreenGUI;
import javax.swing.*;
import java.awt.*;

public class AdminUserScreenGUI extends javax.swing.JFrame {

    private Image backgroundImage = Toolkit.getDefaultToolkit().getImage("banner2.png");

    private javax.swing.JLabel messageLabel;
    private javax.swing.JComboBox cb;
    private javax.swing.JButton logOutBtn;
    private javax.swing.JButton exitBtn;
    private AdminUser user;

    public AdminUserScreenGUI(AdminUser user) {
        this.user = user;

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
        exitBtn = new JButton();
        logOutBtn = new JButton();

        setLocation(200, 200);
        setPreferredSize(new Dimension(1000, 500));
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin User " + this.user.getEmail() + " Window");

        messageLabel.setText(
                "Welcome "
                        + user.getName()
                        + " today is: "
                        + Administrator.getListOfDays()
                        .get(Administrator.getListOfDays().size() - 1)
                        .getDate());
        messageLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 22));

        switch (user.getAccessLevel()) {
            case "High": {
                String[] menu = {
                        "Select...",
                        "Change Password",
                        "Create Cardholder",
                        "List of Cardholders",
                        "List of Transit Cards",
                        "See Report",
                        "Create New Day",
                        "List of Admin Users",
                        "Create Admin User"
                };
                cb = new JComboBox<>(menu);
                break;
            }
            case "Medium": {
                String[] menu = {
                        "Select...",
                        "Change Password",
                        "Create Cardholder",
                        "List of Cardholders",
                        "List of Transit Cards",
                        "See Report",
                        "Create New Day"
                };
                cb = new JComboBox<>(menu);
                break;
            }
            case "Low": {
                String[] menu = {
                        "Select...",
                        "Change Password",
                        "Create Cardholder",
                        "List of Cardholders",
                        "List of Transit Cards"
                };
                cb = new JComboBox<>(menu);
                break;
            }
        }
        logOutBtn.setText("Log Out");
        exitBtn.setText("Exit");

        cb.addActionListener(this::cbMenuActionPerformed);
        logOutBtn.addActionListener(this::logOutBtnActionPerformed);
        exitBtn.addActionListener(this::exitButtonActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout
                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(
                                layout
                                        .createSequentialGroup()
                                        .addContainerGap(390, Short.MAX_VALUE)
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                        .addGroup(layout.createSequentialGroup().addComponent(messageLabel))
                                                        .addGroup(
                                                                layout
                                                                        .createSequentialGroup()
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(cb))
                                                        .addGroup(
                                                                layout
                                                                        .createSequentialGroup()
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(logOutBtn)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(exitBtn)))
                                        .addContainerGap(390, Short.MAX_VALUE)));

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
                                                layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(cb))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                        .addComponent(logOutBtn)
                                                        .addComponent(exitBtn))
                                        .addContainerGap(165, Short.MAX_VALUE)));
        pack();
    }

    private void cbMenuActionPerformed(java.awt.event.ActionEvent evt) {
        if (cb.getSelectedIndex() == 1) { // Change password on admin
            this.setVisible(false);
            new ChangePasswordScreenGUI(user, user).setVisible(true);
        } else if (cb.getSelectedIndex() == 2) { // Create Transit User
            new CreateCardholderScreenGUI(user).setVisible(true);
            this.setVisible(false);
        } else if (cb.getSelectedIndex() == 3) { // Transit Users
            new ListOfCardholdersScreenGUI(user).setVisible(true);
            this.setVisible(false);
        } else if (cb.getSelectedIndex() == 4) { // Transit Cards
            new ListOfTransitCardsScreenGUI(user).setVisible(true);
            this.setVisible(false);
        } else if (cb.getSelectedIndex() == 5) { // See Report
            new ReportScreenGUI(user).setVisible(true);
            this.setVisible(false);
        } else if (cb.getSelectedIndex() == 6) { // Create New Day
            new NewDayScreenGUI(user).setVisible(true);
            this.setVisible(false);
        } else if (cb.getSelectedIndex() == 7) { // See Admin Users
            new ListOfAdminUsersScreenGUI(user).setVisible(true);
            this.setVisible(false);
        } else if (cb.getSelectedIndex() == 8) { // Create Admin Users
            new CreateAdminUserScreenGUI(user).setVisible(true);
            this.setVisible(false);
        }
    }

    private void logOutBtnActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        new WelcomeScreenGUI().setVisible(true);
    }

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }
}
