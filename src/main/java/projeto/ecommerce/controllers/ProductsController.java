package projeto.ecommerce.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import projeto.ecommerce.dtos.ProductsDto;
import projeto.ecommerce.entities.Products;
import projeto.ecommerce.response.Response;
import projeto.ecommerce.services.ProductsService;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductsController {

    private static final Logger log = LoggerFactory.getLogger(ProductsController.class);

    @Value("25")
    private int qtdPorPagina;
    @Autowired
    private ProductsService productsService;


    private ProductsController(){

    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Response<ProductsDto>> listaPorId(@PathVariable("id") Long id){
        log.info("Buscando products por ID {}", id);
        Response<ProductsDto> response = new Response<>();
        Optional<Products> products = this.productsService.buscarPorId(id);

        if(!products.isPresent()){
           log.info("Products não encontrado para o ID {}", id);
           response.getErrors().add("Products não encontrado para o id" + id);
           return ResponseEntity.badRequest().body(response);

        }
        response.setData(this.converterProductsPraDTo(products.get()));
        return ResponseEntity.ok(response);
    }


    @GetMapping(value = "/categorias/{categoriesID}")
    public ResponseEntity<Response<Page<ProductsDto>>> listarPorProdutosCategorias(
            @PathVariable("categoriesID") Long categoriesID,
            @RequestParam(value = "pag", defaultValue = "0") int pag,
            @RequestParam(value = "ord", defaultValue = "id") String ord,
            @RequestParam(value = "dir", defaultValue = "DESC") String dir,
            @RequestParam (value = "categoria" , defaultValue = "") String nomeProducts) {
        log.info("Buscando lançamentos por ID do funcionário: {}, página: {}", categoriesID, pag);
        Response<Page<ProductsDto>> response = new Response<Page<ProductsDto>>();

        PageRequest pageRequest = PageRequest.of(pag, this.qtdPorPagina, Sort.Direction.valueOf(dir), ord);

        Page<Products> products = this.productsService.buscarPorCategoiresOrProducts(categoriesID,nomeProducts, pageRequest);
        Page<ProductsDto> productsDtos = products.map(products1 -> this.converterProductsPraDTo(products1));

        response.setData(productsDtos);
        return ResponseEntity.ok(response);
    }


    @GetMapping(value = "/salesman/{salesmanID}")
    public ResponseEntity<Response<Page<ProductsDto>>> listarPorProdutoSalesman(
            @PathVariable("salesmanID") Long salesmanID,
            @RequestParam(value = "pag", defaultValue = "0") int pag,
            @RequestParam(value = "ord", defaultValue = "id") String ord,
            @RequestParam(value = "dir", defaultValue = "DESC") String dir,
            @RequestParam (value = "salesman" , defaultValue = "") String nomesalesman) {
        log.info("Buscando lançamentos por ID do funcionário: {}, página: {}", salesmanID, pag);
        Response<Page<ProductsDto>> response = new Response<Page<ProductsDto>>();

        PageRequest pageRequest = PageRequest.of(pag, this.qtdPorPagina, Sort.Direction.valueOf(dir), ord);

        Page<Products> products = this.productsService.buscarPorSalesmanOrProducts(salesmanID,nomesalesman, pageRequest);
        Page<ProductsDto> productsDtos = products.map(products1 -> this.converterProductsPraDTo(products1));

        response.setData(productsDtos);
        return ResponseEntity.ok(response);
    }



    @GetMapping
    public List<Products> listaSalesman(){
        return this.productsService.buscarTodos();
    }




    @GetMapping(value = "/name/{nameProduct}")
    public ResponseEntity<Response<ProductsDto>> listarPorNome(@PathVariable("nameProduct") String nameProduct){

        log.info("Buscando products Name Produto {}", nameProduct);
        Response<ProductsDto> response = new Response<>();
        Optional<Products> products = this.productsService.buscarPorNomeProduct(nameProduct);

        if(!products.isPresent()){
            log.info("Products não encontrado para o ID {}", nameProduct);
            response.getErrors().add("Products não encontrado para o id" + nameProduct);
            return ResponseEntity.badRequest().body(response);

        }


        response.setData(this.converterProductsPraDTo(products.get()));
        return ResponseEntity.ok(response);


    }


    @PostMapping
    public ResponseEntity<Response<ProductsDto>> cadastrar(@Validated @RequestBody ProductsDto productsDto,
                                                           BindingResult result) throws ParseException{

        log.info("Cadastrando products: {}", productsDto.toString());
        Response<ProductsDto> response = new Response<ProductsDto>();
        Products products = this.converterDtoParaProducts(productsDto, result);

        if (result.hasErrors()) {
            log.error("Erro a cadastrar products: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        products = this.productsService.pesistir(products);
        response.setData(this.converterProductsPraDTo(products));
        return ResponseEntity.ok(response);

    }



    @PutMapping(value = "/{id}")
    public ResponseEntity<Response<ProductsDto>> atualizar(@PathVariable("id") Long id,
                                                             @Valid @RequestBody ProductsDto productsDto, BindingResult result) throws ParseException {
        log.info("Atualizando lançamento: {}", productsDto.toString());
        Response<ProductsDto> response = new Response<ProductsDto>();
        productsDto.setId(id);
        Products products = this.converterDtoParaProducts(productsDto, result);

        if (result.hasErrors()) {
            log.error("Erro validando lançamento: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        products = this.productsService.pesistir(products);
        response.setData(this.converterProductsPraDTo(products));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}")

    public ResponseEntity<Response<String>> remover(@PathVariable("id") Long id) {
        log.info("Removendo lançamento: {}", id);
        Response<String> response = new Response<String>();
        Optional<Products> lancamento = this.productsService.buscarPorId(id);

        if (!lancamento.isPresent()) {
            log.info("Erro ao remover devido ao lançamento ID: {} ser inválido.", id);
            response.getErrors().add("Erro ao remover lançamento. Registro não encontrado para o id " + id);
            return ResponseEntity.badRequest().body(response);
        }

        this.productsService.remover(id);
        return ResponseEntity.ok(new Response<String>());
    }

    private Products converterDtoParaProducts(ProductsDto productsDto, BindingResult result) throws ParseException {

        Products products = new Products();

        products.setCategories(productsDto.getCategories());
        products.setDescription(productsDto.getDescription());
        products.setId(productsDto.getId());
        products.setNameProduct(productsDto.getNameProduct());
        products.setPhoto(productsDto.getPhoto());
        products.setSalesman(productsDto.getSalesman());
        products.setStatus(productsDto.getStatus());
        products.setStock(productsDto.getStock());
        products.setValue(productsDto.getValue());


        return products;

    }



    private ProductsDto converterProductsPraDTo(Products products){

        ProductsDto productsDto = new ProductsDto();

        productsDto.setCategories(products.getCategories());
        productsDto.setDescription(products.getDescription());
        productsDto.setId(products.getId());
        productsDto.setNameProduct(products.getNameProduct());
        productsDto.setPhoto(products.getPhoto());
        productsDto.setSalesman(products.getSalesman());
        productsDto.setStatus(products.getStatus());
        productsDto.setStock(products.getStock());
        productsDto.setValue(products.getValue());


        return productsDto;

    }
}
