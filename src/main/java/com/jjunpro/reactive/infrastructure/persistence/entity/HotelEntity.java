package com.jjunpro.reactive.infrastructure.persistence.entity;

import com.jjunpro.reactive.domain.hotel.Hotel;
import com.jjunpro.reactive.domain.hotel.type.Amenities;
import com.jjunpro.reactive.domain.room.Room;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "hotels")
public class HotelEntity {

    @Id
    private String          id;
    private String          name;
    private String          phoneNumber;
    private String          location;
    private List<Room>      rooms;
    private List<Amenities> amenities;
    private String          userId;
    private LocalDateTime   createdDate;
    private LocalDateTime   modifiedDate;

    public Hotel toHotel() {
        return Hotel
            .builder()
            .id(id)
            .name(name)
            .phoneNumber(phoneNumber)
            .location(location)
            .rooms(rooms)
            .amenities(amenities)
            .userId(userId)
            .createdDate(createdDate)
            .modifiedDate(modifiedDate)
            .build();
    }
}
