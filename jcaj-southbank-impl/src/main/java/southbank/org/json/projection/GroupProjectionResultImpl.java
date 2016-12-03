package southbank.org.json.projection;

import southbank.org.json.projection.GroupProjectionResult;

public class GroupProjectionResultImpl implements GroupProjectionResult {

	private final String groupedProperty;
	private final Object groupedValue;
	private final String displayKey;
	private final Object value;

	public GroupProjectionResultImpl(final String groupedProperty, final Object groupedValue, final String displayKey,
			final Object value) {
		this.groupedProperty = groupedProperty;
		this.groupedValue = groupedValue;
		this.displayKey = displayKey;
		this.value = value;
	}

	@Override
	public String getGroupedProperty() {
		return this.groupedProperty;
	}

	@Override
	public Object getGroupedValue() {
		return this.groupedValue;
	}

	@Override
	public String getDisplayKey() {
		return this.displayKey;
	}

	@Override
	public Object getValue() {
		return this.value;
	}

}
