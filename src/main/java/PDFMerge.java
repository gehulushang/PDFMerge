import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfDocumentBase;

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;



public class PDFMerge extends Application{

    static  InputStream[] streams = new FileInputStream[2];
    static ArrayList<String> PDFPath = new ArrayList<String>() ;
    static String outputFile = "";
    @Override
    public void start(final Stage primaryStage) throws Exception{
        Button btn = new Button("选择文件");
        Button button = new Button("输出到");
        Button button1 = new Button("确认");

        final Label label = new Label();
        final Label lab= new Label();

        Image image = new Image("file:D://java学习//JAVA项目//PDFMerge//image//pdf (2).png");
        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(btn,label,button,button1);
        root.setTopAnchor(label ,30.0);
        root.setTopAnchor(label ,30.0);
        root.setLeftAnchor(button,100.0);
        root.setLeftAnchor(button1,200.0);
        final FileChooser fileChooser = new FileChooser();

        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
                File file = fileChooser.showOpenDialog(primaryStage);

                String tempPath = file.getAbsolutePath() ;
                String showText = label.getText()==""?tempPath:label.getText()+"\n"+tempPath;
                label.setText(showText);
                label.setWrapText(true);
                PDFPath.add(tempPath);
                System.out.println(tempPath);
                if(file != null){}

            }
        });
        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
                File file = fileChooser.showSaveDialog(primaryStage);
                String tempPath = file.getAbsolutePath() ;
                label.setText(tempPath);
                outputFile = file.getPath();


            }
        });

        button1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                for(int i = 0;i<PDFPath.size();i++) {
             //       String temp = PDFPath.get(i);
                    try {
                        streams[i] = new FileInputStream(PDFPath.get(i));
                    }catch (FileNotFoundException e){
                        e.fillInStackTrace();
                    }
                }
                PdfDocumentBase doc = PdfDocument.mergeFiles(streams);
                //保存文档
                doc.save(outputFile);
                doc.close();
                System.out.println("Success");
                PDFPath.clear();
            }
        });
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("PDFMerge");
       // primaryStage.getIcons().add(new Image("/image/PDF.png"));


        primaryStage.setScene(scene);
        primaryStage.getIcons().add(image);
        primaryStage.show();
    }
    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
