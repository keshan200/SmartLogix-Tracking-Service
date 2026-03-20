package lk.ijse.tackingservice.services;



import lk.ijse.tackingservice.dto.TrackingLogDTO;

import java.util.List;

public interface TrackingLogService {
    int addTrackingLog(TrackingLogDTO trackingLogDTO);

    List<TrackingLogDTO> getTrackingLogs(String orderCode);
}