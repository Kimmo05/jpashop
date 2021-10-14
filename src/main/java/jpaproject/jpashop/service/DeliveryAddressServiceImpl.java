package jpaproject.jpashop.service;


import jpaproject.jpashop.domain.DeliveryAddress;
import jpaproject.jpashop.domain.Member;
import jpaproject.jpashop.dto.AddressDto;
import jpaproject.jpashop.exception.AddressNotFoundException;
import jpaproject.jpashop.exception.LoginIdNotFoundException;
import jpaproject.jpashop.repository.DeliveryAddressRepository;
import jpaproject.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryAddressServiceImpl implements DeliveryAddressService {
    private final DeliveryAddressRepository deliveryAddressRepository;
    private final MemberRepository memberRepository;

//    @Transactional
//    @Override
//    public void registerAddress(String loginId, AddressDto addressDto) {
//        Member findMember = memberRepository.findByloginId(loginId).orElseThrow(
//                () -> new LoginIdNotFoundException("해당하는 회원이 존재하지 않습니다")
//        );
//        DeliveryAddress deliveryAddress = new DeliveryAddress();
//
//        deliveryAddress.setDetailStreet(addressDto.getDetailStreet());
//        deliveryAddress.setStreet(addressDto.getStreet());
//        deliveryAddress.setZipcode(addressDto.getZipcode());
//        deliveryAddress.setMember(findMember);
//
//        deliveryAddressRepository.save(deliveryAddress);
//    }
//
//    @Override
//    public DeliveryAddress findAddressById(Long id) {
//        DeliveryAddress findDeliveryAddress = deliveryAddressRepository.findById(id).orElseThrow(
//                () -> new AddressNotFoundException("해당하는 주소가 없습니다")
//        );
//
//        return findDeliveryAddress;
//    }
//
//    @Override
//    public List<DeliveryAddress> getDeliveryAddressByLoginId(String loginId) {
//
//        return deliveryAddressRepository.findAllByMemberLoginId(loginId);
//    }
////    회원에게 등록된 주소 모두 표시
//
//    @Override
//    public void deleteAddressById(Long id) {
//        deliveryAddressRepository.deleteById(id);
//    }



}
