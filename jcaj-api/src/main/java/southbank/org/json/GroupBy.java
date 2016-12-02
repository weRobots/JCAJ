package southbank.org.json;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

public interface GroupBy {

	public Map<JSONObject, List<JSONObject>> group(List<JSONObject> list);
}
