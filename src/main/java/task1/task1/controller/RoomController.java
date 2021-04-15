package task1.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import task1.task1.dto.RoomDTO;
import task1.task1.entity.Hotel;
import task1.task1.entity.Room;
import task1.task1.repository.HotelRepository;
import task1.task1.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    RoomRepository roomRepository;

    @PostMapping("/addRoom")
    public String addRoom(@RequestBody RoomDTO roomDTO) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDTO.getHotelId());
        if (!optionalHotel.isPresent()) return "Hotel ID not found";
        Room room = new Room();
        room.setFloor(roomDTO.getFloor());
        room.setNumber(room.getNumber());
        room.setSize(room.getSize());
        room.setHotel(optionalHotel.get());
        roomRepository.save(room);
        return "Room added";
    }

    @GetMapping("/getByHotel/{hotelId}")
    public Page<Room> getRoomByHotelId(@PathVariable Integer hotelId, @RequestParam int page) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(hotelId);
        if (!optionalHotel.isPresent()) return null;
        Pageable pageable = PageRequest.of(page, 3);
        Page<Room> allByHotelId = roomRepository.findAllByHotelId(hotelId, pageable);
        return allByHotelId;
    }

    @PutMapping("/editroom/{id}")
    public String editRoom(@RequestBody RoomDTO roomDTO, @PathVariable Integer id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (!optionalRoom.isPresent()) return "Room ID not found";
        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDTO.getHotelId());
        if (!optionalHotel.isPresent()) return "Hotel ID not found";
        Room room = optionalRoom.get();
        room.setNumber(roomDTO.getNumber());
        room.setFloor(roomDTO.getFloor());
        room.setSize(roomDTO.getSize());
        room.setHotel(optionalHotel.get());
        roomRepository.save(room);
        return "Room edited";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRoom(@PathVariable Integer id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (!optionalRoom.isPresent()) return "Room ID not found";
        roomRepository.deleteById(id);
        return "Room deleted";
    }

}
