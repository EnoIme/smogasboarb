package com.blast.inapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by owner on 9/11/2016.
 */

@RunWith(value = MockitoJUnitRunner)
public class UtilTest {
    @Test
    public void getPeopleList() throws Exception {

    }

    @Test
    public void getRandomPerson() throws Exception {

    }

    @Test
    public void getDate() throws Exception {
        assertThat(Util.getDate("23/03/2016"), is(1458687600000L));
    }

    @Test
    public void formatDate() throws Exception {
        assertThat(Util.formatDate(795913200000L), is("23/03/1995"));
    }

}