package org.craftedsw.tripservicekata.user;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;

public class UserSession {

	private static final UserSession userSession = new UserSession();
	
	private UserSession() {
	}
	
	public static UserSession getInstance() {
		return userSession;
	}

	public static User loggedUser() {
		return getInstance().getLoggedUser();
	}

	public User getLoggedUser() {
		throw new CollaboratorCallException(
				"UserSession.getLoggedUser() should not be called in an unit test");
	}

}
