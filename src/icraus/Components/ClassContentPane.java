/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.Components;

import com.icraus.ide.ui.components.DraggableCanvasComponentEventHandler;
import icraus.Components.util.ClassDialogs;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 *
 * @author Shoka
 */
public class ClassContentPane extends VBox implements Selectable{

    @FXML
    private Label classNameLabel;
    @FXML
    private VBox headVBox;
    @FXML
    private VBox methodsVBox;
    @FXML
    private VBox fieldsVBox;
    private ClassComponent parent;

    public ClassContentPane(ClassComponent _parent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClassContentPane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.parent = _parent;
        initialize();
        setId("GUI" + parent.getUUID());
        String css = getClass().getResource("styleclass.css").toExternalForm();
        getStylesheets().add(css);

    }

    private void drawMethods() {
        methodsVBox.getChildren().clear();
        ObservableList<MethodComponent> lst = parent.getMethodsList();
        for (MethodComponent mc : lst) {
            methodsVBox.getChildren().add(mc.getLineLabel());
        }
    }

    private void createContextMenu() {
        ContextMenu menu = new ContextMenu();
        MenuItem addMethodItem = new MenuItem("Add Method");
        MenuItem removeMethodItem = new MenuItem("Remove Method");
        MenuItem addFieldItem = new MenuItem("Add Field");
        MenuItem removeFieldItem = new MenuItem("Remove Field");
        addMethodItem.setOnAction(e -> {
            Optional<MethodComponent> res = ClassDialogs.getAddMethodDialog().showAndWait();
            res.ifPresent(val -> {
                try {
                    ComponentsModel.getInstance().addMethodByUuid(getParentComponentUuid(), val);
                } catch (IllegalComponent ex) {
                    Logger.getLogger(ClassContentPane.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ComponentNotFoundException ex) {
                    Logger.getLogger(ClassContentPane.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            });
        });
        addFieldItem.setOnAction(e -> {
        });
        menu.getItems().add(addFieldItem);
        menu.getItems().add(removeFieldItem);
        menu.getItems().add(addMethodItem);
        menu.getItems().add(removeMethodItem);
        this.setOnContextMenuRequested(e -> {
            menu.show(this, Side.LEFT, e.getX(), e.getY());
        });
    }

    private void createMouseEvent() {
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if (e.getClickCount() == 2) {
                TextInputDialog d = new TextInputDialog(parent.getClassName().getValue());
                Optional<String> res = d.showAndWait();
                res.ifPresent(v -> {
                    parent.setClassName(v);
                });
            }
        });
    }

//    @FXML
    private void initialize() {
        parent.getMethodsList().addListener((Observable e) -> {
            drawMethods();
        });
        createContextMenu();
        createMouseEvent();
        this.addEventHandler(MouseEvent.DRAG_DETECTED, new DraggableCanvasComponentEventHandler());
        classNameLabel.textProperty().bindBidirectional(parent.getClassName());

    }

    @Override
    public String getParentComponentUuid() {
        return parent.getUUID();
    }
}
