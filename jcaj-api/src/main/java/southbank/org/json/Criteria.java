package southbank.org.json;

import java.util.List;
import java.util.Map;

public interface Criteria {

	public List<Map<String, Object>> getResult();

	public void createJoin(final Join join);

	public void orderBy(final OrderBy order);

	public void groupBy(final GroupBy group);

	public void addProjection(final Projection projection);

	public void addRestriction(final Restriction restriction);

	public String getTable();
}
