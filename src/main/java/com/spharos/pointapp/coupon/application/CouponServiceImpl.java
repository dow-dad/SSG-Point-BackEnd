package com.spharos.pointapp.coupon.application;

import com.spharos.pointapp.coupon.domain.*;
import com.spharos.pointapp.coupon.dto.CouponCreateDto;
import com.spharos.pointapp.coupon.dto.CouponGetDto;
import com.spharos.pointapp.coupon.dto.CouponUpdateDto;
import com.spharos.pointapp.coupon.infrastructure.CouponListRepository;
import com.spharos.pointapp.coupon.infrastructure.CouponRepository;
import jakarta.persistence.Convert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final CouponListRepository couponListRepository;

//    쿠폰 생성
    @Override
    @Convert(converter = CouponTypeConverter.class)
    public void createCoupon(CouponCreateDto couponCreateDto) {
        CouponType couponType = new CouponTypeConverter().convertToEntityAttribute(couponCreateDto.getCouponType());
        couponRepository.save(Coupon.builder()
                .couponName(couponCreateDto.getCouponName())
                .couponDesc(couponCreateDto.getCouponDesc())
                .usePlace(couponCreateDto.getUsePlace())
                .couponNum(couponCreateDto.getCouponNum())
                .couponType(couponType)
                .couponValue(couponCreateDto.getCouponValue())
                .build());
    }

//    쿠폰 수정

    @Convert(converter = CouponTypeConverter.class)
    public void updateCoupon(CouponUpdateDto couponUpdateDto, Long couponId) {
        CouponType couponType = new CouponTypeConverter().convertToEntityAttribute(couponUpdateDto.getCouponType());
        couponRepository.save(Coupon.builder()
                .Id(couponId)
                .couponName(couponUpdateDto.getCouponName())
                .couponDesc(couponUpdateDto.getCouponDesc())
                .usePlace(couponUpdateDto.getUsePlace())
                .couponNum(couponUpdateDto.getCouponNum())
                .couponType(couponType)
                .couponValue(couponUpdateDto.getCouponValue())
                .build());
    }

//    쿠폰 전체 조회

    @Override
    public List<CouponGetDto> getCoupons() {
        List<Coupon> couponList = couponRepository.findAll();
        log.info("{}", couponList);
        List<CouponGetDto> couponGetDtoList = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        couponList.forEach(
                coupon -> couponGetDtoList.add(
                    mapper.map(coupon, CouponGetDto.class)
                )
        );
        return couponGetDtoList;
    }

//    사용자가 보유한 쿠폰 조회
@Override
@Convert(converter = CouponTypeConverter.class)
public List<CouponGetDto> getCouponByUser(Long userId) {
    List<CouponList> couponListList = couponListRepository.findByUserId(userId);
    List<CouponGetDto> couponGetDtoList = couponListList.stream().map(coupon -> {
//        Coupon coupon = couponListEntry.getCoupon();
        CouponType couponType = new CouponTypeConverter().convertToEntityAttribute(coupon.getCouponType().getCode());

        return CouponGetDto.builder()
                .couponName(coupon.getCouponName())
                .couponDesc(coupon.getCouponDesc())
                .usePlace(coupon.getUsePlace())
                .couponNum(coupon.getCouponNum())
                .couponType(couponType.getValue())
                .couponValue(coupon.getCouponValue())
                .build();
    }).toList();

    log.info("Coupon List is : {}", couponListList);
    return ;
}

//    쿠폰 삭제

    @Override
    public void deleteCoupon(Long couponId) {
        couponRepository.deleteById(couponId);
    }


}
