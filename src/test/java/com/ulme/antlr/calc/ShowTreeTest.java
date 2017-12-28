package com.ulme.antlr.calc;

import org.antlr.v4.gui.TestRig;
import org.junit.Test;

public class ShowTreeTest {
    @Test
    public void name() throws Exception {
        /*
                                        <configuration>
                                    <mainClass>org.antlr.v4.gui.TestRig</mainClass>
                                    <arguments>
                                        <argument>com.ulme.antlr.scheme.Scheme</argument>
                                        <argument>prog</argument>
                                        <argument>-gui</argument>
                                        <argument>target/classes/code.demo</argument>
                                    </arguments>
                                </configuration>
         */
        String[] args = {"com.ulme.antlr.scheme.Scheme",
                "prog",
                "-tree",
                "target/classes/code.demo"
        };
        TestRig testRig = new TestRig(args);
        testRig.process();
    }
}
