package com.example.coffeeshop;

import com.example.coffeeshop.resource.*;
import com.example.coffeeshop.handler.DB;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.coffeeshop.handler.DB.*;

public class Menu {
    private Order order;
    private ObservableList<Drink> listD = null;
    private ObservableList<Book> listB = null;
    private ObservableList<Event> listE = null;
    private ObservableList<Order> listO = FXCollections.observableArrayList();
    private ObservableList<DoneOrder> listDO = null;
    @FXML
    private AnchorPane ap;
    @FXML
    private TableView<Drink> dTable;
    @FXML
    private TableView<Book> bTable;
    @FXML
    private TableView<Order> oTable;
    @FXML
    private TableView<DoneOrder> oTable1;
    @FXML
    private TableView<Event> eTable;
    @FXML
    private ScrollPane dPane;
    @FXML
    private ScrollPane bPane;
    @FXML
    private ScrollPane ePane;
    @FXML
    private ScrollPane oPane;
    @FXML
    private ScrollPane oPane1;
    @FXML
    private TableColumn<Drink, Drink> dRowNum;
    @FXML
    private TableColumn<Drink, String> dName;
    @FXML
    private TableColumn<Drink, Integer> dCost;
    @FXML
    private TableColumn<Drink, Void> dNum;
    @FXML
    private TableColumn<Book, Book> bRowNum;
    @FXML
    private TableColumn<Book, String> bName;
    @FXML
    private TableColumn<Book, Integer> bCost;
    @FXML
    private TableColumn<Book, Void> bNum;
    @FXML
    private TableColumn<Book, Void> bInfo;
    @FXML
    private TableColumn<Order, Order> oRowNum;
    @FXML
    private TableColumn<Order, String> oCustomer;
    @FXML
    private TableColumn<Order, Integer> oCost;
    @FXML
    private TableColumn<Order, Void> oInfo;
    @FXML
    private TableColumn<Order, Void> oControl;
    @FXML
    private TableColumn<DoneOrder, DoneOrder> oRowNum1;
    @FXML
    private TableColumn<DoneOrder, String> oCustomer1;
    @FXML
    private TableColumn<DoneOrder, Integer> oCost1;
    @FXML
    private TableColumn<DoneOrder, Void> oInfo1;
    @FXML
    private TableColumn<DoneOrder, Void> oControl1;
    @FXML
    private TableColumn<Event, Event> eRowNum;
    @FXML
    private TableColumn<Event, String> eName;
    @FXML
    private TableColumn<Event, String> eDate;
    @FXML
    private TableColumn<Event, Void> eControl;

    @FXML
    private TextArea eNameInput;
    @FXML
    private TextField eCostInput;
    @FXML
    private DatePicker eDateInput;
    @FXML
    private Button confirm;
    @FXML
    private Button pOrder;
    @FXML
    private Button doneOrder;
    @FXML
    private Label tab;

    public void initialize() throws SQLException {
        initOrder();

        drink();

        dName.setCellValueFactory(new PropertyValueFactory<Drink, String>("name"));
        dCost.setCellValueFactory(new PropertyValueFactory<Drink, Integer>("cost"));
        addNumToDTable();
        addRowNumToDTable();

        bName.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
        bCost.setCellValueFactory(new PropertyValueFactory<Book, Integer>("cost"));
        addButtonToBTable();
        addNumToBTable();
        addRowNumToBTable();

        oCustomer.setCellValueFactory(new PropertyValueFactory<Order, String>("customer"));
        oCost.setCellValueFactory(new PropertyValueFactory<Order, Integer>("sum"));
        addButtonToOTable();
        addRowNumToOTable();

        oCustomer1.setCellValueFactory(new PropertyValueFactory<DoneOrder, String>("customer"));
        oCost1.setCellValueFactory(new PropertyValueFactory<DoneOrder, Integer>("sum"));
        addRowNumToDOTable();
        addButtonToDOTable();

        eName.setCellValueFactory(new PropertyValueFactory<Event,String>("name"));
        eDate.setCellValueFactory(new PropertyValueFactory<Event,String>("date"));
        addButtonToETable();
        addRowNumToETable();

        updateDrink();
        updateBooks();
        updateDO();
        updateEvent();
        oTable.setItems(listO);
    }
    public void initOrder(){
        order = new Order();
    }
    public void initDialog(String info) {
        Dialog<String> dialog = new Dialog<String>();
        dialog.setTitle("Thông tin sách");
        VBox vb = new VBox();
        vb.getChildren().add(new Label(info));
        dialog.getDialogPane().setContent(vb);
        ButtonType bt = new ButtonType("Đóng", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(bt);
        dialog.showAndWait();
    }
    private void addRowNumToDTable(){
        dRowNum.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue()));
        dRowNum.setCellFactory(new Callback<TableColumn<Drink, Drink>, TableCell<Drink, Drink>>() {
            @Override public TableCell<Drink, Drink> call(TableColumn<Drink, Drink> param) {
                return new TableCell<Drink, Drink>() {
                    @Override protected void updateItem(Drink item, boolean empty) {
                        super.updateItem(item, empty);

                        if (this.getTableRow() != null && item != null) {
                            int tmp = this.getTableRow().getIndex() + 1;
                            setText(tmp + "");
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });
        dRowNum.setSortable(false);
    }
    private void addRowNumToBTable(){
        bRowNum.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue()));
        bRowNum.setCellFactory(new Callback<TableColumn<Book, Book>, TableCell<Book, Book>>() {
            @Override public TableCell<Book, Book> call(TableColumn<Book, Book> param) {
                return new TableCell<Book, Book>() {
                    @Override protected void updateItem(Book item, boolean empty) {
                        super.updateItem(item, empty);

                        if (this.getTableRow() != null && item != null) {
                            int tmp = this.getTableRow().getIndex() + 1;
                            setText(tmp + "");
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });
        bRowNum.setSortable(false);
    }
    private void addRowNumToETable(){
        eRowNum.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue()));
        eRowNum.setCellFactory(new Callback<TableColumn<Event, Event>, TableCell<Event, Event>>() {
            @Override public TableCell<Event, Event> call(TableColumn<Event, Event> param) {
                return new TableCell<Event, Event>() {
                    @Override protected void updateItem(Event item, boolean empty) {
                        super.updateItem(item, empty);

                        if (this.getTableRow() != null && item != null) {
                            int tmp = this.getTableRow().getIndex() + 1;
                            setText(tmp + "");
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });
        eRowNum.setSortable(false);
    }
    private void addRowNumToOTable(){
        oRowNum.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue()));
        oRowNum.setCellFactory(new Callback<TableColumn<Order, Order>, TableCell<Order, Order>>() {
            @Override public TableCell<Order, Order> call(TableColumn<Order, Order> param) {
                return new TableCell<Order, Order>() {
                    @Override protected void updateItem(Order item, boolean empty) {
                        super.updateItem(item, empty);

                        if (this.getTableRow() != null && item != null) {
                            int tmp = this.getTableRow().getIndex() + 1;
                            setText(tmp + "");
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });
        oRowNum.setSortable(false);
    }
    private void addRowNumToDOTable(){
        oRowNum1.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue()));
        oRowNum1.setCellFactory(new Callback<TableColumn<DoneOrder, DoneOrder>, TableCell<DoneOrder, DoneOrder>>() {
            @Override public TableCell<DoneOrder, DoneOrder> call(TableColumn<DoneOrder, DoneOrder> param) {
                return new TableCell<DoneOrder, DoneOrder>() {
                    @Override protected void updateItem(DoneOrder item, boolean empty) {
                        super.updateItem(item, empty);
                        if (this.getTableRow() != null && item != null) {
                            int tmp = this.getTableRow().getIndex() + 1;
                            setText(tmp + "");
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });
        oRowNum1.setSortable(false);
    }
    private void addButtonToBTable() {
        Callback<TableColumn<Book, Void>, TableCell<Book, Void>> cellFactory = new Callback<TableColumn<Book, Void>, TableCell<Book, Void>>() {
            @Override
            public TableCell<Book, Void> call(final TableColumn<Book, Void> param) {
                final TableCell<Book, Void> cell = new TableCell<Book, Void>() {

                    private final Button btn = new Button("Thông tin");

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
    private void addButtonToOTable() {
        Callback<TableColumn<Order, Void>, TableCell<Order, Void>> cellFactory = new Callback<TableColumn<Order, Void>, TableCell<Order, Void>>() {
            @Override
            public TableCell<Order, Void> call(final TableColumn<Order, Void> param) {
                final TableCell<Order, Void> cell = new TableCell<Order, Void>() {

                    private final Button btn = new Button("Thông tin");

                    {
                        btn.setPadding(new Insets(5, 10, 5, 10));
                        btn.setOnAction((ActionEvent event) -> {
                            Order pOrder = getTableView().getItems().get(getIndex());
                            Dialog<String> dialog = new Dialog<String>();
                            dialog.setTitle("Thông tin đơn");
                            VBox vb = new VBox();
                            TableColumn<Map.Entry<Product,Integer>,String> product = new TableColumn<>("Sản phẩm");
                            product.setCellValueFactory(entryStringCellDataFeatures -> new SimpleStringProperty(entryStringCellDataFeatures.getValue().getKey().getName()));
                            TableColumn<Map.Entry<Product,Integer>,Integer> num = new TableColumn<>("Số lượng");
                            num.setCellValueFactory(entryIntegerCellDataFeatures -> new SimpleIntegerProperty(entryIntegerCellDataFeatures.getValue().getValue()).asObject());
                            ObservableList<Map.Entry<Product, Integer>> items = FXCollections.observableArrayList(pOrder.getOrder().entrySet());
                            TableView<Map.Entry<Product,Integer>> table = new TableView<>(items);
                            product.setResizable(false);
                            num.setResizable(false);
                            table.getColumns().setAll(product,num);
                            vb.getChildren().add(table);
                            dialog.getDialogPane().setContent(vb);
                            ButtonType bt = new ButtonType("Đóng", ButtonBar.ButtonData.CANCEL_CLOSE);
                            dialog.getDialogPane().getButtonTypes().add(bt);
                            dialog.showAndWait();
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
        oInfo.setCellFactory(cellFactory);
        Callback<TableColumn<Order, Void>, TableCell<Order, Void>> cellFactory1 = new Callback<TableColumn<Order, Void>, TableCell<Order, Void>>() {
            @Override
            public TableCell<Order, Void> call(final TableColumn<Order, Void> param) {
                final TableCell<Order, Void> cell = new TableCell<Order, Void>() {

                    private final Button btn = new Button("Xác nhận");

                    {
                        btn.setPadding(new Insets(5, 10, 5, 10));
                        btn.setOnAction((ActionEvent event) -> {
                            Order pOrder = getTableView().getItems().get(getIndex());
                            Dialog<String> dialog = new Dialog<String>();
                            dialog.setTitle("Bảng điều khiển");
                            VBox vb = new VBox(10);
                            Button finished = new Button("Hoàn thành đơn");
                            Button canceled = new Button("Hủy đơn");
                            finished.setPrefSize(200,10);
                            canceled.setPrefSize(200,10);
                            finished.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    pOrder.setStatus("Finished");
                                    DB.insertDoneOrder(pOrder.getOrderID(),pOrder.getCustomer(),pOrder.getTime(),pOrder.getStatus(),pOrder.getSum());
                                    for(Map.Entry<Product,Integer> entry:pOrder.getOrder().entrySet()) {
                                        DB.insertSoldProduct(pOrder.getOrderID(), entry.getKey().getId(), entry.getValue());
                                    }
                                    getTableView().getItems().removeAll(pOrder);
                                    updateDO();
                                    dialog.close();
                                }
                            });
                            canceled.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    pOrder.setStatus("Canceled");
                                    DB.insertDoneOrder(pOrder.getOrderID(),pOrder.getCustomer(),pOrder.getTime(),pOrder.getStatus(),pOrder.getSum());
                                    for(Map.Entry<Product,Integer> entry:pOrder.getOrder().entrySet()) {
                                        DB.insertSoldProduct(pOrder.getOrderID(), entry.getKey().getId(), entry.getValue());
                                    }
                                    getTableView().getItems().removeAll(pOrder);
                                    updateDO();
                                    dialog.close();
                                }
                            });
                            vb.getChildren().addAll(finished,canceled);
                            dialog.getDialogPane().setContent(vb);
                            ButtonType bt = new ButtonType("Đóng", ButtonBar.ButtonData.CANCEL_CLOSE);
                            dialog.getDialogPane().getButtonTypes().add(bt);
                            dialog.showAndWait();
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
        oControl.setCellFactory(cellFactory1);
    }
    private void addButtonToDOTable() {
        Callback<TableColumn<DoneOrder, Void>, TableCell<DoneOrder, Void>> cellFactory = new Callback<TableColumn<DoneOrder, Void>, TableCell<DoneOrder, Void>>() {
            @Override
            public TableCell<DoneOrder, Void> call(final TableColumn<DoneOrder, Void> param) {
                final TableCell<DoneOrder, Void> cell = new TableCell<DoneOrder, Void>() {

                    private final Button btn = new Button("Thông tin");

                    {
                        btn.setPadding(new Insets(5, 10, 5, 10));
                        btn.setOnAction((ActionEvent event) -> {
                            DoneOrder doneOrder = getTableView().getItems().get(getIndex());
                            Dialog<String> dialog = new Dialog<String>();
                            dialog.setTitle("Thông tin đơn");
                            VBox vb = new VBox();
                            Label time = new Label(doneOrder.getTime());
                            Label status = new Label(doneOrder.getStatus());
                            TableColumn<Map.Entry<Product,Integer>,String> product = new TableColumn<>("Sản phẩm");
                            product.setCellValueFactory(entryStringCellDataFeatures -> new SimpleStringProperty(entryStringCellDataFeatures.getValue().getKey().getName()));
                            TableColumn<Map.Entry<Product,Integer>,Integer> num = new TableColumn<>("Số lượng");
                            num.setCellValueFactory(entryIntegerCellDataFeatures -> new SimpleIntegerProperty(entryIntegerCellDataFeatures.getValue().getValue()).asObject());
                            ObservableList<Map.Entry<Product, Integer>> items = null;
                            try {
                                items = FXCollections.observableArrayList(DB.getSoldProducts(doneOrder.getOrderID()).entrySet());
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            TableView<Map.Entry<Product,Integer>> table = new TableView<>(items);
                            product.setResizable(false);
                            num.setResizable(false);
                            table.getColumns().setAll(product,num);
                            vb.getChildren().addAll(time,status,table);
                            dialog.getDialogPane().setContent(vb);
                            ButtonType bt = new ButtonType("Đóng", ButtonBar.ButtonData.CANCEL_CLOSE);
                            dialog.getDialogPane().getButtonTypes().add(bt);
                            dialog.showAndWait();
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
        oInfo1.setCellFactory(cellFactory);
        Callback<TableColumn<DoneOrder, Void>, TableCell<DoneOrder, Void>> cellFactory1 = new Callback<TableColumn<DoneOrder, Void>, TableCell<DoneOrder, Void>>() {
            @Override
            public TableCell<DoneOrder, Void> call(final TableColumn<DoneOrder, Void> param) {
                final TableCell<DoneOrder, Void> cell = new TableCell<DoneOrder, Void>() {

                    private final Button btn = new Button("Xác nhận");

                    {
                        btn.setPadding(new Insets(5, 10, 5, 10));
                        btn.setOnAction((ActionEvent event) -> {
                            DoneOrder doneOrder = getTableView().getItems().get(getIndex());
                            Dialog<String> dialog = new Dialog<String>();
                            dialog.setTitle("Bảng điều khiển");
                            VBox vb = new VBox(10);
                            Button finished = new Button("Xóa đơn");
                            finished.setPrefSize(200,10);
                            finished.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    try {
                                        DB.delOrder(doneOrder.getOrderID());
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                    getTableView().getItems().removeAll(doneOrder);
                                    presentID-=1;
                                    dialog.close();
                                }
                            });
                            vb.getChildren().addAll(finished);
                            dialog.getDialogPane().setContent(vb);
                            ButtonType bt = new ButtonType("Đóng", ButtonBar.ButtonData.CANCEL_CLOSE);
                            dialog.getDialogPane().getButtonTypes().add(bt);
                            dialog.showAndWait();
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
        oControl1.setCellFactory(cellFactory1);
    }
    private void addButtonToETable() {
        Callback<TableColumn<Event, Void>, TableCell<Event, Void>> cellFactory = new Callback<TableColumn<Event, Void>, TableCell<Event, Void>>() {
            @Override
            public TableCell<Event, Void> call(final TableColumn<Event, Void> param) {
                final TableCell<Event, Void> cell = new TableCell<Event, Void>() {

                    private final Button btn = new Button("Xác nhận");

                    {
                        btn.setPadding(new Insets(5, 10, 5, 10));
                        btn.setOnAction((ActionEvent event) -> {
                            Event mEvent = getTableView().getItems().get(getIndex());
                            Dialog<String> dialog = new Dialog<String>();
                            dialog.setTitle("Bảng điều khiển");
                            VBox vb = new VBox(10);
                            Button finished = new Button("Thành công");
                            finished.setPrefSize(200,10);
                            Button canceled = new Button("Hủy đơn");
                            canceled.setPrefSize(200,10);
                            finished.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    mEvent.setStatus("Finished");
                                    LocalDateTime now = LocalDateTime.now();
                                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                    String time = dateFormat.format(now);
                                    try {
                                        DB.delEvent(mEvent.getId());
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                    DB.insertEvent(mEvent.getId(),mEvent.getName(), mEvent.getCost(),mEvent.getDate(),mEvent.getStatus());
                                    DB.insertDoneOrder(mEvent.getId(),mEvent.getName(),time,mEvent.getStatus(),mEvent.getCost());
                                    DB.insertSoldProduct(mEvent.getId(),mEvent.getId(),1);
                                    updateEvent();
                                    updateDO();
                                    dialog.close();
                                }
                            });
                            canceled.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    mEvent.setStatus("Canceled");
                                    LocalDateTime now = LocalDateTime.now();
                                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                    String time = dateFormat.format(now);
                                    try {
                                        DB.delEvent(mEvent.getId());
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                    DB.insertEvent(mEvent.getId(),mEvent.getName(), mEvent.getCost(),mEvent.getDate(),mEvent.getStatus());
                                    DB.insertDoneOrder(mEvent.getId(),mEvent.getName(),time,mEvent.getStatus(),mEvent.getCost());
                                    DB.insertSoldProduct(mEvent.getId(),mEvent.getId(),1);
                                    updateEvent();
                                    updateDO();
                                    dialog.close();
                                }
                            });
                            vb.getChildren().addAll(finished,canceled);
                            dialog.getDialogPane().setContent(vb);
                            ButtonType bt = new ButtonType("Đóng", ButtonBar.ButtonData.CANCEL_CLOSE);
                            dialog.getDialogPane().getButtonTypes().add(bt);
                            dialog.showAndWait();
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
        eControl.setCellFactory(cellFactory);
    }
    private void addNumToBTable() {
        Callback<TableColumn<Book, Void>, TableCell<Book, Void>> cellFactory = new Callback<TableColumn<Book, Void>, TableCell<Book, Void>>() {
            @Override
            public TableCell<Book, Void> call(final TableColumn<Book, Void> param) {
                final TableCell<Book, Void> cell = new TableCell<Book, Void>() {

                    private final Spinner<Integer> spn = new Spinner<Integer>(0,999,0);

                    {
                        spn.valueProperty().addListener(new ChangeListener<Integer>() {
                            @Override
                            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                                Book book = getTableView().getItems().get(getIndex());
                                if(t1 != 0){
                                    order.getOrder().put(book,observableValue.getValue());
                                }
                                else{
                                    order.getOrder().remove(book);
                                }
                                if(t1>integer){
                                    order.setSum(order.getSum()+book.getCost());
                                }else{
                                    order.setSum(order.getSum()-book.getCost());
                                }
                                System.out.println(order.getOrder());
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
    }

    private void addNumToDTable() {
        Callback<TableColumn<Drink, Void>, TableCell<Drink, Void>> cellFactory = new Callback<TableColumn<Drink, Void>, TableCell<Drink, Void>>() {
            @Override
            public TableCell<Drink, Void> call(final TableColumn<Drink, Void> param) {
                final TableCell<Drink, Void> cell = new TableCell<Drink, Void>() {

                    private final Spinner<Integer> spn = new Spinner<Integer>(0,999,0);

                    {
                        spn.valueProperty().addListener(new ChangeListener<Integer>() {
                            @Override
                            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                                Drink drink = getTableView().getItems().get(getIndex());
                                if(t1 != 0){
                                    order.getOrder().put(drink,observableValue.getValue());
                                }
                                else{
                                    order.getOrder().remove(drink);
                                }
                                if(t1>integer){
                                    order.setSum(order.getSum()+drink.getCost());
                                }else{
                                    order.setSum(order.getSum()-drink.getCost());
                                }
                                System.out.println(order.getOrder());
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
        dNum.setCellFactory(cellFactory);
    }
    public void onConfirm() {
        if(!order.getOrder().isEmpty()) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Xác nhận?");
            dialog.setContentText("Nhập tên khách hàng:");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                String cName = result.get();
                if (!cName.isEmpty()) {
                    addNumToDTable();
                    addNumToBTable();
                    order.setCustomer(cName);
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String time = dateFormat.format(now);
                    order.setTime(time);
                    listO.add(order);
                    presentID++;
                    initOrder();
                }else {
                    Alert noCustomer = new Alert(Alert.AlertType.ERROR, "Không nhập vào tên khách hàng", ButtonType.OK);
                    noCustomer.showAndWait();
                }
            }
        }else{
            Alert noProduct = new Alert(Alert.AlertType.ERROR, "Chưa chọn sản phẩm nào", ButtonType.OK);
            noProduct.showAndWait();
        }
    }
    public void createEvent(){
        if(!eNameInput.getText().isEmpty()&&!eCostInput.getText().isEmpty()&&eDateInput.getValue() !=null){
            if(existedDate.contains(eDateInput.getValue().toString())){
                Alert coincidence = new Alert(Alert.AlertType.ERROR, "Đã có event tổ chức hôm đó", ButtonType.OK);
                coincidence.showAndWait();
            }else {
                int cost = Integer.parseInt(eCostInput.getText());
                String name = eNameInput.getText();
                String date = eDateInput.getValue().toString();
                DB.insertEvent(presentEventID,name,cost,date);
                updateEvent();
            }
        }else{
            Alert insufficientInfo = new Alert(Alert.AlertType.ERROR, "Xin nhập đầy đủ thông tin", ButtonType.OK);
            insufficientInfo.showAndWait();
        }

    }

    public void drink() {
        dPane.setVisible(true);
        bPane.setVisible(false);
        oPane.setVisible(false);
        oPane1.setVisible(false);
        ePane.setVisible(false);
    }

    public void books() {
        bPane.setVisible(true);
        dPane.setVisible(false);
        oPane.setVisible(false);
        oPane1.setVisible(false);
        ePane.setVisible(false);
    }
    public void pOrder() {
        oPane.setVisible(true);
        dPane.setVisible(false);
        bPane.setVisible(false);
        oPane1.setVisible(false);
        ePane.setVisible(false);
    }
    public void dOrder() {
        oPane.setVisible(false);
        dPane.setVisible(false);
        bPane.setVisible(false);
        oPane1.setVisible(true);
        ePane.setVisible(false);
    }
    public void event() {
        oPane.setVisible(false);
        dPane.setVisible(false);
        bPane.setVisible(false);
        oPane1.setVisible(false);
        ePane.setVisible(true);
    }
    public void updateDrink(){
        try {
            listD = DB.getDrink();
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        dTable.setItems(listD);
    }
    public void updateBooks(){
        try {
            listB = DB.getBook();
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        bTable.setItems(listB);
    }
    public void updateDO(){
        try {
            listDO = DB.getDoneOrder();
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        oTable1.setItems(listDO);
    }
    public void updateEvent(){
        try {
            listE = DB.getEvent();
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        eTable.setItems(listE);
    }
}