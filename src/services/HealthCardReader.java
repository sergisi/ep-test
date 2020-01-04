package services;

import exceptions.HealthCardException;
import data.*;
public interface HealthCardReader {
    HealthCardID getHealthCardID() throws HealthCardException;
}
