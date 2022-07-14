package org.craftedsw.tripservicekata.user;

import org.craftedsw.tripservicekata.trip.Trip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    private User user;
    @BeforeEach
    void setup(){
        user = new User();
    }

    @Test
    void should_be_empty_when_no_value_is_add(){
        assertEquals(user.getTrips().size(),0);
        assertEquals(user.getFriends().size(),0);
    }
    @Test
    void should_has_one_friend_when_add_a_friend() {
        //given
        //when
        user.addFriend(new User());
        //then
        assertEquals(user
                .getFriends().size(), 1);
    }

    @Test
    void should_has_one_trip_when_add_a_trip(){
        user.addTrip(new Trip());
        assertEquals(user.getTrips().size(),1);
    }
}
