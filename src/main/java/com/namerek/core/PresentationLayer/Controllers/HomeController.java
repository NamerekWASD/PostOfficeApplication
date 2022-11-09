package com.namerek.core.PresentationLayer.Controllers;

import com.namerek.core.BusinessLogic.Interfaces.IEmployeeService;
import com.namerek.core.BusinessLogic.Services.EmployeeService;
import com.namerek.core.DTOs.EmployeeDTO;
import com.namerek.core.Mappers.ModelMapperConverter;
import com.namerek.core.Models.EmployeeModel;
import com.namerek.core.PresentationLayer.View;
import com.namerek.core.PresentationLayer.ViewSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class HomeController {

    public Text log;
    public TextField employee_id;
    public PasswordField employee_password;
    public Button login;

    private final IEmployeeService employeeService;
    private static final ModelMapperConverter<EmployeeDTO, EmployeeModel> mapper =
            new ModelMapperConverter<>(EmployeeDTO.class, EmployeeModel.class);

    public HomeController(){
        employeeService = new EmployeeService();
    }
    @FXML
    public void login(ActionEvent actionEvent) {
        try {
            EmployeeModel employee = mapper.toB(
                    employeeService.Login(
                            Long.parseLong(employee_id.getText()), employee_password.getText()));
        }catch (Exception exception){
            log.setText("Wrong id or password!");
            return;
        }
        ViewSwitcher.switchTo(View.MAIN);
    }
}