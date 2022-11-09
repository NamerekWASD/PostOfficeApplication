package com.namerek.core.PresentationLayer.Controllers;

import com.namerek.core.BusinessLogic.Interfaces.IAddressService;
import com.namerek.core.BusinessLogic.Interfaces.IClientService;
import com.namerek.core.BusinessLogic.Interfaces.IOrderService;
import com.namerek.core.BusinessLogic.Interfaces.IPostOfficeService;
import com.namerek.core.BusinessLogic.Services.AddressService;
import com.namerek.core.BusinessLogic.Services.ClientService;
import com.namerek.core.BusinessLogic.Services.OrderService;
import com.namerek.core.BusinessLogic.Services.PostOfficeService;
import com.namerek.core.DTOs.*;
import com.namerek.core.Helpers.Utils.PackageProperties;
import com.namerek.core.Mappers.ModelMapperConverter;
import com.namerek.core.Models.*;
import com.namerek.core.PresentationLayer.View;
import com.namerek.core.PresentationLayer.ViewSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class OrderController implements Initializable {
    public TextField senderPhoneNumber = new TextField();
    public TextField senderName = new TextField();
    public TextField receiverName = new TextField();
    public TextField senderLastName = new TextField();
    public TextField receiverLastName = new TextField();
    public TextField receiverPhoneNumber = new TextField();
    public TextField senderEDRPOU = new TextField();
    public TextField receiverEDRPOU = new TextField();
    public ComboBox<String> senderStatus = new ComboBox<>();
    public ComboBox<String> receiverStatus = new ComboBox<>();

    public TextField countryD = new TextField();
    public TextField cityD = new TextField();
    public TextField streetD = new TextField();
    public TextField postalCodeD = new TextField();
    public TextField buildingD = new TextField();
    public CheckBox isPostOfficeD = new CheckBox();
    public ComboBox<String> postOfficeNumberD = new ComboBox<>();

    public TextField countryDP = new TextField();
    public TextField cityDP = new TextField();
    public TextField streetDP = new TextField();
    public TextField postalCodeDP = new TextField();
    public TextField buildingDP = new TextField();
    public CheckBox isPostOfficeDP = new CheckBox();
    public ComboBox<String> postOfficeNumberDP = new ComboBox<>();


    public TextField weight;
    public TextField width;
    public TextField length;
    public TextField category;
    public TextField height;
    public TextField description;
    public TextField evaluativeCost;
    public TextField content;

    public TableColumn<PackageModel, String> packageSize = new TableColumn<>("size");
    public TableColumn<PackageModel, Double> packageWeight = new TableColumn<>("weight");
    public TableColumn<PackageModel, String> packageCategory = new TableColumn<>("category");
    public TableColumn<PackageModel, String> packageContent = new TableColumn<>("content");
    public TableColumn<PackageModel, String> packageDescription = new TableColumn<>("description");
    public TableColumn<PackageModel, Double> packageEvaluativeCost = new TableColumn<>("evaluativeCost");
    public TableView<PackageModel> packages = new TableView<>();

    public TableColumn<PackageModel, Integer> packageWidth = new TableColumn<>("width");
    public TableColumn<PackageModel, Integer> packageLength = new TableColumn<>("length");
    public TableColumn<PackageModel, Integer> packageHeight = new TableColumn<>("height");
    public TableView<PackageProperties> packageSizeTable = new TableView<>();

    public Button send;
    public Button addOrder;
    public Button showDetails;

    public TableColumn<LightOrderModel, Long> orderTrackNumber = new TableColumn<>("trackNumber");
    public TableColumn<LightOrderModel, Long> orderSender = new TableColumn<>("sender");
    public TableColumn<LightOrderModel, Long> orderReceiver = new TableColumn<>("receiver");
    public TableColumn<LightOrderModel, String> orderDeparturePoint = new TableColumn<>("departurePoint");
    public TableColumn<LightOrderModel, String> orderDestination = new TableColumn<>("destination");
    public TableColumn<LightOrderModel, String> orderStatus = new TableColumn<>("status");
    public TableColumn<LightOrderModel, Date> orderDateOfCreation = new TableColumn<>("dateOfCreation");
    public TableColumn<LightOrderModel, String> orderCell = new TableColumn<>("cell");
    public TableView<LightOrderModel> orderTable = new TableView<>();
    public Button createNewReceiver;
    public Button createNewSender;
    public Button findSender;
    public Button findReceiver;

    public TextField trackNumber = new TextField();
    public TextField orderCost = new TextField();
    public ComboBox<String> orderStatusComboBox = new ComboBox<>();
    public Text log = new Text();

    private static OrderModel currentOrder = new OrderModel();


    private static IPostOfficeService postOfficeService;
    private static IAddressService addressService;
    private static IOrderService orderService;
    private static IClientService clientService;
    private final static ModelMapperConverter<OrderDTO, OrderModel> orderMapper =
            new ModelMapperConverter<>(OrderDTO.class, OrderModel.class);
    private final static ModelMapperConverter<AddressDTO, AddressModel> addressMapper =
            new ModelMapperConverter<>(AddressDTO.class, AddressModel.class);
    private final static ModelMapperConverter<PostOfficeDTO, PostOfficeModel> postOfficeMapper =
            new ModelMapperConverter<>(PostOfficeDTO.class, PostOfficeModel.class);
    private final static ModelMapperConverter<ClientDTO, ClientModel> clientMapper =
            new ModelMapperConverter<>(ClientDTO.class, ClientModel.class);
    private final static ModelMapperConverter<PackageDTO, PackageModel> packageMapper =
            new ModelMapperConverter<>(PackageDTO.class, PackageModel.class);
    private final static ModelMapperConverter<LightOrderDTO, LightOrderModel> lightOrderMapper =
            new ModelMapperConverter<>(LightOrderDTO.class, LightOrderModel.class);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (postOfficeService == null) {
            postOfficeService = new PostOfficeService();
        }
        if (orderService == null) {
            orderService = new OrderService();
        }
        if (addressService == null) {
            addressService = new AddressService();
        }
        if (clientService == null) {
            clientService = new ClientService();
        }

        orderTrackNumber.setCellValueFactory(new PropertyValueFactory<>("trackNumber"));
        orderSender.setCellValueFactory(new PropertyValueFactory<>("sender"));
        orderReceiver.setCellValueFactory(new PropertyValueFactory<>("receiver"));
        orderDeparturePoint.setCellValueFactory(new PropertyValueFactory<>("departurePoint"));
        orderDestination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        orderStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        orderDateOfCreation.setCellValueFactory(new PropertyValueFactory<>("dateOfCreation"));
        orderCell.setCellValueFactory(new PropertyValueFactory<>("cell"));

        packageSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        packageWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        packageCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        packageContent.setCellValueFactory(new PropertyValueFactory<>("content"));
        packageDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        packageEvaluativeCost.setCellValueFactory(new PropertyValueFactory<>("evaluativeCost"));

        packageWidth.setCellValueFactory(new PropertyValueFactory<>("width"));
        packageLength.setCellValueFactory(new PropertyValueFactory<>("length"));
        packageHeight.setCellValueFactory(new PropertyValueFactory<>("height"));

        orderTable.setItems(
                FXCollections.observableArrayList(
                        lightOrderMapper.toBList(orderService.getLightOrders())));
        if(currentOrder != null &&
                currentOrder.getTrackNumber() != 0 &&
                ViewSwitcher.getCurrentView() != View.SHOW_ORDER){
            bindAll();
        }
    }

    public void sendOrder(ActionEvent actionEvent) {
        OrderModel order = collectData();

        List<PackageModel> packages = this.packages.getItems();

        orderTrackNumber.setText(String.valueOf(orderService.addOrder(orderMapper.toA(order), packageMapper.toAList(packages), packageSizeTable.getItems())));

        ViewSwitcher.switchTo(View.SHOW_ORDER);
    }

    private OrderModel collectData() {
        OrderModel order = new OrderModel();

        AddressModel departurePoint = new AddressModel(
                countryDP.getText(),
                cityDP.getText(),
                streetDP.getText(),
                buildingDP.getText(),
                postalCodeDP.getText(),
                isPostOfficeDP.isSelected());



        departurePoint = addressMapper.toB(
                addressService.checkIfAddressExist(
                        addressMapper.toA(departurePoint)));

        AddressModel destination = new AddressModel(
                countryD.getText(),
                cityD.getText(),
                streetD.getText(),
                buildingD.getText(),
                postalCodeD.getText(),
                isPostOfficeD.isSelected());

        destination = addressMapper.toB(
                addressService.checkIfAddressExist(
                        addressMapper.toA(destination)));

        order.setDeparturePoint(departurePoint);
        order.setDestination(destination);

        ClientModel sender = editSender();
        ClientModel receiver = editReceiver();

        order.setSender(sender);
        order.setReceiver(receiver);
        return order;
    }

    public void addPackageToTable(ActionEvent actionEvent) {

        ObservableList<PackageModel> packages = FXCollections.observableArrayList();
        if(this.packages.getItems().size() != 0){
            packages = this.packages.getItems();
        }
        PackageModel package_ = new PackageModel();
        package_.setWeight(Double.parseDouble(weight.getText()));
        package_.setCategory(category.getText());
        package_.setDescription(description.getText());
        package_.setContent(content.getText());
        package_.setEvaluativeCost(Double.parseDouble(evaluativeCost.getText()));
        packages.add(package_);
        this.packages.setItems(packages);

        ObservableList<PackageProperties> properties = FXCollections.observableArrayList();
        if(this.packageSizeTable.getItems().size() != 0){
            properties = this.packageSizeTable.getItems();
        }
        PackageProperties property = new PackageProperties();
        property.setWidth(Integer.parseInt(width.getText()));
        property.setLength(Integer.parseInt(length.getText()));
        property.setHeight(Integer.parseInt(height.getText()));
        properties.add(property);
        this.packageSizeTable.setItems(properties);
    }



    public void findPostOfficeDP(ActionEvent event) {
        bindDeparturePoint();
    }

    public void findPostOfficeD(ActionEvent event) {
        bindDestination();
    }
    private boolean bindDeparturePoint() {

        if(postOfficeNumberDP.getValue() == null ||
                postOfficeNumberDP.getValue().length() == 0) {
            log.setText("Type office number first.");
        }
        AddressModel postOfficeAddress = postOfficeMapper
                .toB(postOfficeService.getByNumber(
                        Long.parseLong(postOfficeNumberDP.getValue()))).getAddress();

        if(postOfficeAddress == null){
            log.setText("Address not exist.");
            return false;
        }
        countryDP.setText(postOfficeAddress.getCountry());
        cityDP.setText(postOfficeAddress.getCity());
        streetDP.setText(postOfficeAddress.getStreet());
        buildingDP.setText(postOfficeAddress.getBuilding());
        postalCodeDP.setText(postOfficeAddress.getPostalCode());
        isPostOfficeDP.setSelected(true);
        return true;
    }

    private boolean bindDestination() {
        if(postOfficeNumberD.getValue() == null || 
                postOfficeNumberD.getValue().length() == 0) {
            log.setText("Type office number first.");
        }
        AddressModel postOfficeAddress = postOfficeMapper
                .toB(postOfficeService.getByNumber(
                        Long.parseLong(postOfficeNumberD.getValue()))).getAddress();
        
        if(postOfficeAddress == null){
            log.setText("Address not exist.");
            return false;
        }
        
        countryD.setText(postOfficeAddress.getCountry());
        cityD.setText(postOfficeAddress.getCity());
        streetD.setText(postOfficeAddress.getStreet());
        buildingD.setText(postOfficeAddress.getBuilding());
        postalCodeD.setText(postOfficeAddress.getPostalCode());
        isPostOfficeD.setSelected(true);
        return true;
    }

    public void getPostOffices(Event event) {
        ObservableList<String> officeNumbers = FXCollections.observableArrayList();
        for (var office :
                postOfficeService.getPostOffices()) {
            officeNumbers.add(String.valueOf(office.getOfficeNumber()));
        }
        postOfficeNumberD.setItems(officeNumbers);
        postOfficeNumberDP.setItems(officeNumbers);
    }
    
    public void tryFindFromDBReceiver(ActionEvent event) {
        if(bindReceiver()){
            setDisableEditReceiver(false);
        }
    }
    public void tryFindFromDBSender(ActionEvent event) {
        if(bindSender()){
            setDisableEditSender(false);
        }
    }

    private boolean bindReceiver() {
        if(receiverPhoneNumber.getText() == null ||
                receiverPhoneNumber.getText().length() == 0) {
            log.setText("Type phone number first");
            return false;
        }
        
        ClientModel clientModel = clientMapper.toB(clientService
                .getById(Long.parseLong(receiverPhoneNumber.getText())));

        if(clientModel == null) {
            log.setText("Client not found");
            return false;
        }
        receiverName.setText(clientModel.getName());
        receiverLastName.setText(clientModel.getLastName());
        receiverStatus.setValue(clientModel.getPersonalStatus());
        receiverEDRPOU.setText(String.valueOf(clientModel.getEDRPOU()));
        return true;
    }


    private boolean bindSender() {
        if(senderPhoneNumber.getText() == null || 
                senderPhoneNumber.getText().length() == 0) {
            log.setText("Type phone number first");
            return false;
        }
        ClientModel clientModel = clientMapper.toB(clientService
                .getById(Long.parseLong(senderPhoneNumber.getText())));

        if(clientModel == null) {
            log.setText("Client not found");
            return false;
        }

        senderName.setText(clientModel.getName());
        senderLastName.setText(clientModel.getLastName());
        senderStatus.setValue(clientModel.getPersonalStatus());
        senderEDRPOU.setText(String.valueOf(clientModel.getEDRPOU()));
        return true;
    }

    public void createNewR(ActionEvent event) {
        setDisableEditReceiver(false);
    }

    public void createNewS(ActionEvent event) {
        setDisableEditSender(false);
    }

    private void setDisableEditReceiver(boolean flag){
        receiverName.setDisable(flag);
        receiverLastName.setDisable(flag);
        receiverStatus.setDisable(flag);
        receiverEDRPOU.setDisable(flag);
        createNewReceiver.setDisable(!flag);
    }
    private void setDisableEditSender(boolean flag){
        senderName.setDisable(flag);
        senderLastName.setDisable(flag);
        senderStatus.setDisable(flag);
        senderEDRPOU.setDisable(flag);
        createNewSender.setDisable(!flag);
    }
    private void bindAll(){
        senderPhoneNumber.setText(String.valueOf(currentOrder.getSender().getPhoneNumber()));
        bindSender();

        receiverPhoneNumber.setText(String.valueOf(currentOrder.getReceiver().getPhoneNumber()));
        bindReceiver();

        AddressModel departurePoint = currentOrder.getDeparturePoint();
        if(departurePoint.isPostOffice()){
            postOfficeNumberDP.setValue(String.valueOf(
                    postOfficeService.getByAddress(
                            departurePoint.getId()).getOfficeNumber()));
            isPostOfficeDP.setSelected(true);
        }
        bindDeparturePoint();

        AddressModel destination = currentOrder.getDestination();
        if(destination.isPostOffice()){
            postOfficeNumberD.setValue(String.valueOf(
                    postOfficeService.getByAddress(
                            destination.getId()).getOfficeNumber()));
            isPostOfficeD.setSelected(true);
        }
        bindDestination();

        trackNumber.setText(String.valueOf(currentOrder.getTrackNumber()));
        orderCost.setText(String.valueOf(currentOrder.getCost()));
        orderStatusComboBox.setValue(String.valueOf(currentOrder.getStatus()));

        ObservableList<PackageModel> packages = FXCollections.observableArrayList(
                packageMapper.toBList(
                        orderService.getPackages(currentOrder.getTrackNumber())));

        this.packages.setItems(packages);
    }
    @FXML
    protected void showCreateOrderDialog(ActionEvent event) {
        ViewSwitcher.switchTo(View.CREATE_ORDER);
    }

    @FXML
    public void showOrderDetailsDialog(ActionEvent actionEvent) {
        if(orderTable.getSelectionModel() == null){
            log.setText("Select order first!");
            return;
        }
        long trackNumber = orderTable.getSelectionModel()
                .getSelectedItem().getTrackNumber();
        currentOrder = orderMapper.toB(orderService.getById(trackNumber));
        ViewSwitcher.switchTo(View.SHOW_ORDER);
    }

    public void saveOrder(ActionEvent event) {

        editCurrentOrder();

        orderService.editOrder(orderMapper.toA(currentOrder));
        orderService.editPackages(packageMapper.toAList(packages.getItems()));
        ViewSwitcher.switchTo(View.MAIN);
    }

    private void editCurrentOrder() {
        AddressModel departurePoint = editDeparturePoint();
        currentOrder.setDeparturePoint(departurePoint);

        AddressModel destination = editDestination();
        currentOrder.setDestination(destination);

        ClientModel sender = editSender();
        currentOrder.setSender(sender);

        ClientModel receiver = editReceiver();
        currentOrder.setReceiver(receiver);

        currentOrder.setStatus(orderStatus.getText());
    }

    private ClientModel editReceiver() {
        return new ClientModel(
                Long.parseLong(receiverPhoneNumber.getText()),
                receiverName.getText(),
                receiverLastName.getText(),
                receiverStatus.getValue(),
                Long.parseLong(receiverEDRPOU.getText())
        );
    }

    private ClientModel editSender() {
        return new ClientModel(
                Long.parseLong(senderPhoneNumber.getText()),
                senderName.getText(),
                senderLastName.getText(),
                senderStatus.getValue(),
                Long.parseLong(senderEDRPOU.getText())
        );
    }

    private AddressModel editDestination() {
        AddressModel destination = new AddressModel(currentOrder.getDestination().getId(),
                countryD.getText(),
                cityD.getText(),
                streetD.getText(),
                buildingD.getText(),
                postalCodeD.getText(),
                isPostOfficeD.isSelected());
        destination = addressMapper.toB(
                addressService.checkIfAddressExist(
                        addressMapper.toA(destination)));
        return destination;
    }

    private AddressModel editDeparturePoint() {
        AddressModel departurePoint = new AddressModel(currentOrder.getDeparturePoint().getId(),
                countryDP.getText(),
                cityDP.getText(),
                streetDP.getText(),
                buildingDP.getText(),
                postalCodeDP.getText(),
                isPostOfficeDP.isSelected());
        departurePoint = addressMapper.toB(
                addressService.checkIfAddressExist(
                        addressMapper.toA(departurePoint)));
        return departurePoint;
    }

    public void backToMain(ActionEvent event) {
        ViewSwitcher.switchTo(View.MAIN);
    }

    public void deleteOrder(ActionEvent event) {
        orderService.deleteOrder(orderMapper.toA(currentOrder));
        ViewSwitcher.switchTo(View.MAIN);
    }
}
