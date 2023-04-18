package com.carnegieworks;

import java.text.Normalizer;

public class SanitizeStringQuery {
    public static String of(final String text){

        final String regex = "^[a-zA-Z\\u00C0-\\u00FF ]+";
        return text.replaceAll(regex, "");
    }

    public static String sanitize(String input) {
//        final String regex = "[^a-zA-Z\\u00C0-\\u00FF ]+";
        final String regex = "^[a-zA-Z\\u00C0-\\u00FF]*$";
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);

//        normalized.replaceAll("([\\\\_%])", "\\\\$1");
        return normalized.replaceAll(regex, "").trim();
    }
}
