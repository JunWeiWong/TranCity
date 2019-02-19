package InterfaceGUI.CardsView;

import Controllers.TransitCard;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import Controllers.*;
import InterfaceGUI.BothView.ListOfTransitCardsScreenGUI;

public class RemoveCardScreenGUI extends javax.swing.JFrame {

    private Image backgroundImage = Toolkit.getDefaultToolkit().getImage("banner2.png");
    private javax.swing.JLabel message1Label;
    private javax.swing.JLabel message2Label;
    private javax.swing.JLabel outcomeLabel;
    private javax.swing.JButton removeBtn;
    private javax.swing.JButton goBackBtn;
    private TransitCard card;
    private User user;

    public RemoveCardScreenGUI(User u, TransitCard c) {

        this.setContentPane(
                new JPanel() {
                    @Override
                    public void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawImage(backgroundImage, 0, 0, null);
                    }
        });
        user = u;
        card = c;
        initComponents();
    }

    private void initComponents() {

        message1Label = new JLabel();
        message2Label = new JLabel();
        outcomeLabel = new JLabel();
        removeBtn = new JButton();
        goBackBtn = new JButton();

        setLocation(200, 200);
        setPreferredSize(new Dimension(1000, 500));
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Remove Card " + card.getCardNumber());

        message1Label.setText(
                "Please note that you won't be able to access to this card if you remove it");
        message1Label.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 20));
        message2Label.setText(
                "If a balance remains in the card, we suggest to transfer it before removing ");
        message2Label.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 20));
        removeBtn.setText("Remove");
        goBackBtn.setText("Back");

        removeBtn.addActionListener(this::removeButtonActionPerformed);
        goBackBtn.addActionListener(this::goBackButtonActionPerformed);

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
                                                        .addGroup(layout.createSequentialGroup().addComponent(message2Label))
                                                        .addGroup(layout.createSequentialGroup().addComponent(outcomeLabel))
                                                        .addGroup(
                                                                layout
                                                                        .createSequentialGroup()
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(removeBtn)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(goBackBtn)))
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
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                        .addComponent(message2Label))
                                        .addContainerGap(30, Short.MAX_VALUE)
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                        .addComponent(outcomeLabel))
                                        .addContainerGap(30, Short.MAX_VALUE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                        .addComponent(removeBtn)
                                                        .addComponent(goBackBtn))
                                        .addContainerGap(200, Short.MAX_VALUE)));
        pack();
    }

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        outcomeLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 16));
        try {
            card.deactivate();
            String email;
            if (user instanceof AdminUser) {
                TransitUser tu = Administrator.getCardholderByCardNumber(card.getCardNumber());
                email = tu.getEmail();
                tu.removeCard(card);
                outcomeLabel.setText("The Card has been removed from this Cardholder");
            } else {
                TransitUser tu = (TransitUser) this.user;
                email = tu.getEmail();
                tu.removeCard(card);
                outcomeLabel.setText("The Card has been removed from your account");
            }
            Writer.writeAction("RemoveCard," + email + "," + card.getCardNumber());
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void goBackButtonActionPerformed(java.awt.event.ActionEvent evt) {
        new ListOfTransitCardsScreenGUI(user).setVisible(true);
        this.setVisible(false);
    }
}
