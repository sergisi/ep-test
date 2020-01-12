package data;

import exceptions.data.NotAValidValue;

public interface DataTest {
    void setUp() throws NotAValidValue;
    void constructorExceptions();
    void testToString();
}
