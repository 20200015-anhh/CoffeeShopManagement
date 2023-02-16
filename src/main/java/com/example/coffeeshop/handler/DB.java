    package com.example.coffeeshop.handler;

    import com.example.coffeeshop.resource.*;
    import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.scene.control.Alert;
    import javafx.scene.control.ButtonType;

    import java.sql.Connection;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.Statement;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.Map;

    public class DB {
        public static Alert E = new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK);
        public static int presentID = 0;
        public static int presentEventID = 1000;
        public static ArrayList<String> existedDate= new ArrayList<>();

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
                String SqlInsert = "INSERT INTO Drink(id,name,cost) " + "VALUES(" + id + ",'" + name + "'," + cost + ")";
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
                String SqlInsert = "INSERT INTO Books(id,name,cost,info) " + "VALUES(" + id + ",'" + name + "'," + cost + ",'" + info + "')";
                s.execute(SqlInsert);
                return true;
            } catch (Exception e) {
                E.show();
                return false;
            }
        }
        public static boolean insertEvent(int id,String name, int cost, String date) {

            try {
                Connection con = getConnection();
                Statement s = con.createStatement();
                String SqlInsert = "INSERT INTO Product(id) " + "VALUES(" + id + ")";
                s.execute(SqlInsert);
                Connection con1 = getConnection();
                Statement s1 = con1.createStatement();
                String SqlInsert1 = "INSERT INTO Events(id,name,cost,date,status) " + "VALUES(" + id + ",'" + name + "'," + cost + ",'" + date +"', 'Unfinished')";
                s1.execute(SqlInsert1);
                return true;
            } catch (Exception e) {
                E.show();
                return false;
            }
        }
        public static boolean insertEvent(int id,String name, int cost, String date, String status) {

            try {
                Connection con = getConnection();
                Statement s = con.createStatement();
                String SqlInsert = "INSERT INTO Product(id) " + "VALUES(" + id + ")";
                s.execute(SqlInsert);
                Connection con1 = getConnection();
                Statement s1 = con1.createStatement();
                String SqlInsert1 = "INSERT INTO Events(id,name,cost,date,status) " + "VALUES(" + id + ",'" + name + "'," + cost + ",'" + date +"','"+ status+ "')";
                s1.execute(SqlInsert1);
                return true;
            } catch (Exception e) {
                E.show();
                return false;
            }
        }
        public static boolean insertDoneOrder(int orderID,String customer, String time,String status, int sum) {

            try {
                Connection con = getConnection();
                Statement s = con.createStatement();
                String SqlInsert = "INSERT INTO Orders(orderID,customer,time,status,sum) " + "VALUES(" + orderID + ",'" + customer + "','" + time + "','" + status + "'," + sum +")";
                s.execute(SqlInsert);
                return true;
            } catch (Exception e) {
                E.show();
                return false;
            }
        }
        public static boolean insertSoldProduct(int orderID,int id, int num) {

            try {
                Connection con = getConnection();
                Statement s = con.createStatement();
                String SqlInsert = "INSERT INTO SoldProducts(orderID,id,num) " + "VALUES(" + orderID + "," + id + "," + num +")";
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
                bookList.add(new Book(id, cost, name,info));
            }
            return bookList;
        }
        public static ObservableList<Event> getEvent() throws SQLException {
            Connection con = getConnection();
            Statement s = con.createStatement();
            String sqlSelect = "select * from Events WHERE status = 'Unfinished'";
            ResultSet result = s.executeQuery(sqlSelect);
            ObservableList<Event> eventsList = FXCollections.observableArrayList();
            while (result.next()) {
                int id = result.getInt("id");
                String date = result.getString("date");
                existedDate.add(date);
                String name = result.getString("name");
                int cost = result.getInt("cost");
                eventsList.add(new Event(id,cost,name,date));
            }
            Connection con1 = getConnection();
            Statement s1 = con1.createStatement();
            String sqlSelect1 = "select * from Events";
            ResultSet result1 = s1.executeQuery(sqlSelect1);
            while (result1.next()) {
                int id = result1.getInt("id");
                if(id >= presentEventID)
                    presentEventID = id+1;
            }
            System.out.println(presentEventID);
            return eventsList;
        }
        public static ObservableList<DoneOrder> getDoneOrder() throws SQLException {
            Connection con = getConnection();
            Statement s = con.createStatement();
            String sqlSelect = "select * from Orders";
            ResultSet result = s.executeQuery(sqlSelect);
            ObservableList<DoneOrder> ordersList = FXCollections.observableArrayList();
            while (result.next()) {
                int orderID = result.getInt("orderID");
                if(orderID>= presentID)
                    presentID = orderID + 1;
                int sum = result.getInt("sum");
                String time = result.getString("time");
                String status = result.getString("status");
                String customer = result.getString("customer");
                ordersList.add(new DoneOrder(orderID,customer,time,status,sum));
            }
            return ordersList;
        }
        public static Map<Product, Integer> getSoldProducts(int orderID) throws SQLException {
            Connection con = getConnection();
            Statement s = con.createStatement();
            String sqlSelect = "select id, num from SoldProducts WHERE orderID = " + orderID;
            ResultSet result = s.executeQuery(sqlSelect);
            ArrayList<Integer> idList = new ArrayList<>();
            ArrayList<Integer> numList = new ArrayList<>();
            while (result.next()) {
                int id = result.getInt("id");
                int num = result.getInt("num");
                idList.add(id);
                numList.add(num);
            }
            ArrayList<Product> productList = new ArrayList<>();
            for (int i:idList) {
                Connection con1 = getConnection();
                Statement s1 = con1.createStatement();
                String sqlSelect1 = "select * from Drink WHERE id = " + i;
                ResultSet result1 = s1.executeQuery(sqlSelect1);
                while (result1.next()) {
                    int cost = result1.getInt("cost");
                    String name = result1.getString("name");
                    productList.add(new Drink(i,cost,name));
                }
            }
            for (int i:idList) {
                Connection con1 = getConnection();
                Statement s1 = con1.createStatement();
                String sqlSelect1 = "select * from Books WHERE id = " + i;
                ResultSet result1 = s1.executeQuery(sqlSelect1);
                while (result1.next()) {
                    int cost = result1.getInt("cost");
                    String name = result1.getString("name");
                    productList.add(new Book(i,cost,name,""));
                }
            }
            for (int i:idList) {
                Connection con2 = getConnection();
                Statement s2 = con2.createStatement();
                String sqlSelect2 = "select * from Events WHERE id = " + i;
                ResultSet result2 = s2.executeQuery(sqlSelect2);
                while (result2.next()) {
                    int cost = result2.getInt("cost");
                    String name = result2.getString("name");
                    productList.add(new Event(i,cost,name,""));
                }
            }
            Map<Product,Integer> soldProducts = new HashMap<>();
            int tmp =0;
            for (Product i:productList) {
                soldProducts.put(i, numList.get(tmp));
                tmp++;
            }
            return soldProducts;
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
            Connection con1 = getConnection();
            Statement s1 = con1.createStatement();
            String sqlDelete1 = "DELETE FROM Product WHERE id = " + id + " ";
            boolean execute1 = s1.execute(sqlDelete1);
            return execute;
        }
        public static boolean delOrder(int orderID) throws SQLException {
            Connection con = getConnection();
            Statement s = con.createStatement();
            String sqlDelete = "DELETE FROM SoldProducts WHERE orderID = " + orderID + " ";
            boolean execute = s.execute(sqlDelete);
            Connection con1 = getConnection();
            Statement s1 = con1.createStatement();
            String sqlDelete1 = "DELETE FROM Orders WHERE orderID = " + orderID + " ";
            boolean execute1 = s1.execute(sqlDelete1);
            return execute1;
        }
    }

