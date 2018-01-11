package com.irfanstore.productPrice.controller;

import com.irfanstore.productPrice.dto.ProductPriceDto;
import com.irfanstore.productPrice.service.ProductPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class ProductPriceController {

    @Autowired
    private ProductPriceService productPriceService;

    @GetMapping(path="/api/productprice/{id}")
    public ResponseEntity<ProductPriceDto> getProductPrice(@PathVariable Long id) {
        return new ResponseEntity<ProductPriceDto>(productPriceService.getProductPrice(id), HttpStatus.OK);
    }

    @GetMapping(path="/api/productprice")
    public ResponseEntity<List<ProductPriceDto>> getProductPrices() {
        return new ResponseEntity<List<ProductPriceDto>>(productPriceService.getProductPrices(), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/productprice/productcode/{productCode}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductPriceDto> getByProducCode(@PathVariable String productCode) {
        return new ResponseEntity<ProductPriceDto>(productPriceService.getProductPrice(productCode.toUpperCase()), HttpStatus.OK);
    }


    @RequestMapping(value = "/api/productprice",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductPriceDto> createProductPrice(@Valid @RequestBody ProductPriceDto productPriceDto) throws URISyntaxException
    {

        System.out.println(String.format("REST request to save ProductPrice: {%s}",  productPriceDto));
        if (productPriceDto.getId() != null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        ProductPriceDto mewproductPrice = productPriceService.createOrUpdateProductPrice(productPriceDto);

        return ResponseEntity.created(new URI("/api/productprice/" + mewproductPrice.getId()))
                .body(mewproductPrice);
    }


    @RequestMapping(value = "/api/productprice",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductPriceDto> updateProductPrice(@Valid @RequestBody ProductPriceDto productPriceDto) throws URISyntaxException
    {
        System.out.println(String.format("REST request to update ProductPrice : {%s}", productPriceDto));
        if (productPriceDto.getId() == null)
        {
            return createProductPrice(productPriceDto);
        }

        ProductPriceDto updatedProductPrice = productPriceService.createOrUpdateProductPrice(productPriceDto);
        return ResponseEntity.ok()
                .body(updatedProductPrice);
    }

    @RequestMapping(value = "/api/productprice/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteProductPrice(@PathVariable Long id)
    {
        System.out.println(String.format("REST request to delete ProductPrice : {%s}", id));
        productPriceService.deleteProductPrice(id);
        return ResponseEntity.ok().build();
    }

}
