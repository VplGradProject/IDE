
package test;

import icraus.Components.Component;
import javafx.scene.input.MouseEvent;

public class DeclareVariable extends Item {
    
    private String type;
    private String name;
    private Boolean isArray = false;
    private int arrayLength;
            
    DeclareVariable(Component parent){
        super(parent);
        setText("Declare");
        setMinWidth(120);
        setMinHeight(40);
        getStyleClass().add("declareVariableStyle");
        
        setContextMenu(new ItemContextMenu(5, getUUID()));
        
        setOnMouseClicked((MouseEvent mouseEvent) -> {
            //FIXME add Handler
        });
    }  

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsArray(Boolean isArray) {
        this.isArray = isArray;
    }

    public void setArrayLength(int arrayLength) {
        this.arrayLength = arrayLength;
    }
    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Boolean getIsArray() {
        return isArray;
    }

    public int getArrayLength() {
        return arrayLength;
    }
    
}
