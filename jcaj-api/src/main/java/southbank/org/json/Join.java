package southbank.org.json;

import java.util.List;

import org.json.JSONObject;

public interface Join {

	public List<JSONObject> join(Criteria c, String propertyL, String table, String propertyR);
}
