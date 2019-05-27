package org.nora.modules.system.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RoleSelectMenuTreeDto {

    private String title;
    private String value;
    private String key;
    private String parentId;
    private List<RoleSelectMenuTreeDto> children;
}
