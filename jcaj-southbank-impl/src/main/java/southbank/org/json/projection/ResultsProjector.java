package southbank.org.json.projection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import southbank.org.json.GroupResult;
import southbank.org.json.projection.GroupProjectionResult;
import southbank.org.json.projection.Projection;
import southbank.org.json.projection.Projector;

public class ResultsProjector implements Projector {

	@Override
	public List<Map<String, Object>> projectGroup(List<GroupResult> results, List<Projection> projections) {
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

	@Override
	public List<Map<String, Object>> project(List<Map<String, Object>> resuts, List<Projection> projections) {
		// TODO Auto-generated method stub
		return null;
	}

}
