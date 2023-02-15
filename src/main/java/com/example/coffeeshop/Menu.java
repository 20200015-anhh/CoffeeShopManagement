package com.example.coffeeshop;

import com.example.coffeeshop.resource.Book;
import com.example.coffeeshop.resource.Drink;
import com.example.coffeeshop.handler.DB;
import com.example.coffeeshop.resource.Order;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Menu {
    private Order order;
    private ObservableList<Drink> listD = null;
    private ObservableList<Book> listB = null;
    @FXML
    private AnchorPane ap;
    @FXML
    private TableView<Drink> dTable;
    @FXML
    private TableView<Book> bTable;
    @FXML
    private ImageView logo;
    @FXML
    private ScrollPane dPane;
    @FXML
    private ScrollPane bPane;
    @FXML
    private TableColumn<Drink, Integer> dID;
    @FXML
    private TableColumn<Drink, String> dName;
    @FXML
    private TableColumn<Drink, Integer> dCost;
    @FXML
    private TableColumn<Book, Void> dNum;
    @FXML
    private TableColumn<Book, Integer> bID;
    @FXML
    private TableColumn<Book, String> bName;
    @FXML
    private TableColumn<Book, Integer> bCost;
    @FXML
    private TableColumn<Book, Void> bNum;
    @FXML
    private TableColumn<Book, Void> bInfo;
    @FXML
    private Button confirm;
    @FXML
    private Button pOrder;
    @FXML
    private Button doneOrder;

    public void initialize() {
        dPane.setVisible(true);
        bPane.setVisible(false);

        dID.setCellValueFactory(new PropertyValueFactory<Drink, Integer>("id"));
        dName.setCellValueFactory(new PropertyValueFactory<Drink, String>("name"));
        dCost.setCellValueFactory(new PropertyValueFactory<Drink, Integer>("cost"));

        bID.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
        bName.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
        bCost.setCellValueFactory(new PropertyValueFactory<Book, Integer>("cost"));
        addButtonToTable();
        addNumToTable();

        try {
            listD = DB.getDrink();
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        dTable.setItems(listD);
        try {
            listB = DB.getBook();
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        bTable.setItems(listB);
    }

    public void initDialog(String info) {
        Dialog<String> dialog = new Dialog<String>();
        dialog.setTitle("Book's info");
        VBox vb = new VBox();
        vb.getChildren().add(new Label(info));
        dialog.getDialogPane().setContent(vb);
        ButtonType bt = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(bt);
        dialog.showAndWait();
    }

    private void addButtonToTable() {
        Callback<TableColumn<Book, Void>, TableCell<Book, Void>> cellFactory = new Callback<TableColumn<Book, Void>, TableCell<Book, Void>>() {
            @Override
            public TableCell<Book, Void> call(final TableColumn<Book, Void> param) {
                final TableCell<Book, Void> cell = new TableCell<Book, Void>() {

                    private final Button btn = new Button("Info");

                    {
                        btn.setPadding(new Insets(5, 10, 5, 10));
                        btn.setOnAction((ActionEvent event) -> {
                            Book book = getTableView().getItems().get(getIndex());
                            initDialog(book.getInfo());
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        }else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        bInfo.setCellFactory(cellFactory);
    }

    private void addNumToTable() {
        Callback<TableColumn<Book, Void>, TableCell<Book, Void>> cellFactory = new Callback<TableColumn<Book, Void>, TableCell<Book, Void>>() {
            @Override
            public TableCell<Book, Void> call(final TableColumn<Book, Void> param) {
                final TableCell<Book, Void> cell = new TableCell<Book, Void>() {

                    private final Spinner<Integer> spn = new Spinner<Integer>(0,999,0);

                    {
                        spn.valueProperty().addListener(new ChangeListener<Integer>() {
                            @Override
                            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                                ;
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        }else {
                            setGraphic(spn);
                        }
                    }
                };
                return cell;
            }
        };
        bNum.setCellFactory(cellFactory);
        dNum.setCellFactory(cellFactory);
    }


    public void Drink() {
        dPane.setVisible(true);
        bPane.setVisible(false);
    }

    public void Books() {
        bPane.setVisible(true);
        dPane.setVisible(false);
    }
}