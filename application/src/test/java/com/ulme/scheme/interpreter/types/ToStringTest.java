package com.ulme.scheme.interpreter.types;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ToStringTest {

    private String description;
    private String output;
    private PairType pairType;

    public ToStringTest(String description, String output, PairType pairType) {
        this.description = description;
        this.output = output;
        this.pairType = pairType;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"(cons 1 2)", "(1 . 2)",
                        new PairType(
                                new DecimalType(1),
                                new DecimalType(2)
                        )
                },
                {"(cons 1 (cons 2 3))", "(1 2 . 3)",
                        new PairType(
                                new DecimalType(1),
                                new PairType(
                                        new DecimalType(2),
                                        new DecimalType(3)
                                )
                        )
                },
                {"(cons (cons 1 2) 3)", "((1 . 2) . 3)",
                        new PairType(
                                new PairType(
                                        new DecimalType(1),
                                        new DecimalType(2)
                                ),
                                new DecimalType(3)
                        )
                },
                {"(cons 1 (cons 2 (cons 3 4)))", "(1 2 3 . 4)",
                        new PairType(
                                new DecimalType(1),
                                new PairType(
                                        new DecimalType(2),
                                        new PairType(
                                                new DecimalType(3),
                                                new DecimalType(4)
                                        )
                                )
                        )
                },
                {"(cons (cons 1 2) (cons 3 4))", "((1 . 2) 3 . 4)",
                        new PairType(
                                new PairType(
                                        new DecimalType(1),
                                        new DecimalType(2)
                                ),
                                new PairType(
                                        new DecimalType(3),
                                        new DecimalType(4)
                                )
                        )
                },
                {"(cons (cons (cons 1 2) (cons 3 4)) (cons (cons 5 6) (cons 7 8)))",
                        "(((1 . 2) 3 . 4) (5 . 6) 7 . 8)",
                        new PairType(
                                new PairType(
                                        new PairType(
                                                new DecimalType(1),
                                                new DecimalType(2)
                                        ),
                                        new PairType(
                                                new DecimalType(3),
                                                new DecimalType(4)
                                        )
                                ),
                                new PairType(
                                        new PairType(
                                                new DecimalType(5),
                                                new DecimalType(6)
                                        ),
                                        new PairType(
                                                new DecimalType(7),
                                                new DecimalType(8)
                                        )
                                )
                        )
                },
        });
    }

    @Test
    public void test() throws Exception {
        assertEquals(description, output, pairType.toString());
    }
}