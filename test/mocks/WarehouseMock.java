package mocks;

import exceptions.InsufficientExistences;
import pharmacy.ProductSaleLine;
import services.Warehouse;

import java.util.List;

public class WarehouseMock implements Warehouse {

    public boolean isWascalled() {
        return wascalled;
    }

    private boolean wascalled;

    public WarehouseMock() {
        this.wascalled = false;
    }

    @Override
    public void updateStock(List<ProductSaleLine> listOfProducts) throws InsufficientExistences {
        wascalled = true;
    }
}
