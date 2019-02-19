package InterfaceGUI.CardsView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import Controllers.*;
import InterfaceGUI.BothView.ListOfTransitCardsScreenGUI;

public class DeactivateCardScreenGUI extends javax.swing.JFrame {

    private Image backgroundImage = Toolkit.getDefaultToolkit().getImage("banner2.png");
    private javax.swing.JLabel messageLabel;
    private javax.swing.JLabel outcomeLabel;
    private javax.swing.JLabel reasonLabel;
    private javax.swing.JComboBox cbReason;
    private javax.swing.JButton deactivateBtn;
    private javax.swing.JButton goBackBtn;
    private TransitCard card;
    private User user;

    public DeactivateCardScreenGUI(User u, TransitCard c) {

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
        reasonLabel = new JLabel();
        deactivateBtn = new JButton();
        goBackBtn = new JButton();

        setLocation(200, 200);
        setPreferredSize(new Dimension(1000, 500));
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Deactivate Card " + card.getCardNumber());

        messageLabel.setText("Please select the reason");
        messageLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 15));
        reasonLabel.setText("Reason: ");
        String[] menu = {"Suspend", "Report as Stolen", "Misplaced"};
        cbReason = new JComboBox<>(menu);

        deactivateBtn.setText("Suspend");
        goBackBtn.setText("Back");

        deactivateBtn.addActionListener(this::deactivateButtonActionPerformed);
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
                                                                        .addComponent(reasonLabel)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(cbReason))
                                                        .addGroup(
                                                                layout
                                                                        .createSequentialGroup()
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(outcomeLabel))
                                                        .addGroup(
                                                                layout
                                                                        .createSequentialGroup()
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(deactivateBtn)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(goBackBtn)))
                                        .addContainerGap(100, Short.MAX_VALUE)));

        layout.setVerticalGroup(
                layout
                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(
                                layout
                                        .createSequentialGroup()
                                        .addContainerGap(100, Short.MAX_VALUE)
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                        .addComponent(messageLabel))
                                        .addContainerGap(40, Short.MAX_VALUE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                        .addComponent(reasonLabel)
                                                        .addComponent(cbReason))
                                        .addContainerGap(40, Short.MAX_VALUE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                        .addComponent(outcomeLabel))
                                        .addContainerGap(30, Short.MAX_VALUE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                        .addComponent(deactivateBtn)
                                                        .addComponent(goBackBtn))
                                        .addContainerGap(150, Short.MAX_VALUE)));
        pack();
    }

    private void deactivateButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            card.deactivate();
            outcomeLabel.setText("The card has been suspended");
            outcomeLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 16));
            Writer.writeAction("SuspendCard," + card.getCardNumber());
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void goBackBtnActionPerformed(java.awt.event.ActionEvent evt) {
        new ListOfTransitCardsScreenGUI(user).setVisible(true);
        this.setVisible(false);
    }
}
