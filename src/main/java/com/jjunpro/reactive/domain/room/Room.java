package com.jjunpro.reactive.domain.room;

import lombok.Builder;
import lombok.EqualsAndHashCode;

@Builder
@EqualsAndHashCode
public class Room {

    private String id;
    private String name;
    private String phoneNumber;
    private String location;
    private String amenities;
}
