package southbank.org.json;

import java.util.List;
import java.util.Map;

public interface Projector {

	public List<Map<String, Object>> project(List<GroupResult> resuts, List<Projection> projections);
}
