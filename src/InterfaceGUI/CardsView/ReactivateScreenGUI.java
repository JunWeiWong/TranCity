package InterfaceGUI.CardsView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import Controllers.*;
import InterfaceGUI.BothView.ListOfTransitCardsScreenGUI;

public class ReactivateScreenGUI extends javax.swing.JFrame {

    private Image backgroundImage = Toolkit.getDefaultToolkit().getImage("banner2.png");

    private javax.swing.JLabel messageLabel;
    private javax.swing.JLabel outcomeLabel;
    private javax.swing.JButton reactivateBtn;
    private javax.swing.JButton goBackBtn;
    private TransitCard card;
    private User user;

    public ReactivateScreenGUI(User u, TransitCard c) {

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

        messageLabel = new JLabel();
        outcomeLabel = new JLabel();
        reactivateBtn = new JButton();
        goBackBtn = new JButton();

        setLocation(200, 200);
        setPreferredSize(new Dimension(1000, 500));
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Reactivate Card " + card.getCardNumber());

        messageLabel.setText("This card will be reactivated and ready to use");
        messageLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 20));
        reactivateBtn.setText("Reactivate");
        goBackBtn.setText("Back");

        reactivateBtn.addActionListener(this::reactivateButtonActionPerformed);
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
                                                        .addGroup(layout.createSequentialGroup().addComponent(messageLabel))
                                                        .addGroup(layout.createSequentialGroup().addComponent(outcomeLabel))
                                                        .addGroup(
                                                                layout
                                                                        .createSequentialGroup()
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(reactivateBtn)
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
                                                        .addComponent(messageLabel))
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
                                                        .addComponent(reactivateBtn)
                                                        .addComponent(goBackBtn))
                                        .addContainerGap(200, Short.MAX_VALUE)));
        pack();
    }

    private void reactivateButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            card.activate();
            outcomeLabel.setText("Changes Saved");
            outcomeLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 16));
            Writer.writeAction("ActivateCard," + card.getCardNumber());
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void goBackButtonActionPerformed(java.awt.event.ActionEvent evt) {
        new ListOfTransitCardsScreenGUI(user).setVisible(true);
        this.setVisible(false);
    }
}
