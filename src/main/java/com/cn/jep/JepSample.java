package com.cn.jep;

import org.apache.log4j.Logger;
import org.nfunk.jep.JEP;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;

/**
 * Created by jupiter on 15-12-2.
 */
public class JepSample {

    /**
     * 变量
     */
    public double x;
    public double y;
    public double z;

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    /**
     * 表达式
     */
    public String expression;
    /**
     * 日志
     */
    public Logger logger = Logger.getLogger(JepSample.class);

    public void calculate() throws ParseException {
        JEP jep = new JEP();
        jep.addVariable("x", x);
        jep.addVariable("y", y);
        jep.addVariable("z", z);

        Node node = jep.parse(this.expression);
        double result = (double) jep.evaluate(node);

        logger.debug(String.format("The expression = %s; x=%s; y=%s; z=%s. The result=%s", expression, x, y, z, result));

    }
}
