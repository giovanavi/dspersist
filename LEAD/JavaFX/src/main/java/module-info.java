module com.dell.lead.javafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.dell.lead.javafx to javafx.fxml;
    exports com.dell.lead.javafx;
    exports com.dell.lead;
    opens com.dell.lead to javafx.fxml;
}