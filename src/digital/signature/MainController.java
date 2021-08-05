/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digital.signature;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
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
    @FXML
    private ToggleGroup pick;
    @FXML
    private JFXButton button;
    @FXML
    private Text errorMsg;
    @FXML
    private AnchorPane panee;
    @FXML
    private TextField editPath1;
    @FXML
    private AnchorPane panee1;
    @FXML
    private TextField editPath11;
    @FXML
    private JFXRadioButton signRadioBtn;
    
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
        
        if(signRadioBtn.isSelected()){
        GenSig sign = new GenSig();
        File file = new File(editPath.getText());
        
        
        System.out.println(file.getAbsolutePath()+" - "+file.getName());
        if(!sign.GenSig(file)){
            errorMsg.setText("Failed to Sign");
        }else{
            errorMsg.setText("Succesfully Sign");
        }
        
        }else{
            
         File file = new File(editPath.getText());
         File key = new File(editPath1.getText());
         File signature = new File(editPath11.getText());
        
        VerSig ver = new VerSig();
        if(!ver.verify(file, key, signature)){
            errorMsg.setText("Failed to Verify");
        }else{
            errorMsg.setText("Succesfully Verified");
        }
        }
       
    }

    @FXML
    private void radioSign(MouseEvent event) {
        panee.setVisible(false);
        panee1.setVisible(false);
        button.setText("Sign Result");
    }

    @FXML
    private void radioVerify(MouseEvent event) {
         panee.setVisible(true);
        panee1.setVisible(true);
         button.setText("Verify Result");
    }

    @FXML
    private void chooseKey(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File selectedDirectory = fileChooser.showOpenDialog(stage);

        if(selectedDirectory == null){
             //No Directory selected
        }else{
             editPath1.setText(selectedDirectory.getAbsolutePath());
        }
    }

    @FXML
    private void chooseSignature(ActionEvent event) {
         FileChooser fileChooser = new FileChooser();
        File selectedDirectory = fileChooser.showOpenDialog(stage);

        if(selectedDirectory == null){
             //No Directory selected
        }else{
             editPath11.setText(selectedDirectory.getAbsolutePath());
        }
    }
    
}
