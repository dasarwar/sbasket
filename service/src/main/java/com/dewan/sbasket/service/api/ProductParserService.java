package com.dewan.sbasket.service.api;

import java.util.List;
import com.dewan.sbasket.domain.Product;

public interface ProductParserService {
    List<Product> parseProducts(String productsUrl);

}
