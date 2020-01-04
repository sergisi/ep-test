package services;

import exceptions.InsufficientExistences;
import pharmacy.ProductSaleLine;

import java.util.List;

public interface Warehouse {
    void updateStock(List<ProductSaleLine> listOfProducts) throws InsufficientExistences;
}
