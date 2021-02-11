package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.TreeMap;

public class StorageBag implements Serializable{

	public TreeMap<String, User> userMap;
	public HashSet<Trail> trailSet;

	public StorageBag(TreeMap<String, User> userMap, HashSet<Trail> trailSet) {
		super();
		this.userMap = userMap;
		this.trailSet = trailSet;
	}

	public TreeMap<String, User> getUserMap() {
		return userMap;
	}

	public void setUserMap(TreeMap<String, User> userMap) {
		this.userMap = userMap;
	}

	public HashSet<Trail> getTrailSet() {
		return trailSet;
	}

	public void setTrailSet(HashSet<Trail> trailSet) {
		this.trailSet = trailSet;
	}

	@Override
	public String toString() {
		return "StorageBag [userMap=" + userMap + ", trailSet=" + trailSet + "]";
	}

}
