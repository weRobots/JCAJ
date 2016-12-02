package southbank.org.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupResultProjector implements Projector {

	@Override
	public List<Map<String, Object>> project(List<GroupResult> results, List<Projection> projections) {
		List<Map<String, Object>> finalList = new ArrayList<Map<String, Object>>();

		if (results == null || results.isEmpty())
			return new ArrayList<Map<String, Object>>();

		// no projection return all keys and values
		if (projections == null || projections.isEmpty()) {
			// TODO

		}

		for (GroupResult group : results) {
			Map<String, Object> groupFinalRecord = new HashMap<String, Object>();

			for (Projection projection : projections) {
				GroupProjectionResult projectionResult = projection.project(group);
				groupFinalRecord.put(projectionResult.getDisplayKey(), projectionResult.getValue());
			}

			finalList.add(groupFinalRecord);

		}

		return finalList;
	}

}
