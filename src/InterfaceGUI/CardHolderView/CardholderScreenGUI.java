package InterfaceGUI.CardHolderView;

import InterfaceGUI.CardsView.CreateCardScreenGUI;
import InterfaceGUI.BothView.ListOfTransitCardsScreenGUI;
import InterfaceGUI.BothView.ChangeNameScreenGUI;
import InterfaceGUI.BothView.ChangePasswordScreenGUI;
import InterfaceGUI.BothView.ChangeEmailScreenGUI;
import InterfaceGUI.WelcomeScreenGUI;
import Controllers.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class CardholderScreenGUI extends javax.swing.JFrame {

  private Image backgroundImage = Toolkit.getDefaultToolkit().getImage("banner2.png");

  private javax.swing.JLabel messageLabel;
  private javax.swing.JLabel outcomeLabel;
  private javax.swing.JLabel message2Label;
  private javax.swing.JComboBox cb;
  private javax.swing.JButton logOutBtn;
  private javax.swing.JButton exitBtn;
  private TransitUser user;

  public CardholderScreenGUI(TransitUser user) {
    this.setContentPane(
            new JPanel() {
              @Override
              public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, null);
              }
        });
    initComponents(user);
  }

  private void initComponents(TransitUser user) {
    this.user = user;
    messageLabel = new JLabel();
    message2Label = new JLabel();
    outcomeLabel = new JLabel();
    exitBtn = new JButton();
    logOutBtn = new JButton();

    setLocation(200, 200);
    setPreferredSize(new Dimension(1000, 500));
    setResizable(false);
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Cardholder " + user.getName() + " Window");

    messageLabel.setText(
            "Welcome "
                    + user.getName()
                    + ", today is: "
                    + Administrator.getListOfDays()
                    .get(Administrator.getListOfDays().size() - 1)
                    .getDate());
    messageLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 22));
    double balance = 0.00;
    for (TransitCard card : user.getCards()) {
      balance = balance + card.getBalance();
    }
    message2Label.setText("The total balance on your card(s) is: " + balance);
    message2Label.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 20));
    String[] menu = {
            "Select...",
            "Change Password",
            "Create New Card",
            "Change Name",
            "Change Email",
            "Transfer Balance",
            "See Cards",
            "See Recent Trips",
            "See Average Cost Per Month",
            "Delete Account"
    };
    cb = new JComboBox<>(menu);
    exitBtn.setText("Exit");
    logOutBtn.setText("Log Out");

    cb.addActionListener(this::cbMenuActionPerformed);
    exitBtn.addActionListener(this::exitButtonActionPerformed);
    logOutBtn.addActionListener(this::logOutBtnActionPerformed);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);

    layout.setHorizontalGroup(
            layout
                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addGroup(
                            layout
                                    .createSequentialGroup()
                                    .addContainerGap(92, Short.MAX_VALUE)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addGroup(layout.createSequentialGroup().addComponent(messageLabel))
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
                                                                    .addComponent(logOutBtn)
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(exitBtn)))
                                    .addContainerGap(92, Short.MAX_VALUE)));

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
                                    .addContainerGap(60, Short.MAX_VALUE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(message2Label))
                                    .addContainerGap(60, Short.MAX_VALUE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(cb))
                                    .addContainerGap(60, Short.MAX_VALUE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(outcomeLabel))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(logOutBtn)
                                                    .addComponent(exitBtn))
                                    .addContainerGap(250, Short.MAX_VALUE)));
    pack();
  }

  private void cbMenuActionPerformed(java.awt.event.ActionEvent evt) {

    if (cb.getSelectedIndex() == 1) { // Change Password
      new ChangePasswordScreenGUI(user, null).setVisible(true);
      this.setVisible(false);
    } else if (cb.getSelectedIndex() == 2) { // Create New Card\
      new CreateCardScreenGUI(user, null).setVisible(true);
      this.setVisible(false);
    } else if (cb.getSelectedIndex() == 3) { // Change Name
      new ChangeNameScreenGUI(user, null).setVisible(true);
      this.setVisible(false);
    } else if (cb.getSelectedIndex() == 4) { // Change Email
      new ChangeEmailScreenGUI(user, null).setVisible(true);
      this.setVisible(false);
    } else if (cb.getSelectedIndex() == 5) { // Transfer Balance
      if (user.getCards().size() < 2) {
        outcomeLabel.setText("Ups, you need to have at least 2 cards to make transfers");
        outcomeLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 16));
      } else {
        new TransferBalanceScreenGUI(user).setVisible(true);
        this.setVisible(false);
      }
    } else if (cb.getSelectedIndex() == 6) { // See Cards
      new ListOfTransitCardsScreenGUI(user).setVisible(true);
      this.setVisible(false);
    } else if (cb.getSelectedIndex() == 7) { // Recent Trips
      new RecentTripsScreenGUI(user, null).setVisible(true);
      this.setVisible(false);
    } else if (cb.getSelectedIndex() == 8) { // See Average Cost
      new AverageCPMScreenGUI(user).setVisible(true);
      this.setVisible(false);
    } else if (cb.getSelectedIndex() == 9) { // Delete Account
      new DeleteAccountScreenGUI(user, null).setVisible(true);
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
