package org.nora.common.responses;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ITree<T> {

    private Integer id;

    private String name;

    private boolean isParent=false;

    private boolean open=true;

    private boolean checked=false;

    private List<T> children;

    public ITree() {
    }

}
