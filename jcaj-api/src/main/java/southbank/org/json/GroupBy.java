package southbank.org.json;

import java.util.List;
import java.util.Map;

public interface GroupBy {

	public List<GroupResult> group(List<Map<String, Object>> list);
}
