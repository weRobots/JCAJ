package southbank.org.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GroupResultImpl implements GroupResult {

	private final String groupedProperty;
	private final Object groupedValue;
	private final List<Map<String, Object>> value;

	public GroupResultImpl(final String groupedProperty, final Object groupedValue) {
		this.groupedProperty = groupedProperty;
		this.groupedValue = groupedValue;
		this.value = new ArrayList<Map<String, Object>>();
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
	public List<Map<String, Object>> value() {
		return this.value;
	}

	@Override
	public void add(Map<String, Object> row) {
		value.add(row);
	}

}
