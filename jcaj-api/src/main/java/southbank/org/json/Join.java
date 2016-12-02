package southbank.org.json;

import java.util.List;
import java.util.Map;

public interface Join {

	/**
	 * return the merged results as a single map.
	 * 
	 * @param c
	 * @return
	 */
	public List<Map<String, Object>> join(Criteria c);
}
