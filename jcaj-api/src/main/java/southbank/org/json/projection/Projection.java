package southbank.org.json.projection;

import java.util.List;

import southbank.org.json.GroupResult;

public interface Projection {

	public List<GroupProjectionResult> project(List<GroupResult> results);

	public GroupProjectionResult project(GroupResult result);

}
