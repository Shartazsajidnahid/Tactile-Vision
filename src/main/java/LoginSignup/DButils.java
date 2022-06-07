package LoginSignup;


        import javafx.scene.control.Alert;
        import javafx.stage.Window;

        import java.sql.SQLException;
        import java.util.HashMap;
        import java.sql.SQLException;

public class DButils {
    private HashMap<String, String> tableMap;

    public DButils(){
        tableMap= new HashMap<>();
        tableMap.put("std","Student");
        tableMap.put("adm","Admin");
        tableMap.put("tea","Teacher");
        tableMap.put("stf","Staff");
    }

    public String parseID(String unparsedID){
        return new String(unparsedID.substring(4,unparsedID.length()));
    }

    public String parseTable (String unparsedID){
        return tableMap.get(new String(unparsedID.substring(0,3)));
    }

    public void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    public void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}