package model;

import java.io.Serializable;

public class Trail implements Serializable{
	private String trailName;
	private String trailAddress;
	private double length;
	private double elevation;
	private Level difficulty;
	private HikeType type;

	public Trail(String trailName, String trailAddress, double length, double elevation, Level difficulty,
			HikeType type) {
		super();
		this.trailName = trailName;
		this.trailAddress = trailAddress;
		this.length = length;
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

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
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
		return "Trail [trailName=" + trailName + ", trailAddress=" + trailAddress + ", length=" + length + ", elevation="
				+ elevation + ", difficulty=" + difficulty + ", type=" + type + "]";
	}
}
