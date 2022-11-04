package recipes.entity;

public class Unit {
	private Integer unitId;
	private String unitNameSingular;
	private String unitNamePrural;

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public String getUnitNameSingular() {
		return unitNameSingular;
	}

	public void setUnitNameSingular(String unitNameSingular) {
		this.unitNameSingular = unitNameSingular;
	}

	public String getUnitNamePrural() {
		return unitNamePrural;
	}

	public void setUnitNamePrural(String unitNamePrural) {
		this.unitNamePrural = unitNamePrural;
	}

	@Override
	public String toString() {
		return "Unit [unitId=" + unitId + ", unitNameSingular=" + unitNameSingular + ", unitNamePrural="
				+ unitNamePrural + "]";
	}

}
