package com.spharos.pointapp.partner.domain;

import com.spharos.pointapp.coupon.domain.CodeValue;
import lombok.Getter;

@Getter
public enum PartnerName implements CodeValue {
    EMART("1", "이마트"),
    SSGDEPART("2", "신세계백화점"),
    SSGCOM("3","신세계닷컴"),
    EMARTEVERY("4", "이마트에브리데이"),
    SSGOUTLET("5", "신세게아울렛"),
    STARFIELD("6", "스타필드"),
    EMART24("7","이마트24"),
    STARBUCKS("8", "스타벅스"),
    CASAMIA("9", "까사미아"),
    SSGDUTYFREE("10", "신세계면세점"),
    SSGSHOPPING("11", "신세계쇼핑"),
    WM("12", "와인앤모어"),
    SSGFOOD("13", "신세계푸드"),
    JOSUN("14", "조선호텔리조트"),
    SMOOTHIEKING("15", "스무디킹"),
    SSGRESORT("16", "영랑호리조트"),
    GMARKET("17", "지마켓"),
    AUCTION("18", "옥션"),
    LANDERS("19", "SSG랜더스");

    private String code;
    private String value;

    PartnerName(String code, String value) {
        this.code = code;
        this.value = value;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return value;
    }
}