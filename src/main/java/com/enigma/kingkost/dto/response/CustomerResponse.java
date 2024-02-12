package com.enigma.kingkost.dto.response;

import com.enigma.kingkost.entities.GenderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CustomerResponse {
    private String id;
    private String fullName;
    private String email;
    private GenderType genderTypeId;
    private String phoneNumber;
    private String address;

    private String profileImageName;
    private String profileImageType;
    private byte[] profileImageData;
}
