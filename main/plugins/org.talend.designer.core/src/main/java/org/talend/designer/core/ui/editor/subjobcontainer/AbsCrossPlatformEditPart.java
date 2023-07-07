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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.gef.commands.Command;
import org.talend.designer.core.ui.editor.nodes.ICrossPlatformEditPartViewer;
import org.talend.designer.core.ui.editor.nodes.ICrossPlatformEditPolicy;
import org.talend.designer.core.ui.editor.nodes.ICrossPlatformFigure;


/**
 * DOC cmeng  class global comment. Detailled comment
 */
public abstract class AbsCrossPlatformEditPart implements ICrossPlatformEditPart {

    private Object model;

    private ICrossPlatformEditPart parentPart;

    private List<Object> children = new ArrayList<>();

    private ICrossPlatformEditPartViewer crossPlatformViewer;

    private ICrossPlatformFigure crossPlatformFigure;

    private Map<Object, ICrossPlatformEditPolicy> policyMap = new LinkedHashMap<>();

    public AbsCrossPlatformEditPart(Object model) {
        this.model = model;
        createEditPolicies();
    }

    @Override
    public Object getCrossPlatformModel() {
        return model;
    }

    @Override
    public void setCrossPlatformModel(Object model) {
        this.model = model;
    }

    @Override
    public ICrossPlatformEditPart getCrossPlatformParentPart() {
        return parentPart;
    }

    public void setParentPart(ICrossPlatformEditPart parentPart) {
        this.parentPart = parentPart;
    }

    public void createEditPolicies() {

    }

    public void installEditPolicy(Object role, ICrossPlatformEditPolicy editPolicy) {
        if (editPolicy != null) {
            editPolicy.setHost(this);
        }
        policyMap.put(role, editPolicy);
    }

    @Override
    public Command getCommand(ICrossPlatformRequest request) {
        Command command = null;
        for (ICrossPlatformEditPolicy policy : policyMap.values()) {
            if (command != null) {
                command = command.chain(policy.getCommand(request));
            } else {
                command = policy.getCommand(request);
            }
        }
        return command;
    }

    @Override
    public ICrossPlatformEditPartViewer getCrossPlatformViewer() {
        return crossPlatformViewer;
    }

    public void setCrossPlatformViewer(ICrossPlatformEditPartViewer viewer) {
        this.crossPlatformViewer = viewer;
    }

    @Override
    public ICrossPlatformFigure getCrossPlatformFigure() {
        return crossPlatformFigure;
    }

    public void setCrossPlatformFigure(ICrossPlatformFigure figure) {
        this.crossPlatformFigure = figure;
    }

    @Override
    public List getCrossPlatformChildren() {
        return children;
    }

}
