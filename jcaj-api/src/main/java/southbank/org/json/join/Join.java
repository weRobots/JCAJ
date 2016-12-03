package southbank.org.json.join;

import java.util.List;
import java.util.Map;

import southbank.org.json.Criteria;

public interface Join {

	/**
	 * return the merged results as a single map.
	 * 
	 * @param c
	 * @return
	 */
	public List<Map<String, Object>> join(Criteria c);
}
