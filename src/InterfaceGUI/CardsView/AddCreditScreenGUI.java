package InterfaceGUI.CardsView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import Controllers.*;
import InterfaceGUI.BothView.ListOfTransitCardsScreenGUI;

public class AddCreditScreenGUI extends javax.swing.JFrame {

    private Image backgroundImage = Toolkit.getDefaultToolkit().getImage("banner2.png");
    private javax.swing.JLabel messageLabel;
    private javax.swing.JLabel outcomeLabel;
    private javax.swing.JLabel amountLabel;
    private javax.swing.JComboBox cbAmount;
    private javax.swing.JButton addBtn;
    private javax.swing.JButton goBackBtn;
    private TransitCard card;
    private TransitUser user;

    public AddCreditScreenGUI(TransitCard c, TransitUser u) {

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
        amountLabel = new JLabel();
        addBtn = new JButton();
        goBackBtn = new JButton();

        setLocation(200, 200);
        setPreferredSize(new Dimension(1000, 500));
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Add credit to " + card.getCardNumber());

        messageLabel.setText("Select the amount");
        messageLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 22));
        amountLabel.setText("Amount");
        String[] menu = {"50.00", "20.00", "10.00"};
        cbAmount = new JComboBox<>(menu);

        addBtn.setText("Add Credit");
        goBackBtn.setText("Back");

        addBtn.addActionListener(this::addButtonActionPerformed);
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
                                                                        .addComponent(amountLabel)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(cbAmount))
                                                        .addGroup(layout.createSequentialGroup().addComponent(outcomeLabel))
                                                        .addGroup(
                                                                layout
                                                                        .createSequentialGroup()
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(addBtn)
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
                                        .addContainerGap(50, Short.MAX_VALUE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                        .addComponent(amountLabel)
                                                        .addComponent(cbAmount))
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
                                                        .addComponent(addBtn)
                                                        .addComponent(goBackBtn))
                                        .addContainerGap(200, Short.MAX_VALUE)));
        pack();
    }

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
        outcomeLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 16));
        try {
            if (cbAmount.getSelectedItem() != null) {
                card.addCredit(Double.valueOf((String) cbAmount.getSelectedItem()));
                outcomeLabel.setText("The amount has been added to this card");
                Writer.writeAction(
                        "AddCredit,"
                                + card.getCardNumber()
                                + ","
                                + Double.valueOf((String) cbAmount.getSelectedItem()));
            } else {
                outcomeLabel.setText("The amount was not selected");
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void goBackBtnActionPerformed(java.awt.event.ActionEvent evt) {
        new ListOfTransitCardsScreenGUI(user).setVisible(true);
        this.setVisible(false);
    }
}
