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
package org.talend.sdk.component.studio.util;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * created by hcyi on Jun 15, 2023
 * Detailled comment
 *
 */
public class TaCoKitSpeicalManager {

    public final static String[] NO_GUESS_SCHEMA_COMPONENTS = { "tJDBCNewRow", "tJDBCNewOutputBulk", "tJDBCNewCommit", //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
            "tJDBCNewRollback", "tJDBCNewSP" }; //$NON-NLS-1$ //$NON-NLS-2$

    public final static String[] NO_EXISTING_CONNECTION_NAMES = { "Commit", "Rollback" }; //$NON-NLS-1$ //$NON-NLS-2$

    public static boolean supportGuessSchema(String componentName) {
        if (StringUtils.isNotBlank(componentName)) {
            if (ArrayUtils.contains(NO_GUESS_SCHEMA_COMPONENTS, componentName)
                    || ArrayUtils.contains(NO_GUESS_SCHEMA_COMPONENTS, "t" + componentName)) { //$NON-NLS-1$
                return false;
            }
        }
        return true;
    }

    public static boolean supportUseExistingConnection(String name) {
        if (StringUtils.isNotBlank(name)) {
            if (ArrayUtils.contains(NO_EXISTING_CONNECTION_NAMES, name)) {
                return false;
            }
        }
        return true;
    }

}
