package com.spharos.pointapp.coupon.dto;

import com.spharos.pointapp.partner.domain.Partner;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CouponGetDto {
    private String couponName;
    private String couponDesc;
    private Long partnerId;
    private String couponNum;
    private String couponType;
    private Integer couponValue;
}