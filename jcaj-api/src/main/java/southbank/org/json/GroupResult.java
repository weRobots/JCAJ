package southbank.org.json;

import java.util.List;
import java.util.Map;

public interface GroupResult {

	public String getGroupedProperty();

	public Object getGroupedValue();

	public List<Map<String, Object>> value();

	public void add(Map<String, Object> row);
}
