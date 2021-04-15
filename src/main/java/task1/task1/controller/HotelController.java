package task1.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;
import task1.task1.entity.Hotel;
import task1.task1.repository.HotelRepository;

import javax.persistence.GeneratedValue;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;

    @PostMapping("/add")
    public String addHotel(@RequestBody Hotel hoteldto) {
        Hotel hotel = new Hotel();
        hotel.setName(hoteldto.getName());
        hotelRepository.save(hotel);
        return "Hotel qo'shildi";
    }

    @GetMapping("/hotels")
    public List<Hotel> getHotels() {
        return hotelRepository.findAll();
    }

    @GetMapping("/hotel/{id}")
    public Hotel getHotel(@PathVariable Integer id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (!optionalHotel.isPresent()) return null;
        return optionalHotel.get();
    }

    @PutMapping("/edit/{id}")
    public String editHotel(@PathVariable Integer id,@RequestBody Hotel hoteldto) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (!optionalHotel.isPresent()) return "Hotel ID not found";
        Hotel hotel = optionalHotel.get();
        hotel.setName(hoteldto.getName());
        hotelRepository.save(hotel);
        return "Hotel edited";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteHotel(@PathVariable Integer id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (!optionalHotel.isPresent()) return "Hotel ID not found";
        hotelRepository.deleteById(id);
        return "Hotel deleted";
    }
}
