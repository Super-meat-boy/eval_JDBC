import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {

        Connection connection;

        try {

            String url = args[0];
            String user = args[1];
            String pwd = args[2];

            connection = DriverManager.getConnection(url, user, pwd);

        } catch (SQLException e) {
            e.printStackTrace();

            return;
        }

        System.out.println("Connexion established !");

        String name;
        String type;
        int calories;
        double protein;
        int idtodelete;
        int Menuchoice = 0;
        Scanner sc = new Scanner(System.in);


        while (Menuchoice != 4) {

            System.out.println("====== WELCOME TO THE FOOD BOOK 2.0 ======\n");
            System.out.println("====== Please make your selection ======\n");
            System.out.println("For adding a new aliment press 1 ");
            System.out.println("For displaying all aliments press 2 ");
            System.out.println("For deleting an aliment press 3 ");
            System.out.println("For simply quitting, press 4 ");

            Menuchoice = sc.nextInt();

            switch (Menuchoice) {

                case 1:

                    System.out.println("Please enter his name");
                    name = sc.next();
                    System.out.println("What's his type ?");
                    type = sc.next();
                    System.out.println("What is his calorie value ?");
                    calories = sc.nextInt();
                    System.out.println("What about protein ?");
                    protein = sc.nextDouble();

                    Aliment aliment1 = new Aliment(name, type, calories, protein);
                    aliment1.savealiment(connection);
                    break;


                case 2:
                    Aliment.selectAliment(connection);
                    break;


                case 3:
                    System.out.println("Thanks for entering the ID of the element you want to delete");
                    idtodelete = sc.nextInt();
                    Aliment.deleteAliment(connection, idtodelete);
                    System.out.println("Your Aliment has been suscessfully deleted from database");
                    break;

                case 4:
                    System.out.println("========= Thanks for step and by ========= \n ========= BYE BYE =========");
                    break;

                default:


            }
        }
    }
}
