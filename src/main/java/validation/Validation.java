package validation;

public class Validation {

    public static boolean isValidDate(String dateInput) {
        String dateRegex = "^([1-9]|0[1-9]|[1-2][0-9]|3[0-1])/([1-9]|0[1-9]|1[0-2])/[0-9]{4}$";

        return dateInput.matches(dateRegex);
    }
}
