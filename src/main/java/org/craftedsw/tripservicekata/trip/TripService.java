package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {
	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		User loggedUser = loggedUser();
		validate(loggedUser);
		List<Trip> tripList = new ArrayList<Trip>();
		boolean isFriend = false;
		for (User friend : user.getFriends()) {
			if (friend.equals(loggedUser)) {
				isFriend = true;
				break;
			}
		}
		if (isFriend) {
			tripList = findTripsByUser(user);
		}
		return tripList;
	}

	private void validate(User loggedUser) throws UserNotLoggedInException {
		if (loggedUser == null) throw new UserNotLoggedInException();
	}
	protected List<Trip> findTripsByUser(User user) {
		return TripDAO.findTripsByUser(user);
	}

	protected User loggedUser() {
		return UserSession.getInstance().getLoggedUser();
	}

}
