package southbank.org.json;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

public interface Projection {

	public Map<JSONObject, List<JSONObject>> project(Map<JSONObject, List<JSONObject>> gropedList);
}
