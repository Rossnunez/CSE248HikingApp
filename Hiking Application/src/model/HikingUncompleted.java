package model;


public class HikingUncompleted implements Comparable<HikingUncompleted> {
	private String date;
	private Trail trail;

	public HikingUncompleted(String date, Trail trail) {
		super();
		this.date = date;
		this.trail = trail;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Trail getTrail() {
		return trail;
	}

	public void setTrail(Trail trail) {
		this.trail = trail;
	}

	@Override
	public int compareTo(HikingUncompleted o) {
		if (date.compareTo(o.getDate()) == 0) {
			return 0;
		} else if (date.compareTo(o.getDate()) > 0) {
			return 1;
		} else {
			return -1;
		}
	}

}
