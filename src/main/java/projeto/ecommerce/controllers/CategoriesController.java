package projeto.ecommerce.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import projeto.ecommerce.dtos.CategoriesDto;
import projeto.ecommerce.entities.Categories;
import projeto.ecommerce.response.Response;
import projeto.ecommerce.services.CategoriesService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class CategoriesController {

    private static final Logger log = LoggerFactory.getLogger(CategoriesController.class);

    @Autowired
    private CategoriesService categoriesService;


    public CategoriesController(){

    }

    @GetMapping(value = "/")
    public ResponseEntity<Response<List<CategoriesDto>>> getAll(){

        log.info("Buscando por id: {}");
        Response<List<CategoriesDto>> response = new Response<>();
        List<Categories> categories = categoriesService.getAll();
        response.setData(this.converterCategoriesPraDToList(categories));
        return ResponseEntity.ok(response);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Response<CategoriesDto>> buscarPorId(@PathVariable("id") Long id){

        log.info("Buscando por id: {}", id);
        Response<CategoriesDto> response = new Response<>();
        Optional<Categories> categories = categoriesService.buscarPorId(id);

        if (!categories.isPresent()){
            log.info("Categories não encontrada para o ID: {}", id);
            response.getErrors().add("Categories não encontrada para o ID:" + id);
            return ResponseEntity.badRequest().body(response);
        }

        response.setData(this.converterCategoriesPraDTo(categories.get()));
        return ResponseEntity.ok(response);
    }


    @GetMapping(value = "/name/{name}")
    public ResponseEntity<Response<CategoriesDto>> buscarPorName(@PathVariable("name") String name){

        log.info("Buscando por id: {}", name);
        Response<CategoriesDto> response = new Response<>();
        Optional<Categories> categories = categoriesService.buscarPorName(name);

        if (!categories.isPresent()){
            log.info("Categories não encontrada para o Name: {}", name);
            response.getErrors().add("Categories não encontrada para o name" + name);
            return ResponseEntity.badRequest().body(response);
        }

        response.setData(this.converterCategoriesPraDTo(categories.get()));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response<CategoriesDto>> cadastrar(@Validated @RequestBody CategoriesDto categoriesDto,
                                                           BindingResult result) throws ParseException{

        log.info("Cadastrando salesman: {}", categoriesDto.toString());
        Response<CategoriesDto> response = new Response<CategoriesDto>();
        Categories categories = this.converterDtoParaCategories(categoriesDto, result);

        if (result.hasErrors()) {
            log.error("Erro validando lançamento: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        categories = this.categoriesService.pesistir(categories);
        response.setData(this.converterCategoriesPraDTo(categories));
        return ResponseEntity.ok(response);

    }



    private Categories converterDtoParaCategories(CategoriesDto categoriesDto, BindingResult result) throws ParseException {

        Categories categories = new Categories();

        categories.setId(categoriesDto.getId());
        categories.setName(categoriesDto.getName());

        return categories;

    }



    private CategoriesDto converterCategoriesPraDTo(Categories categories){

        CategoriesDto categoriesDto = new CategoriesDto();

        categoriesDto.setId(categories.getId());
        categoriesDto.setName(categories.getName());

        return categoriesDto;

    }
    private List<CategoriesDto> converterCategoriesPraDToList(List<Categories> categories){

        List<CategoriesDto> categoriesDto = new ArrayList<>();
        for(Categories categorie : categories){
            CategoriesDto categoriesDto1 = new CategoriesDto();
            categoriesDto1.setId(categorie.getId());
            categoriesDto1.setName(categorie.getName());
            categoriesDto.add(categoriesDto1);
        }
        return categoriesDto;

    }
}
