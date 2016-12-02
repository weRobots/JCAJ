package southbank.org.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupByImpl implements GroupBy {

	private final String property;
	private final String table;

	public GroupByImpl(final String table, final String property) {
		this.table = table;
		this.property = property;
	}

	@Override
	public List<GroupResult> group(List<Map<String, Object>> list) {
		Map<Object, GroupResult> groupedMap = new HashMap<Object, GroupResult>();

		// put in to the correct group
		for (Map<String, Object> map : list) {
			Object object = map.get(table + "." + property);

			if (object == null)
				object = map.get(property);

			if (object == null)
				throw new UnsupportedOperationException("cannot find the gropued property   " + table + "." + property);

			GroupResult group = groupedMap.get(object);

			// Initialised the group if not exist
			if (group == null) {
				group = new GroupResultImpl(table + "." + property, object);
				groupedMap.put(object, group);
			}

			// add the new result to group
			group.add(map);
		}

		Collection<GroupResult> values = groupedMap.values();

		return new ArrayList<GroupResult>(values);
	}
}
