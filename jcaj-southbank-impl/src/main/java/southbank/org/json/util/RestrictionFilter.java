package southbank.org.json.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import southbank.org.json.Restriction;

public class RestrictionFilter {

	/**
	 * Filter the result set applying the given restrictions.
	 * 
	 * @param results
	 * @param restrictions
	 * @return
	 */
	public List<Map<String, Object>> filter(List<Map<String, Object>> results, List<Restriction> restrictions) {

		List<Map<String, Object>> afterRestrictions = new ArrayList<Map<String, Object>>();

		if ((results == null || results.isEmpty()) || (restrictions == null || restrictions.isEmpty()))
			return results;

		filter: for (Map<String, Object> result : results) {
			for (Restriction restriction : restrictions) {
				if (!restriction.apply(result))
					continue filter;
			}

			afterRestrictions.add(result);
		}

		return afterRestrictions;
	}
}
