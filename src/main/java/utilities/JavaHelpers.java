package utilities;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class JavaHelpers {
    //Reading system properties
    Random randomNumber = new Random();
    static SecureRandom random = new SecureRandom();

    /**
     * Set system variable - set it from system variable first, if not found -set it from properties file
     *
     * @param name variable name
     * @param propertyFileLocation properties file location
     * @return variable value
     */
    public static String setSystemVariable(String propertyFileLocation, String name) {
        //Reading from system properties.
        String variable = System.getProperty(name);

        //if not specified via command line, take it from constants.properties file
        if (variable == null || variable.isEmpty()) {
            variable = JavaHelpers.getPropertyValue(propertyFileLocation, name);
        }
        return variable;
    }

    //Data Reader

    /**
     * Get Property value
     *
     * @param propertyFile property file name
     * @param propertyName property name
     * @return property value
     */
    public static String getPropertyValue(String propertyFile, String propertyName) {
        Properties prop = accessPropertiesFile(propertyFile);
        return prop.getProperty(propertyName);
    }

    /**
     * Access property file
     *
     * @param propertyFile property file name
     * @return Properties object
     */
    public static Properties accessPropertiesFile(String propertyFile) {
        Properties prop = new Properties();

        try (FileInputStream fis = new FileInputStream(propertyFile)) {
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(String.format("Failed to load properties file: %s", propertyFile), e);
        }
        return prop;
    }

    //Folder-File Operations

    /**
     * Delete all files from given folder
     *
     * @param fileFolder folder path
     */
    public void deleteAllFilesFromFolder(String fileFolder) {
        File dir = new File(fileFolder);
        if (!dir.isDirectory()) {
            return;
        }
        File[] listFiles = dir.listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                boolean delete = file.delete();
                Reporter.log("Deleted file: " + delete);
            }
        }
    }

    /**
     * Returns a boolean indicating whether a file with the specified partial name has been downloaded to the specified folder.
     *
     * @param extension the extension of file name
     * @param partialFileName the partial name of the file to look for
     * @return true if a file with the specified partial name has been downloaded, false otherwise
     */
    public boolean isFileDownloaded(String extension, String partialFileName, boolean regex) {
        File folder = new File(Constants.DOWNLOAD_FOLDER);
        if (!folder.exists() || !folder.isDirectory()) {
            return false;
        }

        File[] files = folder.listFiles();
        if (regex) {
            Pattern pattern = Pattern.compile(partialFileName);
            for (File file : Objects.requireNonNull(files)) {
                String fileName = file.getName();
                Matcher matcher = pattern.matcher(fileName);

                if (matcher.find() || fileName.endsWith(extension)) {
                    return true;
                }
            }
        } else {
            for (File file : Objects.requireNonNull(files)) {
                if (file.getName().contains(partialFileName) || file.getName().endsWith(extension)) {
                    return true;
                }
            }
        }
        return false;
    }

    //To get randomString

    /**
     * Random Alpha numeric String
     *
     * @param n number of characters
     * @return string
     */
    public String getAlphaNumericString(int n) {
        String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = randomNumber.nextInt(alphaNumericString.length());
            sb.append(alphaNumericString.charAt(index));
        }

        return sb.toString();
    }

    /**
     * Random Number
     *
     * @param n number of characters
     * @return String
     */
    public String getRandomNumber(int n) {
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int digit;

            // Avoid leading 0
            if (i == 0) {
                digit = 1 + randomNumber.nextInt(9); // 1–9
            } else {
                digit = randomNumber.nextInt(10); // 0–9
            }

            sb.append(digit);
        }
        return sb.toString();
    }

    /**
     * Random String
     *
     * @param n number of characters
     * @return string
     */
    public String getAlphaString(int n) {
        String alphaString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = randomNumber.nextInt(alphaString.length());
            sb.append(alphaString
                    .charAt(index));
        }
        return sb.toString();
    }

    /**
     * Random Special characters
     *
     * @param n number of characters
     * @return string
     */
    public String getSpecialCharacterString(int n) {
        String alphaString = "^[^<>{}\"/|;:.,~!?@#$%^=&*\\]\\\\()\\*$";

        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {

            int randomNo = randomNumber.nextInt(alphaString.length());
            sb.append(alphaString.charAt(randomNo));

        }
        return sb.toString();
    }

    /**
     * Returns a random integer within the specified range.
     *
     * @param maxNumber the maximum value of the range (inclusive)
     * @param minNumber the minimum value of the range (inclusive)
     * @return a random integer within the specified range
     */
    public int getRandomNumber(int maxNumber, int minNumber) {
        return (int) Math.floor(Math.random() * ((maxNumber) - minNumber) + minNumber);
    }

    /**
     * Returns a random value from the given array of strings.
     *
     * @param listOfArray the array of strings to choose from
     * @return a random string from the array
     */
    public String getRandomValue(String[] listOfArray) {
        int randomIndex = randomNumber.nextInt(listOfArray.length);
        return listOfArray[randomIndex];
    }

    /**
     * Returns a phone number in the specified country format.
     *
     * @param phoneNumber the phone number to format
     * @param phoneNumberFormat the country format to use (e.g. "USA", "India", etc.)
     * @return the formatted phone number
     */
    public String getPhoneNumberInSpecificCountryFormat(String phoneNumber, String phoneNumberFormat) {
        Map<String, String> formats = new HashMap<>();
        formats.put("USA", "(%s) %s-%s"); // (123) 456-7890
        formats.put("India", "%s-%s-%s"); // 123-456-7890
        formats.put("Canada", "(%s) %s-%s"); // (123) 456-7890
        formats.put("NZ", "+64 %s %s %s");  // +64 12 3456 7890

        phoneNumber = phoneNumber.replaceAll("\\D", "");

        // Check if the format is supported
        if (!formats.containsKey(phoneNumberFormat)) {
            throw new UnsupportedOperationException("Unsupported phone number format: " + phoneNumberFormat);
        }

        // Validate phone number length
        if (phoneNumber.length() < 10) {
            throw new IllegalArgumentException("Invalid phone number length.");
        }

        String part1;
        String part2;
        String part3;

        if ((phoneNumberFormat.equals("NZ"))) {
            part1 = phoneNumber.substring(0, 2);  // First two digits (NZ area code)
            part2 = phoneNumber.substring(2, 6);  // Next four digits
        } else {
            part1 = phoneNumber.substring(0, 3);
            part2 = phoneNumber.substring(3, 6);
        }
        part3 = phoneNumber.substring(6);

        // Apply the format
        return String.format(formats.get(phoneNumberFormat), part1, part2, part3);
    }

    /**
     * Returns the input text with newline characters removed and trimmed.
     *
     * @param text the input text to process
     * @param trimText the text to trim from the input text
     * @return the processed text with newline characters removed and trimmed
     */
    public String getReplaceNewLineTextWithTrim(String text, String trimText) {
        if (text == null || trimText == null) return "";
        String cleanedText = text.replaceAll("\\r?\\n", "");
        String escapedTrimText = Pattern.quote(trimText.trim());
        return cleanedText.replaceAll(escapedTrimText, "").trim();
    }

    /**
     * Extracts a value from the input text based on the specified key.
     *
     * @param text the input text to search
     * @param key the key to search for
     * @return the extracted value, or "Not found" if the key is not found
     */
    public String getExtractedValuesFromString(String text, String key) {
        int index = text.indexOf(key);
        if (index != -1) {
            return text.substring(index + key.length()).split("\n")[0].trim();
        }
        return "Not found";
    }

    /**
     * Cleans and normalizes time text from WebElements to ensure proper parsing.
     *
     * @param timeText The raw time text extracted from a WebElement.
     * @return A cleaned and formatted time string in uppercase (e.g., "10:30 AM").
     */
    private String cleanTimeText(String timeText) {
        return timeText.replaceAll("[^\\x00-\\x7F]", "") // Remove non-ASCII characters
                .replaceAll("\\s+", " ") // Normalize spaces
                .trim();
    }

    //Date and Time-stamps

    /**
     * Get current time-stamp in given format
     *
     * @param format e.g. "yyyy MMM dd", 'yyyyMMdd_HHmmss' etc.
     * @return String timestamp
     */
    public String getTimeStamp(String format) {
        /*
         * Example format are :
         *
         * "yyyy MMM dd" for "2013 Nov 28"
         *
         * "yyyyMMdd_HHmmss" for "20130131000000"
         *
         * "yyyy MMM dd HH:mm:ss" for "2013 Jan 31 00:00:00"
         *
         * "dd MMM yyyy" for "28 Nov 2017"
         */
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return dateFormat.format(date);
    }

    /**
     * Future Past DateAndTime
     *
     * @param date   number
     * @param minute number
     * @return date of future,past as per number
     */
    public String getFuturePastCurrentDateAndTime(int date, int minute, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date currentDate = new Date();

        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);

        // manipulate date
        c.add(Calendar.DATE, date);
        c.add(Calendar.MINUTE, minute);

        // convert calendar to date
        Date currentDatePlusOne = c.getTime();
        return dateFormat.format(currentDatePlusOne);
    }

    public String getFuturePastCurrentDateAndTimeWithMonth(int daysToAdd, int minutesToAdd, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        // Add specified days and minutes
        calendar.add(Calendar.DATE, daysToAdd);
        calendar.add(Calendar.MINUTE, minutesToAdd);

        return dateFormat.format(calendar.getTime());
    }


    /**
     * Converts a date string to a specific format.
     *
     * @param date the date string to convert
     * @param newFormat the desired format for the date
     * @return the converted date string in the specified format
     * @throws ParseException if the date string cannot be parsed
     */
    public String getSpecificFormatDateAndTime(String date, String oldFormat, String newFormat) throws ParseException {
        DateFormat oldFormatter = new SimpleDateFormat(oldFormat);
        Date parsedDate = oldFormatter.parse(date);

        // Convert Date to new formatted String
        DateFormat newFormatter = new SimpleDateFormat(newFormat);
        return newFormatter.format(parsedDate);
    }

    /**
     * Returns the month and year in the specified format by adding/subtracting months from the current date.
     *
     * @param monthOffset the number of months to add (negative for past months)
     * @param format      the desired format (e.g., "MMMM, yyyy")
     * @return the formatted month and year string
     */
    public String getMonthAndYearByOffset(int monthOffset, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, monthOffset);
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * Returns the formatted date string after shifting the given formatted date by a certain number of days.
     *
     * @param formattedDate the original date string (e.g., "Sunday, June 29, 2025")
     * @param offsetDays    how many days to shift (negative for past, positive for future)
     * @param pattern       desired output format (e.g., "EEEE, MMMM dd, yyyy")
     * @return formatted shifted date string
     */
    public String shiftFormattedDateByDays(String formattedDate, int offsetDays, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(formattedDate, formatter);
        return date.plusDays(offsetDays).format(DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH));
    }

    /**
     * Calculates the total difference in hours and minutes between multiple start and end time elements.
     *
     * @param startingTimeElements List of WebElements representing the start times.
     * @param endTimeElements      List of WebElements representing the end times.
     * @return A string representing the total duration (e.g., "5 hours and 30 minutes").
     */
    public String getDifferenceBetweenStartAndEndTimeInHoursAndMinutes(List<WebElement> startingTimeElements, List<WebElement> endTimeElements) {
        Duration totalDuration = Duration.ZERO; // To sum up the total duration
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH);

        for (int i = 0; i < startingTimeElements.size(); i++) {
            String startTimeStr = cleanTimeText(startingTimeElements.get(i).getText()).toUpperCase();
            String endTimeStr = cleanTimeText(endTimeElements.get(i).getText()).toUpperCase();

            LocalTime startTime = LocalTime.parse(startTimeStr, formatter);
            LocalTime endTime = LocalTime.parse(endTimeStr, formatter);

            long startMinutes = startTime.getHour() * 60 + startTime.getMinute();
            long endMinutes = endTime.getHour() * 60 + endTime.getMinute();

            if (endMinutes < startMinutes) {
                endMinutes += 24 * 60; // Add 24 hours if the end time is before start time (overnight case)
            }

            long diffMinutes = endMinutes - startMinutes;

            totalDuration = totalDuration.plusMinutes(diffMinutes);
        }

        long totalHours = totalDuration.toHours();
        long totalMinutes = totalDuration.toMinutesPart();

        return totalHours + " hours and " + totalMinutes + " minutes";
    }

    /**
     * Retrieves either the start or end date of the current week.
     *
     * @param isStart If true, returns the Monday (start) of the week; if false, returns Sunday (end) of the week.
     * @return The formatted date as "M/d/yyyy"
     */
    public String getWeekDateWithMonthAndYear(boolean isStart) {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
        LocalDate date = isStart ? startOfWeek : startOfWeek.plusDays(6);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return date.format(dateFormatter);
    }

    /**
     * Retrieves either the start or end date of the current week.
     *
     * @param isStart If true, returns the Monday (start) of the week; if false, returns Sunday (end) of the week.
     * @return The formatted date as "M/d/yyyy"
     */
    public String getWeekDate(boolean isStart, boolean isNextWeek) {
        LocalDate today = LocalDate.now();
        LocalDate baseWeek = isNextWeek ? today.plusWeeks(1) : today;
        LocalDate startOfWeek = baseWeek.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        LocalDate date = isStart ? startOfWeek : startOfWeek.plusDays(6);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d");
        return date.format(formatter);
    }

    public String getWeekRange(int weekOffset) {
        DateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
        Calendar calendar = Calendar.getInstance();

        // Move to target week
        calendar.add(Calendar.WEEK_OF_YEAR, weekOffset);

        // Set to the start of the week (Sunday)
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        Date weekStart = calendar.getTime();

        // Move to end of the week (Saturday)
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        Date weekEnd = calendar.getTime();

        return dateFormat.format(weekStart) + " - " + dateFormat.format(weekEnd);
    }

    /**
     * Returns the current date and time for a given country or time zone.
     *
     * @param countryOrZoneId The country or time zone ID (e.g. "US", "Europe/London", etc.)
     * @return The current date and time in the format "MM/dd/yy hh:mm a" (e.g. "02/16/22 03:45 PM")
     * @throws IllegalArgumentException If the country or zone ID is invalid
     */
    public String getCurrentDateTimeForCountry(String countryOrZoneId) {
        try {
            ZoneId zoneId = ZoneId.of(countryOrZoneId);
            ZonedDateTime currentDateTime = ZonedDateTime.now(zoneId);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy hh:mm a", Locale.ENGLISH);
            return currentDateTime.format(formatter);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid country or zone ID: " + countryOrZoneId);
        }
    }

    public String getDateTimeForCountryWithOffset(String countryOrZoneId, int offsetInMinutes) {
        try {
            ZoneId zoneId = ZoneId.of(countryOrZoneId);
            ZonedDateTime currentDateTime = ZonedDateTime.now(zoneId).plusMinutes(offsetInMinutes);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy hh:mm a", Locale.ENGLISH);
            return currentDateTime.format(formatter);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid country or zone ID: " + countryOrZoneId);
        }
    }


    //List of webElements operations

    /**
     * Converts a list of WebElements to a list of strings, where each string is the text content of the corresponding WebElement.
     * The text content is converted to lowercase.
     *
     * @param e the list of WebElements to convert
     * @return a list of strings representing the text content of the WebElements
     */
    public List<String> getList(List<WebElement> e, boolean toLowerCase) {
        List<String> list = new ArrayList<>();
        for (WebElement elem : e) {
            String text = elem.getText();
            list.add(toLowerCase ? text.toLowerCase() : text);
        }
        return list;
    }

    /**
     * Returns the text content of a random WebElement from the specified list.
     *
     * @param elements the list of WebElements to choose from
     * @return the text content of a random WebElement
     */
    public String getRandomWebElement(List<WebElement> elements) {
        int randomIndex = randomNumber.nextInt(elements.size());
        return elements.get(randomIndex).getText().trim();
    }

    /**
     * Generates a random password with at least one uppercase letter, one digit, and one special character.
     * The rest of the characters are randomly selected from uppercase, lowercase letters, digits, and special characters.
     *
     * @param len The length of the password to generate.
     * @return A randomly generated password of the specified length.
     */
    public String generatePassword(int len) {
        String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ", DIGIT = "0123456789", SPECIAL = "!@", ALL = UPPER + UPPER.toLowerCase() + DIGIT + SPECIAL;

        List<Character> pwd = Stream.of(randomChar(UPPER), randomChar(DIGIT), randomChar(SPECIAL))
                .collect(Collectors.toList());
        while (pwd.size() < len) pwd.add(randomChar(ALL));
        Collections.shuffle(pwd, random);
        return pwd.stream().map(String::valueOf).collect(Collectors.joining());
    }

    /**
     * Selects a random character from the given string.
     *
     * @param s The string containing characters to pick from.
     * @return A randomly selected character from the input string.
     */
    private char randomChar(String s) {
        return s.charAt(random.nextInt(s.length()));
    }

    public String getLastDateOfCurrentMonth(String format) {
        return LocalDate.now().withDayOfMonth(YearMonth.now().lengthOfMonth()).format(DateTimeFormatter.ofPattern(format));
    }

    public String getFirstDateOfCurrentMonth(String format) {
        return LocalDate.now().withDayOfMonth(1).format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * Retrieves a list of random web elements from the current page.
     * <p>
     * This method is used to get a collection of web elements that match the specified locator.
     * The elements are retrieved in a random order.
     *
     * @return A list of random web elements.
     */
    public int getRandomWebElements(List<WebElement> elements) {
        if (elements.isEmpty()) {
            throw new IllegalArgumentException("Element list is empty. Cannot pick a random element.");
        }
        return randomNumber.nextInt(elements.size());
    }

    /**
     * Returns the text content of a random WebElement from the specified list.
     *
     * @param elements the list of WebElements to choose from
     * @return the text content of a random WebElement
     */
    public String getRandomWebElementAttributeValue(List<WebElement> elements,String value) {
        int randomIndex = randomNumber.nextInt(elements.size());
        return elements.get(randomIndex).getDomAttribute(value);
    }


    public String extractSuccessMessage(String message){
        String[] lines = message.split("\n");
        return lines[lines.length - 1];
    }

    public String calculateTimeDifference(String startDateTime, String endDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy hh:mm a", Locale.ENGLISH);
        LocalDateTime start = LocalDateTime.parse(startDateTime, formatter);
        LocalDateTime end = LocalDateTime.parse(endDateTime, formatter);

        long minutes = ChronoUnit.MINUTES.between(start, end);
        double hours = minutes / 60.0;

        return String.format("%.2f", hours);
    }

    public String getCurrentMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM"));
    }

    public String addDaysToGivenDate(String dateStr, int daysToAdd, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);
        LocalDate startDate = LocalDate.parse(dateStr, formatter);
        LocalDate endDate = startDate.plusDays(daysToAdd);
        return endDate.format(formatter);
    }


    public Integer getNumbersFromString(String input) {
        return Integer.valueOf(input.replaceAll("[^0-9]", ""));
    }

    public String roundTwoDecimalPlaces(double amount) {
        return String.format("%.2f", amount);
    }

    public double calculateDurationBetweenTwoTimes(String startTime, String endTime) {
        return Duration.between(LocalTime.parse(startTime), LocalTime.parse(endTime)).toMinutes() / 60.0;
    }

    public double convertMinutesToHours(double minutes) {
        return minutes / 60.0;
    }


    public String removeSpecialCharAndReturnNumbersFromText(String text) {
        return  text.replaceAll(".*(\\d{1,5}\\.\\d{1,2})", "$1");
    }

    /**
     * Calculates units between two times based on the minutesPerUnit.
     *
     * @param startTime      Start time in "HH:mm" format.
     * @param endTime        End time in "HH:mm" format.
     * @param minutesPerUnit Number of minutes that equal one unit.
     * @return The number of units (rounded to 2 decimal places).
     */
    public double calculateUnitsBasedOnMinutesPerUnit(String startTime, String endTime, int minutesPerUnit) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime start = LocalTime.parse(startTime, formatter);
        LocalTime end = LocalTime.parse(endTime, formatter);
        long minutesBetween = Duration.between(start, end).toMinutes();
        if (minutesBetween < 0) {
            throw new IllegalArgumentException("End time must be after start time.");
        }
        if (minutesPerUnit <= 0) {
            throw new IllegalArgumentException("minutesPerUnit must be > 0");
        }
        return Math.round(((double) minutesBetween / minutesPerUnit) * 100.0) / 100.0;
    }

    public double getTotalUnitsExcludingBreak(String start, String end) {
        // Use 24-hour formatter
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        // Parse strings like "08:00" or "13:45"
        LocalTime startTime = LocalTime.parse(start, timeFormatter);
        LocalTime endTime = LocalTime.parse(end, timeFormatter);

        long minutes = Duration.between(startTime, endTime).toMinutes();

        // Handle overnight case
        if (minutes < 0) {
            minutes += 24 * 60;
        }

        return Math.round((minutes / 60.0) * 100.0) / 100.0;
    }


    public String convert24HourTo12Hour(String time24Hour) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("hh:mm");
            Date date = simpleDateFormat.parse(time24Hour);
            return simpleDateFormat1.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String addMinutesToTime(String time, int minutesToAdd) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime localTime = LocalTime.parse(time, formatter);
        LocalTime updatedTime = localTime.plusMinutes(minutesToAdd);
        return updatedTime.format(formatter);
    }


    /**
     * Normalizes the base name of a PDF file by performing the following:
     * - Removes the ".pdf" extension
     * - Removes trailing numeric suffixes (e.g., "_095519538")
     * - Removes any commas
     * - Removes all whitespace characters
     * - Replaces multiple underscores with a single underscore
     * - Trims any leading or trailing spaces
     *
     * @param fileName The original PDF file name (e.g., "ClientFifteen_ ScheduleCalendar_095519538.pdf")
     * @return A cleaned and normalized version of the base name (e.g., "ClientFifteen_ScheduleCalendar")
     */
    public String normalizePdfBaseName(String fileName) {
        String baseName = fileName.replace(".pdf", "").trim();
        return baseName
                .replaceAll("_[0-9]+$", "") // remove suffix like _095519538
                .replaceAll(",", "")        // remove commas
                .replaceAll("\\s+", "")     // remove all spaces
                .replaceAll("_+", "_")      // replace multiple underscores with single
                .trim();
    }


    /**
     * Extracts the day part from a date string in the format "MM/DD/YYYY" or similar.
     * Assumes that the day is the second part of the string when split by '/'.
     *
     * @param dateString the date string to parse (e.g., "06/13/2025")
     * @return the day as an integer (e.g., 13)
     * @throws NumberFormatException if the day part is not a valid integer
     * @throws ArrayIndexOutOfBoundsException if the input string does not contain at least two parts
     */
    public int getDayFromDateString(String dateString) {
        return Integer.parseInt(dateString.split("/")[1].trim());
    }

    public String getNextDateInTargetFormat(String inputDateStr, int daysToAdd) {

        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate parsedDate = LocalDate.parse(inputDateStr, inputFormat);
        LocalDate nextDate = parsedDate.plusDays(daysToAdd);
        return nextDate.format(outputFormat);
    }

    public String[] splitCoordinates(String coordinates, String separator) {
        String[] parts = coordinates.split(separator);
        String latitude = parts[0].trim();
        String longitude = parts[1].trim();
        return new String[] { latitude, longitude };
    }

    public long getDurationInMinutes(String startTime, String endTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");

        LocalTime start = LocalTime.parse(startTime, formatter);
        LocalTime end = LocalTime.parse(endTime, formatter);

        Duration duration = Duration.between(start, end);

        return duration.toMinutes();
    }
    public int roundNumberToCustomRange(int number, String operation) {
        List<Integer> rangeList = Arrays.asList(15, 30, 45, 60);

        switch (operation.toLowerCase()) {
            case "nearest":
                return rangeList.stream()
                        .min((a, b) -> Integer.compare(Math.abs(a - number), Math.abs(b - number)))
                        .orElse(number);

            case "down":
                return rangeList.stream()
                        .filter(r -> r <= number)
                        .max(Integer::compareTo)
                        .orElse(rangeList.get(0));

            case "up":
                return rangeList.stream()
                        .filter(r -> r >= number)
                        .min(Integer::compareTo)
                        .orElse(rangeList.get(rangeList.size() - 1));

            default:
                throw new IllegalArgumentException("Invalid operation: " + operation + ". Use 'nearest', 'up', or 'down'.");
        }
    }


}
