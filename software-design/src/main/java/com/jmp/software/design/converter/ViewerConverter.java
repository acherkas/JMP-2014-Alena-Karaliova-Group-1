package com.jmp.software.design.converter;

import com.jmp.software.design.model.Viewer;
import com.jmp.software.design.vo.ViewerVO;

public class ViewerConverter {

    public Viewer convertToModel(ViewerVO viewerVo) {
        return new Viewer(viewerVo.getFirstName(), viewerVo.getLastName());
    }

    public ViewerVO convertToVO(Viewer viewer) {
        return new ViewerVO(viewer.getFirstName(), viewer.getLastName());
    }
}
