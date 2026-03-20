package lk.ijse.tackingservice.services.impl;


import lk.ijse.tackingservice.dto.TrackingLogDTO;
import lk.ijse.tackingservice.entity.TrackingLog;
import lk.ijse.tackingservice.repo.TrackingLogRepo;
import lk.ijse.tackingservice.services.TrackingLogService;
import lk.ijse.tackingservice.util.VarList;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrackingLogServiceImpl implements TrackingLogService {

    private final TrackingLogRepo trackingLogRepo;
    private final ModelMapper modelMapper;
    private static final Logger logger = LoggerFactory.getLogger(TrackingLogServiceImpl.class);

    public TrackingLogServiceImpl(TrackingLogRepo trackingLogRepo, ModelMapper modelMapper) {
        this.trackingLogRepo = trackingLogRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public int addTrackingLog(TrackingLogDTO trackingLogDTO) {
        try {
            TrackingLog trackingLog = modelMapper.map(trackingLogDTO, TrackingLog.class);
            trackingLogRepo.save(trackingLog);
            return VarList.Created;
        } catch (Exception e) {
            logger.error("Error adding tracking log", e);
            return VarList.Internal_Server_Error;
        }
    }


    @Override
    public List<TrackingLogDTO> getTrackingLogs(String orderCode) {
        try {
            List<TrackingLog> logs = trackingLogRepo.findByOrderCodeOrderByTimestampDesc(orderCode);
            return logs.stream()
                    .map(log -> modelMapper.map(log, TrackingLogDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error fetching tracking logs", e);
            return List.of();
        }
    }
}