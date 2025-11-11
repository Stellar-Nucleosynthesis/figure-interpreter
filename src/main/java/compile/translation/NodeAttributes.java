package compile.translation;

import lombok.*;
import runtime.commands.Command;
import types.Type;

import java.util.List;

@Data
@Builder
public class NodeAttributes {
    @Builder.Default
    private final List<Command> code = List.of();
    private final Type type;
    private final String varName;
    @Builder.Default
    private final List<String> errors = List.of();

    public static NodeAttributes fromError(String errors){
        return NodeAttributes.builder()
                .errors(List.of(errors))
                .build();
    }

    public static NodeAttributes fromErrors(List<String> errors){
        return NodeAttributes.builder()
                .errors(errors)
                .build();
    }

    public static NodeAttributes fromCode(List<Command> code){
        return NodeAttributes.builder()
                .code(code)
                .build();
    }

    public static NodeAttributes fromCode(Command code){
        return NodeAttributes.builder()
                .code(List.of(code))
                .build();
    }
}
