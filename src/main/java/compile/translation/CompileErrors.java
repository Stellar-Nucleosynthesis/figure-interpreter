package compile.translation;

import types.Type;

public class CompileErrors {
    public static String idNotDeclared(int line, int ch, String id){
        return String.format(
                "Line %d:%d - Identifier %s not declared",
                line, ch, id);
    }

    public static String idAlreadyExists(int line, int ch, String id){
        return String.format(
                "Line %d:%d - Identifier %s already exists in the current scope",
                line, ch, id);
    }

    public static String typeConversionError(int line, int ch, Type to, Type from){
        return String.format(
                "Line %d:%d - Could not convert value of type %s to type %s",
                line, ch, from.getName(), to.getName());
    }

    public static String figureShowError(int line, int ch, Type type){
        return String.format(
                "Line %d:%d - Tried to show expression of type %s which is not a figure",
                line, ch, type.getName());
    }

    public static String calledNotAFunction(int line, int ch, String id){
        return String.format(
                "Line %d:%d - Tried to call identifier %s, which is not a function",
                line, ch, id);
    }

    public static String wrongNumberOfArgs(int line, int ch, String id){
        return String.format(
                "Line %d:%d - Tried to call function %s with wrong number of arguments",
                line, ch, id);
    }

    public static String syntaxError(int line, int ch, String text){
        return String.format("Line %d:%d - Syntax error near '%s'",
                line, ch, text);
    }
}
