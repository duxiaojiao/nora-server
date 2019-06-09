package org.nora.modules.system.param;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MenuParam {

    private String guid;
    private String menuName;
    private String menuCode;
    private String parentId;
    private String router;
    private String icon;
    private String menuType;
    private List<String> permissions;
    private Integer sorter;
}
