package model;

import java.util.TreeSet;

public class HikingHistory {
	private String trailName;
	private String date;
	private String dateDone;
	private double distance;
	private String duration;
	private TreeSet<String> images;
	private String pace;

	public HikingHistory(String trailName, String date, String dateDone, double distance, String duration,
			TreeSet<String> images, String pace) {
		super();
		this.trailName = trailName;
		this.date = date;
		this.dateDone = dateDone;
		this.distance = distance;
		this.duration = duration;
		this.images = images;
		this.pace = pace;
	}

	public String getTrailName() {
		return trailName;
	}

	public void setTrailName(String trailName) {
		this.trailName = trailName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDateDone() {
		return dateDone;
	}

	public void setDateDone(String dateDone) {
		this.dateDone = dateDone;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public TreeSet<String> getImages() {
		return images;
	}

	public void setImages(TreeSet<String> images) {
		this.images = images;
	}

	public String getPace() {
		return pace;
	}

	public void setPace(String pace) {
		this.pace = pace;
	}

	@Override
	public String toString() {
		return "HikingHistory [trailName=" + trailName + ", date=" + date + ", dateDone=" + dateDone + ", distance="
				+ distance + ", duration=" + duration + ", images=" + images + ", pace=" + pace + "]";
	}

}
