package lk.ijse.tackingservice.controller;


import lk.ijse.tackingservice.dto.ResponseDTO;
import lk.ijse.tackingservice.dto.TrackingLogDTO;
import lk.ijse.tackingservice.dto.TrackingRequest;
import lk.ijse.tackingservice.services.TrackingLogService;
import lk.ijse.tackingservice.util.VarList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/v1/tracking")
@CrossOrigin(origins = "*")
public class TrackingLogController {

    private final TrackingLogService trackingLogService;

    public TrackingLogController(TrackingLogService trackingLogService) {
        this.trackingLogService = trackingLogService;
    }



    @PostMapping( "/init")
    public ResponseEntity<String> initializeTracking(@RequestBody TrackingRequest request) {
        try {
            trackingLogService.initTracking(request);
            System.out.println("REQUEST: " + request);
            return ResponseEntity.ok("Tracking record created in MongoDB");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error initializing tracking: " + e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> addTrackingLog(@RequestBody TrackingLogDTO trackingLogDTO) {
        int result = trackingLogService.addTrackingLog(trackingLogDTO);

        return switch (result) {
            case VarList.Created -> ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDTO(VarList.Created, "Tracking log added", trackingLogDTO));
            default -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, "Error adding tracking log", null));
        };
    }

    @GetMapping("/logs/{orderCode}")
    public ResponseEntity<List<TrackingLogDTO>> getTrackingLogs(@PathVariable String orderCode) {
        List<TrackingLogDTO> logs = trackingLogService.getTrackingLogs(orderCode);

        if (logs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(logs);
    }
}