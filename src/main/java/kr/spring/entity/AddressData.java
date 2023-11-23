package kr.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AddressData {
//	지도에서 경도, 위도 가져오기
        private String roadAddress;
        private String jibunAddress;
        private String cityName;
        private String latitude;
        private String longitude;


}
