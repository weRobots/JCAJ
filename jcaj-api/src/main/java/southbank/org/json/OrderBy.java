package southbank.org.json;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

public interface OrderBy {

	public Map<JSONObject, List<JSONObject>> order(Map<JSONObject, List<JSONObject>> list);
}
