package lk.ijse.tackingservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "tracking_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackingLog {

    @Id
    private String id;


    private String orderCode;

    private String currentLocation;

    private String status;

    private LocalDateTime timestamp = LocalDateTime.now();

    private Long updatedBy;

    private String remarks;
}