package org.nora.modules.system.param;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RoleMenuParam {

    private String roleId;
    private List<String> menuIds;
}
