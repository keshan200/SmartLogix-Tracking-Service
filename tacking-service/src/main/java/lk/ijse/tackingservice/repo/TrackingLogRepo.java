package lk.ijse.tackingservice.repo;
import lk.ijse.tackingservice.entity.TrackingLog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TrackingLogRepo extends MongoRepository<TrackingLog, String> {

    List<TrackingLog> findByOrderCodeOrderByTimestampDesc(String orderCode);

    List<TrackingLog> findByOrderCode(String orderCode);

}