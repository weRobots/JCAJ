package southbank.org.json.projection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import southbank.org.json.GroupResult;
import southbank.org.json.projection.GroupProjectionResult;
import southbank.org.json.projection.Projection;

public class SUMProjection implements Projection {

	private final String property;
	private final String displayKey;

	public SUMProjection(final String property, final String displayKey) {
		this.property = property;
		this.displayKey = displayKey;
	}

	@Override
	public List<GroupProjectionResult> project(List<GroupResult> results) {
		List<GroupProjectionResult> projectionResults = new ArrayList<GroupProjectionResult>();

		if (results == null || results.isEmpty())
			return projectionResults;

		for (GroupResult group : results) {
			Double sum = 0.0D;

			for (Map<String, Object> groupRow : group.value()) {
				Object object = groupRow.get(this.property);

				if (object == null || !(object instanceof Double))
					throw new UnsupportedOperationException(
							"cannot perform SUM aggregation on null property for " + this.property);

				sum += (Double) object;
			}

			projectionResults.add(new GroupProjectionResultImpl(group.getGroupedProperty(), group.getGroupedValue(),
					this.displayKey, sum));

		}

		return projectionResults;
	}

	@Override
	public GroupProjectionResult project(GroupResult group) {
		if (group == null)
			return null;

		Double sum = 0.0D;

		for (Map<String, Object> groupRow : group.value()) {
			Object object = groupRow.get(this.property);

			if (object == null || !(object instanceof Double))
				throw new UnsupportedOperationException(
						"cannot perform SUM aggregation on null property for " + this.property);

			sum += (Double) object;
		}

		return new GroupProjectionResultImpl(group.getGroupedProperty(), group.getGroupedValue(), this.displayKey, sum);
	}

}
