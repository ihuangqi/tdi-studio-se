// ============================================================================
//
// Copyright (C) 2006-2023 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.core.ui.editor.subjobcontainer;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.talend.designer.core.ui.editor.nodes.ICrossPlatformEditPartViewer;
import org.talend.designer.core.ui.editor.nodes.ICrossPlatformFigure;

/**
 * DOC cmeng  class global comment. Detailled comment
 */
public interface ICrossPlatformEditPart {

    Object getCrossPlatformModel();

    void setCrossPlatformModel(Object model);

    ICrossPlatformEditPart getCrossPlatformParentPart();

    List getCrossPlatformChildren();

    ICrossPlatformEditPartViewer getCrossPlatformViewer();

    Command getCommand(ICrossPlatformRequest request);

    ICrossPlatformFigure getCrossPlatformFigure();

}
