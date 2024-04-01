package crptapi;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class Document {
    @SerializedName("description")
    private Description description;
    @SerializedName("doc_id")
    private String docId;
    @SerializedName("doc_status")
    private String docStatus;
    @SerializedName("doc_type")
    private String docType;
    private boolean importRequest;
    @SerializedName("owner_inn")
    private String ownerInn;
    @SerializedName("participant_inn")
    private String participantInn;
    @SerializedName("producer_inn")
    private String producerInn;
    @SerializedName("production_date")
    private String productionDate;
    @SerializedName("production_type")
    private String productionType;
    @SerializedName("products")
    private List<Product> products;
    @SerializedName("reg_date")
    private String regDate;
    @SerializedName("reg_number")
    private String regNumber;

    @Setter
    @Getter
    @AllArgsConstructor
    @ToString
    static class Description {
        private String participantInn;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @ToString
    static class Product {
        @SerializedName("certificate_document")
        private String certificateDocument;
        @SerializedName("certificate_document_date")
        private String certificateDocumentDate;
        @SerializedName("certificate_document_number")
        private String certificateDocumentNumber;
        @SerializedName("owner_inn")
        private String ownerInn;
        @SerializedName("producer_inn")
        private String producerInn;
        @SerializedName("production_date")
        private String productionDate;
        @SerializedName("tnved_code")
        private String tnvedCode;
        @SerializedName("uit_code")
        private String uitCode;
        @SerializedName("uitu_code")
        private String uituCode;
    }
}