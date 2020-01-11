package mocks;

import pharmacy.Sale;
import services.SalesHistory;

public class SalesHistoryMock implements SalesHistory {

    private boolean called;

    public SalesHistoryMock() {
        this.called = false;
    }

    @Override
    public void registerSale(Sale sale) {
        called = true;
    }

    public boolean isCalled() {
        return called;
    }
}
