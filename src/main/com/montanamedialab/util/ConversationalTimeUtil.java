package com.montanamedialab.util;

import java.util.AbstractMap;
import java.util.HashMap;

/**
 * Simple utility class for translating 'digital' time into informal English
 * conversational style.
 * 
 * The algorithm is that 'on the hour' times are translated to 'noon', 'one am',
 * 'two pm' etc., and other times are rounded to the closest five minutes: five
 * past three, 25 to ten, quarter to four etc
 * 
 * @author phawkins@montanamedialab.com, hawkins.pete@gmail.com Copyright 2013,
 *         2014 Montana Medialab Limited All rights reserved
 **/
public final class ConversationalTimeUtil extends GenericInformalTimeUtil {

    /**
     * Mapping digital hour names into a form that people use in conversational
     * English. Array chosen as hours linear i.e., 0 - 23 in 24 hour clock.
     */
    private static final String[] INFORMAL_HOUR_NAMES = {"midnight", "one am", "two am", "three am", "four am", "five am", "six am", "seven am", "eight am", "nine am", "ten am", "eleven am", "noon", "one pm", "two pm", "three pm", "four pm", "five pm", "six pm", "seven pm", "eight pm", "nine pm",
            "ten pm", "eleven pm" };

    /** Mapping of 5 minute time slots into conversational, informal English. **/
    private static AbstractMap<Integer, String> minuteRoundedNames;

    static {

        minuteRoundedNames = new HashMap<Integer, String>();
        minuteRoundedNames.put(0, "");
        minuteRoundedNames.put(5, "five past");
        minuteRoundedNames.put(10, "ten past");
        minuteRoundedNames.put(15, "quarter past");
        minuteRoundedNames.put(20, "twenty past");
        minuteRoundedNames.put(25, "twenty five past");
        minuteRoundedNames.put(30, "half past");
        minuteRoundedNames.put(35, "twenty five to");
        minuteRoundedNames.put(40, "twenty to");
        minuteRoundedNames.put(45, "a quarter to");
        minuteRoundedNames.put(50, "ten to");
        minuteRoundedNames.put(55, "five to");
    }

    /** Private constructor; Class is a static util. **/
    // Since this method is not used, it does not show in coverage stats.
    // Unfortunately
    // there is currently no way to exclude this from eclEmma by annotation
    private ConversationalTimeUtil() {
    }

    /**
     * Converts a 'digital' time like 00:05 to an informal conversational form
     * e.g. 'five past midnight'.
     * 
     * @param digitalTime
     *            - format <hours>":"<minutes, where hours and minutes are
     *            Strings parseable to Java Integers, with HH between 0 and 23,
     *            and MM between 0 and 59
     * @return - informal, conversation time string. e.g., 03:56 = 'just after 5
     *         to four am'
     * @throws NumberFormatException if digital time format invalid
     */
    public static String digitalTimeToInformalTime(final String digitalTime) throws NumberFormatException {

        String minuteRoundingToken;
        String minuteRoundedName;
        String informalHourName;
        Integer minutes;
        Integer digitalHourIdx;

        DigitalTime digitalTimeBean = parseDigitalTime(digitalTime);

        minutes = digitalTimeBean.getMinutes();
        digitalHourIdx = digitalTimeBean.getHours();

        informalHourName = INFORMAL_HOUR_NAMES[digitalHourIdx];
        minuteRoundedName = minuteRoundedNames.get(minutes);

        minuteRoundingToken = "";

        if (minuteRoundedName == null) {

            // Round up / down to nearest 5 minute marker so can say
            // 'nearly' or 'just after'

            Integer roundedMinutes = (int) Math.round(new Double(minutes) / 5) * 5;

            minuteRoundingToken = (roundedMinutes > minutes ? "nearly " : "just after ");

            // Reset 'on the hour' 60 minutes to zero for lookup rounded
            // name
            if (roundedMinutes >= 60) {
                roundedMinutes = 0;
            }

            minuteRoundedName = minuteRoundedNames.get(roundedMinutes);

        }

        if (minutes >= 33) {
            digitalHourIdx++;
            if (digitalHourIdx > 23) {
                digitalHourIdx = 0;
            }

            informalHourName = INFORMAL_HOUR_NAMES[digitalHourIdx];

        }

        return formatInformalTime(minuteRoundedName, minuteRoundingToken, informalHourName);
    }

    /**
     * Generate nicely formatted conversational / informal time string,
     * intelligently dealing with whitespace.
     * 
     * @param minuteRoundedName
     *            e.g., '5 past'
     * @param minuteRoundingToken
     *            - e.g. - 'nearly'
     * @param informalHourName
     *            - e.g.: 'midnight', 'noon', one am
     * @return informalTime - nicely formatted informal time string
     */
    private static String formatInformalTime(final String minuteRoundedName, final String minuteRoundingToken, final String informalHourName) {
        String informalTime;
        if ("".equals(minuteRoundedName)) {
            informalTime = minuteRoundingToken + informalHourName;
        } else {
            informalTime = minuteRoundingToken + minuteRoundedName + " " + informalHourName;

        }
        return informalTime;
    }

} /* FiN */
