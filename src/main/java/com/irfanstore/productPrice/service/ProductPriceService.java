package com.irfanstore.productPrice.service;

import com.irfanstore.productPrice.dto.ProductPriceDto;
import com.irfanstore.productPrice.entity.ProductPrice;
import com.irfanstore.productPrice.repository.ProductPriceRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Service
public class ProductPriceService {
    @Autowired
    private ProductPriceRepository productPriceRepository;

    @Autowired
    private ProductSettings productSettings;

    private ModelMapper modelMapper= new ModelMapper();

    public List<ProductPriceDto> getProductPrices() {

        List<ProductPrice> productPrices = productPriceRepository.findAll();
        Type listType = new TypeToken<List<ProductPriceDto>>() {}.getType();
        List<ProductPriceDto> productPriceDtoList = modelMapper.map(productPrices, listType);

        String ipAddress = getServerIpAddress();
        String promoCode = productSettings.getPromoCode();
        for (ProductPriceDto productPriceDto : productPriceDtoList) {
            productPriceDto.setPromoCode(promoCode);
            productPriceDto.setIpAddress(ipAddress);
        }

        return productPriceDtoList;
    }

    public ProductPriceDto getProductPrice(long id) {
        ProductPrice productPrice =  productPriceRepository.findOne(id);

        ProductPriceDto productPriceDto =  modelMapper.map(productPrice, ProductPriceDto.class);
        productPriceDto.setPromoCode(productSettings.getPromoCode());
        productPriceDto.setIpAddress(getServerIpAddress());
        return productPriceDto;


    }

   public ProductPriceDto getProductPrice(String productCode) {
       ProductPrice productPrice = productPriceRepository.findByProductCode(productCode);

       ProductPriceDto productPriceDto =  modelMapper.map(productPrice, ProductPriceDto.class);
       productPriceDto.setPromoCode(productSettings.getPromoCode());
       productPriceDto.setIpAddress(getServerIpAddress());
       return productPriceDto;

    }

    public ProductPriceDto createOrUpdateProductPrice(ProductPriceDto productPriceDto) {
        ProductPrice productPrice =  modelMapper.map(productPriceDto, ProductPrice.class);
        ProductPrice productPriceCreated =  productPriceRepository.save(productPrice);

        ProductPriceDto productPriceDtoCreated =  modelMapper.map(productPriceCreated, ProductPriceDto.class);
        productPriceDtoCreated.setPromoCode(productSettings.getPromoCode());
        productPriceDtoCreated.setIpAddress(getServerIpAddress());
        return productPriceDtoCreated;
    }

    public void deleteProductPrice(long id) {
        productPriceRepository.delete(id);

    }

    private String getServerIpAddress()  {
        try
        {
           return InetAddress.getLocalHost().getHostAddress();
        } catch(UnknownHostException e)
        {
            return "0.0.0.0.";
        }

    }


}
