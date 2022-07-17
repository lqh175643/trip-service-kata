package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TripServiceTest {
    private final User loggedUser = new User();
    private final Trip trip1 = new Trip();
    private final Trip trip2 = new Trip();

    private final TripService tripService = new TripService();
	@Test
    void should_throw_exception_when_loggedUser_return_null(){
        assertThrows(UserNotLoggedInException.class, ()->tripService.getTripsByUser(new User(), null));
    }

    @Test
    void should_be_empty_list_while_user_friends_not_equal_loggedUser(){
        try(MockedStatic<User> user = mockStatic(User.class)) {
            user.when(()->User.isFriend(any(),any())).thenReturn(false);
            List<Trip> tripList = tripService.getTripsByUser(new User(), loggedUser);
            user.verify(()->User.isFriend(any(),any()));
            assertEquals(tripList.size(),0);
        }
    }

    @Test
    void should_return_trips_while_logged_user_is_a_friend(){
        User friend = new User();
        friend.addTrip(trip1);
        friend.addTrip(trip2);
        try(MockedStatic<User> user = mockStatic(User.class);
            MockedStatic<TripDAO> tripDAO = mockStatic(TripDAO.class)){
            user.when(()->User.isFriend(any(),any())).thenReturn(true);
            tripDAO.when(()->TripDAO.findTripsByUser(any())).thenReturn(List.of(trip1,trip2));
            List<Trip> tripList = tripService.getTripsByUser(friend, loggedUser);
            user.verify(()->User.isFriend(any(),any()));
            tripDAO.verify(()->TripDAO.findTripsByUser(any()));
            assertEquals(tripList,friend.getTrips());
        }
    }
}
