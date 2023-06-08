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

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.talend.designer.core.ui.AbstractMultiPageTalendEditor;
import org.talend.designer.core.ui.editor.AbstractTalendEditor;
import org.talend.designer.core.ui.editor.properties.controllers.ui.ICommonUIHandler;


/**
 * DOC cmeng  class global comment. Detailled comment
 */
public class CommonSwtUIHandler extends AbsCmdSwtUIHandler implements ICommonUIHandler {

    /**
     * DOC cmeng CommonSwtUIHandler constructor comment.
     */
    public CommonSwtUIHandler() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean execute(Command cmd) {
        IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        if (part instanceof AbstractMultiPageTalendEditor) {
            AbstractTalendEditor te = ((AbstractMultiPageTalendEditor) part).getTalendEditor();
            CommandStack cmdStack = (CommandStack) te.getAdapter(CommandStack.class);
            cmdStack.execute(cmd);
            return true;
        }
        return false;
    }

}
