package org.nora.modules.system.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserDetailDto {

    private String guid;
    private String userName;
    private String empCode;
    private String empName;
    private String email;
    private String phone;
}
