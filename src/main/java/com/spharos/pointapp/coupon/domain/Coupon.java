package com.spharos.pointapp.coupon.domain;

import jakarta.persistence.*;
import lombok.*;
import com.spharos.pointapp.partner.domain.Partner;
import org.hibernate.annotations.Cascade;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45, name = "coupon_name")
    private String couponName;

    @Column(nullable = false, length = 300, name = "coupon_desc")
    private String couponDesc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partner_id")
    private Partner partner;

    @Column(nullable = false, length = 100, name = "coupon_num")
    private String couponNum;

    @Column(nullable = false)
    @Convert(converter = CouponTypeConverter.class)
    private CouponType couponType;

    @Column(nullable = false, name = "coupon_value")
    private Integer couponValue; // tinyint

}