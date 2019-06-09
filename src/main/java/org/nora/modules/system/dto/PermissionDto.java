package org.nora.modules.system.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PermissionDto {

    private String name;
    private String permission;
    private String method;
    private String mapping;
}
