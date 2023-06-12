package org.talend.repository.model.migration;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.eclipse.jface.preference.IPreferenceStore;
import org.talend.core.model.general.Project;
import org.talend.core.model.migration.AbstractProjectMigrationTask;
import org.talend.repository.RepositoryPlugin;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import us.monoid.json.JSONObject;

public class RemoveUnwantedUsageDataMigrationTask extends AbstractProjectMigrationTask {

	private static final String PREF_TOS_JOBS_RECORDS = "TOS_Jobs_Records";

	@SuppressWarnings("unchecked")
	@Override
	public ExecutionResult execute(Project project) {

		boolean isDataUpdated = false;

		IPreferenceStore preferenceStore = RepositoryPlugin.getDefault().getPreferenceStore();
		String oldData = preferenceStore.getString(PREF_TOS_JOBS_RECORDS);
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			HashMap<String,Object> result = (HashMap<String,Object>) objectMapper.readValue(oldData, Map.class);
			
			isDataUpdated = deletedUnwantedData(result, "nb.route.osgi");
			
			// store the updated value
			if(isDataUpdated) {
				JSONObject jsonObject = new JSONObject(result);
				preferenceStore.setValue(PREF_TOS_JOBS_RECORDS, jsonObject.toString());
			}
			
			return ExecutionResult.SUCCESS_NO_ALERT;
			
		} catch (Exception e) {
			// the value is not set, or is empty
			e.printStackTrace();
		}

		return ExecutionResult.NOTHING_TO_DO;
	}

	@Override
	public Date getOrder() {
		GregorianCalendar gc = new GregorianCalendar(2017, 4, 17, 12, 0, 0);
		return gc.getTime();
	}

	@SuppressWarnings("unchecked")
	private static boolean deletedUnwantedData(HashMap<String, Object> result, String keyToRemove) throws JsonProcessingException, JsonMappingException {

		boolean isDataUpdated = false;
		
		for(Entry<String, Object> entry : result.entrySet()) {
			
			HashMap<String,Object> mainValue = (HashMap<String, Object>) entry.getValue();
			HashMap<String,Object> projectsObj = (HashMap<String, Object>) mainValue.get("projects");

			if(null != projectsObj) {
				HashMap<String,Object> processObject = (HashMap<String, Object>) projectsObj.get("PROCESS");
				if(null != processObject) {
					HashMap<String,Object> detailsObject = (HashMap<String, Object>) processObject.get("details");
					if(null != detailsObject && detailsObject.containsKey(keyToRemove)) {
							detailsObject.remove(keyToRemove);
							isDataUpdated = true;
					}
				}
			}
		}
		
		return isDataUpdated;
	}
}
