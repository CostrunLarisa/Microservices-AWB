package com.unibuc.ro.application.client;
import com.unibuc.ro.application.dto.ProductRequest;
import com.unibuc.ro.application.dto.ProductResponse;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
@WebService(name = "ProductService")
public interface ProductServiceClient {
    @WebMethod(action = "updateProduct")
    @RequestWrapper(localName = "updateProduct",
            className = "com.unibuc.ro.application.dto.ProductRequest")
    @ResponseWrapper(localName = "updateProduct",
            className = "com.unibuc.ro.application.dto.ProductResponse")
    @WebResult(name =  "updateProductResponse", targetNamespace = "")
    ProductResponse updateProduct(@WebParam(name = "id") Long id, @WebParam(name = "request")ProductRequest productRequest);
}
