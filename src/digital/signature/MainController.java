/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digital.signature;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author Way4ward Adefuwa
 */
public class MainController implements Initializable {

    @FXML
    private TextField editPath;
    Stage stage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    stage = new Stage();   
    }    

    @FXML
    private void chooseFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
           new ExtensionFilter("Text Files", "*.txt", "*.pdf","*.doc","*.docx","*.ptt"));

        File selectedDirectory = fileChooser.showOpenDialog(stage);

        if(selectedDirectory == null){
             //No Directory selected
        }else{
             editPath.setText(selectedDirectory.getAbsolutePath());
        }
    }

    @FXML
    private void signDocument(ActionEvent event) {
    }
    
}
