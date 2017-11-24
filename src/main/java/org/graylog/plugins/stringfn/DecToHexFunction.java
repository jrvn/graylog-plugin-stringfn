package org.graylog.plugins.stringfn;

import org.graylog.plugins.pipelineprocessor.EvaluationContext;
import org.graylog.plugins.pipelineprocessor.ast.functions.AbstractFunction;
import org.graylog.plugins.pipelineprocessor.ast.functions.FunctionArgs;
import org.graylog.plugins.pipelineprocessor.ast.functions.FunctionDescriptor;
import org.graylog.plugins.pipelineprocessor.ast.functions.ParameterDescriptor;
import java.util.Optional;

/**
 * This is the plugin. Your class should implement one of the existing plugin
 * interfaces. (i.e. AlarmCallback, MessageInput, MessageOutput)
 */
public class DecToHexFunction extends AbstractFunction<String> {

    private static final String PARAM = "longval";
    private static final String PARAM_LEN = "len";
	public static final String NAME = "dectohex";

    private final ParameterDescriptor<Long, Long> valueParam = ParameterDescriptor
    		.integer(PARAM)
    		.description("Number to be converted.")
    		.build();

    private final ParameterDescriptor<Long, Long> lenParam = ParameterDescriptor
    		.integer(PARAM_LEN)
    		.optional()
    		.description("String length (leftpaded with 0).")
    		.build();

    @Override
    public String evaluate(FunctionArgs functionArgs, EvaluationContext evaluationContext) {
    	Long number = valueParam.required(functionArgs, evaluationContext);
    	Optional<Long> numLength = lenParam.optional(functionArgs, evaluationContext);

        if (number == null) return null;
        String length = String.valueOf(Math.abs(numLength.orElse(1L)));

        return String.format("%0" + (length == "0" ? "1" : length) + "x", number);
    }

   	@Override
   	public FunctionDescriptor<String> descriptor() {
   	    return FunctionDescriptor.<String>builder()
   	            .name(NAME)
   	            .description("Returns hexadecimal string converted from decimal value.")
   	            .params(valueParam, lenParam)
   	            .returnType(String.class)
   	            .build();
   	}
}
