package InterfaceGUI.AdminView;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

import Controllers.*;

class ReportScreenGUI extends javax.swing.JFrame {

  private Image backgroundImage = Toolkit.getDefaultToolkit().getImage("banner2.png");
  private javax.swing.JLabel messageLabel;
  private javax.swing.JLabel reachedLabel;
  private javax.swing.JLabel revenueLabel;
  private javax.swing.JLabel ageGroupLabel;
  private javax.swing.JTable reportTable;
  private javax.swing.JLabel costLabel;
  private javax.swing.JButton goBackBtn;
  private AdminUser user;

  ReportScreenGUI(AdminUser u) {

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
    reachedLabel = new JLabel();
    revenueLabel = new JLabel();
    ageGroupLabel = new JLabel();
    costLabel = new JLabel();
    goBackBtn = new JButton();

    setLocation(200, 200);
    setPreferredSize(new Dimension(1000, 500));
    setResizable(false);
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Report");

    String rows[][] = Administrator.getNestedOfFareCollectors();
    String headers[] = {"Date", "Fare", "Stations/Stops Reached", "Distance (km)", "Passengers"};

    reportTable = new JTable(rows, headers);
    reportTable.setRowHeight(20);
    reportTable.getColumnModel().getColumn(0).setPreferredWidth(100);
    reportTable.getColumnModel().getColumn(1).setPreferredWidth(50);
    reportTable.getColumnModel().getColumn(2).setPreferredWidth(100);
    reportTable.getColumnModel().getColumn(3).setPreferredWidth(100);
    reportTable.getColumnModel().getColumn(4).setPreferredWidth(100);
    JScrollPane scrollPane = new JScrollPane(reportTable);
    reportTable.setGridColor(Color.gray.brighter());
    Dimension d = new Dimension(580, 200);
    reportTable.setPreferredScrollableViewportSize(d);

    messageLabel.setText("======    REPORT    ======");
    messageLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 16));
    reachedLabel.setText(
            "The total stations/stops reached by all cardholders: " + Administrator.getTotalReached());
    reachedLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 16));
    revenueLabel.setText(
            "The total revenue is: "
                    + Administrator.getTotalRevenue()
                    + " in "
                    + Administrator.getListOfDays().size()
                    + " days");
    revenueLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 16));
    costLabel.setText(
            "The operating cost is: " + Administrator.getOperatingCostPerDay() + " per day.");
    costLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 16));
    int[] ag = Administrator.getAgeGroupReport();
    ageGroupLabel.setText(
            "<html> ======  Number of Users based on age groups ======<br>"
                    + "Under 20: "
                    + ag[0]
                    + " cardholders<br> "
                    + "Adults: "
                    + ag[1]
                    + " cardholders<br> "
                    + "Seniors: "
                    + ag[2]
                    + " cardholders</html>");
    ageGroupLabel.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 16));
    goBackBtn.setText("Back");

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
                                                                    .addComponent(reachedLabel))
                                                    .addGroup(
                                                            layout
                                                                    .createSequentialGroup()
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(revenueLabel))
                                                    .addGroup(
                                                            layout
                                                                    .createSequentialGroup()
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(costLabel))
                                                    .addGroup(
                                                            layout
                                                                    .createSequentialGroup()
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(scrollPane))
                                                    .addGroup(
                                                            layout
                                                                    .createSequentialGroup()
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(ageGroupLabel))
                                                    .addGroup(
                                                            layout
                                                                    .createSequentialGroup()
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
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(reachedLabel))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(revenueLabel))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(costLabel))
                                    .addContainerGap(50, Short.MAX_VALUE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(scrollPane))
                                    .addContainerGap(50, Short.MAX_VALUE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(ageGroupLabel))
                                    .addContainerGap(50, Short.MAX_VALUE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                            layout
                                                    .createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(goBackBtn))
                                    .addContainerGap(100, Short.MAX_VALUE)));
    pack();
  }

  private void goBackBtnActionPerformed(java.awt.event.ActionEvent evt) {
    new AdminUserScreenGUI(user).setVisible(true);
    this.setVisible(false);
  }
}
