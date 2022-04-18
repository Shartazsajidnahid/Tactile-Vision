package com.example.javafxloginformjdbctutorial.Authentication;

import java.sql.SQLException;
import java.util.HashMap;
import java.sql.SQLException;

public class DButils {
    private HashMap<String, String> tableMap;

    public DButils(){
        tableMap= new HashMap<>();
        tableMap.put("std","student");
        tableMap.put("adm","admin");
        tableMap.put("tea","teacher");
        tableMap.put("stf","staff");
    }

    public String parseID(String unparsedID){
        return new String(unparsedID.substring(4,unparsedID.length()));
    }

    public String parseTable (String unparsedID){
        return tableMap.get(new String(unparsedID.substring(0,3)));
    }

    public static void printSQLException(SQLException ex) {
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
}
