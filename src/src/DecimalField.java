package src;

import javafx.scene.control.TextField;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

public class DecimalField extends TextField {
    private static Pattern decimalPattern = Pattern.compile("[-]?[0-9]*(\\.[0-9]*)?");

    @Override
    public void replaceText(int start, int end, String text) {
        if (validate(start, text)) {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text) {
        if (validate(Integer.MAX_VALUE, text)) {
            super.replaceSelection(text);
        }
    }

    private boolean validate(int start, String text) {
        String currentText = isNull(getText()) ? "" : getText();
        if(start == 0) { //to handle "-1.1" or ".1" cases
            return decimalPattern.matcher(text + currentText).matches();
        } else {
            return decimalPattern.matcher(currentText + text).matches();
        }
    }

}
