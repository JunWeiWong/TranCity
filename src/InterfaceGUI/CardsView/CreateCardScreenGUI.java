package InterfaceGUI.CardsView;

import Controllers.AdminUser;
import InterfaceGUI.AdminView.ListOfCardholdersScreenGUI;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import Controllers.*;
import InterfaceGUI.CardHolderView.CardholderScreenGUI;

public class CreateCardScreenGUI extends javax.swing.JFrame {

    private Image backgroundImage = Toolkit.getDefaultToolkit().getImage("banner2.png");
    private javax.swing.JLabel message1Label;
    private javax.swing.JLabel outcomeLabel;
    private javax.swing.JLabel message2Label;
    private javax.swing.JLabel amountLabel;
    private javax.swing.JComboBox cbAmount;
    private javax.swing.JButton createBtn;
    private javax.swing.JButton goBackBtn;
    private TransitUser user;
    private AdminUser controller;

    public CreateCardScreenGUI(TransitUser u, AdminUser c) {

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

    private void initComponents(TransitUser u) {

        message1Label = new JLabel();
        outcomeLabel = new JLabel();
        message2Label = new JLabel();
        amountLabel = new JLabel();
        createBtn = new JButton();
        goBackBtn = new JButton();
        this.user = u;

        setLocation(200, 200);
        setPreferredSize(new Dimension(1000, 500));
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Create new Transit Card");

        message1Label.setText("The new card will be created with $19 dollars of credit.");
        message1Label.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 20));
        message2Label.setText("If you want to add more credit, please select the amount");
        message2Label.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 15));
        amountLabel.setText("Amount");
        String[] menu = {"Default", "50.00", "20.00", "10.00"};
        cbAmount = new JComboBox<>(menu);
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
                                                        .addGroup(layout.createSequentialGroup().addComponent(message1Label))
                                                        .addGroup(
                                                                layout
                                                                        .createSequentialGroup()
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(message2Label))
                                                        .addGroup(
                                                                layout
                                                                        .createSequentialGroup()
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(amountLabel)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(cbAmount))
                                                        .addGroup(layout.createSequentialGroup().addComponent(outcomeLabel))
                                                        .addGroup(
                                                                layout
                                                                        .createSequentialGroup()
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(createBtn)
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
                                        .addContainerGap(50, Short.MAX_VALUE)
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                        .addComponent(message1Label))
                                        .addContainerGap(10, Short.MAX_VALUE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                        .addComponent(message2Label))
                                        .addContainerGap(50, Short.MAX_VALUE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                        .addComponent(amountLabel)
                                                        .addComponent(cbAmount))
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                        .addComponent(outcomeLabel))
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
            double amount = 0.00;
            TransitCard newCard;
            int cardNum;
            if (!cbAmount.getSelectedItem().equals("Default")) {
                amount = Double.valueOf((String) cbAmount.getSelectedItem());
            }
            if (Administrator.getListOfCards().size() == 0) {
                cardNum = 1200;
                if (amount == 0.0) {
                    newCard = new TransitCard(cardNum, 0.0);
                } else {
                    newCard = new TransitCard(cardNum, amount);
                }
            } else {
                cardNum =
                        Administrator.getListOfCards()
                                .get(Administrator.getListOfCards().size() - 1)
                                .getCardNumber()
                                + 1;
                if (amount == 0.0) {
                    newCard = new TransitCard(cardNum, 0.0);
                } else {
                    newCard = new TransitCard(cardNum, amount);
                }
            }
            user.addCard(newCard);
            Administrator.add(newCard);
            Writer.writeAction("AddCard," + user.getEmail() + "," + cardNum + "," + amount);
            outcomeLabel.setText(
                    "Card: " + cardNum + " has been created with: $" + newCard.getBalance() + " of credit");
            outcomeLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 16));
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void goBackBtnActionPerformed(java.awt.event.ActionEvent evt) {
        if (controller != null) new ListOfCardholdersScreenGUI(controller).setVisible(true);
        else new CardholderScreenGUI((TransitUser) user).setVisible(true);
        this.setVisible(false);
    }
}
