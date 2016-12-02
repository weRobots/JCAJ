package southbank.org.json;

import java.util.List;

public class PropertyProjection implements Projection {

	private final String property;
	private final String displayName;
	
	public PropertyProjection(final String property, final String displayName) {
		this.property = property;
		this.displayName = displayName;
	}
	
	@Override
	public List<GroupProjectionResult> project(List<GroupResult> results) {
		// TODO Auto-generated method stub
		return null;
	}

}
