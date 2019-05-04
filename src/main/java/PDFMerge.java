import java.io.*;
import java.util.*;

import com.spire.ms.lang.Event;
import com.spire.pdf.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



public class PDFMerge extends Application{

    static  InputStream[] streams = new FileInputStream[2];
    static ArrayList<String> PDFPath = new ArrayList<String>() ;
    static String outputFile = "";
    @Override
    public void start(final Stage primaryStage) throws Exception{
        Button btn = new Button("选择文件");
        Button button = new Button("输出到");

        final Label label = new Label();

        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(btn,label,button);
        root.setTopAnchor(label ,30.0);
        root.setLeftAnchor(button,100.0);
        final FileChooser fileChooser = new FileChooser();

        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                //fd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                File file = fileChooser.showOpenDialog(primaryStage);
                String tempPath = file.getAbsolutePath().toString() ;
                label.setText(tempPath);
                label.setWrapText(true);
                PDFPath.add(tempPath);
                System.out.println(tempPath);
                if(file != null){}

            }
        });
        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                File file = fileChooser.showSaveDialog(primaryStage);

                outputFile = file.getPath();
            }
        });

        //场景
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) throws IOException {
        launch(args);


    for(int i = 0;i<PDFPath.size();i++) {
        String temp = PDFPath.get(i);

        streams[i] = new FileInputStream(PDFPath.get(i));

    }

    PdfDocumentBase doc = PdfDocument.mergeFiles(streams);
    //保存文档
    doc.save(outputFile);
    doc.close();
        System.out.println("Success");
        PDFPath.clear();
 }


}
