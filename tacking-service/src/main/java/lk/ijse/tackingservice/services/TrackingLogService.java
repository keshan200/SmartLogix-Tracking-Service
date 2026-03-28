package lk.ijse.tackingservice.services;



import lk.ijse.tackingservice.dto.TrackingLogDTO;
import lk.ijse.tackingservice.dto.TrackingRequest;

import java.util.List;

public interface TrackingLogService {
    int addTrackingLog(TrackingLogDTO trackingLogDTO);

    List<TrackingLogDTO> getTrackingLogs(String orderCode);

     void initTracking(TrackingRequest request);
}