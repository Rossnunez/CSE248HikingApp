package model;

public class Trail {
	private String trailName;
	private String trailAddress;
	private double miles;
	private double elevation;
	private Level difficulty;
	private HikeType type;

	public Trail(String trailName, String trailAddress, double miles, double elevation, Level difficulty,
			HikeType type) {
		super();
		this.trailName = trailName;
		this.trailAddress = trailAddress;
		this.miles = miles;
		this.elevation = elevation;
		this.difficulty = difficulty;
		this.type = type;
	}

	public String getTrailName() {
		return trailName;
	}

	public void setTrailName(String trailName) {
		this.trailName = trailName;
	}

	public String getTrailAddress() {
		return trailAddress;
	}

	public void setTrailAddress(String trailAddress) {
		this.trailAddress = trailAddress;
	}

	public double getMiles() {
		return miles;
	}

	public void setMiles(double miles) {
		this.miles = miles;
	}

	public double getElevation() {
		return elevation;
	}

	public void setElevation(double elevation) {
		this.elevation = elevation;
	}

	public Level getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Level difficulty) {
		this.difficulty = difficulty;
	}

	public HikeType getType() {
		return type;
	}

	public void setType(HikeType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Trail [trailName=" + trailName + ", trailAddress=" + trailAddress + ", miles=" + miles + ", elevation="
				+ elevation + ", difficulty=" + difficulty + ", type=" + type + "]";
	}
}
