package org.nora.modules.system.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MenuTreeDto {

    private String guid;
    private String key;
    private String menuName;
    private String menuCode;
    private String parentId;
    private String icon;
    private List<MenuTreeDto> children;

}
