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
public class HexToDecFunction extends AbstractFunction<Long> {

    private static final String PARAM = "hexstring";
    public static final String NAME = "hextodec";

    @Override
    public Long evaluate(FunctionArgs functionArgs, EvaluationContext evaluationContext) {
    	String hexString = valueParam.required(functionArgs, evaluationContext);

        if (hexString == null) return 0L;

        hexString = hexString.trim().replaceAll("[_ ]", "");
        if (hexString.length() == 0) return 0L;

        char sign = hexString.charAt(0);
        hexString = hexString.replaceAll("^([+-])?(0[xX])?0*", "");
        if (sign == '-') hexString = sign + hexString;

        try {
        	return Long.parseLong(hexString, 16);
        }
        catch (NumberFormatException nfe) {}

        return 0L;
    }

    private final ParameterDescriptor<String, String> valueParam = ParameterDescriptor
    		.string(PARAM)
    		.description("Hexadecimal string.")
    		.build();

   	@Override
   	public FunctionDescriptor<Long> descriptor() {
   	    return FunctionDescriptor.<Long>builder()
   	            .name(NAME)
   	            .description("Returns decimal representation of hexadecimal string.")
   	            .params(valueParam)
   	            .returnType(Long.class)
   	            .build();
   	}
}
