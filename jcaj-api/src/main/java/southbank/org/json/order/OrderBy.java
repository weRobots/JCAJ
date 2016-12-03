package southbank.org.json.order;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public interface OrderBy extends Comparator<Map<String, Object>> {

	public List<Map<String, Object>> order(List<Map<String, Object>> list);
}
