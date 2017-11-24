package org.graylog.plugins.stringfn;

import org.graylog.plugins.pipelineprocessor.EvaluationContext;
import org.graylog.plugins.pipelineprocessor.ast.functions.AbstractFunction;
import org.graylog.plugins.pipelineprocessor.ast.functions.FunctionArgs;
import org.graylog.plugins.pipelineprocessor.ast.functions.FunctionDescriptor;
import org.graylog.plugins.pipelineprocessor.ast.functions.ParameterDescriptor;

/**
 * This is the plugin. Your class should implement one of the existing plugin
 * interfaces. (i.e. AlarmCallback, MessageInput, MessageOutput)
 */
public class StrLenFunction extends AbstractFunction<Integer> {

    public static final String NAME = "strlen";
    private static final String PARAM = "string";

    @Override
    public Integer evaluate(FunctionArgs functionArgs, EvaluationContext evaluationContext) {
        String txtString = valueParam.required(functionArgs, evaluationContext);
        if (txtString == null) return 0;
        return txtString.length();
    }

    private final ParameterDescriptor<String, String> valueParam = ParameterDescriptor
            .string(PARAM)
            .description("String.")
            .build();

    @Override
    public FunctionDescriptor<Integer> descriptor() {
        return FunctionDescriptor.<Integer>builder()
                .name(NAME)
                .description("Returns length of string.")
                .params(valueParam)
                .returnType(Integer.class)
                .build();
    }
}
