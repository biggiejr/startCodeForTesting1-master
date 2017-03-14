/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testex;

import java.util.Date;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mato
 */
public class DateFormatterTest {

    private IDateFormatter dateFormatter;

    public DateFormatterTest() {
        dateFormatter = new DateFormatter();
    }

    @Test
    public void testGetFormattedDate() throws Exception {
        String expDate = "17 feb 2017 10:56 AM";
        String tZone = "Europe/Copenhagen";
        Date date = new Date(2017 - 1900, 1, 17, 10, 56);
        assertThat(dateFormatter.getFormattedDate(tZone, date), is(expDate));

    }

    @Test
    public void testMain() throws Exception {
    }

}
