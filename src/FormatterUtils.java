import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

public class FormatterUtils {

    public static String formatNumber(int number) {
        // Generate a phone number utility instance
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        String defaultRegion = "US";
        String snumber;
        try {
            snumber = String.valueOf(number);
            Phonenumber.PhoneNumber phoneNumber = phoneUtil.parse(snumber, defaultRegion);
            return phoneUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164);

        } catch (com.google.i18n.phonenumbers.NumberParseException e) {
            System.out.println("Error: Formatting Operation Failed.");
        }
        return null;
    }
}
