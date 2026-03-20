package lk.ijse.tackingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackingLogDTO {
    private String id;
    private String orderCode;
    private String currentLocation;
    private String status;
    private LocalDateTime timestamp;
    private Long updatedBy;
    private String remarks;
}