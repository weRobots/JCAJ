package southbank.org.json;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

public class CriteriaImpl implements Criteria {

	private String table;

	//
	private Join join;
	private GroupBy groupBy;
	private OrderBy orderBy;
	private List<Projection> projections;

	private CriteriaImpl(String table) {
		this.table = table;
	}

	@Override
	public List<JSONObject> getResult() {
		// if join exist get intersect by joining two tables
		List<JSONObject> results;

		return null;
	}

	@Override
	public void createJoin(Join join) {
		this.join = join;
	}

	@Override
	public void orderBy(OrderBy orderBy) {
		this.orderBy = orderBy;
	}

	@Override
	public void groupBy(GroupBy groupBy) {
		this.groupBy = groupBy;
	}

	@Override
	public void addProjection(Projection projection) {
		if (this.projections == null)
			this.projections = new ArrayList<Projection>();

		this.projections.add(projection);
	}

	public static Criteria createCriteria(String table) {
		return new CriteriaImpl(table);
	}

	@Override
	public String getTable() {
		return this.table;
	}
}
