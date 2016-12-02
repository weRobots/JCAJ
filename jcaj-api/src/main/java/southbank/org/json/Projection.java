package southbank.org.json;

import java.util.List;

public interface Projection {

	public List<GroupProjectionResult> project(List<GroupResult> results);
}
