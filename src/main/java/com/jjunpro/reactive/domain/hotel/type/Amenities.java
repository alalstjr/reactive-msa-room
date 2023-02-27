package com.jjunpro.reactive.domain.hotel.type;

import com.jjunpro.reactive.domain.config.type.EnumModel;

public enum Amenities implements EnumModel {
      PARKING("주차기능")
    , BREAKFAST_OPERATION("조식운영 (뷔페)")
    , WI_FI("와이파이")
    , SMOKING_ZONE("흡연구역")
    , NON_SMOKING_ZONE("객실금연")
    , BAR("바")
    , BANQUET_HALL("연회장")
    , FITNESS_CENTER("피트니스")
    , BUFFET("뷔페")
    , PAID_LAUNDRY("유료세탁")
    , COFFEE_SHOP("커피숍")
    , BUSINESS_CENTER("비즈니스")
    , LUGGAGE_STORAGE("수화물보관")
    ;

    private final String serviceName;

    Amenities(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return serviceName;
    }
}
