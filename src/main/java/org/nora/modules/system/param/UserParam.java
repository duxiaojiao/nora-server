package org.nora.modules.system.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserParam {


    private String guid;
    private String empCode;
    private String empName;
    private String email;
    private String phone;
    private Boolean locked;
    private List<String> roleIds;

}
