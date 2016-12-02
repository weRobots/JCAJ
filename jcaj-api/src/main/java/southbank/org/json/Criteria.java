package southbank.org.json;

import java.util.List;

import org.json.simple.JSONObject;

public interface Criteria {

	public List<JSONObject> getResult();

	public void createJoin(final Join join);

	public void orderBy(final OrderBy order);

	public void groupBy(final GroupBy group);

	public void addProjection(final Projection projection);

	public String getTable();
}
