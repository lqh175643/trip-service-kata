package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;

public class TripService {
	public List<Trip> getTripsByUser(User friend, User loggedUser) throws UserNotLoggedInException {
		validate(loggedUser);
		return User.isFriend(friend, loggedUser) ? findTripsByUser(friend) : new ArrayList<Trip>();
	}

	private void validate(User loggedUser) throws UserNotLoggedInException {
		if (loggedUser == null) throw new UserNotLoggedInException();
	}
	protected List<Trip> findTripsByUser(User user) {
		return TripDAO.findTripsByUser(user);
	}

}
