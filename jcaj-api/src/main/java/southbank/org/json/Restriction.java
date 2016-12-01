package southbank.org.json;

import java.util.List;

import org.json.JSONObject;

public interface Restriction {

	public List<JSONObject> apply(List<JSONObject> list);
}
