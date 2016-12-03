package southbank.org.json.projection;

import java.util.List;
import java.util.Map;

import southbank.org.json.GroupResult;

public interface Projector {

	public List<Map<String, Object>> projectGroup(List<GroupResult> resuts, List<Projection> projections);

	public List<Map<String, Object>> project(List<Map<String, Object>> resuts, List<Projection> projections);
}
