package southbank.org.json.projection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import southbank.org.json.GroupResult;
import southbank.org.json.projection.GroupProjectionResult;
import southbank.org.json.projection.Projection;

public class PropertyProjection implements Projection {

	private final String property;
	private final String displayKey;

	public PropertyProjection(final String property, final String displayKey) {
		this.property = property;
		this.displayKey = displayKey;
	}

	@Override
	public List<GroupProjectionResult> project(List<GroupResult> results) {

		List<GroupProjectionResult> projectionResults = new ArrayList<GroupProjectionResult>();

		if (results == null || results.isEmpty())
			return projectionResults;

		// find the last id of table
		String table = this.property.split("\\.")[0];

		for (GroupResult group : results) {
			Double maxID = 0.0D;
			Object object = null;

			for (Map<String, Object> groupRow : group.value()) {
				Object currentObject = groupRow.get(this.property);
				Object id = groupRow.get(table + "._id");

				if (currentObject == null)
					throw new UnsupportedOperationException(
							"cannot perform property projection on null property for " + this.property);

				if ((Double) id > maxID)
					object = currentObject;

			}

			projectionResults.add(new GroupProjectionResultImpl(group.getGroupedProperty(), group.getGroupedValue(),
					this.displayKey, object));
		}

		return projectionResults;

	}

	@Override
	public GroupProjectionResult project(GroupResult group) {

		if (group == null)
			return null;

		Double maxID = 0.0D;
		Object object = null;

		// find the last id of table
		String table = this.property.split("\\.")[0];

		for (Map<String, Object> groupRow : group.value()) {
			Object currentObject = groupRow.get(this.property);
			Object id = groupRow.get(table + "._id");

			if (currentObject == null)
				throw new UnsupportedOperationException(
						"cannot perform property projection on null property for " + this.property);

			if ((Double) id > maxID)
				object = currentObject;

		}

		return new GroupProjectionResultImpl(group.getGroupedProperty(), group.getGroupedValue(), this.displayKey,
				object);
	}

}
