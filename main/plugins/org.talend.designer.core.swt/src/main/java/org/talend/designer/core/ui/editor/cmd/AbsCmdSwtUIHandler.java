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
package org.talend.designer.core.ui.editor.cmd;

import org.eclipse.jface.dialogs.MessageDialog;
import org.talend.commons.ui.gmf.util.DisplayUtils;

/**
 * DOC cmeng  class global comment. Detailled comment
 */
public abstract class AbsCmdSwtUIHandler extends AbsCmdUIHandler implements ICommonCommandUIHandler {

    public AbsCmdSwtUIHandler() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean openQuestion(String title, String message) {
        boolean needPropagate = MessageDialog.openQuestion(DisplayUtils.getDefaultShell(false), title, message);
        return needPropagate;
    }

}
