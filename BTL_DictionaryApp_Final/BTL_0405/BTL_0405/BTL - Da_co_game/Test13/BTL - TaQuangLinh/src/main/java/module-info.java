module com.example.demo7 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires jsapi;


    opens com.example.demo7 to javafx.fxml;
    exports com.example.demo7;
}