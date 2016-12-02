package southbank.org.json;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

public interface GroupBy {

	public Map<Object, List<Map<String, Object>>> group(List<Map<String, Object>> list);
}
