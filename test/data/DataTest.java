package data;

import exceptions.NotAValidValue;

public interface DataTest {
    void setUp() throws NotAValidValue;
    void constructorExceptions();
    void testToString();
}
