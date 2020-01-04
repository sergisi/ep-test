package pharmacy;

import exceptions.DispensingNotAvailableException;
import data.*;
import java.util.Date;

public class Dispensing {
    private byte nOrder;
    private Date initDate, finalDate;
    private boolean isCompleated;

    public Dispensing() {
        //TODO: INIT THINGS AND ALL
    }

    public boolean dispensingEnabled() throws DispensingNotAvailableException {
        return false;
    }

    public void setProductAsDispensed(ProductID prodID) {

    }

    public void setCompleated() {

    }
    // TODO: rest of the getters and setters (maybe for tests)
}
