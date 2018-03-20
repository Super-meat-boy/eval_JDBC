import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class Aliment {

    private Long idAliment;
    private String name;
    private String type;
    public int calorie;
    public double protein;


    public Aliment(Long idAliment, String name, int calorie, double protein) {
        this.idAliment = idAliment;
        this.name = name;
        this.type = type;
        this.calorie = calorie;
        this.protein = protein;

    }

    public Aliment(String name, String type, int calorie, double protein) {
        this.idAliment = null;
        this.name = name;
        this.type = type;
        this.calorie = calorie;
        this.protein = protein;

    }


    public void savealiment(Connection connection) throws SQLException {
        String sqlcmd = "INSERT INTO aliment (name,type) VALUES (?,?);";

        try (PreparedStatement insertStatement = connection.prepareStatement(sqlcmd, RETURN_GENERATED_KEYS)) {
            insertStatement.setString(1, getName());
            insertStatement.setString(2, getType());
            insertStatement.execute();
            ResultSet rs = insertStatement.getGeneratedKeys();

            if (rs.next()) {
                setIdAliment(rs.getLong("id"));
                System.out.println("\n \n === CONGRATULATIONS AMIGO ===\n the aliment " + rs.getString("name") + " has been correctly created \n \n \n");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sqlcmd2 = "INSERT INTO energy (id_aliment,calories,protein) VALUES (?,?,?);";

        try (PreparedStatement insertStatement = connection.prepareStatement(sqlcmd2, RETURN_GENERATED_KEYS)) {
            insertStatement.setLong(1, getIdAliment());
            insertStatement.setInt(2, getCalorie());
            insertStatement.setDouble(3, getProtein());
            insertStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }


    public static void deleteAliment(Connection connection, int id_aliment) throws SQLException {
        String SQL = "DELETE FROM energy WHERE id_aliment= (?)";

        try (PreparedStatement insertStatement = connection.prepareStatement(SQL)) {
            insertStatement.setInt(1, id_aliment);
            insertStatement.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        String SQL1 = "DELETE FROM aliment WHERE id= (?)";

        try (PreparedStatement inserStatement = connection.prepareStatement(SQL1)) {
            inserStatement.setLong(1, id_aliment);
            inserStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public static void selectAliment(Connection conn) throws SQLException {
        String selectCmd = "SELECT * FROM aliment;";

        try (Statement selectStatement = conn.createStatement()) {
            ResultSet res = selectStatement.executeQuery(selectCmd);


            while (res.next()) {
                System.out.println("Aliment ID : " + res.getLong("id") + "\nName : " + res.getString("name") + "\nType : " + res.getString("type") + "\n");

            }
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public Long getIdAliment() {
        return idAliment;
    }

    public void setIdAliment(Long idAliment) {
        this.idAliment = idAliment;
    }


    public String toString() {
        return "Aliment name : " + this.name + " Type : " + this.type + " Calories : " + this.calorie + " Protein : " + this.protein;
    }


}



