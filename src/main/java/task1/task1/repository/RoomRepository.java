package task1.task1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import task1.task1.entity.Room;

public interface RoomRepository extends JpaRepository<Room,Integer> {
Page<Room> findAllByHotelId(Integer hotelId, Pageable pageable);
}
