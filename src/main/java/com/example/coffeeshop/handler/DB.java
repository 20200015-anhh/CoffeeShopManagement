    package com.example.coffeeshop.handler;
    import com.example.coffeeshop.resource.Book;
    import com.example.coffeeshop.resource.Drink;
    import com.example.coffeeshop.resource.Event;
    import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
    import com.microsoft.sqlserver.jdbc.SQLServerException;
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.scene.control.Alert;
    import javafx.scene.control.ButtonType;

    import java.sql.*;
    public class DB {
        public static Alert E = new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK);
        private static Connection getConnection() throws SQLException {
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser("sa");
            ds.setPassword("1");
            ds.setServerName("LAPTOP-DNQ26QN4");
            ds.setPortNumber(1433);
            ds.setDatabaseName("Livre_Cafe");
            ds.setTrustServerCertificate(true);
            Connection con = null;
            try{
                con = ds.getConnection();
            } catch (SQLException ex) {
            System.err.println("Exception: " + ex.getMessage());
            E.show();
        }
            return con;
        }
        public static int count(String col, String Table) throws SQLException {
            Connection con = getConnection();
            Statement s = con.createStatement();
            String sqlSelect = "select count(" + col + ") from " + Table;
            ResultSet result = s.executeQuery(sqlSelect);
            if (result.next()) {
                return Integer.parseInt(result.getString(1));
            }

            return 0;
        }
        public static boolean insertDrink(String name, int id, int cost) {

            try {
                Connection con = getConnection();
                Statement s = con.createStatement();
                String SqlInsert = "INSERT INTO Drink(id,name,cost) " + "VALUES('" + id + "','" + name + "'," + cost + ")";
                s.execute(SqlInsert);
                return true;
            } catch (Exception e) {
                E.show();
                return false;
            }
        }
        public static boolean insertBooks(String name, int id, int cost, String info) {

            try {
                Connection con = getConnection();
                Statement s = con.createStatement();
                String SqlInsert = "INSERT INTO Books(id,name,cost,info) " + "VALUES('" + id + "','" + name + "'," + cost + "','" + info + ")";
                s.execute(SqlInsert);
                return true;
            } catch (Exception e) {
                E.show();
                return false;
            }
        }
        public static boolean insertEvent(String name, int id, int cost, String status) {

            try {
                Connection con = getConnection();
                Statement s = con.createStatement();
                String SqlInsert = "INSERT INTO Events(id,name,cost,status) " + "VALUES('" + id + "','" + name + "'," + cost + "','" + status + ")";
                s.execute(SqlInsert);
                return true;
            } catch (Exception e) {
                E.show();
                return false;
            }
        }
        public static ObservableList<Drink> getDrink() throws SQLException {
            Connection con = getConnection();
            Statement s = con.createStatement();
            String sqlSelect = "select * from Drink";
            ResultSet result = s.executeQuery(sqlSelect);
            ObservableList<Drink> drinkList = FXCollections.observableArrayList();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                int cost = result.getInt("cost");
                System.out.println(new Drink(id, cost, name));
                drinkList.add(new Drink(id, cost, name));
            }
            return drinkList;
        }
        public static ObservableList<Book> getBook() throws SQLException {
            Connection con = getConnection();
            Statement s = con.createStatement();
            String sqlSelect = "select * from Books";
            ResultSet result = s.executeQuery(sqlSelect);
            ObservableList<Book> bookList = FXCollections.observableArrayList();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                int cost = result.getInt("cost");
                String info = result.getString("info");
                System.out.println(new Book(id, cost, name,info));
                bookList.add(new Book(id, cost, name,info));
            }
            return bookList;
        }
        public static ObservableList<Event> getEvent() throws SQLException {
            Connection con = getConnection();
            Statement s = con.createStatement();
            String sqlSelect = "select * from Events";
            ResultSet result = s.executeQuery(sqlSelect);
            ObservableList<Event> eventList = FXCollections.observableArrayList();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                int cost = result.getInt("cost");
                String status = result.getString("status");
                eventList.add(new Event(id, cost, name,status));
            }
            return eventList;
        }
        public static boolean delDrink(int id) throws SQLException {
            Connection con = getConnection();
            Statement s = con.createStatement();
            String sqlDelete = "DELETE FROM Drink WHERE id = " + id + " ";
            boolean execute = s.execute(sqlDelete);
            return execute;
        }
        public static boolean delBook(int id) throws SQLException {
            Connection con = getConnection();
            Statement s = con.createStatement();
            String sqlDelete = "DELETE FROM Books WHERE id = " + id + " ";
            boolean execute = s.execute(sqlDelete);
            return execute;
        }
        public static boolean delEvent(int id) throws SQLException {
            Connection con = getConnection();
            Statement s = con.createStatement();
            String sqlDelete = "DELETE FROM Events WHERE id = " + id + " ";
            boolean execute = s.execute(sqlDelete);
            return execute;
        }
    }

