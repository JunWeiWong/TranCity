package InterfaceGUI.BothView;

import Controllers.Administrator;
import InterfaceGUI.AdminView.AdminUserScreenGUI;
import InterfaceGUI.CardHolderView.CardholderScreenGUI;
import InterfaceGUI.CardsView.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ItemEvent;

import Controllers.*;

public class ListOfTransitCardsScreenGUI extends javax.swing.JFrame {

  Image backgroundImage = Toolkit.getDefaultToolkit().getImage("banner2.png");

  private javax.swing.JLabel messageLabel;
  private javax.swing.JLabel message2Label;
  private javax.swing.JLabel outcomeLabel;
  private javax.swing.JLabel userInfoLabel;
  private javax.swing.JLabel statusInfoLabel;
  private javax.swing.JLabel balanceInfoLabel;
  private javax.swing.JList cardsList;
  private javax.swing.JComboBox cbCard;
  private javax.swing.JScrollPane scroll;
  private javax.swing.JButton selectBtn;
  private javax.swing.JButton goBackBtn;
  private User user;

  public ListOfTransitCardsScreenGUI(User user) {
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
    outcomeLabel = new JLabel();
    message2Label = new JLabel();
    userInfoLabel = new JLabel();
    statusInfoLabel = new JLabel();
    balanceInfoLabel = new JLabel();
    selectBtn = new JButton();
    scroll = new JScrollPane();
    goBackBtn = new JButton();

    setLocation(200, 200);
    setPreferredSize(new Dimension(1000, 500));
    setResizable(false);
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Cards List");

    messageLabel.setText("Select a Card:");
    messageLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 20));
    message2Label.setText("Select an option");
    message2Label.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 20));

    if (user instanceof AdminUser) {
      cardsList = new JList(Administrator.getArrayOfCards());
      String[] menu = {"Select...", "Reactivate", "Remove Card", "Suspend Card"};
      cbCard = new JComboBox<>(menu);
    } else {
      TransitUser u = (TransitUser) user;
      cardsList = new JList(u.getArrayOfCards());
      String[] menu = {
              "Select...",
              "Reactivate",
              "Remove Card",
              "Report/Suspend Card",
              "Add Credit",
              "Tap-In Subway",
              "Tap-Out Subway",
              "Tap-In Bus",
              "Tap-Out Bus"
      };
      cbCard = new JComboBox<>(menu);
    }
    scroll.setViewportView(cardsList);
    selectBtn.setText("Select");
    goBackBtn.setText("Back");

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    setTitle("List of Transit Cards");

    selectBtn.addActionListener(this::selectButtonActionPerformed);
    cardsList.addListSelectionListener(this::cardsListValueSelected);
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
                                                                    .addComponent(scroll))
                                                    .addGroup(
                                                            layout
                                                                    .createSequentialGroup()
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(userInfoLabel))
                                                    .addGroup(
                                                            layout
                                                                    .createSequentialGroup()
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(statusInfoLabel))
                                                    .addGroup(
                                                            layout
                                                                    .createSequentialGroup()
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(balanceInfoLabel))
                                                    .addGroup(
                                                            layout
                                                                    .createSequentialGroup()
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(message2Label))
                                                    .addGroup(
                                                            layout
                                                                    .createSequentialGroup()
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(cbCard))
                                                    .addGroup(
                                                            layout
                                                                    .createSequentialGroup()
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(selectBtn)
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(goBackBtn))
                                                    .addGroup(
                                                            layout
                                                                    .createSequentialGroup()
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(outcomeLabel)))
                                    .addContainerGap(300, Short.MAX_VALUE)));

    layout.setVerticalGroup(
            layout
                    .createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addGroup(
                            layout
                                    .createSequentialGroup()
                                    .addContainerGap(80, Short.MAX_VALUE)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                    .addComponent(messageLabel))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                    .addComponent(scroll))
                                    .addContainerGap(60, Short.MAX_VALUE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                    .addComponent(userInfoLabel))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                    .addComponent(statusInfoLabel))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                    .addComponent(balanceInfoLabel))
                                    .addContainerGap(70, Short.MAX_VALUE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                    .addComponent(message2Label))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                    .addComponent(cbCard))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                    .addComponent(selectBtn)
                                                    .addComponent(goBackBtn))
                                    .addContainerGap(40, Short.MAX_VALUE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                    .addComponent(outcomeLabel))
                                    .addContainerGap(150, Short.MAX_VALUE)));
    pack();
  }

  private void cardsListValueSelected(ListSelectionEvent evt) {
    if (!cardsList.getValueIsAdjusting() && user instanceof AdminUser) {
      TransitUser tu =
              Administrator.getCardholderByCardNumber(
                      Integer.valueOf((String) cardsList.getSelectedValue()));
      TransitCard card =
              Administrator.getCardByNumber(Integer.valueOf((String) cardsList.getSelectedValue()));

      if (tu != null) {
        userInfoLabel.setText("Email: " + tu.getEmail());
      } else {
        userInfoLabel.setText("Card is no longer associated with cardholder");
      }
      statusInfoLabel.setText("Status: " + card.getStatus());
      balanceInfoLabel.setText("Balance: " + String.valueOf(card.getBalance()));

    } else if (!cardsList.getValueIsAdjusting() && user instanceof TransitUser) {
      TransitCard card =
              Administrator.getCardByNumber(Integer.valueOf((String) cardsList.getSelectedValue()));
      statusInfoLabel.setText("Status: " + card.getStatus());
      balanceInfoLabel.setText("Balance: " + String.valueOf(card.getBalance()));
    }
  }

  private void selectButtonActionPerformed(java.awt.event.ActionEvent evt) {
    if (cardsList.getSelectedValue() != null) {
      TransitCard card =
              Administrator.getCardByNumber(Integer.valueOf((String) cardsList.getSelectedValue()));
      if (cbCard.getSelectedIndex() == 1) { // Reactivate
        new ReactivateScreenGUI(user, card).setVisible(true);
        this.setVisible(false);
      } else if (cbCard.getSelectedIndex() == 2) { // Remove Card
        new RemoveCardScreenGUI(user, card).setVisible(true);
        this.setVisible(false);
      } else if (cbCard.getSelectedIndex() == 3) { // Deactivate Card
        new DeactivateCardScreenGUI(user, card).setVisible(true);
        this.setVisible(false);
      } else if (cbCard.getSelectedIndex() == 4) { // Add Credit
        new AddCreditScreenGUI(card, (TransitUser) user).setVisible(true);
        this.setVisible(false);
      } else if (cbCard.getSelectedIndex() == 5) { // Tap In Subway
        new TapInScreenGUI("Subway", card, (TransitUser) user).setVisible(true);
        this.setVisible(false);
      } else if (cbCard.getSelectedIndex() == 6) { // Tap Out Subway
        new TapOutScreenGUI("Subway", card, (TransitUser) user).setVisible(true);
        this.setVisible(false);
      } else if (cbCard.getSelectedIndex() == 7) { // Tap In Bus
        new TapInScreenGUI("Bus", card, (TransitUser) user).setVisible(true);
        this.setVisible(false);
      } else if (cbCard.getSelectedIndex() == 8) { // Tap Out Bus
        new TapOutScreenGUI("Bus", card, (TransitUser) user).setVisible(true);
        this.setVisible(false);
      }
    } else {
      outcomeLabel.setText("Card must be selected!");
      outcomeLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 16));
    }
  }

  private void goBackBtnActionPerformed(java.awt.event.ActionEvent evt) {
    if (user instanceof TransitUser) new CardholderScreenGUI((TransitUser) user).setVisible(true);
    else new AdminUserScreenGUI((AdminUser) user).setVisible(true);
    this.setVisible(false);
  }
}
