package mocks;

import data.HealthCardID;
import exceptions.HealthCardException;
import exceptions.NotAValidValue;
import services.HealthCardReader;

public class HealthCardReaderMock implements HealthCardReader {

    @Override
    public HealthCardID getHealthCardID() throws HealthCardException {
        try {
            return new HealthCardID("Jo");
        } catch (NotAValidValue notAValidValue) {
            throw new HealthCardException();
        }
    }
}
