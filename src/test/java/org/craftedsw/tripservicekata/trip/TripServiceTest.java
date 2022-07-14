package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TripServiceTest {
    private final User NON_LOGGED_USER = null;
    private User loggedUser = new User();
    private final TripService tripService = new TripService(){
        @Override
        protected List<Trip> findTripsByUser(User user){
            return user.getTrips();
        }
    };
	@Test
    void should_throw_exception_when_loggedUser_return_null(){
        loggedUser = NON_LOGGED_USER;
        assertThrows(UserNotLoggedInException.class, ()->tripService.getTripsByUser(new User(), NON_LOGGED_USER));
    }

    @Test
    void should_be_empty_list_while_user_friends_not_equal_loggedUser(){
        List<Trip> tripList = tripService.getTripsByUser(new User(), loggedUser);
        assertEquals(tripList.size(),0);
    }

    @Test
    void should_return_trips_while_logged_user_is_a_friend(){
        //given
        User user = new User();
        user.addFriend(loggedUser);
        user.addTrip(new Trip());
        user.addTrip(new Trip());
        //when
        List<Trip> tripList = tripService.getTripsByUser(user, loggedUser);
        //then
        assertEquals(tripList,user.getTrips());
    }
}
