package Controllers;

import Model.TransitMap;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class Reader {

  /**
   * Reads all the stored info in log.txt, so that every time reopens the program, data remains.
   */
  static void read() {

    TransitMap ttc = new TransitMap("Toronto Transit Commission");
    String fileName = "log.txt";
    String line;

    try {
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      while ((line = bufferedReader.readLine()) != null) {

        Object[] parts = line.split(",");
        String keyword = (String) parts[0];
        switch (keyword) {
          case "NewDay":
            InfoCollector newCollector = new InfoCollector((String) parts[1]);
            Administrator.add(newCollector);
            break;
          case "New": {
            String user = (String) parts[1];
            if (user.equals("Admin")) {
              AdminUser au =
                      new AdminUser(
                              (String) parts[2], (String) parts[3], (String) parts[4], (String) parts[5]);
              Administrator.add(au);
            } else {
              TransitUser newUser =
                      new TransitUser(
                              (String) parts[2],
                              (String) parts[3],
                              (Long.valueOf((String) parts[4])),
                              (String) parts[5],
                              Long.valueOf((String) parts[6]));
              Administrator.add(newUser);
            }
            break;
          }
          case "ChangeName": {
            String user = (String) parts[1];
            if (user.equals("Admin")) {
              AdminUser au = Administrator.getAdminUserByEmail((String) parts[2]);
              au.setName((String) parts[3]);
            } else {
              TransitUser tu = Administrator.getTransitUserByEmail((String) parts[2]);
              tu.setName((String) parts[3]);
            }
            break;
          }
          case "ChangePassword": {
            String user = (String) parts[1];
            if (user.equals("Admin")) {
              AdminUser au = Administrator.getAdminUserByEmail((String) parts[2]);
              au.setPassword((String) parts[3]);
            } else {
              TransitUser tu = Administrator.getTransitUserByEmail((String) parts[2]);
              tu.setPassword((String) parts[3]);
            }
            break;
          }
          case "ChangeEmail": {
            String user = (String) parts[1];
            if (user.equals("Admin")) {
              AdminUser au = Administrator.getAdminUserByEmail((String) parts[2]);
              au.setEmail((String) parts[3]);
            } else {
              TransitUser tu = Administrator.getTransitUserByEmail((String) parts[2]);
              tu.setEmail((String) parts[3]);
            }
            break;
          }
          case "ChangeAccess":
            AdminUser au = Administrator.getAdminUserByEmail((String) parts[1]);
            au.setAccessLevel((String) parts[2]);
            break;
          case "AddCard":
            TransitCard newCard =
                    new TransitCard(
                            Integer.valueOf((String) parts[2]), Double.valueOf((String) parts[3]));
            TransitUser tu = Administrator.getTransitUserByEmail((String) parts[1]);
            tu.addCard(newCard);
            Administrator.add(newCard);
            break;
          case "SuspendCard": {
            TransitCard card = Administrator.getCardByNumber(Integer.valueOf((String) parts[1]));
            card.deactivate();
            break;
          }
          case "ActivateCard": {
            TransitCard card = Administrator.getCardByNumber(Integer.valueOf((String) parts[1]));
            card.activate();
            break;
          }
          case "RemoveCard": {
            TransitCard card = Administrator.getCardByNumber(Integer.valueOf((String) parts[1]));
            TransitUser user = Administrator.getTransitUserByEmail((String) parts[1]);
            user.removeCard(card);
            break;
          }
          case "AddCredit":
            TransitCard c = Administrator.getCardByNumber(Integer.valueOf((String) parts[1]));
            c.addCredit(Double.valueOf((String) parts[2]));
            break;
          case "TransferBalance": {
            TransitUser user = Administrator.getTransitUserByEmail((String) parts[1]);
            user.transferBalance(
                    Integer.valueOf((String) parts[2]),
                    Integer.valueOf((String) parts[3]),
                    Double.valueOf((String) parts[4]));
            break;
          }
          case "EnterSubway": {
            TransitCard card = Administrator.getCardByNumber(Integer.valueOf((String) parts[1]));
            card.tapInSubway(
                    (String) parts[2], Long.valueOf((String) parts[4]), (String) parts[3]);
            break;
          }
          case "ExitSubway": {
            TransitCard card = Administrator.getCardByNumber(Integer.valueOf((String) parts[1]));
            double cost = card.getBalance();
            int reached = card.getStationsReached();
            card.tapOutSubway(
                    (String) parts[2], Long.valueOf((String) parts[4]), (String) parts[3]);
            cost = cost - card.getBalance();
            reached = card.getStationsReached() - reached;
            Administrator.getListOfDays()
                    .get(Administrator.getListOfDays().size() - 1)
                    .incrementReached(reached);
            Administrator.getListOfDays()
                    .get(Administrator.getListOfDays().size() - 1)
                    .incrementFare(cost);
            break;
          }
          case "EnterBus": {
            TransitCard card = Administrator.getCardByNumber(Integer.valueOf((String) parts[1]));
            double cost = card.getBalance();
            card.tapInBus(
                    Integer.valueOf((String) parts[2]),
                    Long.valueOf((String) parts[4]),
                    (String) parts[3]);
            cost = cost - card.getBalance();
            Administrator.getListOfDays()
                    .get(Administrator.getListOfDays().size() - 1)
                    .incrementFare(cost);
            break;
          }
          case "ExitBus": {
            TransitCard card = Administrator.getCardByNumber(Integer.valueOf((String) parts[1]));
            int reached = card.getStopsReached();
            card.tapOutBus(
                    Integer.valueOf((String) parts[2]),
                    Long.valueOf((String) parts[4]),
                    (String) parts[3]);
            reached = card.getStopsReached() - reached;
            Administrator.getListOfDays()
                    .get(Administrator.getListOfDays().size() - 1)
                    .incrementReached(reached);
            break;
          }
          case "DeleteCardholder": {
            TransitUser user = Administrator.getTransitUserByEmail((String) parts[1]);
            for (TransitCard card : user.getCards()) {
              card.deactivate();
            }
            Administrator.getTransitUsers().remove(user);
            break;
          }
        }
      }
      // Closing bufferedReader
      bufferedReader.close();
    } catch (FileNotFoundException ex) {
      System.out.println("Unable to open file '" + fileName + "'");
    } catch (IOException ex) {
      System.out.println("Error reading file '" + fileName + "'");
    }
  }
}
