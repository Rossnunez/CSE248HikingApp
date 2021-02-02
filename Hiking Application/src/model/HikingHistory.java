package model;

import java.util.TreeSet;

public class HikingHistory implements Comparable<HikingHistory> {
	private Trail trail;
	private String date;
	private String dateDone;
	private String duration;
	private TreeSet<String> images;
	private String pace;

	public HikingHistory(Trail trail, String date, String dateDone, String duration, TreeSet<String> images,
			String pace) {
		super();
		this.trail = trail;
		this.date = date;
		this.dateDone = dateDone;
		this.duration = duration;
		this.images = images;
		this.pace = pace;
	}

	public Trail getTrail() {
		return trail;
	}

	public void setTrail(Trail trail) {
		this.trail = trail;
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
		return "HikingHistory [trail=" + trail + ", date=" + date + ", dateDone=" + dateDone + ", duration=" + duration
				+ ", images=" + images + ", pace=" + pace + "]";
	}

	@Override
	public int compareTo(HikingHistory o) {
		if (date.compareTo(o.getDate()) == 0) {
			return 0;
		} else if (date.compareTo(o.getDate()) > 0) {
			return 1;
		} else {
			return -1;
		}
	}

}
