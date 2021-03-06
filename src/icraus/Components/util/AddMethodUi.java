/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.Components.util;

import icraus.Components.MethodComponent;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 *
 * @author Shoka
 */
public class AddMethodUi extends VBox{
    @FXML
    Button okButton;
    @FXML
    Button cancelButton;
    @FXML
    private TextField methodNameTextField;
    @FXML
    private TextField returnTypeTextField;
    @FXML
    private TextField parametersTextField;
    @FXML
    private ComboBox<String> accessTypeComboBox;
    MethodComponent method;
    Dialog parentDialog;
    public AddMethodUi(Dialog parent) {
        parentDialog = parent;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddMethodUi.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
    @FXML
    protected void initialize() {
        accessTypeComboBox.getItems().add("public");
        accessTypeComboBox.getItems().add("private");
        accessTypeComboBox.getItems().add("default");
        accessTypeComboBox.getItems().add("protected");
        parentDialog.getDialogPane().setContent(this);
        okButton.setOnAction(e-> {
            method = new MethodComponent(methodNameTextField.getText(), returnTypeTextField.getText(), accessTypeComboBox.getValue());
            
//TODO            String text = parametersTextField.getText();
//            String[] split = text.split(", ");
//            int l = split.length;
//            for(int i = 0; i < l; ++i){
//                String[] vars = split[i].split(" ");
//                method.addParameter(vars[0], vars[1]);
//                
//            }
            parentDialog.setResult(method);
            parentDialog.close();
//            
//        });
//        parentDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
//        cancelButton.setOnAction(e-> {
//            parentDialog.setResult(null);
//            parentDialog.hide();
//            
////            parentDialog.close();            
        });
        
    }

}
