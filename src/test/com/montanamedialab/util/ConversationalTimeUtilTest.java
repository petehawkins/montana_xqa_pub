/**

 */

package com.montanamedialab.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class ConversationalTimeUtilTest {
    /**
     * Test method for
     * {@link com.montanamedialab.util.ConversationalTimeUtil#digitalTimeToInformalTime(java.lang.String)}
     * . TODO: Use something like an ErrorCollector pattern to process all tests
     * even if one fails along the way...
     * 
     * @author phawkins@montanamedialab.com, hawkins.pete@gmail.com Copyright
     *         2013, 2014 Montana Medialab Limited All rights reserved
     * 
     * 
     */

    //
    // Stuff expected to work ...
    //

    @Test
    public void testDigitalTimeToInformalTime() {

        assertEquals("midnight", ConversationalTimeUtil.digitalTimeToInformalTime("00:00"));
        assertEquals("just after midnight", ConversationalTimeUtil.digitalTimeToInformalTime("00:01"));
        assertEquals("just after five past midnight", ConversationalTimeUtil.digitalTimeToInformalTime("00:07"));
        assertEquals("ten past midnight", ConversationalTimeUtil.digitalTimeToInformalTime("00:10"));
        assertEquals("nearly half past midnight", ConversationalTimeUtil.digitalTimeToInformalTime("00:29"));
        assertEquals("just after half past midnight", ConversationalTimeUtil.digitalTimeToInformalTime("00:31"));
        assertEquals("nearly twenty five to one am", ConversationalTimeUtil.digitalTimeToInformalTime("00:33"));
        assertEquals("nearly twenty five to one am", ConversationalTimeUtil.digitalTimeToInformalTime("00:34"));
        assertEquals("nearly one am", ConversationalTimeUtil.digitalTimeToInformalTime("00:59"));

        assertEquals("two am", ConversationalTimeUtil.digitalTimeToInformalTime("02:00"));
        assertEquals("just after two am", ConversationalTimeUtil.digitalTimeToInformalTime("02:01"));
        assertEquals("just after five past two am", ConversationalTimeUtil.digitalTimeToInformalTime("02:07"));
        assertEquals("ten past two am", ConversationalTimeUtil.digitalTimeToInformalTime("02:10"));
        assertEquals("nearly half past two am", ConversationalTimeUtil.digitalTimeToInformalTime("02:29"));
        assertEquals("just after half past two am", ConversationalTimeUtil.digitalTimeToInformalTime("02:31"));
        assertEquals("nearly twenty five to three am", ConversationalTimeUtil.digitalTimeToInformalTime("02:33"));
        assertEquals("nearly twenty five to three am", ConversationalTimeUtil.digitalTimeToInformalTime("02:34"));
        assertEquals("nearly three am", ConversationalTimeUtil.digitalTimeToInformalTime("02:59"));

        assertEquals("eleven pm", ConversationalTimeUtil.digitalTimeToInformalTime("23:00"));
        assertEquals("just after eleven pm", ConversationalTimeUtil.digitalTimeToInformalTime("23:01"));
        assertEquals("just after five past eleven pm", ConversationalTimeUtil.digitalTimeToInformalTime("23:07"));
        assertEquals("ten past eleven pm", ConversationalTimeUtil.digitalTimeToInformalTime("23:10"));
        assertEquals("nearly half past eleven pm", ConversationalTimeUtil.digitalTimeToInformalTime("23:29"));
        assertEquals("just after half past eleven pm", ConversationalTimeUtil.digitalTimeToInformalTime("23:31"));
        assertEquals("nearly twenty five to midnight", ConversationalTimeUtil.digitalTimeToInformalTime("23:33"));
        assertEquals("nearly twenty five to midnight", ConversationalTimeUtil.digitalTimeToInformalTime("23:34"));
        assertEquals("nearly midnight", ConversationalTimeUtil.digitalTimeToInformalTime("23:59"));

    }

    //
    // Stuff expected to fail ...
    //

    /* Vaguely bad times */

    @Test(expected = java.lang.NumberFormatException.class)
    public void testBadTimeFormat1() {
        ConversationalTimeUtil.digitalTimeToInformalTime("25:34");
    }

    @Test(expected = java.lang.NumberFormatException.class)
    public void testBadTimeFormat2() {
        ConversationalTimeUtil.digitalTimeToInformalTime("23:60");
    }

    /* Silly formats */
    @Test(expected = java.lang.NumberFormatException.class)
    public void testBadTimeFormat3() {
        ConversationalTimeUtil.digitalTimeToInformalTime("fubar");
    }

    @Test(expected = java.lang.NumberFormatException.class)
    public void testBadTimeFormat4() {
        ConversationalTimeUtil.digitalTimeToInformalTime("000:677.1");
    }

    /* Out of range <0-23>:<0-59> */

    @Test(expected = java.lang.NumberFormatException.class)
    public void testBadTimeFormat5() {
        ConversationalTimeUtil.digitalTimeToInformalTime("-1:00");
    }

    @Test(expected = java.lang.NumberFormatException.class)
    public void testBadTimeFormat6() {
        ConversationalTimeUtil.digitalTimeToInformalTime("-1:-1");
    }

    @Test(expected = java.lang.NumberFormatException.class)
    public void testBadTimeFormat7() {
        ConversationalTimeUtil.digitalTimeToInformalTime("-1:60");
    }

    @Test(expected = java.lang.NumberFormatException.class)
    public void testBadTimeFormat8() {
        ConversationalTimeUtil.digitalTimeToInformalTime("1:-1");
    }

    @Test(expected = java.lang.NumberFormatException.class)
    public void testBadTimeFormat9() {
        ConversationalTimeUtil.digitalTimeToInformalTime("1:60");
    }

    @Test(expected = java.lang.NumberFormatException.class)
    public void testBadTimeFormat10() {
        ConversationalTimeUtil.digitalTimeToInformalTime("24:00");
    }

    @Test(expected = java.lang.NumberFormatException.class)
    public void testBadTimeFormat11() {
        ConversationalTimeUtil.digitalTimeToInformalTime("24:-1");
    }

    @Test(expected = java.lang.NumberFormatException.class)
    public void testBadTimeFormat12() {
        ConversationalTimeUtil.digitalTimeToInformalTime("24:60");
    }

} /* FiN */
