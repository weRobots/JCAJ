package southbank.org.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import southbank.org.json.util.RestrictionFilter;
import southbank.org.json.util.TableReader;

public class CriteriaImpl implements Criteria {

	private String table;

	//
	private Join join;
	private GroupBy groupBy;
	private OrderBy orderBy;
	private List<Projection> projections;
	private List<Restriction> restrictions;

	private CriteriaImpl(String table) {
		this.table = table;
	}

	@Override
	public List<Map<String, Object>> getResult() {
		List<Map<String, Object>> results;

		// if join exist get intersect by joining two tables
		if (this.join != null)
			results = this.join.join(this);
		else
			results = new TableReader().fullRead(this.table);

		// apply given restrictions
		results = new RestrictionFilter().filter(results, this.restrictions);
		
		// groups if defined
		List<GroupResult> groups;
		
		// apply group by
		if (this.groupBy != null) 
			groups = this.groupBy.group(results);
			
		// project 
		
		
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

	@Override
	public void addRestriction(Restriction restriction) {
		if (this.restrictions == null)
			this.restrictions = new ArrayList<Restriction>();

		this.restrictions.add(restriction);

	}
}
