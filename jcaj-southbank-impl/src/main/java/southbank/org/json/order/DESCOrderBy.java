package southbank.org.json.order;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import southbank.org.json.order.OrderBy;

public class DESCOrderBy implements OrderBy {

	private final String property;

	public DESCOrderBy(final String property) {
		this.property = property;
	}

	@Override
	public int compare(Map<String, Object> o1, Map<String, Object> o2) {
		return ((Double) o2.get(this.property)).compareTo((Double) o1.get(this.property));
	}

	@Override
	public List<Map<String, Object>> order(List<Map<String, Object>> list) {
		Collections.sort(list, this);
		return list;
	}
}
