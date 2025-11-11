import lombok.Getter;
import org.antlr.v4.runtime.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormattedErrorListener extends BaseErrorListener {
    @Getter
    private final List<String> errors = new ArrayList<>();

    private static String decodeUnicode(String msg) {
        Pattern pattern = Pattern.compile("\\\\u([0-9A-Fa-f]{4})");
        Matcher matcher = pattern.matcher(msg);
        StringBuilder decoded = new StringBuilder();
        while (matcher.find()) {
            char ch = (char) Integer.parseInt(matcher.group(1), 16);
            matcher.appendReplacement(decoded, Character.toString(ch));
        }
        matcher.appendTail(decoded);
        return decoded.toString();
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg, RecognitionException e) {
        msg = decodeUnicode(msg);
        errors.add(String.format("Line %d:%d — %s%n", line, charPositionInLine, msg));
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }
}
