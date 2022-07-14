package org.craftedsw.tripservicekata.user;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.trip.Trip;

public class User {

	private List<Trip> trips = new ArrayList<Trip>();
	private List<User> friends = new ArrayList<User>();

	public void addFriend(User user) {
		friends.add(user);
	}

	public List<User> getFriends() {
		return friends;
	}

	public void addTrip(Trip trip) {
		trips.add(trip);
	}

	public List<Trip> getTrips() {
		return trips;
	}


	public static boolean isFriend(User user, User loggedUser) {
		boolean isFriend = false;
		for (User friend : user.getFriends()) {
			if (friend.equals(loggedUser)) {
				isFriend = true;
				break;
			}
		}
		return isFriend;
	}
}
