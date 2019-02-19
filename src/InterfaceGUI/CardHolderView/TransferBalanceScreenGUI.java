package InterfaceGUI.CardHolderView;

import Controllers.Administrator;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import Controllers.*;

class TransferBalanceScreenGUI extends javax.swing.JFrame {

    private Image backgroundImage = Toolkit.getDefaultToolkit().getImage("banner2.png");

    private javax.swing.JLabel messageLabel;
    private javax.swing.JLabel outcomeLabel;
    private javax.swing.JLabel noteLabel;
    private javax.swing.JLabel card1Label;
    private javax.swing.JLabel card2Label;
    private javax.swing.JLabel amountLabel;
    private javax.swing.JTextField card1TextField;
    private javax.swing.JTextField card2TextField;
    private javax.swing.JTextField amountTextField;
    private javax.swing.JButton transferBtn;
    private javax.swing.JButton goBackBtn;
    private TransitUser user;

    TransferBalanceScreenGUI(TransitUser u) {

        this.setContentPane(
                new JPanel() {
                    @Override
                    public void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawImage(backgroundImage, 0, 0, null);
                    }
        });
        user = u;
        initComponents();
    }

    private void initComponents() {

        messageLabel = new JLabel();
        outcomeLabel = new JLabel();
        noteLabel = new JLabel();
        card1Label = new JLabel();
        card2Label = new JLabel();
        amountLabel = new JLabel();
        card1TextField = new JTextField();
        card2TextField = new JTextField();
        amountTextField = new JTextField();
        transferBtn = new JButton();
        goBackBtn = new JButton();

        setLocation(200, 200);
        setPreferredSize(new Dimension(1000, 500));
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Transfer Balance");

        messageLabel.setText("Please enter the information required");
        messageLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 20));
        noteLabel.setText("NOTE: if an amount is not entered, the full balance will be transferred");
        noteLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 16));
        card1Label.setText("From:");
        card2Label.setText("To:");
        amountLabel.setText("Amount:");
        transferBtn.setText("Transfer");
        goBackBtn.setText("Back");

        transferBtn.addActionListener(this::transferButtonActionPerformed);
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
                                                                        .addComponent(noteLabel))
                                                        .addGroup(
                                                                layout
                                                                        .createSequentialGroup()
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(card1Label)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(card1TextField))
                                                        .addGroup(
                                                                layout
                                                                        .createSequentialGroup()
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(card2Label)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(card2TextField))
                                                        .addGroup(
                                                                layout
                                                                        .createSequentialGroup()
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(amountLabel)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(amountTextField))
                                                        .addGroup(
                                                                layout
                                                                        .createSequentialGroup()
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(outcomeLabel))
                                                        .addGroup(
                                                                layout
                                                                        .createSequentialGroup()
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(transferBtn)
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
                                        .addContainerGap(50, Short.MAX_VALUE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                        .addComponent(noteLabel))
                                        .addContainerGap(50, Short.MAX_VALUE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                        .addComponent(card1Label)
                                                        .addComponent(card1TextField))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                        .addComponent(card2Label)
                                                        .addComponent(card2TextField))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                        .addComponent(amountLabel)
                                                        .addComponent(amountTextField))
                                        .addContainerGap(50, Short.MAX_VALUE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                        .addComponent(outcomeLabel))
                                        .addContainerGap(50, Short.MAX_VALUE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                        .addComponent(transferBtn)
                                                        .addComponent(goBackBtn))
                                        .addContainerGap(200, Short.MAX_VALUE)));
        pack();
    }

    private void transferButtonActionPerformed(java.awt.event.ActionEvent evt) {
        outcomeLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 16));
        try {
            TransitCard c1 = Administrator.getCardByNumber(Integer.valueOf(card1TextField.getText()));
            TransitCard c2 = Administrator.getCardByNumber(Integer.valueOf(card2TextField.getText()));
            if (amountTextField.getText().length() > 0
                    && user.getCards().contains(c1)
                    && user.getCards().contains(c2)
                    && c1.getBalance() >= Double.valueOf(amountTextField.getText())) {
                user.transferBalance(
                        c1.getCardNumber(), c2.getCardNumber(), Double.valueOf(amountTextField.getText()));
                outcomeLabel.setText("The Amount has been transferred");

                Writer.writeAction(
                        "TransferBalance,"
                                + user.getEmail()
                                + ","
                                + c1.getCardNumber()
                                + ","
                                + c2.getCardNumber()
                                + ","
                                + Double.valueOf(amountTextField.getText()));

            } else if (amountTextField.getText().length() == 0
                    && user.getCards().contains(c1)
                    && user.getCards().contains(c2)) {
                this.user.transferBalance(c1.getCardNumber(), c2.getCardNumber(), 0.0);
                outcomeLabel.setText("The full balance has been transferred");
                Writer.writeAction(
                        "TransferBalance,"
                                + user.getEmail()
                                + ","
                                + c1.getCardNumber()
                                + ","
                                + c2.getCardNumber()
                                + ",0.0");

            } else {
                outcomeLabel.setText(
                        "One of the card's number is incorrect or the card has not enough funds");
            }

        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void goBackBtnActionPerformed(java.awt.event.ActionEvent evt) {
        new CardholderScreenGUI(user).setVisible(true);
        this.setVisible(false);
    }
}
