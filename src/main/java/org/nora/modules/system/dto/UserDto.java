package org.nora.modules.system.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserDto {

    private String guid;
    private String token;
    private String userName;
    private String empCode;
    private String empName;
    private String email;
    private String phone;
    private Boolean locked;
    private List<String> roleIds;
}
