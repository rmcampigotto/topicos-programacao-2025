package com.crud.demo.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
     private ProductRepository productRepository;


    public List<ProductModel> findAll(){
        return  productRepository.findAll();
    }

    public ProductModel criarLivro(ProductModel productModel){
        return productRepository.save(productModel);
    }

    public void deletarLivro(Long id){
        productRepository.deleteById(id);
    }

    public ProductModel update(Long id, ProductModel productModel){
       ProductModel newbook =  productRepository.findById(id).get();
       newbook.setNome(productModel.getNome());
       newbook.setPreco(productModel.getPreco());
       newbook.setQuantidade_estoque(productModel.getQuantidade_estoque());
       return productRepository.save(newbook);
    }

}
