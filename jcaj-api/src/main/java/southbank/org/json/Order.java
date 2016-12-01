package southbank.org.json;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public interface Order {

	public Map<JSONObject, List<JSONObject>> apply(Map<JSONObject, List<JSONObject>> list);
}
