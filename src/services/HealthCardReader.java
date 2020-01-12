package services;

import exceptions.services.HealthCardException;
import data.*;
public interface HealthCardReader {
    HealthCardID getHealthCardID() throws HealthCardException;
}
