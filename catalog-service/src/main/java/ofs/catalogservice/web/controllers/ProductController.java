package ofs.catalogservice.web.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ofs.catalogservice.domain.PagedResult;
import ofs.catalogservice.domain.Product;
import ofs.catalogservice.domain.ProductNotFoundException;
import ofs.catalogservice.domain.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@Slf4j
@RequiredArgsConstructor
class ProductController {

    private final ProductService productService;

    @GetMapping
    ResponseEntity<PagedResult<Product>> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo) {
        return ResponseEntity.ok(productService.getProducts(pageNo));
    }

    @GetMapping("/{code}")
    ResponseEntity<Product> getProductByCode(@PathVariable("code") String code) {
        return productService
                .getProductByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> ProductNotFoundException.forCode(code));
    }
}
